package com.challenge.jestrada.rtstatistic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.challenge.jestrada.rtstatistic.bo.Statistic;
import com.challenge.jestrada.rtstatistic.service.StatisticService;
import com.challenge.jestrada.rtstatistic.util.DataMemStore;

public class StatisticServiceImpl implements StatisticService {

	@Autowired
	private DataMemStore dms;

	@Override
	public Statistic getLastSixtySecondsStatistic() {
		return dms.getStatistic();
	}

}
