package com.example.venteproductapp;

public class ProductData {
    public String name;
    public  String price;
    public String image;
    public ProductData(String name, String price, String image) {
        this.name = name;
        this.image = image;
        this.price = price;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
