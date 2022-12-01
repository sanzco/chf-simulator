/*
 ===================================================================================
   
  Copyright (c) 2005, 2021 Oracle Ⓡ and/or its affiliates. All rights reserved.
  
  ====================================================================================
 */

package com.oracle.cgbu.simulator.chf.api.controller;



import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.cgbu.simulator.chf.api.SubscriptionsApi;
import com.oracle.cgbu.simulator.chf.api.model.SpendingLimitContext;
import com.oracle.cgbu.simulator.chf.api.model.SpendingLimitStatus;
import com.oracle.cgbu.simulator.chf.entity.Counter;
import com.oracle.cgbu.simulator.chf.entity.Subscription;
import com.oracle.cgbu.simulator.chf.notify.StatusInfoBuilder;
import com.oracle.cgbu.simulator.chf.services.CHFService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * The controller for CHF subscription API.
 */
@Log4j2
@RestController
@RequestMapping(path = "/nchf-spendinglimitcontrol/v1")
public class NfSubscriptionController implements SubscriptionsApi {

	@Autowired
	private CHFService chfService;

	@Value("${chf.namespace}")
    private String namespace;
	
	@Value("${chf.servicefqdn}")
    private String servicefqdn;
	
	@Value("${chf.subscriptionURL}")
    private String subscriptionURL;
	
	
	
	/**
   * Subscribe service operation.
   * @return Subscription Created (status code 201)
   */
  @Override
  public ResponseEntity<SpendingLimitStatus> subscriptionsPost(
		  SpendingLimitContext spendingLimitContext) {
    log.traceEntry("({incoming request})", spendingLimitContext);
    log.debug("Request: {}",spendingLimitContext);
    Iterable<Counter> counters = chfService.getUserCounters(spendingLimitContext.getSupi());
    SpendingLimitStatus spendingL = null;
    HttpHeaders responseHeaders = new HttpHeaders();
    if(counters!=null) {
    	spendingL = StatusInfoBuilder.buildSpendingLimit(spendingLimitContext.getSupi(), counters, spendingLimitContext.getSupportedFeatures(), spendingLimitContext.getExpiry());
    	Subscription subs = chfService.saveSubscription(spendingLimitContext);
	    
	    try {
	    	URI location = new URI("http://"+servicefqdn+"."+namespace+subscriptionURL+subs.getSubscriptionId());
	    	responseHeaders.setLocation(location)	;
	    	log.debug(location);
	    }catch (URISyntaxException e) {
	    	log.error("ERROR creating subscription URI");
	    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }else {
    	log.info("No user found with supi {}",spendingLimitContext.getSupi());
    	return new ResponseEntity<>(spendingL, responseHeaders, HttpStatus.NOT_FOUND);
    }
    log.traceExit();
    log.info("subscription response from CHF:{}",spendingL);
    return new ResponseEntity<>(spendingL,responseHeaders, HttpStatus.CREATED);
  }
  
  

  @Override
	public ResponseEntity<SpendingLimitStatus> subscriptionsSubscriptionIdPut(String subscriptionId,
			@Valid SpendingLimitContext spendingLimitContext) {
		// TODO Auto-generated method stub
		return SubscriptionsApi.super._subscriptionsSubscriptionIdPut(subscriptionId, spendingLimitContext);
	}
  
  @Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(String subscriptionId) {
		chfService.deleteSubscription(subscriptionId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
  
  
  
  
}
