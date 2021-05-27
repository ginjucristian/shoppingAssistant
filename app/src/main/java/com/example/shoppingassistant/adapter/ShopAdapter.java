package com.example.shoppingassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shoppingassistant.R;
import com.example.shoppingassistant.data.model.Shop;

import java.util.List;

public class ShopAdapter extends ArrayAdapter<Shop> {

    private final List<Shop> shopList;

    public ShopAdapter(@NonNull Context context, int resource, List<Shop> shopList) {
        super(context, resource);
        this.shopList = shopList;
    }

    @Override
    public int getCount() {
        return shopList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_shop_view, parent, false);
        }

        TextView shopNameText = (TextView) convertView.findViewById(R.id.shop_name);
        shopNameText.setText(shopList.get(position).getName());

        TextView coordinates = (TextView) convertView.findViewById(R.id.shop_coordinates);
        coordinates.setText(String.format("%s %s", shopList.get(position).getLat(), shopList.get(position).getLng()));

        return convertView;
    }
}