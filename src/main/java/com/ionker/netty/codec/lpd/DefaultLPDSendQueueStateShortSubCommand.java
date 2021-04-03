package com.ionker.netty.codec.lpd;

import java.util.List;

public class DefaultLPDSendQueueStateShortSubCommand extends AbstractLPDSendQueueStateCommand{

    public DefaultLPDSendQueueStateShortSubCommand(String queue, List<String> list) {
        super(queue, list);
    }
}
