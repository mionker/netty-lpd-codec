package com.ionker.netty.codec.lpd.receiveJob;

import io.netty.buffer.ByteBuf;

public interface LPDReceiveJobFullMessage extends LPDReceiveJobDataFileSubCommand, LPDReceiveJobDataFileLastContent {
    
    @Override
    LPDReceiveJobFullMessage copy();

    @Override
    LPDReceiveJobFullMessage duplicate();

    @Override
    LPDReceiveJobFullMessage retainedDuplicate();

    @Override
    LPDReceiveJobFullMessage replace(ByteBuf content);

    @Override
    LPDReceiveJobFullMessage retain(int increment);

    @Override
    LPDReceiveJobFullMessage retain();

    @Override
    LPDReceiveJobFullMessage touch();

    @Override
    LPDReceiveJobFullMessage touch(Object hint);
}
