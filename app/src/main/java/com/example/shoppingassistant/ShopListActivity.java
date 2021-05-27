package com.example.shoppingassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.shoppingassistant.adapter.ShopAdapter;
import com.example.shoppingassistant.adapter.ShopListAdapter;
import com.example.shoppingassistant.data.database.AppDatabaseHelper;
import com.example.shoppingassistant.data.model.ShopList;
import com.example.shoppingassistant.data.model.User;
import com.example.shoppingassistant.data.service.ShopListService;
import com.example.shoppingassistant.data.service.ShopService;
import com.example.shoppingassistant.data.service.UserService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ShopListActivity extends AppCompatActivity {
    private User loggedInUser;

    private UserService userService;
    private ShopListService shopListService;

    List<ShopList> shopLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);


        userService = new UserService(new AppDatabaseHelper(getApplicationContext()));
        shopListService = new ShopListService(new AppDatabaseHelper(getApplicationContext()));

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            loggedInUser = userService.getUserByEmail(intent.getExtras().getString("user_email"));
        }

        shopLists = shopListService.getAllShopLists(loggedInUser);

        ((ListView) findViewById(R.id.shop_list_list_view))
                .setAdapter(
                        new ShopListAdapter(getApplicationContext(), R.id.shop_list_list_view, shopLists)
                );
    }

    private void loadBottomNavigationView() {
        BottomNavigationView view = (BottomNavigationView) findViewById(R.id.bottom_menu);
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_item_dashboard:
                        break;

                    case R.id.menu_item_shop_lists:
                        Intent itemShopList = new Intent(getApplicationContext(), EditShopListActivity.class);
                        itemShopList.putExtra("user_email", loggedInUser.getEmail());
                        startActivity(itemShopList);
                        break;

                    case R.id.menu_shops:
                        Intent shopsActivity = new Intent(getApplicationContext(), ShopsActivity.class);
                        shopsActivity.putExtra("user_email", loggedInUser.getEmail());
                        startActivity(shopsActivity);
                        break;
                }

                return true;
            }
        });
    }

}