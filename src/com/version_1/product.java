package com.version_1;

import java.io.Serializable;

public class product extends item implements Serializable {

    private int quantity;
    private double price;
    private String category;

    public product(String name) {
        super(name);
        quantity = 0;
        price = 0.0;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPath(){
        return getCategory() + " > " + getName();
    }

}