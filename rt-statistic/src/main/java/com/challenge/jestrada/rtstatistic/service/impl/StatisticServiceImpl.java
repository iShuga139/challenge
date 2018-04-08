package com.challenge.jestrada.rtstatistic.service.impl;

import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.cache.Cache;

import org.springframework.stereotype.Service;

import com.challenge.jestrada.rtstatistic.bo.Statistic;
import com.challenge.jestrada.rtstatistic.service.StatisticService;
import com.challenge.jestrada.transactions.bo.Transaction;
import com.hazelcast.cache.ICache;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;

/**
 * 
 * @author jonathanestrada
 *
 */
@Service
public class StatisticServiceImpl implements StatisticService {

	private ICache<Long, Transaction> icache;

	@SuppressWarnings("unchecked")
	@PostConstruct
	private void init() {
		try {
			ClientConfig config = new ClientConfig();
			GroupConfig groupConfig = config.getGroupConfig();
			groupConfig.setName("dev");
			groupConfig.setPassword("dev-pass");

			HazelcastInstance hzClient = HazelcastClient.newHazelcastClient(config);
			Cache<Long, Transaction> myCache = hzClient.getCacheManager().getCache("data");
			icache = myCache.unwrap(ICache.class);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private double checkMinValue(double current) {
		return current > -1 ? current : 0;
	}

	@Override
	public Statistic getLastSixtySecondsStatistic(Long currentEpoch) {
		try {
			Iterator<Cache.Entry<Long, Transaction>> allCacheEntries = icache.iterator();
			Cache.Entry<Long, Transaction> currentEntry = null;

			double sum = 0, avg = 0, max = 0, min = -1;
			double auxAmount = 0L;
			Long count = 0L;

			while (allCacheEntries.hasNext()) {
				currentEntry = allCacheEntries.next();
				if (currentEntry.getKey() > currentEpoch)
					break;

				auxAmount = currentEntry.getValue().getAmount();

				count += 1;
				sum += auxAmount;
				avg = sum / count;
				max = max > auxAmount ? max : auxAmount;
				min = (min < auxAmount && min != -1) ? min : auxAmount;
			}

			return new Statistic(sum, avg, checkMinValue(min), max, count);
		} catch (Exception exception) {
			init();
			return new Statistic();
		}
	}

}
