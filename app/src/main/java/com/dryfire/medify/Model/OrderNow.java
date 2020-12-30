package com.dryfire.medify.Model;

import java.io.Serializable;

public class OrderNow implements Serializable {

    private String restaurantName;
    private String item_name;
    private float price;
    private int foodImage;

    public OrderNow(String restaurantName, String item_name, float price, int foodImage) {
        this.restaurantName = restaurantName;
        this.item_name = item_name;
        this.price = price;
        this.foodImage = foodImage;
    }

    public int getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(int foodImage) {
        this.foodImage = foodImage;
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
