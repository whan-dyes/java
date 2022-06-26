package com.whan.wlistview;

public class Fruit {
    private  int imageID;
    private   String name;
    private    String price;

    public Fruit(int imageID, String name, String price) {
        this.imageID = imageID;
        this.name = name;
        this.price = price;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
