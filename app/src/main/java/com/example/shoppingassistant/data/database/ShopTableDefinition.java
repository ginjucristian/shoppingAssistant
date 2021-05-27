package com.example.shoppingassistant.data.database;

public class ShopTableDefinition {
    public static final String SHOP_TABLE_NAME = "Shop";

    public static final String SHOP_ID = "shop_id";
    public static final String SHOP_NAME = "shop_name";
    public static final String SHOP_LAT = "shop_latitude";
    public static final String SHOP_LONG = "shop_longitude";

    public static final String CREATE_TABLE_STRING = "CREATE TABLE Shop(\n" +
            "        shop_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "        shop_name UNIQUE NOT NULL,\n" +
            "        shop_latitude UNIQUE NOT NULL,\n" +
            "        shop_longitude UNIQUE NOT NULL\n" +
            ");";

    public static final String DROP_TABLE_STRING = "DROP TABLE IF EXISTS Shop;";
}
