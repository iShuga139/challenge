package com.challenge.jestrada.rtstatistic.util;

import java.util.Map;
import java.util.Optional;

import com.challenge.jestrada.rtstatistic.bo.Statistic;
import com.challenge.jestrada.rtstatistic.bo.Transaction;

public class DataMemStore {

	private Optional<Map<Long, Transaction>> data;
	private Statistic finalStatistic;
	
	public void addData(Transaction t) {
		data.get().put(1L, t);
		refactorMap(1L);
		calculateStatistic();
	}
	
	public void refactorMap(long oldTimeStamp) {
	}
	
	public void calculateStatistic() {
	}
	
	public Statistic getStatistic() {
		return this.finalStatistic;
	}

}
