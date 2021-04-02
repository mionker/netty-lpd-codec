package com.ionker.netty.codec.lpd;

public class LPDRemoveJobsCommand extends LPDCommand {

    private String agent;

    public LPDRemoveJobsCommand(String queue) {
        super(queue);
    }

    public String getAgent() {
        return agent;
    }
}
