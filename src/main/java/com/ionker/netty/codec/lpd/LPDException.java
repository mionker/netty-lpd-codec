package com.ionker.netty.codec.lpd;

public class LPDException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public LPDException() {
		super();
	}
	
	public LPDException(String message) {
		super(message);
	}
	
	public LPDException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public LPDException(Throwable throwable) {
		super(throwable);
	}
}