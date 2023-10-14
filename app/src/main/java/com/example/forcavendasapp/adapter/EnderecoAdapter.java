package com.example.forcavendasapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.forcavendasapp.model.Endereco;

import java.util.List;

public class EnderecoAdapter extends ArrayAdapter<Endereco> {
    public EnderecoAdapter(Context context, List<Endereco> enderecos) {
        super(context, 0, enderecos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        Endereco endereco = getItem(position);

        if (endereco != null) {
            textView.setText(endereco.getLogradouro());
        }

        return convertView;
    }
}


