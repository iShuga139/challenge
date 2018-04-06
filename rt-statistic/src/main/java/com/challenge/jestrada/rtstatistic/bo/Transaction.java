package com.challenge.jestrada.rtstatistic.bo;

public class Transaction {

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

	public double getAmoung() {
		return amount;
	}

	public void setAmoung(double amount) {
		this.amount = amount;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
