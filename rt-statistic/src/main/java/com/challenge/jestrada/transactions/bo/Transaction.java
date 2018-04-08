package com.challenge.jestrada.transactions.bo;

import java.io.Serializable;

/**
 * BO - Transaction business object
 * @author jonathanestrada
 *
 */
public class Transaction implements Serializable {

	private static final long serialVersionUID = -6229831009550229567L;
	private double amount;
	private long timestamp;

	public Transaction() {
		super();
	}

	public Transaction(double amount, long timestamp) {
		super();
		this.amount = amount;
		this.timestamp = timestamp;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
