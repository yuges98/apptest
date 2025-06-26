package com.s23010743.tatyrootsfinal;

import java.io.Serializable; // Implement Serializable to pass objects via Intent

public class FoodItem implements Serializable {
    private String name;
    private String price;
    private int imageResId; // Resource ID for the drawable image
    private String description; // Detailed description of the food item

    public FoodItem(String name, String price, int imageResId, String description) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.description = description;
    }

    // Getters for all properties
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }
}
