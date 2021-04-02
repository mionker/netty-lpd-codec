package com.ionker.netty.codec.lpd;

import com.ionker.netty.codec.lpd.receiveJob.LPDReceiveJobControlFile;
import com.ionker.netty.codec.lpd.receiveJob.LPDReceiveJobFileSubCommand;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class LPDServerHandler extends ChannelInboundHandlerAdapter {

    private final String queue;

    public LPDServerHandler(String queue) {
        this.queue = queue;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println(String.format("Received a %s", msg.getClass()));

        if ((msg instanceof LPDCommand) || (msg instanceof LPDReceiveJobFileSubCommand) || (msg instanceof LPDReceiveJobControlFile)) {
            final ByteBuf ack = ctx.alloc().buffer(1);
            ack.writeByte(0);
            ctx.writeAndFlush(ack);
            ctx.fireChannelRead(msg);
        }
    }
}
