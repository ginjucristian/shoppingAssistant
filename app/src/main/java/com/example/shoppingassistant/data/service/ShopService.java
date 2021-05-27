package com.example.shoppingassistant.data.service;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoppingassistant.data.database.AppDatabaseHelper;
import com.example.shoppingassistant.data.database.CategoryTableDefinition;
import com.example.shoppingassistant.data.database.ShopItemCategoryTableDefinition;
import com.example.shoppingassistant.data.database.ShopTableDefinition;
import com.example.shoppingassistant.data.model.Category;
import com.example.shoppingassistant.data.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class ShopService {

    private SQLiteDatabase database;

    public ShopService(AppDatabaseHelper appDatabaseHelper) {
        database = appDatabaseHelper.getWritableDatabase();
    }

    public List<Shop> getAllShops() {
        String table = ShopTableDefinition.SHOP_TABLE_NAME;
        String[] columns = {ShopTableDefinition.SHOP_ID, ShopTableDefinition.SHOP_NAME, ShopTableDefinition.SHOP_LONG, ShopTableDefinition.SHOP_LAT};

        Cursor c = database.query(table, columns, null, null, null, null, null);

        if (c.getCount() == 0) {
            c.close();
            return new ArrayList<>();
        }

        List<Shop> shops = new ArrayList<>();

        while (c.moveToNext()) {
            shops.add(
                    new Shop(
                            c.getInt(c.getColumnIndex(ShopTableDefinition.SHOP_ID)),
                            c.getString(c.getColumnIndex(ShopTableDefinition.SHOP_NAME)),
                            c.getString(c.getColumnIndex(ShopTableDefinition.SHOP_LAT)),
                            c.getString(c.getColumnIndex(ShopTableDefinition.SHOP_LONG))
                    )
            );
        }

        c.close();
        return shops;
    }


    public Shop insertShop(Shop shop) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ShopTableDefinition.SHOP_NAME, shop.getName());
        contentValues.put(ShopTableDefinition.SHOP_LAT, shop.getLat());
        contentValues.put(ShopTableDefinition.SHOP_LONG, shop.getLng());

        long id = database.insert(ShopTableDefinition.SHOP_TABLE_NAME, null, contentValues);

        if (id == -1) {
            return null;
        }

        return new Shop((int) id, shop.getName(), shop.getLat(), shop.getLng());
    }

}
