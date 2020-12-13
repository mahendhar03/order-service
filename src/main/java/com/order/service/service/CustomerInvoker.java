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
import com.order.customer.service.ext.CustomerToken;

@Service
public class CustomerInvoker {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${customer-eureka-url}")
	private String serviceUrl;

	@HystrixCommand(fallbackMethod = "getCustomerTokenFallback")
	public CustomerToken getCustomerToken(String customerId) {
		try {
			
			URL customerUrl = new URL(serviceUrl+"/customer/{customer-id}");
			ResponseEntity<CustomerToken> customerResp = restTemplate.getForEntity(customerUrl.toString(), CustomerToken.class,
					customerId);
			if (customerResp.getStatusCode().equals(HttpStatus.OK)) {
				CustomerToken customerToken = customerResp.getBody();
				return customerToken;
			} else {
				throw new RuntimeException("Error in getting customer data");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public CustomerToken getCustomerTokenFallback(String customerId) {
		return new CustomerToken();
	}
}
