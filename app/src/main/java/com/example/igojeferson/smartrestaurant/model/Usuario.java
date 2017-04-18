package com.example.igojeferson.smartrestaurant.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Igo Jeferson on 17/04/2017.
 */

public class Usuario implements Serializable{

    private int id;

    @SerializedName("usuario")
    private String login;

    private String senha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
