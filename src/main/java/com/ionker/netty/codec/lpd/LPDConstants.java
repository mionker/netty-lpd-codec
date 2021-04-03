package com.ionker.netty.codec.lpd;

public class LPDConstants {
    public static final int COMMAND_CODE_PRINT_JOBS = 0x01;
    public static final int COMMAND_CODE_RECEIVE_JOB = 0x02;
    public static final int COMMAND_CODE_REPORT_QUEUE_STATE_SHORT = 0x03;
    public static final int COMMAND_CODE_REPORT_QUEUE_STATE_LONG = 0x04;
    public static final int COMMAND_CODE_REMOVE_PRINT_JOBS = 0x05;

    public static int POSITIVE_ACK = 0x00;
    public static int NEGATIVE_ACK = 0x01;
}
