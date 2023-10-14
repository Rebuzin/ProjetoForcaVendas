package com.example.forcavendasapp.model;

public class Cliente {

    private int codigo;
    private String nome;
    private String CPF;
    private String dtNasc;
    private int codEndereco;

    public Cliente(){

    }

    public Cliente(int codigo, String nome, String CPF, String dtNasc, int codEndereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.CPF = CPF;
        this.dtNasc = dtNasc;
        this.codEndereco = codEndereco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {
        this.dtNasc = dtNasc;
    }

    public int getCodEndereco() {
        return codEndereco;
    }

    public void setCodEndereco(int codEndereco) {
        this.codEndereco = codEndereco;
    }
}
