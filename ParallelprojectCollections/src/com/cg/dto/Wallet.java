package com.cg.dto;

import java.math.BigDecimal;




	public class Wallet {

		private BigDecimal walletBalance;

		public Wallet() {
			super();
		}

		public BigDecimal getWalletBalance() {
			return walletBalance;
		}

		public void setWalletBalance(BigDecimal walletBalance) {
			this.walletBalance = walletBalance;
		}

		@Override
		public String toString() {
			return "Wallet [walletBalance=" + walletBalance + "]";
		}

		public Wallet(BigDecimal walletBalance) {
			super();
			this.walletBalance = walletBalance;
		}

	

	}



