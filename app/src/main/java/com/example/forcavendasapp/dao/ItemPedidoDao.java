package com.example.forcavendasapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.forcavendasapp.helper.SQLiteDataHelper;
import com.example.forcavendasapp.model.ItemPedido;

import java.util.ArrayList;

public class ItemPedidoDao implements GenericDao<ItemPedido> {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase bd;
    private String[] colunas = {"CODIGO", "CODITEM", "CODPEDIDO", "QUANTIDADE", "DESCRICAO"};
    private String tableName = "ITEMPEDIDO";
    private Context context;
    private static ItemPedidoDao instancia;

    private ItemPedidoDao(Context context) {
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null, 1);
        bd = openHelper.getWritableDatabase();
    }

    public static ItemPedidoDao getInstancia(Context context) {
        if (instancia == null)
            return instancia = new ItemPedidoDao(context);
        else
            return instancia;
    }

    @Override
    public long insert(ItemPedido obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("CODITEM", obj.getCodigoItem());
            valores.put("CODPEDIDO", obj.getCodigoPedido());
            valores.put("QUANTIDADE", obj.getQuantidade());
            valores.put("DESCRICAO", obj.getDesc());

            return bd.insert(tableName, null, valores);
        } catch (SQLException ex) {
            Log.e("ERRO", "ItemPedidoDAO.insert(): " + ex.getMessage());
        }
        return -1;
    }

    @Override
    public long update(ItemPedido obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("CODITEM", obj.getCodigoItem());
            valores.put("CODPEDIDO", obj.getCodigoPedido());
            valores.put("QUANTIDADE", obj.getQuantidade());
            valores.put("DESCRICAO", obj.getDesc());

            String[] identificador = {String.valueOf(obj.getCodigo())};
            return bd.update(tableName, valores, "CODIGO = ?", identificador);
        } catch (SQLException ex) {
            Log.e("ERRO", "ItemPedidoDAO.update(): " + ex.getMessage());
        }
        return -1;
    }

    @Override
    public long delete(ItemPedido obj) {
        try {
            String[] identification = {String.valueOf(obj.getCodigo())};
            return bd.delete(tableName, "CODIGO = ?", identification);
        } catch (SQLException ex) {
            Log.e("ERRO", "ItemPedidoDAO.delete(): " + ex.getMessage());
        }
        return -1;
    }

    @Override
    public ArrayList<ItemPedido> getAll() {
        ArrayList<ItemPedido> lista = new ArrayList<>();
        try {
            Cursor cursor = bd.query(tableName, colunas, null, null, null, null, "CODIGO asc");
            if (cursor.moveToFirst()) {
                do {
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setCodigo(cursor.getInt(0));
                    itemPedido.setCodigoItem(cursor.getInt(1));
                    itemPedido.setCodigoPedido(cursor.getInt(2));
                    itemPedido.setQuantidade(cursor.getInt(3));
                    itemPedido.setDesc(cursor.getString(4));

                    lista.add(itemPedido);
                } while (cursor.moveToNext());
            }
        } catch (SQLException ex) {
            Log.e("ERRO", "ItemPedidoDAO.getAll(): " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public ItemPedido getById(int id) {
        try {
            Cursor cursor = bd.query(tableName, colunas, "CODIGO = ?", null, null, null, "CODIGO asc");
            if (cursor.moveToFirst()) {
                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setCodigo(cursor.getInt(0));
                itemPedido.setCodigoItem(cursor.getInt(1));
                itemPedido.setCodigoPedido(cursor.getInt(2));
                itemPedido.setQuantidade(cursor.getInt(3));
                itemPedido.setDesc(cursor.getString(4));

                return itemPedido;
            }
        } catch (SQLException ex) {
            Log.e("ERRO", "ItemPedidoDAO.getById(): " + ex.getMessage());
        }
        return null;
    }
}
