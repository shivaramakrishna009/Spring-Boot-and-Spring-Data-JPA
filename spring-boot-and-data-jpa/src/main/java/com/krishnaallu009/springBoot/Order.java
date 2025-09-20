package com.krishnaallu009.springBoot;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
    @JsonProperty("order_id")
    private int id;
    @JsonProperty("order_name")
    private String name;
    @JsonProperty("order_price")
    private double price;
    @JsonProperty("order_quantity")
    private int quantity;

    public Order() {
    }

    public Order(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

/*
json example:
{
    "order_id": 1,
    "order_name": "Product 1",
    "order_price": 100.0,
    "order_quantity": 2

 */
