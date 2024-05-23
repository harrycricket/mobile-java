package com.example.fruitlist;

public class FruitItem {
    private String name;
    private String description;
    private String fruitUrl;

    public FruitItem(String name, String description, String fruitUrl) {
        this.name = name;
        this.description = description;
        this.fruitUrl = fruitUrl;
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

    public String getFruitUrl() {
        return fruitUrl;
    }

    public void setFruitUrl(String fruitUrl) {
        this.fruitUrl = fruitUrl;
    }
}