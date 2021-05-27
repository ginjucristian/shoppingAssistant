package com.example.shoppingassistant.data.service;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoppingassistant.data.database.AppDatabaseHelper;
import com.example.shoppingassistant.data.database.CategoryTableDefinition;
import com.example.shoppingassistant.data.model.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryService {

    private final SQLiteDatabase database;

    public CategoryService(AppDatabaseHelper appDatabaseHelper) {
        database = appDatabaseHelper.getWritableDatabase();
    }

    public List<Category> getAllCategories() {
        String table = CategoryTableDefinition.CATEGORY_TABLE_NAME;
        String[] columns = {CategoryTableDefinition.CATEGORY_ID, CategoryTableDefinition.CATEGORY_NAME};

        Cursor c = database.query(table, columns, null, null, null, null, null);

        if (c.getCount() == 0) {
            c.close();
            return new ArrayList<>();
        }

        List<Category> categories = new ArrayList<>();

        while (c.moveToNext()) {
            categories.add(
                    new Category(
                            c.getInt(c.getColumnIndex(CategoryTableDefinition.CATEGORY_ID)),
                            c.getString(c.getColumnIndex(CategoryTableDefinition.CATEGORY_NAME))
                    )
            );
        }

        c.close();
        return categories;
    }

    public Category insertCategory(Category category) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoryTableDefinition.CATEGORY_NAME, category.getName());

        long id = database.insert(CategoryTableDefinition.CATEGORY_TABLE_NAME, null, contentValues);

        if (id == -1) {
            return null;
        }

        return new Category((int) id, category.getName());
    }

    public void loadDefaultCategories() {
        String[] defaultCategoryList = {"aliments", "clothes", "electronics", "gadgets"};

        for (String category : defaultCategoryList) {
            insertCategory(new Category(0, category));
        }
    }
}
