package com.order.service.model;

import lombok.Data;

@Data
public class OrderedItem {
	private String itemName;
	private Quantity quantity;
}
