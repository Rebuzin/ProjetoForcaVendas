package com.example.forcavendasapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.forcavendasapp.model.Cliente;

import java.util.List;

public class ClienteAdapter extends ArrayAdapter<Cliente> {

    public ClienteAdapter(Context context, List<Cliente> clientes) {
        super(context, android.R.layout.simple_spinner_item, clientes);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        Cliente cliente = getItem(position);

        if (cliente != null) {
            TextView text = view.findViewById(android.R.id.text1);
            text.setText(cliente.getNome());
        }

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);

        Cliente cliente = getItem(position);

        if (cliente != null) {
            TextView text = view.findViewById(android.R.id.text1);
            text.setText(cliente.getNome());
        }

        return view;
    }
}


