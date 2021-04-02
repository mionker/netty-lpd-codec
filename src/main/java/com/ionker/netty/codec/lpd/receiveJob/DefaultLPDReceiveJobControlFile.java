package com.ionker.netty.codec.lpd.receiveJob;

public class DefaultLPDReceiveJobControlFile implements LPDReceiveJobControlFile {

    private final String contents;

    public DefaultLPDReceiveJobControlFile(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }
}
