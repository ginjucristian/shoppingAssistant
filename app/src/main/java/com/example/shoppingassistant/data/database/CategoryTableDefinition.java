package com.example.shoppingassistant.data.database;

public class CategoryTableDefinition {

    public static final String CATEGORY_TABLE_NAME = "Category";

    public static final String CATEGORY_ID = "category_id";
    public static final String CATEGORY_NAME = "category_name";

    public static final String CREATE_TABLE_STRING = "CREATE TABLE Category(\n" +
            "        category_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "        category_name UNIQUE NOT NULL\n" +
            ");";

    public static final String DROP_TABLE_STRING = "DROP TABLE IF EXISTS Category;";
}
