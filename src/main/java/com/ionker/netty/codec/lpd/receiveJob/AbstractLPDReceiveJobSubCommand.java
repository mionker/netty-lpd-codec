package com.ionker.netty.codec.lpd.receiveJob;

import com.ionker.netty.codec.lpd.AbstractLPDCommand;

public abstract class AbstractLPDReceiveJobSubCommand extends AbstractLPDCommand{

    public AbstractLPDReceiveJobSubCommand(String queue) {
        super(queue);
    }
}
