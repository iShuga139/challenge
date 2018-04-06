package com.challenge.jestrada.rtstatistic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.challenge.jestrada.rtstatistic.bo.Transaction;
import com.challenge.jestrada.rtstatistic.service.TransactionService;
import com.challenge.jestrada.rtstatistic.util.DataMemStore;

public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private DataMemStore dms;
	
	@Override
	public void addTransaction(Transaction t) {
		dms.addData(t);
	}

}
