package com.ionker.netty.codec.lpd.receiveJob;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ionker.netty.codec.lpd.LPDException;

public class DefaultLPDReceiveJobControlFileSubCommand extends AbstractLPDReceiveJobFileSubCommand {

    private static final String CONTROLFILE_OPERANDS_PATTERN = "^(\\d+) (\\S+)$";

    public static DefaultLPDReceiveJobControlFileSubCommand fromSubCommandOperands(String queue, String controlFileOperands) throws LPDException {
        Pattern pattern = Pattern.compile(CONTROLFILE_OPERANDS_PATTERN);
        Matcher matcher = pattern.matcher(controlFileOperands);

        if (matcher.matches()) {
            return new DefaultLPDReceiveJobControlFileSubCommand(queue, Integer.parseInt(matcher.group(1)), matcher.group(2));
        } else {
            throw new LPDException("Invalid receive job control file subcommand!");
        }
    }

    public DefaultLPDReceiveJobControlFileSubCommand(String queue, int size, String name) {
        super(queue, size, name);
    }
}
