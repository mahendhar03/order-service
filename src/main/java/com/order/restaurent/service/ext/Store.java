package com.order.restaurent.service.ext;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


import lombok.Data;

@Data
public class Store {
    private BigInteger storeId;
    private String storeName;
    private String licenceCode;
    private String owner;
    private double rating;
    private List<Item> items=new ArrayList<>();
}
