package com.dryfire.medify.Model;

public class OrderNow {

    private String restaurantName;
    private String item_name;
    private float price;

    public OrderNow(String restaurantName, String item_name, float price) {
        this.restaurantName = restaurantName;
        this.item_name = item_name;
        this.price = price;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
