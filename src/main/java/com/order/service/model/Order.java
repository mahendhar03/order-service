package com.order.service.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Order {
	private String orderId;
	private String customerId;
	private String customerType;
	private String storeName;
	private List<OrderedItem> orderedItems = new ArrayList<>();
}
