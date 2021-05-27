package com.example.shoppingassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shoppingassistant.adapter.CategoryCheckboxListAdapter;
import com.example.shoppingassistant.adapter.ShopItemListAdapter;
import com.example.shoppingassistant.data.database.AppDatabaseHelper;
import com.example.shoppingassistant.data.model.Category;
import com.example.shoppingassistant.data.model.ShopItem;
import com.example.shoppingassistant.data.model.ShopList;
import com.example.shoppingassistant.data.model.User;
import com.example.shoppingassistant.data.service.CategoryService;
import com.example.shoppingassistant.data.service.ShopItemService;
import com.example.shoppingassistant.data.service.ShopListService;
import com.example.shoppingassistant.data.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EditShopListActivity extends AppCompatActivity {

    private CategoryService categoryService;

    private ShopItemService shopItemService;

    private ShopListService shopListService;

    private UserService userService;

    private List<Category> categoryList;

    private List<ShopItem> shopItemList;

    private Set<Integer> selectedItems;

    private ShopItemListAdapter shopItemListAdapter;

    private User loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shop_list);

        AppDatabaseHelper appDatabaseHelper = new AppDatabaseHelper(getApplicationContext());
        categoryService = new CategoryService(appDatabaseHelper);
        shopItemService = new ShopItemService(appDatabaseHelper);
        shopListService = new ShopListService(appDatabaseHelper);
        userService = new UserService(appDatabaseHelper);

        selectedItems = new HashSet<>();
        shopItemList = new ArrayList<>();

        loadUserData();
        loadCategories();
        loadCheckboxListView();
        loadItemView();
        loadShopListView();

        Button saveShopListActionView = (Button) findViewById(R.id.add_list);
        saveShopListActionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView shopListName = (TextView) findViewById(R.id.shop_list_name);
                ShopList shopList = new ShopList(0, shopListName.getText().toString(), loggedInUser, shopItemList);

                shopListService.createShopList(shopList);
            }
        });
    }

    private void loadUserData() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            loggedInUser = userService.getUserByEmail(intent.getExtras().getString("user_email"));
        }
    }

    private void loadItemView() {
        EditText itemName = (EditText)  findViewById(R.id.shop_list_new_item_name);

        Button addItemActionButton = (Button) findViewById(R.id.add_item);
        addItemActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopItem shopItem = new ShopItem(0, itemName.getText().toString(), new ArrayList<>());
                for (Integer position : selectedItems) {
                    shopItem.addCategory(categoryList.get(position));
                }

                shopItem = shopItemService.addShopItem(shopItem);

                shopItemList.add(shopItem);
                shopItemListAdapter.updateShopItemList(shopItemList);
                shopItemListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void loadCheckboxListView() {
        ListView checkBoxListView = (ListView) findViewById(R.id.category_checkboxes_list);
        checkBoxListView.setAdapter(
                new CategoryCheckboxListAdapter(getApplicationContext(), R.id.category_checkboxes_list, categoryList)
        );
        checkBoxListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (selectedItems.contains(position)) {
                    selectedItems.remove(position);
                    TextView categoryText = (TextView) view.findViewById(R.id.item_title);
                    categoryText.setTextColor(Color.BLACK);
                } else {
                    selectedItems.add(position);
                    TextView categoryText = (TextView) view.findViewById(R.id.item_title);
                    categoryText.setTextColor(Color.RED);
                }
            }
        });
    }

    private void loadCategories() {
        this.categoryList = categoryService.getAllCategories();
        if (categoryList == null) {
            categoryList = new ArrayList<>();
        }
    }

    private void loadShopListView() {
        ListView checkBoxListView = (ListView) findViewById(R.id.shop_items);
        shopItemListAdapter = new ShopItemListAdapter(getApplicationContext(), R.id.shop_items, shopItemList);
        checkBoxListView.setAdapter(shopItemListAdapter);
    }
}