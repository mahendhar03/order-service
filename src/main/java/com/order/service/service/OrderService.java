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

@Service
public class OrderService {
	
	@Autowired
	private RestaurentInvoker restaurentInvoker;

	@Autowired
	private CustomerInvoker customerInvoker;

	public String saveOrder(Order order) {
		CustomerToken customerToken = customerInvoker.getCustomerToken(order.getCustomerId());
		if (customerToken.getStatus().equals(Status.ACTIVE)) {
			Store storeDetails = restaurentInvoker.fetchItemsForStore(order.getStoreName());
			for (OrderedItem orderedItem : order.getOrderedItems()) {
				Optional<Item> matchedItemOptional = storeDetails.getItems().stream()
						.filter(storeItem -> storeItem.getItemName().equalsIgnoreCase(orderedItem.getItemName()))
						.findAny();
				if(matchedItemOptional.isPresent()) {
					Item machedItem=matchedItemOptional.get();
					if(machedItem.getQuantity().getAvailableQuantity().compareTo(orderedItem.getQuantity().getOrderedQuantity())>=0) {
						return "Your ordered accepted!!!..please proceed for payment";
					}else {
						throw new RuntimeException("Ordered Items already sold!!!!!!!");
					}
				}else {
					throw new RuntimeException("Ordered Items not found!!!!!!!!!!");
				}
			}
		} else {
			throw new RuntimeException("Customer INACTIVE response");
		}
		return "Order can't placed!!! sorry for incovinence";
	}
}
