package com.ionker.netty.codec.lpd.receiveJob;

import io.netty.handler.codec.DecoderResult;

public class AbstractLPDReceiveJobDataFileSubCommand extends AbstractLPDReceiveJobFileSubCommand implements LPDReceiveJobDataFileSubCommand {
    
    protected DecoderResult decoderResult = DecoderResult.SUCCESS;
    
    public AbstractLPDReceiveJobDataFileSubCommand(String queue, int size, String name) {
        super(queue, size, name);
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
