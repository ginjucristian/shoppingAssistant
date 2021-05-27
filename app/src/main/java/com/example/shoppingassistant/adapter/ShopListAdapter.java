package com.example.shoppingassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shoppingassistant.R;
import com.example.shoppingassistant.data.model.ShopList;

import java.util.List;

public class ShopListAdapter extends ArrayAdapter<ShopList> {

    private final List<ShopList> shopLists;

    public ShopListAdapter(@NonNull Context context, int resource, List<ShopList> shopList) {
        super(context, resource);
        this.shopLists = shopList;
    }

    @Override
    public int getCount() {
        return shopLists.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shop_lists_layout, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.shop_list_name)).setText(shopLists.get(position).getShopListName());

        ((ListView) convertView.findViewById(R.id.shop_list_products)).setAdapter(
                new ShopItemListAdapter(getContext(), R.id.shop_list_products, shopLists.get(position).getShopItemList())
        );

        return convertView;
    }
}
