package com.ionker.netty.codec.lpd.receiveJob;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.MessageAggregator;

public class LPDReceiveJobDataFileAggregator extends MessageAggregator<LPDReceiveJobDataFileObject, LPDReceiveJobDataFileSubCommand, LPDReceiveJobDataFileContent, LPDReceiveJobFullMessage> {

    public LPDReceiveJobDataFileAggregator(int maxContentLength) {
        super(maxContentLength);
    }

    @Override
    protected boolean isStartMessage(LPDReceiveJobDataFileObject msg) throws Exception {
        return msg instanceof LPDReceiveJobDataFileSubCommand;
    }

    @Override
    protected boolean isContentMessage(LPDReceiveJobDataFileObject msg) throws Exception {
        return msg instanceof LPDReceiveJobDataFileContent;
    }

    @Override
    protected boolean isLastContentMessage(LPDReceiveJobDataFileContent msg) throws Exception {
        return msg instanceof LPDReceiveJobDataFileLastContent;
    }

    @Override
    protected boolean isAggregated(LPDReceiveJobDataFileObject msg) throws Exception {
        return msg instanceof LPDReceiveJobFullMessage;
    }

    @Override
    protected boolean isContentLengthInvalid(LPDReceiveJobDataFileSubCommand start, int maxContentLength)
            throws Exception {
            return start.getSize() > maxContentLength;
    }

    @Override
    protected Object newContinueResponse(LPDReceiveJobDataFileSubCommand start, int maxContentLength,
            ChannelPipeline pipeline) throws Exception {
        return nulDelimiter();
    }

    public static ByteBuf nulDelimiter() {
        return Unpooled.wrappedBuffer(new byte[] { 0 }) ;
    }

    @Override
    protected boolean closeAfterContinueResponse(Object msg) throws Exception {
        return false;
    }

    @Override
    protected boolean ignoreContentAfterContinueResponse(Object msg) throws Exception {
        return false;
    }

    @Override
    protected LPDReceiveJobFullMessage beginAggregation(LPDReceiveJobDataFileSubCommand start, ByteBuf content)
            throws Exception {
        return new DefaultLPDReceiveJobFullMessage(start.getQueue(), start.getSize(), start.getName(), content);
    }

    @Override
    protected void aggregate(LPDReceiveJobFullMessage aggregated, LPDReceiveJobDataFileContent content) throws Exception {
        if (content instanceof LPDReceiveJobDataFileLastContent) {
            // ((LPDReceiveJobFullMessage) aggregated).setTrailingHeaders(((LPDReceiveJobDataFileContent) content).trailingHeaders());
        }
    }
}
