package com.ionker.netty.codec.lpd.receiveJob;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ionker.netty.codec.lpd.LPDException;

public class DefaultLPDReceiveJobDataFileSubCommand extends AbstractLPDReceiveJobDataFileSubCommand {
    
    private static final String DATAFILE_OPERANDS_PATTERN = "(^\\d+) (\\S+)$";

    public static DefaultLPDReceiveJobDataFileSubCommand fromSubCommandOperands(String queue, String dataFileOperands) throws LPDException {
        Pattern pattern = Pattern.compile(DATAFILE_OPERANDS_PATTERN);
        Matcher matcher = pattern.matcher(dataFileOperands);
        
        if (matcher.matches()) {
            return new DefaultLPDReceiveJobDataFileSubCommand(queue, Integer.parseInt(matcher.group(1)), matcher.group(2));
        } else {
            throw new LPDException("Invalid receive data file subcommand!");
        }

    }

    public DefaultLPDReceiveJobDataFileSubCommand(String queue, int size, String name) {
        super(queue, size, name);
    }

}
