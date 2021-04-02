package com.ionker.netty.codec.lpd.receiveJob;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.DefaultByteBufHolder;
import io.netty.handler.codec.DecoderResult;

public class DefaultLPDReceiveJobDataFileContent extends DefaultByteBufHolder implements LPDReceiveJobDataFileContent {
    
    private DecoderResult decoderResult = DecoderResult.SUCCESS;

    public DefaultLPDReceiveJobDataFileContent(ByteBuf content) {
        super(content);
    }

    @Override
    public LPDReceiveJobDataFileContent copy() {
        return (LPDReceiveJobDataFileContent) super.copy();
    }

    @Override
    public LPDReceiveJobDataFileContent duplicate() {
        return (LPDReceiveJobDataFileContent) super.duplicate();
    }

    @Override
    public LPDReceiveJobDataFileContent retainedDuplicate() {
        return (LPDReceiveJobDataFileContent) super.retainedDuplicate();
    }

    @Override
    public LPDReceiveJobDataFileContent replace(ByteBuf content) {
        return new DefaultLPDReceiveJobDataFileContent(content);
    }

    @Override
    public LPDReceiveJobDataFileContent retain() {
        super.retain();
        return this;
    }

    @Override
    public LPDReceiveJobDataFileContent retain(int increment) {
        super.retain(increment);
        return this;
    }

    @Override
    public LPDReceiveJobDataFileContent touch() {
        super.touch();
        return this;
    }

    @Override
    public LPDReceiveJobDataFileContent touch(Object hint) {
        super.touch(hint);
        return this;
    }

    @Override
    public DecoderResult decoderResult() {
        return decoderResult;
    }

    @Override
    public void setDecoderResult(DecoderResult decoderResult) {
        this.decoderResult = decoderResult;
    }
}
