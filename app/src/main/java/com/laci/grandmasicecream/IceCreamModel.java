package com.laci.grandmasicecream;

public class IceCreamModel {
    private double price;
    private int id;
    private String name;
    private String status;
    private String imageUrl;

    public IceCreamModel(double price, int id, String name, String status, String imageUrl) {
        this.price = price;
        this.id = id;
        this.name = name;
        this.status = status;
        this.imageUrl = imageUrl;
    }


    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
