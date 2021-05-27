package com.example.shoppingassistant.data.database;

public class ShopListTableDefinition {
    public static final String SHOP_LIST_TABLE_NAME = "ShopList";

    public static final String SHOP_LIST_ID = "shop_list_id";
    public static final String SHOP_LIST_NAME = "shop_list_name";

    public static final String CREATE_TABLE_STRING = "CREATE TABLE ShopList(\n" +
            "        shop_list_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "        shop_list_name TEXT NOT NULL\n" +
            ");";

    public static final String DROP_TABLE_STRING = "DROP TABLE IF EXISTS ShopList;";
}
