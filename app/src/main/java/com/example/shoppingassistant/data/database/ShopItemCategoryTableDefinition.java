package com.example.shoppingassistant.data.database;

public class ShopItemCategoryTableDefinition {
    public static final String SHOP_ITEM_CATEGORY_TABLE_NAME = "ShopItemCategory";

    public static final String SHOP_ITEM_CATEGORY_ID = "shop_item_category_id";
    public static final String SHOP_ITEM_CATEGORY_ITEM_ID = "shop_item_category_item_id";
    public static final String SHOP_ITEM_CATEGORY_CATEGORY_ID = "shop_item_category_category_id";

    public static final String CREATE_TABLE_STRING = "CREATE TABLE ShopItemCategory(\n" +
            "        shop_item_category_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "        shop_item_category_item_id INTEGER NOT NULL,\n" +
            "        shop_item_category_category_id INTEGER NOT NULL\n" +
            ");";


    public static final String DROP_TABLE_STRING = "DROP TABLE IF EXISTS ShopItemCategory";
}
