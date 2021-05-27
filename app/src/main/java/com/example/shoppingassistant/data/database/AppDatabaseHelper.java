package com.example.shoppingassistant.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class AppDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ShoppingAssistantDatabase";
    private static final int DATABASE_VERSION = 12;

    public AppDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserTableDefinition.CREATE_TABLE_STRING);
        db.execSQL(CategoryTableDefinition.CREATE_TABLE_STRING);
        db.execSQL(ShopItemTableDefinition.CREATE_TABLE_STRING);
        db.execSQL(ShopListTableDefinition.CREATE_TABLE_STRING);
        db.execSQL(ShopListItemTableDefinition.CREATE_TABLE_STRING);
        db.execSQL(UserShopListTableDefinition.CREATE_TABLE_STRING);
        db.execSQL(ShopItemCategoryTableDefinition.CREATE_TABLE_STRING);
        db.execSQL(ShopTableDefinition.CREATE_TABLE_STRING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(UserTableDefinition.DROP_TABLE_STRING);
        db.execSQL(CategoryTableDefinition.DROP_TABLE_STRING);
        db.execSQL(ShopItemTableDefinition.DROP_TABLE_STRING);
        db.execSQL(ShopListTableDefinition.DROP_TABLE_STRING);
        db.execSQL(ShopListItemTableDefinition.DROP_TABLE_STRING);
        db.execSQL(UserShopListTableDefinition.DROP_TABLE_STRING);
        db.execSQL(ShopItemCategoryTableDefinition.DROP_TABLE_STRING);
        db.execSQL(ShopTableDefinition.DROP_TABLE_STRING);

        db.execSQL(UserTableDefinition.CREATE_TABLE_STRING);
        db.execSQL(CategoryTableDefinition.CREATE_TABLE_STRING);
        db.execSQL(ShopItemTableDefinition.CREATE_TABLE_STRING);
        db.execSQL(ShopListTableDefinition.CREATE_TABLE_STRING);
        db.execSQL(ShopListItemTableDefinition.CREATE_TABLE_STRING);
        db.execSQL(UserShopListTableDefinition.CREATE_TABLE_STRING);
        db.execSQL(ShopItemCategoryTableDefinition.CREATE_TABLE_STRING);
        db.execSQL(ShopTableDefinition.CREATE_TABLE_STRING);
    }
}
