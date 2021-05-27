package com.example.shoppingassistant.data.model;

public class Shop {
    private final int id;

    private final String name;

    private final String lat;

    private final String lng;

    public Shop(int id, String name, String lat, String lng) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }
}
