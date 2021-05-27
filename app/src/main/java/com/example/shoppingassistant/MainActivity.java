package com.example.shoppingassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shoppingassistant.data.database.AppDatabaseHelper;
import com.example.shoppingassistant.data.model.Shop;
import com.example.shoppingassistant.data.model.User;
import com.example.shoppingassistant.data.service.CategoryService;
import com.example.shoppingassistant.data.service.ShopItemService;
import com.example.shoppingassistant.data.service.ShopListService;
import com.example.shoppingassistant.data.service.UserService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private UserService userService;
    private CategoryService categoryService;
    private ShopListService shopListService;

    private User loggedInUser;
    private ShopItemService shopItemService;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Dashboard");
        loadBottomNavigationView();

        AppDatabaseHelper appDatabaseHelper = new AppDatabaseHelper(getApplicationContext());
        userService = new UserService(appDatabaseHelper);
        categoryService = new CategoryService(appDatabaseHelper);
        shopListService = new ShopListService(appDatabaseHelper);
        shopItemService = new ShopItemService(appDatabaseHelper);


        try {
            categoryService.loadDefaultCategories();
        } catch (Exception e) {

        }


        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            loggedInUser = userService.getUserByEmail(intent.getExtras().getString("user_email"));
        }

        TextView welcomeMessageView = (TextView) findViewById(R.id.main_view_welcome_message);
        welcomeMessageView.setText(String.format("Welcome %s", loggedInUser.getEmail()));

        Integer content = shopListService.getAllShopLists(loggedInUser).size();

        TextView shopListsTextView = (TextView) findViewById(R.id.shop_list_text);
        shopListsTextView.setText(String.format("You have %d shop lists!", content));

        TextView shopItemsText = (TextView) findViewById(R.id.shop_item_text);
        shopItemsText.setText(String.format("You have %d shop items!", shopItemService.getShopItemCount()));
        findViewById(R.id.open_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMaps();
            }
        });

        findViewById(R.id.shop_list_data_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itemShopList = new Intent(getApplicationContext(), ShopListActivity.class);
                itemShopList.putExtra("user_email", loggedInUser.getEmail());
                startActivity(itemShopList);
            }
        });

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

    private void openMaps() {
        Uri gmmIntentUri = Uri.parse("google.streetview:cbll=47.1736681,27.5341151");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}