package com.example.forcavendasapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.forcavendasapp.model.ItemPedido;

import java.util.List;

public class ItemPedidoAdapter extends ArrayAdapter<ItemPedido> {
    public ItemPedidoAdapter(Context context, List<ItemPedido> itemPedidos) {
        super(context, 0, itemPedidos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemPedido itemPedido = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView text1 = convertView.findViewById(android.R.id.text1);
        text1.setText(getDisplayText(itemPedido));

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        ItemPedido itemPedido = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView text1 = convertView.findViewById(android.R.id.text1);
        text1.setText(getDisplayText(itemPedido));

        return convertView;
    }

    private String getDisplayText(ItemPedido itemPedido) {
        return itemPedido.getDesc() + " - Quantidade: " + itemPedido.getQuantidade();
    }
}
