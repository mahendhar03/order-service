package com.order.service.service;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.order.restaurent.service.ext.Store;

import jdk.internal.org.jline.utils.Log;

@Service
public class RestaurentInvoker {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${restaurent-eureka-url}")
	private String serviceUrl;

	@HystrixCommand(fallbackMethod = "fetchItemsForStoreFallback")
	public Store fetchItemsForStore(String storeName) {
		try {
			Log.info("######################### Restaurent Evaluation with {}",storeName);
			URL restaurentUrl = new URL(serviceUrl+"/restaurent/{store-name}");
			ResponseEntity<Store> restaurentResp = restTemplate.getForEntity(restaurentUrl.toString(), Store.class,
					storeName);
			if (restaurentResp.getStatusCode().equals(HttpStatus.OK)) {
				Log.info("######################### Restaurent Success");
				Store location=restaurentResp.getBody();
				return location;
			} else {
				Log.info("######################### ERROR getting Restaurent information");
				throw new RuntimeException("Error in getting restaurents data");
			}
		} catch (MalformedURLException e) {
			Log.info("######################### Restaurent url is not correct");
			e.printStackTrace();
			return null;
		}
	}
	
	public Store fetchItemsForStoreFallback(String storeName) {
		return new Store();
	}
}
