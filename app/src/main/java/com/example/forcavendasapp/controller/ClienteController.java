package com.example.forcavendasapp.controller;

import android.content.Context;

import com.example.forcavendasapp.dao.ClienteDao;
import com.example.forcavendasapp.model.Cliente;

import java.util.ArrayList;

public class ClienteController {

    private Context context;

    public ClienteController(Context context) {
        this.context = context;
    }

    public long salvarCliente(Cliente cliente){
        return ClienteDao.getInstancia(context).insert(cliente);
    }

    public long atualizarCliente(Cliente cliente){
        return ClienteDao.getInstancia(context).update(cliente);
    }

    public long apagarCliente(Cliente cliente){
        return ClienteDao.getInstancia(context).delete(cliente);
    }

    public ArrayList<Cliente> findAllCliente(){
        return ClienteDao.getInstancia(context).getAll();
    }

    public Cliente findByIdCliente(int id){
        return ClienteDao.getInstancia(context).getById(id);}

    public String validaCliente(String codigo, String nome, String cpf, String dtNasc, String codEndereco){
        String mensagem = "";
        if(codigo == null || codigo.isEmpty()){
            mensagem += "Codigo do cliente deve ser informado!\n";
        }else{
            try{
                if(Integer.parseInt(codigo) <= 0){
                    mensagem += "O codigo do cliente deve ser maior que zero!\n";
                }
            }catch(NumberFormatException ex){
                mensagem += "O codigo do cliente deve ser um número válido!\n";
            }

        }
        if(nome == null || nome.isEmpty()){
            mensagem += "O nome do cliente deve ser informado!\n";
        }
        if(cpf == null || cpf.isEmpty()){
            mensagem += "O Cpf do cliente deve ser informado!\n";
        }
        if(dtNasc == null || dtNasc.isEmpty()){
            mensagem += "A Data de Nascimento do cliente deve ser informado!\n";
        }
        if(codEndereco == null || codEndereco.isEmpty()){
            mensagem += "O Codigo do Endereco do cliente deve ser informado!\n";
        }
        return mensagem;
    }
}

