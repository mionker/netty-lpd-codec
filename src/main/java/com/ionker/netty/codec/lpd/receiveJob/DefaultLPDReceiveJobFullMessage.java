package com.ionker.netty.codec.lpd.receiveJob;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.internal.ObjectUtil;

public class DefaultLPDReceiveJobFullMessage extends AbstractLPDReceiveJobDataFileSubCommand implements LPDReceiveJobFullMessage {

    private final ByteBuf content;

    public DefaultLPDReceiveJobFullMessage(String queue, int size, String name) {
        this(queue, size, name, Unpooled.buffer(0));
    }

    public DefaultLPDReceiveJobFullMessage(String queue, int size, String name, ByteBuf content) {
       super(queue, size, name);
       this.content = ObjectUtil.checkNotNull(content, "content");
    }

    @Override
    public ByteBuf content() {
        return content;
    }

    @Override
    public LPDReceiveJobFullMessage copy() {
        return replace(content.copy());
    }

    @Override
    public LPDReceiveJobFullMessage duplicate() {
        return replace(content.duplicate());
    }

    @Override
    public LPDReceiveJobFullMessage retainedDuplicate() {
        return replace(content.retainedDuplicate());
    }

    @Override
    public LPDReceiveJobFullMessage replace(ByteBuf content) {
        return new DefaultLPDReceiveJobFullMessage(queue, size, name, content);
    }

    @Override
    public int refCnt() {
        return content.refCnt();
    }

    @Override
    public LPDReceiveJobFullMessage retain() {
        content.retain();
        return this;
    }

    @Override
    public LPDReceiveJobFullMessage retain(int increment) {
        content.retain(increment);
        return this;
    }

    @Override
    public LPDReceiveJobFullMessage touch() {
        content.touch();
        return this;
    }

    @Override
    public LPDReceiveJobFullMessage touch(Object hint) {
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
