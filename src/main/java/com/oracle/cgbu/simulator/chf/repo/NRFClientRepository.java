package com.oracle.cgbu.simulator.chf.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.oracle.cgbu.simulator.chf.entity.NRFClient;

public interface NRFClientRepository extends CrudRepository<NRFClient, Integer> {
	public Optional<NRFClient> findByAppType(String appType);
}

