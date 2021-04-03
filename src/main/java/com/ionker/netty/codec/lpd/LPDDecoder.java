package com.ionker.netty.codec.lpd;

import static com.ionker.netty.codec.lpd.receiveJob.LPDReceiveJobConstants.RECEIVE_JOB_SUB_COMMAND_CODE_RECEIVE_CONTROL_FILE;
import static com.ionker.netty.codec.lpd.receiveJob.LPDReceiveJobConstants.RECEIVE_JOB_SUB_COMMAND_CODE_ABORT_JOB;
import static com.ionker.netty.codec.lpd.receiveJob.LPDReceiveJobConstants.RECEIVE_JOB_SUB_COMMAND_CODE_RECEIVE_DATA_FILE;

import com.ionker.netty.codec.lpd.receiveJob.DefaultLPDReceiveJobAbortJobSubCommand;
import com.ionker.netty.codec.lpd.receiveJob.DefaultLPDReceiveJobControlFile;
import com.ionker.netty.codec.lpd.receiveJob.DefaultLPDReceiveJobControlFileSubCommand;
import com.ionker.netty.codec.lpd.receiveJob.DefaultLPDReceiveJobDataFileContent;
import com.ionker.netty.codec.lpd.receiveJob.DefaultLPDReceiveJobDataFileLastContent;
import com.ionker.netty.codec.lpd.receiveJob.DefaultLPDReceiveJobDataFileSubCommand;
import com.ionker.netty.codec.lpd.receiveJob.LPDReceiveJobDataFileContent;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;

public class LPDDecoder extends ByteToMessageDecoder {

    private static Logger logger = LoggerFactory.getLogger(LPDDecoder.class);

    private enum State {
        DECODE_COMMAND_CODE, DECODE_RECEIVE_JOB_SUBCOMMAND, DECODE_RECEIVE_JOB_RECEIVE_CONTROL_FILE,
        DECODE_RECEIVE_JOB_RECEIVE_DATA_FILE,
    }

    // current decoding states
    private State state = State.DECODE_COMMAND_CODE;

    // End of line
    private static final int EOL_LENGTH = 1;
    private static final byte EOL = (byte) '\n';
    private static final byte EOF = (byte) 0x00;

    private LPDCommand command;
    private String queue;

    // ReceiveJob variables
    private static final int DEFAULT_CHUNK_SIZE = 8132;
    private int receiveJobControlFileLength = -1;
    private int receiveJobDataFileLength = -1;
    private final int chunkSize;
    private int alreadyReadLength;

    public LPDDecoder() {
        this(DEFAULT_CHUNK_SIZE);
    }

    public LPDDecoder(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        switch (state) {

        case DECODE_COMMAND_CODE:
            if (!decodeCommand(in, out)) {
                return;
            }
            break;

        case DECODE_RECEIVE_JOB_SUBCOMMAND:
            if (!decodeReceiveJobSubCommand(in, out)) {
                return;
            }
            break;

        case DECODE_RECEIVE_JOB_RECEIVE_CONTROL_FILE:
            if (!decodeReceiveJobControlFile(in, out)) {
                return;
            }
            break;

        case DECODE_RECEIVE_JOB_RECEIVE_DATA_FILE:
            if (!decodeReceiveJobDataFile(in, out)) {
                return;
            }
            break;

        default:
            throw new LPDException("Unknown state: " + state);
        }
    }

    private void resetReceiveJobDataFileSubCommand() {
        receiveJobDataFileLength = 0;
        alreadyReadLength = 0;
        queue = null;
    }

    private boolean decodeCommand(ByteBuf in, List<Object> out) throws Exception {

        ByteBuf lineBytes = readLine(in);
        if (lineBytes == null) {
            return false;
        }

        final int commandCode = lineBytes.readByte();
        final String commandOperands = lineBytes.readSlice(lineBytes.readableBytes()).toString(CharsetUtil.US_ASCII);

        switch (commandCode) {

        case LPDConstants.COMMAND_CODE_PRINT_JOBS:
            logger.debug("Print jobs for queue: {}", commandOperands);
            command = new DefaultLPDPrintWaitingJobsCommand(commandOperands);
            break;

        case LPDConstants.COMMAND_CODE_RECEIVE_JOB:
            logger.debug("Receive print job for queue: {}", commandOperands);
            command = new DefaultLPDReceiveJobCommand(commandOperands);
            state = State.DECODE_RECEIVE_JOB_SUBCOMMAND;
            break;

        case LPDConstants.COMMAND_CODE_REPORT_QUEUE_STATE_SHORT:
            logger.debug("Report queue state short for queue: {}", commandOperands);
            command = DefaultLPDSendQueueStateShortCommand.fromCommandOperands(commandOperands);
            break;

        case LPDConstants.COMMAND_CODE_REPORT_QUEUE_STATE_LONG:
            logger.debug("Report queue state short for queue: {}", commandOperands);
            command = DefaultLPDSendQueueStateLongCommand.fromCommandOperands(commandOperands);
            break;

        case LPDConstants.COMMAND_CODE_REMOVE_PRINT_JOBS:
            logger.debug("Remove jobs from queue: {}", commandOperands);
            break;

        default:
            throw new Exception("Unknown command code: " + commandCode);
        }

        out.add(command);
        return true;
    }

    private boolean decodeReceiveJobSubCommand(ByteBuf in, List<Object> out) throws Exception {

        ByteBuf lineBytes = readLine(in);
        if (lineBytes == null) {
            return false;
        }

        final int subCommandCode = lineBytes.readByte();

        switch (subCommandCode) {

        case RECEIVE_JOB_SUB_COMMAND_CODE_RECEIVE_CONTROL_FILE:
            decodeReceiveJobControlFileSubCommand(
                    lineBytes.readSlice(lineBytes.readableBytes()).toString(CharsetUtil.US_ASCII), out);
            break;

        case RECEIVE_JOB_SUB_COMMAND_CODE_RECEIVE_DATA_FILE:
            decodeReceiveJobDataFileSubCommand(
                    lineBytes.readSlice(lineBytes.readableBytes()).toString(CharsetUtil.US_ASCII), out);
            break;

        case RECEIVE_JOB_SUB_COMMAND_CODE_ABORT_JOB:
            out.add(new DefaultLPDReceiveJobAbortJobSubCommand(queue));
            break;

        default:
            throw new Exception("Unknown receive job sub command: " + subCommandCode);
        }

        return true;
    }

    private void decodeReceiveJobControlFileSubCommand(String controlFileOperands, List<Object> out)
            throws LPDException {
        DefaultLPDReceiveJobControlFileSubCommand controlFile = DefaultLPDReceiveJobControlFileSubCommand
                .fromSubCommandOperands(queue, controlFileOperands);
        logger.debug("Received control file: {} size: {}", controlFile.getName(), controlFile.getSize());
        out.add(controlFile);
        receiveJobControlFileLength = controlFile.getSize();
        state = State.DECODE_RECEIVE_JOB_RECEIVE_CONTROL_FILE;
    }

    private void decodeReceiveJobDataFileSubCommand(String dataFileOperands, List<Object> out) throws LPDException {
        DefaultLPDReceiveJobDataFileSubCommand dataFile = DefaultLPDReceiveJobDataFileSubCommand
                .fromSubCommandOperands(queue, dataFileOperands);
        out.add(dataFile);
        receiveJobDataFileLength = dataFile.getSize();
        state = State.DECODE_RECEIVE_JOB_RECEIVE_DATA_FILE;
    }

    private boolean decodeReceiveJobControlFile(ByteBuf in, List<Object> out) throws Exception {
        if (!in.isReadable(receiveJobControlFileLength + 1)) {
            return false;
        }

        String controlFileContent = in.readSlice(receiveJobControlFileLength).toString(CharsetUtil.US_ASCII);
        readEndOfFile(in);
        System.out.println("Control file content: " + controlFileContent);
        state = State.DECODE_RECEIVE_JOB_SUBCOMMAND;
        out.add(new DefaultLPDReceiveJobControlFile(controlFileContent));
        return true;
    }

    private boolean decodeReceiveJobDataFile(ByteBuf in, List<Object> out) throws Exception {
        if (receiveJobDataFileLength > 0) {
            int toRead = in.readableBytes();
            if (toRead == 0) {
                return false;
            }

            if (toRead > chunkSize) {
                toRead = chunkSize;
            }

            int remainingLength = (int) (receiveJobDataFileLength - alreadyReadLength);
            if (toRead == remainingLength) {
                // if we have reached the end of the data file let's wait for the EOF before
                // emitting the last content message
                return false;
            } else {
                LPDReceiveJobDataFileContent dataFileChunk;
                if (toRead == remainingLength + 1) {
                    dataFileChunk = new DefaultLPDReceiveJobDataFileLastContent(in.readRetainedSlice(toRead - 1));
                    readEndOfFile(in);
                    state = State.DECODE_RECEIVE_JOB_SUBCOMMAND;
                } else if (toRead < remainingLength) {
                    dataFileChunk = new DefaultLPDReceiveJobDataFileContent(in.readRetainedSlice(toRead));
                } else {
                    throw new LPDException("Read past receive job data file!");
                }
                alreadyReadLength += toRead;
                out.add(dataFileChunk);
            }
        } else {
            throw new LPDException("No receive job data file content length!");
        }
        return true;
    }

    private static ByteBuf readLine(ByteBuf in) throws Exception {
        if (!in.isReadable(EOL_LENGTH)) {
            return null;
        }

        final int lfIndex = in.forEachByte(ByteProcessor.FIND_LF);
        if (lfIndex < 0) {
            return null;
        }

        ByteBuf data = in.readSlice(lfIndex - in.readerIndex()); // `-1` is for CR
        readEndOfLine(in); // validate LF
        return data;
    }

    private static void readEndOfLine(final ByteBuf in) throws Exception {
        final byte delim = in.readByte();
        if (EOL == delim) {
            return;
        }
        throw new Exception("delimiter: [" + delim + "] (expected: \\n)");
    }

    private static void readEndOfFile(final ByteBuf in) throws Exception {
        final byte endOfFile = in.readByte();
        if (EOF == endOfFile) {
            return;
        }
        throw new Exception("End of file: [" + endOfFile + "] (expected: 0x00)");
    }

}
