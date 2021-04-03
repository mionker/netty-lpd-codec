package com.ionker.netty.codec.lpd;

import java.util.List;

public class DefaultLPDSendQueueStateLongCommand extends AbstractLPDSendQueueStateCommand{

    public DefaultLPDSendQueueStateLongCommand(String queue, List<String> list) {
        super(queue, list);
    }
}
