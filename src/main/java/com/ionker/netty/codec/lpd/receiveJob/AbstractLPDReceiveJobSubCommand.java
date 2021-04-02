package com.ionker.netty.codec.lpd.receiveJob;

import com.ionker.netty.codec.lpd.LPDCommand;

public abstract class AbstractLPDReceiveJobSubCommand extends LPDCommand{

    public AbstractLPDReceiveJobSubCommand(String queue) {
        super(queue);
    }
}
