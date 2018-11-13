package com.cg.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.cg.dto.Customer;
import com.cg.dto.Transactions;
import com.cg.dto.Wallet;
import com.cg.exception.InsufficientBalance;
import com.cg.exception.InvalidInput;
import com.cg.service.WalletService;
import com.cg.service.WalletServiceImpl;

public class Client {

	WalletServiceImpl service;

	Client() {

		service = new WalletServiceImpl();
	}

	public void menu() {
		System.out.println("......Electronic Wallet.....");
		System.out.println("1) Create Account");
		System.out.println("2) Show Balance");
		System.out.println("3) Deposit Amount");
		System.out.println("4) Withdraw Amount");
		System.out.println("5) Fund Transfer");
		System.out.println("6) Print Transactions");
		System.out.println("0) Exit Application");

		Scanner sc = new Scanner(System.in);

		System.out.println("Dear user,Enter your choice");
		int choice = sc.nextInt();

		switch(choice) {

		case 1:

			Customer customer = new Customer();
			Wallet wallet = new Wallet();

			System.out.print("\nEnter name: ");
			String customerName = sc.next();

			System.out.print("\nEnter mobileNumber: ");
			String mobileNumber = sc.next();

			System.out.print("\nEnter Amount: ");
			BigDecimal amount = sc.nextBigDecimal();

			try {
				customer = service.createAccount(customerName, mobileNumber, amount);
				System.out.println("Your account has successfully registered");
			} 
			catch (InvalidInput e) {
				e.printStackTrace();
			}
			break;

		case 2:

			System.out.println("\nEnter mobile number");
			mobileNumber = sc.next();

			try {
				customer = service.showBalance(mobileNumber);
				System.out.print("Account balance is " + customer.getCustomerName());
				System.out.println(" is " + customer.getWallet().getWalletBalance());
			} catch (InvalidInput e3) {
				e3.printStackTrace();
			}	
			break;
		case 3:
			System.out.println("\nEnter mobile number");
			mobileNumber = sc.next();

			System.out.println("\nEnter amount to be deposited");
			amount = sc.nextBigDecimal();

			try {
				customer = service.depositAmount(mobileNumber, amount);
				System.out.println("Amount Successfully deposited");
				System.out.println("Account balance is: " + customer.getWallet().getWalletBalance());
			} catch (InvalidInput e2) {
				e2.printStackTrace();
			}
			break;
		case 4:
			System.out.println("Enter mobile number");
			mobileNumber = sc.next();

			System.out.println("\nEnter amount to be withdrawn");
			amount = sc.nextBigDecimal();

			try {
				customer = service.withdrawAmount(mobileNumber, amount);
				System.out.println("Amount Successfully withdrawn");
				System.out.println("Account balance is: " + customer.getWallet().getWalletBalance());
			} catch (InvalidInput e1) {
				e1.printStackTrace();
			} catch (InsufficientBalance e) {
				e.printStackTrace();
			}
			break;
		case 5:

			System.out.print("\nEnter source mobile number: ");
			String sourceMobile = sc.next();

			System.out.print("\nEnter target mobile number: ");
			String targetMobile = sc.next();

			System.out.println("\nEnter amount to be transferred");
			amount = sc.nextBigDecimal();

			try {
				customer = service.fundTransfer(sourceMobile, targetMobile, amount);
				System.out.println("Amount has successfully transferred from account " + customer.getCustomerName());
				System.out.println("And now your balance is " + customer.getWallet().getWalletBalance());

			} 
			catch (InvalidInput e) {
				e.printStackTrace();
			} catch (InsufficientBalance e) {
				e.printStackTrace();
			}
			break;
		case 6:

			System.out.println("\nEnter mobile transactions to view transactions");
			String mobileNo = sc.next();

			List<Transactions> list = new ArrayList<>();
			list = service.getTransactions(mobileNo);

			Iterator<Transactions> it = list.iterator();

			while(it.hasNext()) {
				Transactions transaction = it.next();
				System.out.println("Mobile No\t: " + mobileNo);
				System.out.println("Transaction Type: " + transaction.getTransactionType());
				System.out.println("Amount\t: " + transaction.getAmount());
			}
			break;
		case 0:
			System.out.println("Thank you for using our services");
			System.out.println("Good Bye");
			System.exit(0);
		default:
			System.out.println("Please enter valid choice");
			break;
		}
	}
	public static void main(String[] args) {

		Client client = new Client();

		while(true) {
			client.menu();
		}
	}


}
