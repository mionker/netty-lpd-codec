package com.ionker.netty.codec.lpd.receiveJob;

import io.netty.buffer.ByteBuf;

public class DefaultLPDReceiveJobDataFileLastContent extends DefaultLPDReceiveJobDataFileContent implements LPDReceiveJobDataFileLastContent  {
    
    public DefaultLPDReceiveJobDataFileLastContent(ByteBuf content) {
        super(content);
    }

    @Override
    public LPDReceiveJobDataFileLastContent copy() {
        return (LPDReceiveJobDataFileLastContent) super.copy();
    }

    @Override
    public LPDReceiveJobDataFileLastContent duplicate() {
        return (LPDReceiveJobDataFileLastContent) super.duplicate();
    }

    @Override
    public LPDReceiveJobDataFileLastContent retainedDuplicate() {
        return (LPDReceiveJobDataFileLastContent) super.retainedDuplicate();
    }

    @Override
    public LPDReceiveJobDataFileLastContent replace(ByteBuf content) {
        return new DefaultLPDReceiveJobDataFileLastContent(content);
    }

    @Override
    public DefaultLPDReceiveJobDataFileLastContent retain() {
        super.retain();
        return this;
    }

    @Override
    public LPDReceiveJobDataFileLastContent retain(int increment) {
        super.retain(increment);
        return this;
    }

    @Override
    public LPDReceiveJobDataFileLastContent touch() {
        super.touch();
        return this;
    }

    @Override
    public LPDReceiveJobDataFileLastContent touch(Object hint) {
        super.touch(hint);
        return this;
    }
}
