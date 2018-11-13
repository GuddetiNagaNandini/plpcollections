package com.cg.dao;
import java.util.HashMap;
import java.util.Map;

import com.cg.dto.Customer;

public class WalletDaoImpl implements Walletdao{

	private Map<String, Customer> data; 

	public WalletDaoImpl() {

		data = new HashMap<String, Customer>();
	}

	public boolean save(Customer customer) {

		if(data.get(customer.getMobileNo()) == null) {
			data.put(customer.getMobileNo(), customer);
			return true;
		}
		return false;
	}

	@Override
	public void remove(String mobileNo) {

		data.remove(mobileNo);
	}

	public Customer findOne(String mobileNo) {

		if(data.get(mobileNo) != null) {
			return data.get(mobileNo);
		}
		return null;
	}	
}
