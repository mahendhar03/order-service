package com.order.customer.service.ext;

import lombok.Data;

@Data
public class CustomerToken {
    private String id;
    private int type;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String emailId;
    private String activationDate;
    private Status status;
    
}
