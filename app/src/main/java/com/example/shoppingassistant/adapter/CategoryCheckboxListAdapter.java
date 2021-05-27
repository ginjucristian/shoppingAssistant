package com.example.shoppingassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shoppingassistant.R;
import com.example.shoppingassistant.data.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryCheckboxListAdapter extends ArrayAdapter<Category> {

    private final List<Category> categoryList;

    public CategoryCheckboxListAdapter(@NonNull Context context, int resource, List<Category> categoryList) {
        super(context, resource);
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_check_box_item, parent, false);
        }

        TextView categoryText = (TextView) convertView.findViewById(R.id.item_title);
        categoryText.setText(categoryList.get(position).getName());

        return convertView;
    }
}
