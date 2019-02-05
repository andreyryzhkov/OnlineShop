package com.aryzhkov.onlineshop.entity;

import java.util.Date;

public class Product {
    private int id;
    private String name;
    private double price;
    private Date dateMaking;

    public Product(){

    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDateMaking(Date dateMaking) {
        this.dateMaking = dateMaking;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Date getDateMaking() {
        return dateMaking;
    }
}
