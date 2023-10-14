package com.example.forcavendasapp.model;

public class ItemPedido {
    private int codigo;
    private int codigoItem;
    private int codigoPedido;
    private String desc;
    private int quantidade;

    public ItemPedido() {

    }

    public ItemPedido(int codigo, int codigoItem, int codigoPedido, int quantidade, String desc) {
        this.codigo = codigo;
        this.codigoItem = codigoItem;
        this.codigoPedido = codigoPedido;
        this.quantidade = quantidade;
        this.desc = desc;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public int getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(int codigoItem) {
        this.codigoItem = codigoItem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }
}
