package com.example.shoppingassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shoppingassistant.data.database.AppDatabaseHelper;
import com.example.shoppingassistant.data.model.Shop;
import com.example.shoppingassistant.data.model.User;
import com.example.shoppingassistant.data.service.ShopService;
import com.example.shoppingassistant.data.service.UserService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddShopActivity extends AppCompatActivity {

    private User loggedInUser;

    private UserService userService;
    private ShopService shopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);

        userService = new UserService(new AppDatabaseHelper(getApplicationContext()));
        shopService = new ShopService(new AppDatabaseHelper(getApplicationContext()));

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            loggedInUser = userService.getUserByEmail(intent.getExtras().getString("user_email"));
        }

        Button addButtonActionView = (Button) findViewById(R.id.add_shop_btn);

        addButtonActionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((TextView) findViewById(R.id.shop_name)).getText().toString();
                String lat = ((TextView) findViewById(R.id.shop_lat)).getText().toString();
                String lng = ((TextView) findViewById(R.id.shop_long)).getText().toString();

                shopService.insertShop(new Shop(0, name, lat, lng));

                onBackPressed();
            }
        });

        loadBottomNavigationView();
    }

    private void loadBottomNavigationView() {
        BottomNavigationView view = (BottomNavigationView) findViewById(R.id.bottom_menu);
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_item_dashboard:
                        Intent dashboardAct = new Intent(getApplicationContext(), MainActivity.class);
                        dashboardAct.putExtra("user_email", loggedInUser.getEmail());
                        startActivity(dashboardAct);
                        break;

                    case R.id.menu_item_shop_lists:
                        Intent itemShopList = new Intent(getApplicationContext(), EditShopListActivity.class);
                        itemShopList.putExtra("user_email", loggedInUser.getEmail());
                        startActivity(itemShopList);
                        break;

                    case R.id.menu_shops:
                        break;
                }

                return true;
            }
        });
    }
}