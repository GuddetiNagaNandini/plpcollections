package com.cg.dto;

import java.math.BigDecimal;

	public class Transactions {

		String transactionType;
		double amount;

		public Transactions() {
			super();
		}

		public Transactions(String transactionType, double amount) {
			super();
			this.transactionType = transactionType;
			this.amount = amount;
		}

		public String getTransactionType() {
			return transactionType;
		}

		public void setTransactionType(String transactionType) {
			this.transactionType = transactionType;
		}

		public double getAmount() {
			return amount;
		}

		

		@Override
		public String toString() {
			return "Transactions [transactionType=" + transactionType + ", amount=" + amount + "]";
		}

		public void setAmount(BigDecimal amount2) {
			// TODO Auto-generated method stub
			
		}

	}



