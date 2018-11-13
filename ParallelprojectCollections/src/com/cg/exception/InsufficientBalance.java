package com.cg.exception;

public class InsufficientBalance extends Exception {
	

		public InsufficientBalance() {
			super();
		}

		public InsufficientBalance(String message, Throwable reason, boolean enableSuppression,
				boolean writableStackTrace) {
			super(message, reason, enableSuppression, writableStackTrace);
		}

		public InsufficientBalance(String message, Throwable reason) {
			super(message, reason);
		}

		public InsufficientBalance(String message) {
			super(message);
		}

		public InsufficientBalance(Throwable reason) {
			super(reason);
		}
		
	}


