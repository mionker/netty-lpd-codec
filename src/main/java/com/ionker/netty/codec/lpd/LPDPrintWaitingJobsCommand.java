package com.ionker.netty.codec.lpd;

public class LPDPrintWaitingJobsCommand extends LPDCommand {

    public LPDPrintWaitingJobsCommand(String queue) {
        super(queue);
    }
}
