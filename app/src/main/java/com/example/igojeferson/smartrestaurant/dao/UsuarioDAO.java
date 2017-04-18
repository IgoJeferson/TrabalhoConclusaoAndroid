package com.example.igojeferson.smartrestaurant.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.igojeferson.smartrestaurant.model.Usuario;

/**
 * Created by Igo Jeferson on 17/04/2017.
 */

public class UsuarioDAO {

    private DBOpenHelper banco;

    public UsuarioDAO(Context context) {
        banco = new DBOpenHelper(context);
    }

    public static final String TABELA_USUARIO = "usuario";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_LOGIN = "login";
    public static final String COLUNA_SENHA = "senha";

    public Usuario autentica(String login) {
        SQLiteDatabase db = banco.getReadableDatabase();
        String colunas[] = {COLUNA_ID, COLUNA_LOGIN, COLUNA_SENHA};
        String where = " login = '" + login + "'";
        Cursor cursor = db.query(true, TABELA_USUARIO, colunas, where, null, null, null, null, null);
        Usuario usuario = null;
        if (cursor != null) {
            cursor.moveToFirst();
            usuario = new Usuario();
            usuario.setLogin(cursor.getString(cursor.getColumnIndex(COLUNA_LOGIN)));
            usuario.setSenha(cursor.getString(cursor.getColumnIndex(COLUNA_SENHA)));
            usuario.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
        }
        return usuario;
    }

    public String add(Usuario usuario) {
        long resultado;
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_LOGIN, usuario.getLogin());
        values.put(COLUNA_SENHA, usuario.getSenha());
        resultado = db.insert(TABELA_USUARIO, null, values);
        db.close();
        if (resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro inserido com sucesso";
        }
    }
}
