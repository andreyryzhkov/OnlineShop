package com.aryzhkov.onlineshop.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Product {
    private int id;
    private String name;
    private double price;
    private LocalDate dateMaking;

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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", dateMaking=" + dateMaking +
                '}';
    }
}
