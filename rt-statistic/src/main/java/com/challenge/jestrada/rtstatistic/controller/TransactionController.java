package com.challenge.jestrada.rtstatistic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.challenge.jestrada.rtstatistic.bo.Transaction;
import com.challenge.jestrada.rtstatistic.service.TransactionService;

@Controller
public class TransactionController {

	private TransactionService transactionService;
	
	@PostMapping("/transaction")
	public ResponseEntity<Transaction> transactionReceived(@RequestBody Transaction t) {
		transactionService.addTransaction(t);
		return new ResponseEntity<Transaction>(t, HttpStatus.CREATED);
	}

}
