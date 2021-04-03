package com.ionker.netty.codec.lpd.receiveJob;

import io.netty.buffer.ByteBuf;

public interface LPDReceiveJobDataFile extends LPDReceiveJobDataFileSubCommand, LPDReceiveJobDataFileLastContent {
    
    @Override
    LPDReceiveJobDataFile copy();

    @Override
    LPDReceiveJobDataFile duplicate();

    @Override
    LPDReceiveJobDataFile retainedDuplicate();

    @Override
    LPDReceiveJobDataFile replace(ByteBuf content);

    @Override
    LPDReceiveJobDataFile retain(int increment);

    @Override
    LPDReceiveJobDataFile retain();

    @Override
    LPDReceiveJobDataFile touch();

    @Override
    LPDReceiveJobDataFile touch(Object hint);
}
