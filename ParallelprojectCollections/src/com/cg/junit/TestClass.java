package com.cg.junit;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.dto.Customer;
import com.cg.dto.Wallet;
import com.cg.exception.InsufficientBalance;
import com.cg.exception.InvalidInput;
import com.cg.service.WalletService;
import com.cg.service.WalletServiceImpl;


public class TestClass {

	static WalletServiceImpl service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new WalletServiceImpl();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception 
	{
	}

	@After
	public void tearDown() throws Exception {
	}
	
boolean result;

	
	@Test
	public void test_ValidateMobileNo_v1()throws InvalidInput{
		result = WalletService.validateMobileNo("0123456789");
		Assert.assertEquals(false, result);
		
	}
	
	
	@Test
	public void test_ValidateMobileNo_v2()throws InvalidInput{
		result = WalletService.validateMobileNo("8912434354dsf");
		Assert.assertEquals(false, result);
		
	}
	
	@Test
	public void test_ValidateMobileNo_v3()throws InvalidInput{
		result = WalletService.validateMobileNo("8978063079");
		Assert.assertEquals(true, result);
		
	}


	@Test(expected=InvalidInput.class)
	public void testCreateAccount1() throws InvalidInput
	{
		service.createAccount(null, "8247766753", new BigDecimal(2000));
	}
	
	
	@Test(expected=InvalidInput.class)
	public void testCreateAccount2() throws InvalidInput
	{
		service.createAccount("", "8247766753", new BigDecimal(2000));
	}
	
	
	@Test(expected=InvalidInput.class)
	public void testCreateAccount3() throws InvalidInput 
	{
		service.createAccount("nandini", "809", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInput.class)
	public void testCreateAccount4() throws InvalidInput
	{
		service.createAccount("nandini", "", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInput.class)
	public void testCreateAccount5() throws InvalidInput 
	{
		service.createAccount("", "", new BigDecimal(1500));
	}
	
	@Test
	public void testCreateAccount6() throws InvalidInput 
	{
		Customer actual = service.createAccount("nandini", "8247766753", new BigDecimal(5000));
		Customer expected = null;
		
		assertNotEquals(expected, actual);
	}
	
	@Test(expected=InvalidInput.class)
	public void testCreateAccount7() throws InvalidInput 
	{
		service.createAccount("nandini", "8247766753", new BigDecimal(9000));
		service.createAccount("nandia", "8247766753", new BigDecimal(10000));
	}
	
	
	@Test(expected=InvalidInput.class)
	public void testCreateAccount8() throws InvalidInput 
	{
		service.createAccount("nandini", "8247766753", new BigDecimal(-100));
	}
	
	@Test(expected=InvalidInput.class)
	public void testCreateAccount9() throws InvalidInput 
	{
		Customer actual=service.createAccount("nandini", null, new BigDecimal(5000.75));
	}
	
	@Test
	public void testCreateAccount10() throws InvalidInput 
	{
		Customer actual=service.createAccount("paru", "9874561231", new BigDecimal(5000.75));
		Customer expected=null;
		
		assertNotEquals(expected, actual);
	}


	
	private void assertNotEquals(Customer expected, Customer actual) {
		// TODO Auto-generated method stub
		
	}

	@Test(expected=InvalidInput.class)
	public void testShowBalance11() throws InvalidInput 
	{
		service.showBalance(null);		
	}
	
	
	@Test(expected=InvalidInput.class)
	public void testShowBalance12() throws InvalidInput 
	{
		service.showBalance("");		
	}
	
	
	@Test(expected=InvalidInput.class)
	public void testShowBalance13() throws InvalidInput
	{
		service.showBalance("12345");		
	}
	
	
	@Test(expected=InvalidInput.class)
	public void testShowBalance14() throws InvalidInput
	{
		service.showBalance("78945612");		
	}
	
	
	@Test(expected=InvalidInput.class)
	public void testShowBalance15() throws InvalidInput 
	{
		service.showBalance("78945612333");		
	}
	
	
	@Test
	public void testShowBalance16() throws InvalidInput
	{
		service.createAccount("nandini", "9603924466", new BigDecimal(7000));
		Customer customer=service.showBalance("9603924466");
		BigDecimal expectedResult=new BigDecimal(7000);
		BigDecimal obtainedResult=customer.getWallet().getWalletBalance();
		
		assertEquals(expectedResult, obtainedResult);
		
	}
	
	@Test
	public void testWithdrawAmount() throws InvalidInput, InsufficientBalance {
		String name = "nandini";
		String mobileNumber = "7095134721";
		BigDecimal balance = new BigDecimal(7000);
		
		service.createAccount(name, mobileNumber, balance);
		
		BigDecimal amount = new BigDecimal(3000);
		
		Customer customer = service.withdrawAmount(mobileNumber, amount);
		assertEquals(new BigDecimal(4000), customer.getWallet().getWalletBalance());
	}
	
	@Test(expected = InsufficientBalance.class)
	public void testWithdrawAmount1() throws InvalidInput, InsufficientBalance {
		String name = "nandini";
		String mobileNumber = "7095134611";
		BigDecimal balance = new BigDecimal(2000);
		
		service.createAccount(name, mobileNumber, balance);
		
		BigDecimal amount = new BigDecimal(3000);
		
		service.withdrawAmount(mobileNumber, amount);
	}
	
	@Test
	public void testDepositAmount() throws InvalidInput {
		String name = "nandini";
		String mobileNumber = "7095134612";
		BigDecimal balance = new BigDecimal(3000);
		
		Customer customer = service.createAccount(name, mobileNumber, balance);
		
		Customer customer1 = service.depositAmount(mobileNumber, new BigDecimal(3000));
		
		assertEquals(new BigDecimal(6000), customer1.getWallet().getWalletBalance());
	}
	
	@Test(expected=InvalidInput.class)
	public void testDepositAmount1() throws InvalidInput {
		String name = "nandini";
		String mobileNumber = "7095134619";
		BigDecimal balance = new BigDecimal(2000);
		
		Customer customer = service.createAccount(name, mobileNumber, balance);
		
		Customer customer1 = service.depositAmount(mobileNumber, new BigDecimal(0));
		
	}
	
	@Test(expected = InvalidInput.class)
	public void testMobileNumber() throws InvalidInput, InsufficientBalance {
		String name = "nandini";
		String mobileNumber = "949245184132";
		BigDecimal balance = new BigDecimal(3000);
		
		service.createAccount(name, mobileNumber, balance);
		
		BigDecimal amount = new BigDecimal(3000);
		
		service.withdrawAmount(mobileNumber, amount);
	}
	
	@Test(expected = InsufficientBalance.class)
	public void testFundTransfer() throws InvalidInput, InsufficientBalance 
	{
		service.createAccount("Raji", "8247766752", new BigDecimal(1000));
		service.createAccount("Janu", "8247766751", new BigDecimal(1000));
		service.fundTransfer("8639119208", "9235548566", new BigDecimal(2000));
	}

}
