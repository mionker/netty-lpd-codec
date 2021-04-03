package com.ionker.netty.codec.lpd;

import java.util.List;

public class DefaultLPDSendQueueStateShortCommand extends AbstractLPDSendQueueStateCommand{

    public LPDSendQueueStateShortCommand(String queue, List<String> list) {
        super(queue, list);
    }
    
}
