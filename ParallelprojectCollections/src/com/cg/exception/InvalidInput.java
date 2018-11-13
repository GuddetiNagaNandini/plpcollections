package com.cg.exception;

public class InvalidInput extends Exception {

	public InvalidInput() {
		super();
	}

	public InvalidInput(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidInput(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidInput(String arg0) {
		super(arg0);
	}

	public InvalidInput(Throwable arg0) {
		super(arg0);
	}
	
}