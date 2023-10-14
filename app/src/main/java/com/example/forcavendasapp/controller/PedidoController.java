package com.example.forcavendasapp.controller;

import android.content.Context;

import com.example.forcavendasapp.dao.PedidoDao;
import com.example.forcavendasapp.model.Pedido;

import java.util.ArrayList;

public class PedidoController {

    private Context context;

    public PedidoController(Context context) {
        this.context = context;
    }

    public long salvarPedido(Pedido pedido){
        return PedidoDao.getInstancia(context).insert(pedido);
    }

    public long atualizarPedido(Pedido pedido){
        return PedidoDao.getInstancia(context).update(pedido);
    }

    public long apagarPedido(Pedido pedido){
        return PedidoDao.getInstancia(context).delete(pedido);
    }

    public ArrayList<Pedido> findAllPedido(){
        return PedidoDao.getInstancia(context).getAll();
    }

    public Pedido findByIdPedido(int codigo) {
        return PedidoDao.getInstancia(context).getById(codigo);
    }

    public String validaPedido(int codigo, int codigoPessoa, double valorDesconto, double valorTotal) {
        String mensagem = "";

        if (codigo <= 0) {
            mensagem += "O Código do pedido deve ser maior que zero!\n";
        }

        if (codigoPessoa <= 0) {
            mensagem += "O Código da pessoa no pedido deve ser maior que zero!\n";
        }

        if (valorDesconto < 0) {
            mensagem += "O valor de desconto não pode ser negativo!\n";
        }

        if (valorTotal < 0) {
            mensagem += "O valor total não pode ser negativo!\n";
        }

        return mensagem;
    }

}


