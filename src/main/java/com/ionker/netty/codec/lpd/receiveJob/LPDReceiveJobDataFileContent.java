package com.ionker.netty.codec.lpd.receiveJob;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;

public interface LPDReceiveJobDataFileContent extends LPDReceiveJobDataFileObject, ByteBufHolder {

    @Override
    LPDReceiveJobDataFileContent copy();

    @Override
    LPDReceiveJobDataFileContent duplicate();

    @Override
    LPDReceiveJobDataFileContent retainedDuplicate();

    @Override
    LPDReceiveJobDataFileContent replace(ByteBuf content);

    @Override
    LPDReceiveJobDataFileContent retain();

    @Override
    LPDReceiveJobDataFileContent retain(int increment);

    @Override
    LPDReceiveJobDataFileContent touch();

    @Override
    LPDReceiveJobDataFileContent touch(Object hint);
    
}
