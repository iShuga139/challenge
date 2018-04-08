package com.challenge.jestrada.rtstatistic.service;

import com.challenge.jestrada.rtstatistic.bo.Statistic;

/**
 * 
 * @author jonathanestrada
 *
 */
public interface StatisticService {

	public Statistic getLastSixtySecondsStatistic(Long currentEpoch);

}
