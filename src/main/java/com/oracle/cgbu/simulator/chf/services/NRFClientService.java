package com.oracle.cgbu.simulator.chf.services;

import java.util.Optional;

import com.oracle.cgbu.simulator.chf.entity.NRFClient;

public interface NRFClientService {
	public Optional<NRFClient> getNRFInfo(String appType);
}
