package com.example.summarize.foodanddrink;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private static Data instance;

    private List<FoodDrinkItem> selectedFoods;
    private List<FoodDrinkItem> selectedDrinks;

    private Data() {}

    public static synchronized Data getInstance() {
        if (instance == null) {
            instance = new Data();
            instance.setSelectedFoods(new ArrayList<>());
            instance.setSelectedDrinks(new ArrayList<>());
        }
        return instance;
    }

    public List<FoodDrinkItem> getSelectedFoods() {
        return selectedFoods;
    }

    public void setSelectedFoods(List<FoodDrinkItem> selectedFoods) {
        this.selectedFoods = selectedFoods;
    }

    public List<FoodDrinkItem> getSelectedDrinks() {
        return selectedDrinks;
    }

    public void setSelectedDrinks(List<FoodDrinkItem> selectedDrinks) {
        this.selectedDrinks = selectedDrinks;
    }

    public void clear() {
        selectedFoods.clear();
        selectedDrinks.clear();
    }

    public boolean isSelectedItem(FoodDrinkItem item) {
        return (
                this.selectedFoods != null
                        && (this.selectedFoods.contains(item) || this.selectedFoods.stream().anyMatch(cur -> cur.getName().equals(item.getName()))))
                || (this.selectedDrinks != null && (this.selectedDrinks.contains(item) || this.selectedDrinks.stream().anyMatch(cur -> cur.getName().equals(item.getName()))));
    }
}
