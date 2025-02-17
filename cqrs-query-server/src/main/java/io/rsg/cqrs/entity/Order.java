package io.rsg.cqrs.entity;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(indexName = "orders")



public class Order {

    @Id
    private String orderId;
    private Customer customer;
    private List<OrderItem> items;
    private BigDecimal totalAmount;
    private String currency;
    private Payment payment;
    private String status;

    @Field(type = FieldType.Date)
    private Instant orderDate;
    @Field(type = FieldType.Date)
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