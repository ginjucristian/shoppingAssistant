package com.example.shoppingassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.shoppingassistant.adapter.ShopAdapter;
import com.example.shoppingassistant.data.database.AppDatabaseHelper;
import com.example.shoppingassistant.data.model.Shop;
import com.example.shoppingassistant.data.model.User;
import com.example.shoppingassistant.data.service.ShopService;
import com.example.shoppingassistant.data.service.UserService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ShopsActivity extends AppCompatActivity {

    UserService userService;

    ShopService shopService;

    User loggedInUser;

    List<Shop> shopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        userService = new UserService(new AppDatabaseHelper(getApplicationContext()));

        shopService = new ShopService(new AppDatabaseHelper(getApplicationContext()));

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            loggedInUser = userService.getUserByEmail(intent.getExtras().getString("user_email"));
        }

        Button addNewShopButton = (Button) findViewById(R.id.add_new_shop_btn);
        addNewShopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shopsActivity = new Intent(getApplicationContext(), AddShopActivity.class);
                shopsActivity.putExtra("user_email", loggedInUser.getEmail());
                startActivity(shopsActivity);
            }
        });

        shopList = shopService.getAllShops();

        ListView shopListView = (ListView) findViewById(R.id.market_list);
        shopListView.setAdapter(new ShopAdapter(getApplicationContext(), R.id.shop_list_list_view, shopList));

        shopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openMaps(shopList.get(position));
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


    private void openMaps(Shop shop) {
        Uri gmmIntentUri = Uri.parse(String.format("google.streetview:cbll=%s,%s", shop.getLat(), shop.getLng()));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}