package com.example.shoppingassistant.data.database;

public class ShopListItemTableDefinition {
    public static final String SHOP_LIST_ITEM_TABLE_NAME = "ShopListItem";

    public static final String SHOP_LIST_ITEM_ID = "shop_list_item_shop_id";
    public static final String SHOP_LIST_ITEM_SHOP_LIST_ID = "shop_list_item_shop_shop_list_id";
    public static final String SHOP_LIST_ITEM_SHOP_ITEM_ID = "shop_list_item_shop_item_id";

    public static final String CREATE_TABLE_STRING = "CREATE TABLE ShopListItem(\n" +
            "        shop_list_item_shop_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "        shop_list_item_shop_shop_list_id INTEGER NOT NULL,\n" +
            "        shop_list_item_shop_item_id INTEGER NOT NULL\n" +
            ");";

    public static final String DROP_TABLE_STRING = "DROP TABLE IF EXISTS ShopListItem;";
}
