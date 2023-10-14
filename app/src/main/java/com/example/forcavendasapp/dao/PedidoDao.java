package com.example.forcavendasapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.forcavendasapp.helper.SQLiteDataHelper;
import com.example.forcavendasapp.model.Pedido;

import java.util.ArrayList;

public class PedidoDao implements GenericDao<Pedido> {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase bd;
    private String[] colunas = {"CODIGO", "CODPESSOA", "CODENDERECOENTREGA", "VLRTOTAL"};
    private String tableName = "PEDIDO";
    private Context context;
    private static PedidoDao instancia;

    private PedidoDao(Context context) {
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null, 1);
        bd = openHelper.getWritableDatabase();
    }

    public static PedidoDao getInstancia(Context context) {
        if (instancia == null)
            return instancia = new PedidoDao(context);
        else
            return instancia;
    }

    @Override
    public long insert(Pedido obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("CODPESSOA", obj.getCodigoPessoa());
            valores.put("CODENDERECOENTREGA", obj.getCodigoEndereco());
            valores.put("VLRTOTAL", obj.getValorTotal());

            return bd.insert(tableName, null, valores);
        } catch (SQLException ex) {
            Log.e("ERRO", "PedidoDAO.insert(): " + ex.getMessage());
        }
        return -1;
    }

    @Override
    public long update(Pedido obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("CODPESSOA", obj.getCodigoPessoa());
            valores.put("CODENDERECOENTREGA", obj.getCodigoEndereco());
            valores.put("VLRTOTAL", obj.getValorTotal());

            String[] identificador = {String.valueOf(obj.getCodigo())};
            return bd.update(tableName, valores, "CODIGO = ?", identificador);
        } catch (SQLException ex) {
            Log.e("ERRO", "PedidoDAO.update(): " + ex.getMessage());
        }
        return -1;
    }

    @Override
    public long delete(Pedido obj) {
        try {
            String[] identification = {String.valueOf(obj.getCodigo())};
            return bd.delete(tableName, "CODIGO = ?", identification);
        } catch (SQLException ex) {
            Log.e("ERRO", "PedidoDAO.delete(): " + ex.getMessage());
        }
        return -1;
    }

    @Override
    public ArrayList<Pedido> getAll() {
        ArrayList<Pedido> lista = new ArrayList<>();
        try {
            Cursor cursor = bd.query(tableName, colunas, null, null, null, null, "CODIGO asc");
            if (cursor.moveToFirst()) {
                do {
                    Pedido pedido = new Pedido();
                    pedido.setCodigo(cursor.getInt(0));
                    pedido.setCodigoPessoa(cursor.getInt(1));
                    pedido.setCodigoEndereco(cursor.getInt(2));
                    pedido.setValorTotal(cursor.getDouble(3));

                    lista.add(pedido);
                } while (cursor.moveToNext());
            }
        } catch (SQLException ex) {
            Log.e("ERRO", "PedidoDAO.getAll(): " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public Pedido getById(int id) {
        try {
            Cursor cursor = bd.query(tableName, colunas, "CODIGO = ?", null, null, null, "CODIGO asc");
            if (cursor.moveToFirst()) {
                Pedido pedido = new Pedido();
                pedido.setCodigo(cursor.getInt(0));
                pedido.setCodigoPessoa(cursor.getInt(1));
                pedido.setCodigoEndereco(cursor.getInt(2));
                pedido.setValorTotal(cursor.getDouble(3));

                return pedido;
            }
        } catch (SQLException ex) {
            Log.e("ERRO", "PedidoDAO.getById(): " + ex.getMessage());
        }
        return null;
    }
}

