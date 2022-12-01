package com.oracle.cgbu.simulator.chf.notify;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.cgbu.simulator.chf.api.model.SpendingLimitStatus;

import lombok.extern.log4j.Log4j2;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Log4j2
public class SubscriptionNotify {
	private static final MediaType TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
	private final ObjectMapper JSON_MAPPER;
	private OkHttpClient h2cClient;

	public SubscriptionNotify() {
		this(new ApiClient());
	}

	public SubscriptionNotify(ApiClient apiClient) {
		JSON_MAPPER = apiClient.getObjectMapper();
	}

	public ApiResponse<Void> notify(String url, SpendingLimitStatus status) throws ApiException {
		log.debug("Trying to notify {}", url);
		Response response = null;
		try {
			RequestBody reqBody = RequestBody.create(TYPE_JSON, JSON_MAPPER.writeValueAsString(status));
			Request request = (new Request.Builder()).url(url).post(reqBody).build();
			log.debug("notify request Body {}", reqBody);
			response = getClient().newCall(request).execute();
			log.debug("Notify response code {}", response.code());
			if (response.code() == javax.ws.rs.core.Response.Status.NO_CONTENT.getStatusCode()) {
				return new ApiResponse<Void>(response.code(), response.headers().toMultimap());
			}
			throw new ApiException(response.code(), response.body().string());
		} catch (IOException e) {
			log.error("Notification ERROR: {}", e.getMessage());
			throw new ApiException(e);
		} finally {
			if (response != null) {
				response.close();
			}
			log.debug("Finalizing request");
		}
	}

	private OkHttpClient getClient() {
		List<Protocol> h2cProtocols = new ArrayList<>();
		h2cProtocols.add(Protocol.H2_PRIOR_KNOWLEDGE);
		this.h2cClient = (new OkHttpClient.Builder()).protocols(h2cProtocols).connectTimeout(5, TimeUnit.SECONDS)
				.callTimeout(5, TimeUnit.SECONDS).build();
		return this.h2cClient;
	}
}
