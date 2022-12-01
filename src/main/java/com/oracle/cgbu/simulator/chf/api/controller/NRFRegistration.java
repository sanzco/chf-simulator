package com.oracle.cgbu.simulator.chf.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.cgbu.simulator.chf.api.model.PatchItem;
import com.oracle.cgbu.simulator.chf.api.model.PatchOperation;
import com.oracle.cgbu.simulator.chf.entity.NRFClient;
import com.oracle.cgbu.simulator.chf.notify.ApiClient;
import com.oracle.cgbu.simulator.chf.notify.ApiResponse;
import com.oracle.cgbu.simulator.chf.services.NRFClientService;

import lombok.extern.log4j.Log4j2;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
@EnableScheduling
@Log4j2
public class NRFRegistration {

	private static final MediaType TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
	private static final MediaType TYPE_JSON_PATCH =  MediaType.parse("application/json-patch+json");
	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	private OkHttpClient h2cClient;
	private static Optional<NRFClient> nrfClient; 
	private static final String CHF_TYPE = "CHF";
	
	@Autowired
	private NRFClientService nrfservice;
	
	public NRFRegistration() {
		this(new ApiClient());
	}
	
	public NRFRegistration(ApiClient apiClient) {
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public ApiResponse<Void> applicationRegister(){
		nrfClient  =  nrfservice.getNRFInfo(CHF_TYPE);
		log.debug("Trying to register with NRF {}",nrfClient.get().getNrfurl()+nrfClient.get().getAppId());
		if(nrfClient.isPresent()) {
			Response response = null;
			try {
				log.info("register body: {}",nrfClient.get().getAppProfile());
				RequestBody reqBody = RequestBody.create(TYPE_JSON,nrfClient.get().getAppProfile());
				Request request = (new Request.Builder()).url(nrfClient.get().getNrfurl()+nrfClient.get().getAppId()).put(reqBody).build();
				response = getClient().newCall(request).execute();
				log.info("register response code {} and body {}", response.code(),response.body().string());
				return new ApiResponse<Void>(response.code(), response.headers().toMultimap());
			} catch (IOException e) {
				log.error("Registration IO ERROR: {}", e.getMessage());
				
			} catch (Exception e) {
				log.error("Registration Exception ERROR: {}", e.getMessage());
			}  finally {
				if (response != null) {
					response.close();
				}
				log.debug("Finalizing request");
			}
		}else {
			log.warn("There is no a NRF profile to register");
		}
		return null;
	}
	
	@Scheduled(fixedDelay = 300000, initialDelay = 10000)
	public ApiResponse<Void> updateProfile() {
		if(nrfClient.isPresent()) {
			Response response = null;
			try {
				RequestBody reqBody = RequestBody.create(TYPE_JSON_PATCH,JSON_MAPPER.writeValueAsBytes(patchOperations()));
				log.info("Send Patch Heartbeat request for nfInstanceId: {}, body: {}", nrfClient.get().getAppId());
				Request.Builder builder = new Request.Builder();
				builder.addHeader("User-Agent",nrfClient.get().getAppType());
				builder.url(nrfClient.get().getNrfurl()+nrfClient.get().getAppId());
				Request request = builder.patch(reqBody).build();
				response = getClient().newCall(request).execute();
				log.info("patch response code: {} and body: {}", response.code(),response.body().string());
				return new ApiResponse<Void>(response.code(), response.headers().toMultimap());
			} catch (IOException e) {
				log.error("patch IO ERROR: {}", e.getMessage());
			} catch (Exception e) {
				log.error("patch Exception ERROR: {}", e.getMessage());
			}  finally {
				if (response != null) {
					response.close();
				}
				log.debug("Finalizing patch request");
			}
		}else {
			log.warn("nrf client empty");
		}
		return null;
	}
	
	private OkHttpClient getClient() {
		List<Protocol> h2cProtocols = new ArrayList<>();
		h2cProtocols.add(Protocol.H2_PRIOR_KNOWLEDGE);
		this.h2cClient = (new OkHttpClient.Builder()).protocols(h2cProtocols).connectTimeout(5, TimeUnit.SECONDS)
				.callTimeout(5, TimeUnit.SECONDS).build();
		return this.h2cClient;
	}
	
	private List<PatchItem> patchOperations(){
		List<PatchItem> patchItems = new ArrayList<>();
        PatchItem patch = new PatchItem();
        patch.setOp(PatchOperation.REPLACE);
        patch.setPath("/nfStatus");
        patch.setValue("REGISTERED");
        patchItems.add(patch);
        return patchItems;
	}
	
}
