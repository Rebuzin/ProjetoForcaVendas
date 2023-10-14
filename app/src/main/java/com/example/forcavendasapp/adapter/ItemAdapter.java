package com.example.forcavendasapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.forcavendasapp.model.Item;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    public ItemAdapter(Context context, List<Item> items) {
        super(context, android.R.layout.simple_spinner_item, items);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        Item item = getItem(position);

        if (item != null) {
            TextView text = view.findViewById(android.R.id.text1);
            text.setText(item.getDescricao());
        }

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);

        Item item = getItem(position);

        if (item != null) {
            TextView text = view.findViewById(android.R.id.text1);
            text.setText(item.getDescricao());
        }

        return view;
    }
}

