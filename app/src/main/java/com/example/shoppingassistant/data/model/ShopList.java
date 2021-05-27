package com.example.shoppingassistant.data.model;

import java.util.ArrayList;
import java.util.List;

public class ShopList {
    private final int id;

    private final String shopListName;

    private final User shopListOwner;

    private final List<ShopItem> shopItemList;

    public ShopList(int id, String shopListName, User shopListOwner, List<ShopItem> shopItemList) {
        this.id = id;
        this.shopListName = shopListName;
        this.shopListOwner = shopListOwner;
        this.shopItemList = shopItemList;
    }

    public ShopList(int id, String shopListName, User shopListOwner) {
        this.id = id;
        this.shopListName = shopListName;
        this.shopListOwner = shopListOwner;
        this.shopItemList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getShopListName() {
        return shopListName;
    }

    public User getShopListOwner() {
        return shopListOwner;
    }

    public List<ShopItem> getShopItemList() {
        return shopItemList;
    }

    public void addProductToShopList(ShopItem item) {
        shopItemList.add(item);
    }
}
