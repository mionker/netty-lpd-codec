package com.ionker.netty.codec.lpd;

public abstract class AbstractLPDCommand implements LPDCommand {

    protected final String queue;

    public AbstractLPDCommand(String queue) {
        this.queue = queue;
    }

    public String getQueue() {
        return queue;
    }
}
