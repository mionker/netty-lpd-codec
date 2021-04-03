package com.ionker.netty.codec.lpd;

public class DefaultLPDRemoveJobsCommand extends AbstractLPDCommand {

    private String agent;

    public DefaultLPDRemoveJobsCommand(String queue) {
        super(queue);
    }

    public String getAgent() {
        return agent;
    }
}
