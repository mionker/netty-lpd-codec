package com.ionker.netty.codec.lpd.receiveJob;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DecoderResult;

public interface LPDReceiveJobDataFileLastContent extends LPDReceiveJobDataFileContent {
    
    LPDReceiveJobDataFileLastContent EMPTY_LAST_CONTENT = new LPDReceiveJobDataFileLastContent() {
        @Override
        public ByteBuf content() {
            return Unpooled.EMPTY_BUFFER;
        }

        @Override
        public LPDReceiveJobDataFileLastContent copy() {
            return EMPTY_LAST_CONTENT;
        }

        @Override
        public LPDReceiveJobDataFileLastContent duplicate() {
            return this;
        }

        @Override
        public LPDReceiveJobDataFileLastContent retainedDuplicate() {
            return this;
        }

        @Override
        public LPDReceiveJobDataFileLastContent replace(ByteBuf content) {
            return new DefaultLPDReceiveJobDataFileLastContent(content);
        }

        @Override
        public LPDReceiveJobDataFileLastContent retain() {
            return this;
        }

        @Override
        public LPDReceiveJobDataFileLastContent retain(int increment) {
            return this;
        }

        @Override
        public LPDReceiveJobDataFileLastContent touch() {
            return this;
        }

        @Override
        public LPDReceiveJobDataFileLastContent touch(Object hint) {
            return this;
        }

        @Override
        public int refCnt() {
            return 1;
        }

        @Override
        public boolean release() {
            return false;
        }

        @Override
        public boolean release(int decrement) {
            return false;
        }

        @Override
        public DecoderResult decoderResult() {
            return DecoderResult.SUCCESS;
        }

        @Override
        public void setDecoderResult(DecoderResult result) {
            throw new UnsupportedOperationException("read only");
        }
    };

    @Override
    LPDReceiveJobDataFileLastContent copy();

    @Override
    LPDReceiveJobDataFileLastContent duplicate();

    @Override
    LPDReceiveJobDataFileLastContent retainedDuplicate();

    @Override
    LPDReceiveJobDataFileLastContent replace(ByteBuf content);

    @Override
    LPDReceiveJobDataFileLastContent retain(int increment);

    @Override
    LPDReceiveJobDataFileLastContent retain();

    @Override
    LPDReceiveJobDataFileLastContent touch();

    @Override
    LPDReceiveJobDataFileLastContent touch(Object hint);

}
