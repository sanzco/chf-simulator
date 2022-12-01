package com.oracle.cgbu.simulator.chf.repo;

import org.springframework.data.repository.CrudRepository;

import com.oracle.cgbu.simulator.chf.entity.Counter;

public interface CounterRepository extends CrudRepository<Counter, Integer> {
}
