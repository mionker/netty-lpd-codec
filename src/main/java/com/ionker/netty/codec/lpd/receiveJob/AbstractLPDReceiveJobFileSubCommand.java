package com.ionker.netty.codec.lpd.receiveJob;

public abstract class AbstractLPDReceiveJobFileSubCommand extends AbstractLPDReceiveJobSubCommand implements LPDReceiveJobFileSubCommand {
   
    protected final int size;
    protected final String name;

    public AbstractLPDReceiveJobFileSubCommand(String queue, int size, String name) {
        super(queue);
        this.size = size;
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }
}
