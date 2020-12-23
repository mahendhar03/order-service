package com.order.service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.customer.service.ext.CustomerToken;
import com.order.customer.service.ext.Status;
import com.order.restaurent.service.ext.Item;
import com.order.restaurent.service.ext.Store;
import com.order.service.model.Order;
import com.order.service.model.OrderedItem;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
	
	@Autowired
	private RestaurentInvoker restaurentInvoker;

	@Autowired
	private CustomerInvoker customerInvoker;

	public String saveOrder(Order order) {
		log.info("######################### Order Evaluation");
		CustomerToken customerToken = customerInvoker.getCustomerToken(order.getCustomerId());
		log.info("######################### Customer status {}",customerToken.getStatus());
		if (customerToken.getStatus().equals(Status.ACTIVE)) {
			Store storeDetails = restaurentInvoker.fetchItemsForStore(order.getStoreName());
			for (OrderedItem orderedItem : order.getOrderedItems()) {
				Optional<Item> matchedItemOptional = storeDetails.getItems().stream()
						.filter(storeItem -> storeItem.getItemName().equalsIgnoreCase(orderedItem.getItemName()))
						.findAny();
				log.info("######################### unable to find matchedItem store itemName and odered itemName");
				if(matchedItemOptional.isPresent()) {
					Item machedItem=matchedItemOptional.get();
					log.info("######################### machedItem found");
					if(machedItem.getQuantity().getAvailableQuantity().compareTo(orderedItem.getQuantity().getOrderedQuantity())>=0) {
						log.info("######################### matchedItem {} and orderedItem {}",machedItem.getQuantity().getAvailableQuantity(),orderedItem.getQuantity().getOrderedQuantity());
						return "Your ordered accepted!!!..please proceed for payment";
					}else {
						log.error("######################### matchedItem {} and orderedItem {}",machedItem.getQuantity().getAvailableQuantity(),orderedItem.getQuantity().getOrderedQuantity());
						throw new RuntimeException("Ordered Items already sold!!!!!!!");
					}
				}else {
					log.info("######################### unable to find matchedItem ");
					throw new RuntimeException("Ordered Items not found!!!!!!!!!!");
				}
			}
		} else {
			log.info("######################### Customer INACTIVE response ");
			throw new RuntimeException("Customer INACTIVE response");
		}
		return "Order can't placed!!! sorry for incovinence";
	}
}
