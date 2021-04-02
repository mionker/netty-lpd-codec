package com.ionker.netty.codec.lpd.receiveJob;

public interface LPDReceiveJobFileSubCommand extends LPDReceiveJobObject{
    
    String getQueue();
    int getSize();
    String getName();
    
}
