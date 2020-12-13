/*
 * package com.order.service.service.test;
 * 
 * import java.net.MalformedURLException; import java.net.URL;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.stereotype.Service; import
 * org.springframework.web.client.RestTemplate;
 * 
 * import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand; import
 * com.order.customer.service.ext.CustomerToken; import
 * com.order.customer.service.ext.Status; import
 * com.order.restaurent.service.ext.Location; import
 * com.order.service.model.Order;
 * 
 * //@Service public class OrderService {
 * 
 * //@Autowired private RestTemplate restTemplate;
 * 
 * //@HystrixCommand(fallbackMethod = "saveOrderFallback") public String
 * saveOrder(Order order) { try { URL customerUrl = new
 * URL("http://customerservice/customer/{customer-id}");
 * ResponseEntity<CustomerToken> customerResp =
 * restTemplate.getForEntity(customerUrl.toString(), CustomerToken.class,
 * order.getCustomerId()); if
 * (customerResp.getStatusCode().equals(HttpStatus.OK)) { CustomerToken
 * customerToken = customerResp.getBody(); if
 * (customerToken.getStatus().equals(Status.ACTIVE)) { URL restaurentUrl = new
 * URL("http://restaurentservice/restaurent/{store-name}");
 * ResponseEntity<Location> restaurentResp =
 * restTemplate.getForEntity(restaurentUrl.toString(), Location.class,
 * order.getStoreName()); if
 * (restaurentResp.getStatusCode().equals(HttpStatus.OK)) { Location
 * location=restaurentResp.getBody(); return
 * "Order has been placed with with store Id : " +location.getStoreID(); } else
 * { throw new RuntimeException("Error in getting restaurents data"); } } else {
 * throw new RuntimeException("Customer INACTIVE response"); } } else { throw
 * new RuntimeException("Error in getting customer data"); } } catch
 * (MalformedURLException e) { e.printStackTrace(); return null; } }
 * 
 * public String saveOrderFallback(Order order) { return
 * "All Restaurents are Fully Loaded"; } }
 */