package com.example.forcavendasapp.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDataHelper extends SQLiteOpenHelper {

    public SQLiteDataHelper(@Nullable Context context,
                            @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory,
                            int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE CLIENTE (CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, NOME VARCHAR(100), CPF VARCHAR(100), DTNASCIMENTO VARCHAR(50), CODENDERECO INTEGER, FOREIGN KEY (CODENDERECO) REFERENCES ENDERECO(CODIGO))");
        sqLiteDatabase.execSQL("CREATE TABLE ENDERECO (CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, LOGRADOURO VARCHAR(100), NUMERO INTEGER, BAIRRO VARCHAR(100), CIDADE VARCHAR(100), UF VARCHAR(2))");
        sqLiteDatabase.execSQL("CREATE TABLE ITEM (CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, DESCRICAO VARCHAR(100), VLRUNIT DOUBLE, UNMEDIDA VARCHAR(50))");
        sqLiteDatabase.execSQL("CREATE TABLE PEDIDO (CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, CODPESSOA INTEGER, CODENDERECOENTREGA INTEGER, VLRTOTAL DOUBLE, FOREIGN KEY (CODPESSOA) REFERENCES CLIENTE(CODIGO), FOREIGN KEY (CODENDERECOENTREGA) REFERENCES ENDERECO(CODIGO))");
        sqLiteDatabase.execSQL("CREATE TABLE ITEMPEDIDO (CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, CODITEM INTEGER, CODPEDIDO INTEGER, QUANTIDADE INTEGER, DESCRICAO VARCHAR(100), FOREIGN KEY (CODITEM) REFERENCES ITEM(CODIGO), FOREIGN KEY (CODPEDIDO) REFERENCES PEDIDO(CODIGO))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
