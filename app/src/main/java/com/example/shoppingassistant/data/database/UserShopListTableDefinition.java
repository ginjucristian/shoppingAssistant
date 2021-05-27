package com.example.shoppingassistant.data.database;

public class UserShopListTableDefinition {

    public static final String USER_SHOP_LIST_TABLE_NAME = "UserShopList";

    public static final String USER_SHOP_LIST_ID = "user_shop_list_id";
    public static final String USER_SHOP_LIST_USER_ID = "user_shop_list_user_id";
    public static final String USER_SHOP_LIST_SHOP_LIST_ID = "user_shop_list_shop_list_id";

    public static final String CREATE_TABLE_STRING = "CREATE TABLE UserShopList(\n" +
            "        user_shop_list_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "        user_shop_list_user_id INTEGER NOT NULL,\n" +
            "        user_shop_list_shop_list_id INTEGER NOT NULL\n" +
            ");\n";

    public static final String DROP_TABLE_STRING = "DROP TABLE IF EXISTS UserShopList";
}
