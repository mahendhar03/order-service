package com.order.restaurent.service.ext;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Item {
	private String itemCode;
	private String itemName;
	private Catageroy catagory;
	private String decription;
	private Quantity quantity;
	private BigDecimal unitPrice;
	private String tags;
}
