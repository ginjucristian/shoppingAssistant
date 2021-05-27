package com.example.shoppingassistant.data.database;

public class UserTableDefinition {
    public static final String USER_TABLE_NAME = "User";

    public static final String USER_ID = "user_id";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_FIRST_NAME = "user_first_name";
    public static final String USER_LAST_NAME = "user_last_name";
    public static final String USER_PASSWORD = "user_password";

    public static final String CREATE_TABLE_STRING = "CREATE TABLE User (\n" +
            "        user_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "        user_email TEXT UNIQUE NOT NULL,\n" +
            "        user_first_name TEXT NOT NULL,\n" +
            "        user_last_name TEXT NOT NULL,\n" +
            "        user_password TEXT\n" +
            "        );";

    public static final String DROP_TABLE_STRING = "DROP TABLE IF EXISTS User;";
}
