package com.cg.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.dto.Customer;
import com.cg.dto.Transactions;
import com.cg.dto.Wallet;
import com.cg.exception.InsufficientBalance;
import com.cg.exception.InvalidInput;
import com.cg.dao.Walletdao;
import com.cg.dao.WalletDaoImpl;

public class WalletServiceImpl implements WalletService{


	private Walletdao dao;
	List<Transactions> list;
	Map<String, List<Transactions>> transactions;

	public WalletServiceImpl() {

		list = new ArrayList<>();
		dao = new WalletDaoImpl();
		transactions = new HashMap<>();
	}

	public Customer createAccount(String name, String mobileNo, BigDecimal amount) throws InvalidInput {

		if(isValid(mobileNo) && isValidName(name) && amount.compareTo(new BigDecimal(0)) > 0) {

			Wallet wallet = new Wallet();
			Customer customer = new Customer();

			wallet.setWalletBalance(amount);
			customer.setCustomerName(name);
			customer.setMobileNo(mobileNo);
			customer.setWallet(wallet);

			if(dao.save(customer) == true) {
				transactions.put(mobileNo, null);
				return customer;
			}
			else
				throw new InvalidInput("User already present");
		}
		else throw new InvalidInput("Enter valid details");
	}

	private boolean isValidName(String name) {

		if( name == null || name.trim().isEmpty() )
			return false;
		return true;
	}

	public Customer showBalance(String mobileNo) throws InvalidInput {

		if(isValid(mobileNo)) {

			Customer customer=dao.findOne(mobileNo);
			if(customer!=null)
				return customer;
			else
				throw new InvalidInput("No person with this mobile no ");
		}
		else 
			throw new InvalidInput("Enter valid mobile number");
	}

	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws InvalidInput, InsufficientBalance {

		if(isValid(sourceMobileNo) == false || isValid(targetMobileNo) == false || sourceMobileNo.equals(targetMobileNo)) throw new InvalidInput();

		Customer customer = withdrawAmount(sourceMobileNo, amount);
		depositAmount(targetMobileNo, amount);

		return customer;
	}

	public Customer depositAmount(String mobileNo, BigDecimal amount) throws InvalidInput {

		if(amount.compareTo(new BigDecimal(0)) <= 0) 
			throw new InvalidInput();

		if(isValid(mobileNo)) {

			Customer customer = dao.findOne(mobileNo);
			Wallet wallet = customer.getWallet();
			wallet.setWalletBalance(wallet.getWalletBalance().add(amount));

			Transactions transaction = new Transactions();
			transaction.setAmount(amount);
			transaction.setTransactionType("Deposit");

			list.add(transaction);
			if(transactions.containsKey(mobileNo)) {
				transactions.remove(mobileNo);
				transactions.put(mobileNo, list);
			}

			dao.remove(mobileNo);

			if(dao.save(customer)) {
				return customer;
			}
		}
		return null;
	}

	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InvalidInput, InsufficientBalance {
		if(isValid(mobileNo)) {

			Customer customer = dao.findOne(mobileNo);
			Wallet wallet = customer.getWallet();
			wallet.setWalletBalance(wallet.getWalletBalance().subtract(amount));

			if(amount.compareTo(wallet.getWalletBalance()) > 0) 
				throw new InsufficientBalance("Amount is not sufficient in your account");

			Transactions transaction = new Transactions();
			transaction.setAmount(amount);
			transaction.setTransactionType("Withdraw");

			list.add(transaction);
			if(transactions.containsKey(mobileNo)) {
				transactions.remove(mobileNo);
				transactions.put(mobileNo, list);
			}

			dao.remove(mobileNo);

			dao.save(customer);

			return customer;
		}
		else throw new InvalidInput("Enter valid mobile number");
	}


	public List<Transactions> getTransactions(String mobileNo) {

		return transactions.get(mobileNo);
	}

	public boolean isValid(String mobileNo) {

		if(mobileNo != null && mobileNo.matches("[1-9][0-9]{9}")) {
			return true;
		} else 
			return false;
	}
	
	public boolean validateMobileNo(String mobileNo)throws InvalidInput {
		// TODO Auto-generated method stub
		if(mobileNo == null)
			throw new InvalidInput("Null value found");
		Pattern p = Pattern.compile("[6789][0-9]{9}");
		Matcher m = p.matcher(mobileNo);
		return m.matches();
	}
}

