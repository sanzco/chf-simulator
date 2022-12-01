package com.oracle.cgbu.simulator.chf.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.oracle.cgbu.simulator.chf.entity.Subscription;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
	public Iterable<Subscription>  findAllBySupi(String supi);
	
	public List<Subscription> findBySubscriptionId(String subscriptionId);
}
