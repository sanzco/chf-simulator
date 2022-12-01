package com.oracle.cgbu.simulator.chf.services;

import java.util.Optional;

import com.oracle.cgbu.simulator.chf.api.model.SpendingLimitContext;
import com.oracle.cgbu.simulator.chf.entity.Counter;
import com.oracle.cgbu.simulator.chf.entity.PendingCounter;
import com.oracle.cgbu.simulator.chf.entity.Subscription;
import com.oracle.cgbu.simulator.chf.entity.User;


public interface CHFService {
	public Iterable<Counter> getUserCounters(String supi) ;
	
	public Subscription saveSubscription(SpendingLimitContext spendingLimit);
	
	public Iterable<User> getUsers();
	
	public User getUserById(Integer userId) ;
	
	public Iterable<Subscription> getAllSubscriptionBySupi(String supi);
	
	public Optional<Counter> getCounter(Integer counterId);
	
	public Counter saveCounter(Counter counter);
	
	public PendingCounter savePendingCounter(PendingCounter pendingCounter);
	
	public void deletePending(Integer pendingId);
	
	public PendingCounter getPendingCounterbyId(Integer pendingId);
	
	public void deleteSubscription(String subsId);
	
	public User addUser(User user);
}
