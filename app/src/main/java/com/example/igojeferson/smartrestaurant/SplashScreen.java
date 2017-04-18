package com.example.igojeferson.smartrestaurant;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.igojeferson.smartrestaurant.dao.UsuarioDAO;
import com.example.igojeferson.smartrestaurant.model.Usuario;
import com.example.igojeferson.smartrestaurant.task.AtualizarUsuario;

import java.util.concurrent.ExecutionException;

public class SplashScreen extends AppCompatActivity {

    //Tempo que nosso splashscreen ficará visivel
    private final int SPLASH_DISPLAY_LENGTH = 3500;
    private static Usuario usuarioRetornado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        AtualizarUsuario atualizarUsuario = new AtualizarUsuario();
        atualizarUsuario.execute();

        try {
            Usuario usuario = atualizarUsuario.get();
            new UsuarioDAO(this).add(usuario);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //Executando o método que iniciará nossa animação
        carregar();

    }

    private void carregar() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.animacao_splash);
        anim.reset();
        //Pegando o nosso objeto criado no layout
        ImageView iv = (ImageView) findViewById(R.id.splash);
        if (iv != null) {
            iv.clearAnimation();
            iv.startAnimation(anim);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Após o tempo definido irá executar a próxima tela
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
