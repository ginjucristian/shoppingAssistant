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
import com.example.shoppingassistant.data.model.ShopItem;

import java.util.List;

public class ShopItemListAdapter extends ArrayAdapter<ShopItem> {

    private List<ShopItem> shopItemList;

    public ShopItemListAdapter(@NonNull Context context, int resource, List<ShopItem> shopItemList) {
        super(context, resource);
        this.shopItemList = shopItemList;
    }

    @Override
    public int getCount() {
        return shopItemList.size();
    }

    public void updateShopItemList(List<ShopItem> shopItems) {
        shopItemList = shopItems;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shop_item_layout, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.shop_item_name);
        textView.setText(shopItemList.get(position).getItemName());

        return convertView;
    }
}
