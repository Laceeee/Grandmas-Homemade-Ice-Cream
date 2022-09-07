package com.laci.grandmasicecream;

public class ExtrasModel {
    private boolean required;
    private String type;
    private long id;
    private String name;
    private double price;

    public ExtrasModel(boolean required, String type, long id, String name, double price) {
        this.required = required;
        this.type = type;
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public boolean isRequired() {
        return required;
    }

    public String getType() {
        return type;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
