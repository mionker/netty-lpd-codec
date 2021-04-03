package com.ionker.netty.codec.lpd;

import java.util.List;

public class DefaultLPDSendQueueStateLongSubCommand extends AbstractLPDSendQueueStateCommand{

    public DefaultLPDSendQueueStateLongSubCommand(String queue, List<String> list) {
        super(queue, list);
    }
}
