package com.ionker.netty.codec.lpd.receiveJob;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.internal.ObjectUtil;

public class DefaultLPDReceiveJobDataFile extends AbstractLPDReceiveJobDataFileSubCommand implements LPDReceiveJobDataFile {

    private final ByteBuf content;

    public DefaultLPDReceiveJobDataFile(String queue, int size, String name) {
        this(queue, size, name, Unpooled.buffer(0));
    }

    public DefaultLPDReceiveJobDataFile(String queue, int size, String name, ByteBuf content) {
       super(queue, size, name);
       this.content = ObjectUtil.checkNotNull(content, "content");
    }

    @Override
    public ByteBuf content() {
        return content;
    }

    @Override
    public LPDReceiveJobDataFile copy() {
        return replace(content.copy());
    }

    @Override
    public LPDReceiveJobDataFile duplicate() {
        return replace(content.duplicate());
    }

    @Override
    public LPDReceiveJobDataFile retainedDuplicate() {
        return replace(content.retainedDuplicate());
    }

    @Override
    public LPDReceiveJobDataFile replace(ByteBuf content) {
        return new DefaultLPDReceiveJobDataFile(queue, size, name, content);
    }

    @Override
    public int refCnt() {
        return content.refCnt();
    }

    @Override
    public LPDReceiveJobDataFile retain() {
        content.retain();
        return this;
    }

    @Override
    public LPDReceiveJobDataFile retain(int increment) {
        content.retain(increment);
        return this;
    }

    @Override
    public LPDReceiveJobDataFile touch() {
        content.touch();
        return this;
    }

    @Override
    public LPDReceiveJobDataFile touch(Object hint) {
        content.touch(hint);
        return this;
    }

    @Override
    public boolean release() {
        return content.release();
    }

    @Override
    public boolean release(int decrement) {
        return content.release(decrement);
    }  
}
