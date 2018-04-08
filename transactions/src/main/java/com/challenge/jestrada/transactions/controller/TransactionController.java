package com.challenge.jestrada.transactions.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.challenge.jestrada.transactions.bo.Transaction;
import com.challenge.jestrada.transactions.service.TransactionService;

/**
 * 
 * @author jonathanestrada
 *
 */
@Controller
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/transactions")
	public ResponseEntity<?> transaction(@RequestBody Transaction transaction) {
		Long currentEpoch = Instant.now().getEpochSecond();
		Long receivedEpoch = transaction.getTimestamp() / 1000;
		boolean isCreatedOrNoContent = (currentEpoch - receivedEpoch) > 60 ? true : false;

		transactionService.addTransaction(transaction);
		return new ResponseEntity<>(isCreatedOrNoContent ? HttpStatus.NO_CONTENT : HttpStatus.CREATED);
	}

}
