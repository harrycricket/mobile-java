package com.example.foodanddrink;

public class FoodDrinkItem {
    private String name;
    private String desc;
    private int price;
    private int avtImage;

    public FoodDrinkItem(String name, String desc, int price, int avtImage) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.avtImage = avtImage;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getPrice() {
        return price;
    }

    public int getAvtImage() {
        return avtImage;
    }
}
