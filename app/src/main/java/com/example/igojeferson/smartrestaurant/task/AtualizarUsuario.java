package com.example.igojeferson.smartrestaurant.task;

import android.os.AsyncTask;
import android.util.Log;

import com.example.igojeferson.smartrestaurant.SplashScreen;
import com.example.igojeferson.smartrestaurant.dao.UsuarioDAO;
import com.example.igojeferson.smartrestaurant.model.Usuario;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class AtualizarUsuario extends AsyncTask<Void, Integer, Usuario> {

    String server_response;

    // Converting InputStream to String
    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    @Override
    protected Usuario doInBackground(Void... strings) {

        URL url;
        HttpURLConnection urlConnection = null;
        Usuario usuario = null;

        try {
            url = new URL("http://www.mocky.io/v2/58b9b1740f0000b614f09d2f");
            urlConnection = (HttpURLConnection) url.openConnection();

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                server_response = readStream(urlConnection.getInputStream());
                usuario = new Gson().fromJson(server_response, Usuario.class);

                Log.v("CatalogClient", server_response);
                Log.i("Usuário retornado", usuario.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usuario;
    }
}
