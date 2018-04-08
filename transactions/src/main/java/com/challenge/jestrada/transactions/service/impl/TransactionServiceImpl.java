package com.challenge.jestrada.transactions.service.impl;

import javax.annotation.PostConstruct;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;

import org.springframework.stereotype.Service;

import com.challenge.jestrada.transactions.bo.Transaction;
import com.challenge.jestrada.transactions.service.TransactionService;
import com.hazelcast.cache.ICache;

/**
 * 
 * @author jonathanestrada
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	private ICache<Long, Transaction> icache;

	@SuppressWarnings("unchecked")
	@PostConstruct
	private void init() {
		CacheManager manager = Caching.getCachingProvider().getCacheManager();
		MutableConfiguration<Long, Transaction> configuration = new MutableConfiguration<Long, Transaction>();
		
		configuration.setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(Duration.ONE_MINUTE));

		Cache<Long, Transaction> myCache = manager.createCache("data", configuration);
		icache = myCache.unwrap(ICache.class);
	}

	@Override
	public void addTransaction(Transaction t) {
		icache.put(t.getTimestamp(), t);
	}

}
