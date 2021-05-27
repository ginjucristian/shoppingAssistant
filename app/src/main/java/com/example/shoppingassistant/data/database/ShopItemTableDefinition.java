package com.example.shoppingassistant.data.database;

public class ShopItemTableDefinition {

    public static final String SHOP_ITEM_TABLE_NAME = "ShopItem";

    public static final String SHOP_ITEM_ID = "shop_item_id";
    public static final String SHOP_ITEM_NAME = "shop_item_name";

    public static final String CREATE_TABLE_STRING = "CREATE TABLE ShopItem(\n" +
            "        shop_item_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "        shop_item_name NOT NULL\n" +
            ");";

    public static final String DROP_TABLE_STRING = "DROP TABLE IF EXISTS ShopItem;";
}
