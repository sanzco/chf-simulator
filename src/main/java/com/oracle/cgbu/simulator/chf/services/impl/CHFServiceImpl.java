package com.oracle.cgbu.simulator.chf.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.cgbu.simulator.chf.api.model.SpendingLimitContext;
import com.oracle.cgbu.simulator.chf.entity.Counter;
import com.oracle.cgbu.simulator.chf.entity.PendingCounter;
import com.oracle.cgbu.simulator.chf.entity.Subscription;
import com.oracle.cgbu.simulator.chf.entity.User;
import com.oracle.cgbu.simulator.chf.repo.CounterRepository;
import com.oracle.cgbu.simulator.chf.repo.PendingCounterRepository;
import com.oracle.cgbu.simulator.chf.repo.SubscriptionRepository;
import com.oracle.cgbu.simulator.chf.repo.UserRepository;
import com.oracle.cgbu.simulator.chf.services.CHFService;

@Service
public class CHFServiceImpl implements CHFService {

	@Autowired
	UserRepository userrepo;

	@Autowired
	SubscriptionRepository subsrepo;
	
	@Autowired
	CounterRepository counterrepo;
	
	@Autowired
	PendingCounterRepository pendingrepo;

	@Override
	public Iterable<Counter> getUserCounters(String supi) {
		User user = userrepo.findBySupi(supi);
		if (user != null)
			return user.getCounters();
		else
			return null;

	}

	@Override
	public Subscription saveSubscription(SpendingLimitContext spendingLimit) {
		Subscription subs = new Subscription();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Using today's date
		c.add(Calendar.DATE, 1); // Adding 5 days
		subs.setExpiry(new DateTime(c.getTime()));
		subs.setGpsi(spendingLimit.getGpsi());
		subs.setNotifUri(spendingLimit.getNotifUri());
		subs.setSupi(spendingLimit.getSupi());
		subs.setSupportedFeatures(spendingLimit.getSupportedFeatures());
		return subsrepo.save(subs);
	}

	@Override
	public Iterable<User> getUsers() {
		return userrepo.findAll();
	}

	@Override
	public User getUserById(Integer userId) {
		return userrepo.findById(userId).get();
	}

	@Override
	public Iterable<Subscription> getAllSubscriptionBySupi(String supi) {
		return subsrepo.findAllBySupi(supi);
	}

	@Override
	public Optional<Counter> getCounter(Integer counterId) {
		return counterrepo.findById(counterId);
	}

	@Override
	public Counter saveCounter(Counter counter) {
		return counterrepo.save(counter);
	}

	@Override
	public PendingCounter savePendingCounter(PendingCounter pendingCounter) {
		return pendingrepo.save(pendingCounter);
		
	}

	@Override
	public void deletePending(Integer pendingId) {
		pendingrepo.deleteById(pendingId);
		
	}

	@Override
	public PendingCounter getPendingCounterbyId(Integer pendingId) {
		return pendingrepo.findById(pendingId).get();
	}

	@Override
	public void deleteSubscription(String subsId) {
		subsrepo.delete(subsrepo.findBySubscriptionId(subsId).get(0));
	}

	@Override
	public User addUser(User user) {
		return userrepo.save(user);
	}

}
