package com.example.shoppingassistant.data.service;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoppingassistant.data.database.AppDatabaseHelper;
import com.example.shoppingassistant.data.database.ShopItemCategoryTableDefinition;
import com.example.shoppingassistant.data.database.ShopItemTableDefinition;
import com.example.shoppingassistant.data.model.Category;
import com.example.shoppingassistant.data.model.ShopItem;

public class ShopItemService {

    private final SQLiteDatabase database;

    public ShopItemService(AppDatabaseHelper appDatabaseHelper) {
        database = appDatabaseHelper.getWritableDatabase();
    }

    public int getShopItemCount() {
        String query = "SELECT COUNT(*) AS count FROM ShopItem;";

        Cursor c = database.rawQuery(query, null);

        if (c.getCount() == 0) {
            return 0;
        }

        c.moveToFirst();

        int rez = c.getInt(0);

        c.close();

        return rez;
    }

    public ShopItem addShopItem(ShopItem shopItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ShopItemTableDefinition.SHOP_ITEM_NAME, shopItem.getItemName());

        long id = database.insert(ShopItemTableDefinition.SHOP_ITEM_TABLE_NAME, null, contentValues);

        if (id == -1) {
            return null;
        }

        ShopItem result = new ShopItem((int) id, shopItem.getItemName(), shopItem.getCategories());

        for (Category category : shopItem.getCategories()) {
            addShopItemCategory(result, category);
        }

        return result;
    }

    public boolean addShopItemCategory(ShopItem shopItem, Category category) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ShopItemCategoryTableDefinition.SHOP_ITEM_CATEGORY_ITEM_ID, shopItem.getId());
        contentValues.put(ShopItemCategoryTableDefinition.SHOP_ITEM_CATEGORY_CATEGORY_ID, category.getId());

        long id = database.insert(ShopItemCategoryTableDefinition.SHOP_ITEM_CATEGORY_TABLE_NAME, null, contentValues);

        return id != -1;
    }
}
