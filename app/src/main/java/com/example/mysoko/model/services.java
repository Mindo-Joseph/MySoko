package com.example.mysoko.model;

public class services {
    public int productImage;
    public String productName;
    public String productPublisher;
    public String timefromlocation;


    public services(String productName, int productImage, String productPublisher, String timefromlocation) {
        this.productImage = productImage;
        this.productName = productName;
        this.productPublisher = productPublisher;
        this.timefromlocation = timefromlocation;


    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;

    }

    public String getProductPublisher() {
        return productPublisher;
    }

    public void setProductPublisher(String productPublisher) {
        this.productPublisher = productPublisher;
    }

    public String getTimefromlocation() {
        return timefromlocation;
    }

    public void setTimefromlocation(String timefromlocation) {
        this.timefromlocation = timefromlocation;
    }
}
