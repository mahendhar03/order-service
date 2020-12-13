package com.order.service.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Quantity {
	private BigDecimal orderedQuantity;
	private QuantityType quantityType;
	public enum QuantityType{
		GRAMS,LITERS,PIECES
	}
}
