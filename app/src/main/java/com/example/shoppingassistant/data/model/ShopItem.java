package com.example.shoppingassistant.data.model;

import java.util.List;

public class ShopItem {

    private final int id;

    private final List<Category> categories;

    private final String itemName;

    public ShopItem(int id, String itemName, List<Category> categories) {
        this.id = id;
        this.categories = categories;
        this.itemName = itemName;
    }

    public int getId() {
        return id;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public String getItemName() {
        return itemName;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }
}
