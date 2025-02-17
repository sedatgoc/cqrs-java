package io.rsg.cqrs.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "Orders")

public class Order {

    @Id
    private String orderId;
    private Customer customer;
    private List<OrderItem> items;
    private BigDecimal totalAmount;
    private String currency;
    private Payment payment;
    private String status;
    private Instant orderDate;
    private Instant deliveryDate;
}


@Getter
@Setter
class Customer {
    private String customerId;
    private String name;
    private String email;
    private String phone;
    private Address address;
}

@Getter
@Setter
class Address {
    private String street;
    private String city;
    private String postalCode;
    private String country;

}

@Getter
@Setter
class OrderItem {
    private String productId;
    private String name;
    private int quantity;
    private BigDecimal price;
    private String currency;
}

@Getter
@Setter
class Payment {
    private String method;
    private String transactionId;
    private String status;
}