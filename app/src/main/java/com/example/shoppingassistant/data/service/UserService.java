package com.example.shoppingassistant.data.service;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoppingassistant.data.database.AppDatabaseHelper;
import com.example.shoppingassistant.data.database.UserTableDefinition;
import com.example.shoppingassistant.data.model.User;

public class UserService {

    private final SQLiteDatabase database;

    public UserService(AppDatabaseHelper appDatabaseHelper) {
        database = appDatabaseHelper.getWritableDatabase();
    }

    public User registerUser(User user) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(UserTableDefinition.USER_EMAIL, user.getEmail());
        contentValues.put(UserTableDefinition.USER_FIRST_NAME, user.getFirstName());
        contentValues.put(UserTableDefinition.USER_LAST_NAME, user.getLastName());
        contentValues.put(UserTableDefinition.USER_PASSWORD, user.getPassword());

        long id = database.insert(UserTableDefinition.USER_TABLE_NAME, null, contentValues);

        if (id == -1) {
            return null;
        }

        return new User((int) id, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
    }

    public User getUserByEmail(String email) {

        String table = UserTableDefinition.USER_TABLE_NAME;
        String[] columns = {UserTableDefinition.USER_ID, UserTableDefinition.USER_EMAIL, UserTableDefinition.USER_PASSWORD, UserTableDefinition.USER_FIRST_NAME, UserTableDefinition.USER_LAST_NAME};
        String selection = String.format("%s = ?", UserTableDefinition.USER_EMAIL);
        String[] selectionArgs = {email};

        Cursor c  = database.query(table, columns, selection, selectionArgs, null, null, null);

        if (c.getCount() == 0) {
            c.close();
            return null;
        }

        c.moveToFirst();
        User user = new User(
                c.getInt(c.getColumnIndex(UserTableDefinition.USER_ID)),
                c.getString(c.getColumnIndex(UserTableDefinition.USER_FIRST_NAME)),
                c.getString(c.getColumnIndex(UserTableDefinition.USER_LAST_NAME)),
                c.getString(c.getColumnIndex(UserTableDefinition.USER_EMAIL)),
                c.getString(c.getColumnIndex(UserTableDefinition.USER_PASSWORD))
        );

        c.close();

        return user;
    }

    public boolean authenticate(String email, String password) {
        User user = getUserByEmail(email);

        if (user == null) {
            return false;
        }

        return user.getPassword().equals(password);
    }
}
