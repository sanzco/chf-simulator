package com.oracle.cgbu.simulator.chf.repo;

import org.springframework.data.repository.CrudRepository;

import com.oracle.cgbu.simulator.chf.entity.PendingCounter;

public interface PendingCounterRepository extends CrudRepository<PendingCounter, Integer> {
}
