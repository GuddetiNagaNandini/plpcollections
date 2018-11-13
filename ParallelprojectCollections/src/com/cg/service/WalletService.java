package com.cg.service;

import java.math.BigDecimal;
import java.util.List;

import com.cg.dto.Customer;
import com.cg.dto.Transactions;
import com.cg.exception.InsufficientBalance;
import com.cg.exception.InvalidInput;

public interface WalletService {

	public Customer createAccount(String name ,String mobileno, BigDecimal amount) throws InvalidInput;

	public Customer showBalance (String mobileno) throws InvalidInput;

	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, BigDecimal amount) throws InvalidInput, InsufficientBalance;

	public Customer depositAmount (String mobileNo,BigDecimal amount ) throws InvalidInput;

	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InvalidInput, InsufficientBalance;

	public List<Transactions> getTransactions(String mobileNo);
	public static boolean validateMobileNo(String mobileNo)throws InvalidInput {
		return false;
	}
}
