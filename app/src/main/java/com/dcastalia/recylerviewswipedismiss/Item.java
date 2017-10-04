package com.dcastalia.recylerviewswipedismiss;

/**
 * Created by piashsarker on 10/4/17.
 */

public class Item {

    int id;
    String name;
    String description;
    double price;
    int thumbnail;

    public Item(int id, String name, String description, double price, int thumbnail) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.thumbnail = thumbnail;
    }

    public Item() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

}
