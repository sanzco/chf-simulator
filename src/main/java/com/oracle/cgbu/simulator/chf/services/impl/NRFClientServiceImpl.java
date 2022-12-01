package com.oracle.cgbu.simulator.chf.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.cgbu.simulator.chf.entity.NRFClient;
import com.oracle.cgbu.simulator.chf.repo.NRFClientRepository;
import com.oracle.cgbu.simulator.chf.services.NRFClientService;

@Service
public class NRFClientServiceImpl implements NRFClientService {

	@Autowired
	NRFClientRepository nrfrepo;

	@Override
	public Optional<NRFClient> getNRFInfo(String appType) {
		return nrfrepo.findByAppType(appType);
	}
}
