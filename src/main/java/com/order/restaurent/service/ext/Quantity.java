package com.order.restaurent.service.ext;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Quantity {
	private BigDecimal availableQuantity;
	private QuantityType quantityType;
	public enum QuantityType{
		GRAMS,LITERS,PIECES
	}
}
