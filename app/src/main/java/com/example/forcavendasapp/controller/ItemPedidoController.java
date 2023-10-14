package com.example.forcavendasapp.controller;

import android.content.Context;

import com.example.forcavendasapp.dao.ItemPedidoDao;
import com.example.forcavendasapp.model.ItemPedido;

import java.util.ArrayList;

public class ItemPedidoController {

    private Context context;

    public ItemPedidoController(Context context) {
        this.context = context;
    }

    public long salvarItemPedido(ItemPedido itemPedido){
        return ItemPedidoDao.getInstancia(context).insert(itemPedido);
    }

    public long atualizarItemPedido(ItemPedido itemPedido){
        return ItemPedidoDao.getInstancia(context).update(itemPedido);
    }

    public long apagarItemPedido(ItemPedido itemPedido){
        return ItemPedidoDao.getInstancia(context).delete(itemPedido);
    }

    public ArrayList<ItemPedido> findAllItemPedido(){
        return ItemPedidoDao.getInstancia(context).getAll();
    }

    public ItemPedido findByIdItemPedido(int codigo) {
        return ItemPedidoDao.getInstancia(context).getById(codigo);
    }

    public String validaItemPedido(int codigo, int codigoItem, int quantidade) {
        String mensagem = "";

        if (codigo <= 0) {
            mensagem += "O Código do item do pedido deve ser maior que zero!\n";
        }

        if (codigoItem <= 0) {
            mensagem += "O Código do item deve ser maior que zero!\n";
        }

        if (quantidade <= 0) {
            mensagem += "A quantidade do item deve ser maior que zero!\n";
        }

        return mensagem;
    }
}


