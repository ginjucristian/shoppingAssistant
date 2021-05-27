package com.example.shoppingassistant.data.service;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoppingassistant.data.database.AppDatabaseHelper;
import com.example.shoppingassistant.data.database.ShopItemTableDefinition;
import com.example.shoppingassistant.data.database.ShopListItemTableDefinition;
import com.example.shoppingassistant.data.database.ShopListTableDefinition;
import com.example.shoppingassistant.data.database.ShopTableDefinition;
import com.example.shoppingassistant.data.database.UserShopListTableDefinition;
import com.example.shoppingassistant.data.model.Shop;
import com.example.shoppingassistant.data.model.ShopItem;
import com.example.shoppingassistant.data.model.ShopList;
import com.example.shoppingassistant.data.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopListService {

    private final SQLiteDatabase database;

    public ShopListService(AppDatabaseHelper appDatabaseHelper) {
        database = appDatabaseHelper.getWritableDatabase();
    }

    public ShopList createShopList(ShopList shopList) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ShopListTableDefinition.SHOP_LIST_NAME, shopList.getShopListName());

        long id = database.insert(ShopListTableDefinition.SHOP_LIST_TABLE_NAME, null, contentValues);

        if (id == -1) {
            return null;
        }

        ShopList list = new ShopList((int) id, shopList.getShopListName(), shopList.getShopListOwner(), shopList.getShopItemList());

        if (!assignShopListToUser(list, shopList.getShopListOwner())) {
            return null;
        }

        for (ShopItem shopItem : shopList.getShopItemList()) {
            assignItemToShopList(list, shopItem);
        }

        return list;
    }


    private boolean assignShopListToUser(ShopList shopList, User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserShopListTableDefinition.USER_SHOP_LIST_SHOP_LIST_ID, shopList.getId());
        contentValues.put(UserShopListTableDefinition.USER_SHOP_LIST_USER_ID, user.getId());

        long id = database.insert(UserShopListTableDefinition.USER_SHOP_LIST_TABLE_NAME, null, contentValues);

        return id != -1;
    }

    private boolean assignItemToShopList(ShopList shopList, ShopItem shopItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ShopListItemTableDefinition.SHOP_LIST_ITEM_SHOP_LIST_ID, shopList.getId());
        contentValues.put(ShopListItemTableDefinition.SHOP_LIST_ITEM_SHOP_ITEM_ID, shopItem.getId());

        long id = database.insert(ShopListItemTableDefinition.SHOP_LIST_ITEM_TABLE_NAME, null, contentValues);

        return id != -1;
    }

    public List<ShopList> getAllShopLists(User user) {
        String rawQuery = "SELECT * FROM ShopList \n" +
                "JOIN UserShopList on ShopList.shop_list_id = UserShopList.user_shop_list_id\n" +
                "JOIN User ON UserShopList.user_shop_list_user_id = User.user_id\n" +
                "JOIN ShopListItem ON ShopListItem.shop_list_item_shop_shop_list_id = ShopList.shop_list_id\n" +
                "JOIN ShopItem ON ShopListItem.shop_list_item_shop_item_id = ShopItem.shop_item_id\n" +
                "GROUP BY ShopItem.shop_item_id\n" +
                "HAVING User.user_email = ?";
        String[] args = {user.getEmail()};

        Cursor c = database.rawQuery(rawQuery, args);

        Map<Integer, ShopList> resultsMap = new HashMap<>();

        while (c.moveToNext()) {
            int shopListId = c.getInt(c.getColumnIndex(ShopListTableDefinition.SHOP_LIST_ID));
            String shopListName = c.getString(c.getColumnIndex(ShopListTableDefinition.SHOP_LIST_NAME));

            if (resultsMap.get(shopListId) == null) {
                resultsMap.put(shopListId, new ShopList(
                    shopListId, shopListName, user, new ArrayList<>()
                ));
            }

            ShopList shopList = resultsMap.get(shopListId);

            shopList.addProductToShopList(
                    new ShopItem(
                            c.getInt(c.getColumnIndex(ShopItemTableDefinition.SHOP_ITEM_ID)),
                            c.getString(c.getColumnIndex(ShopItemTableDefinition.SHOP_ITEM_NAME)),
                            new ArrayList<>()
                    )
            );
        }

        List<ShopList> shopLists = new ArrayList<>();

        for (Integer key : resultsMap.keySet()) {
            shopLists.add(resultsMap.get(key));
        }

        return shopLists;
    }

}
