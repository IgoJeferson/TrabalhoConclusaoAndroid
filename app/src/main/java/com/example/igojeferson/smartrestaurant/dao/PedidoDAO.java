package com.example.igojeferson.smartrestaurant.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.igojeferson.smartrestaurant.model.Pedido;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Igo Jeferson on 17/04/2017.
 */

public class PedidoDAO {

    private DBOpenHelper banco;

    public PedidoDAO(Context context) {
        banco = new DBOpenHelper(context);
    }

    public static final String TABELA_PEDIDO = "pedido";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_DESCRICAO = "descricao";
    public static final String COLUNA_QUANTIDADE = "quantidade";
    public static final String COLUNA_VALOR_UNITARIO = "valor_unitario";

    public String add(Pedido pedido) {
        long resultado;
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_DESCRICAO, pedido.getDescricao());
        values.put(COLUNA_QUANTIDADE, pedido.getQuantidade());
        values.put(COLUNA_VALOR_UNITARIO, pedido.getValorUnitario());
        resultado = db.insert(TABELA_PEDIDO, null, values);
        db.close();
        if (resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro inserido com sucesso";
        }
    }

    public Pedido getBy(int id) {
        SQLiteDatabase db = banco.getReadableDatabase();
        String colunas[] = {COLUNA_ID, COLUNA_DESCRICAO, COLUNA_QUANTIDADE, COLUNA_VALOR_UNITARIO};
        String where = "id = " + id;
        Cursor cursor = db.query(true, TABELA_PEDIDO, colunas, where, null, null, null, null, null);
        Pedido pedido = null;
        if (cursor != null) {
            cursor.moveToFirst();
            pedido = new Pedido();
            pedido.setId(cursor.getInt(0));
            pedido.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
            pedido.setDescricao(cursor.getString(cursor.getColumnIndex(COLUNA_DESCRICAO)));
            pedido.setQuantidade(cursor.getInt(cursor.getColumnIndex(COLUNA_QUANTIDADE)));
            pedido.setValorUnitario(cursor.getDouble(cursor.getColumnIndex(COLUNA_VALOR_UNITARIO)));
        }
        return pedido;
    }

    public List<Pedido> getAll() {
        List<Pedido> pedidos = new LinkedList<>();
        String rawQuery = "SELECT p.* FROM " + TABELA_PEDIDO + " ORDER BY " + COLUNA_DESCRICAO + " ASC";
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(rawQuery, null);
        Pedido pedido = null;
        if (cursor.moveToFirst()) {
            do {
                pedido = new Pedido();
                pedido.setId(cursor.getInt(0));
                pedido.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
                pedido.setDescricao(cursor.getString(cursor.getColumnIndex(COLUNA_DESCRICAO)));
                pedido.setQuantidade(cursor.getInt(cursor.getColumnIndex(COLUNA_QUANTIDADE)));
                pedido.setValorUnitario(cursor.getDouble(cursor.getColumnIndex(COLUNA_VALOR_UNITARIO)));
                pedidos.add(pedido);
            } while (cursor.moveToNext());
        }
        return pedidos;
    }
}

