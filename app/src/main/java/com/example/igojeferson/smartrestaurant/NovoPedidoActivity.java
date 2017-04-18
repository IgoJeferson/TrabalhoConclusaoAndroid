package com.example.igojeferson.smartrestaurant;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.igojeferson.smartrestaurant.dao.PedidoDAO;
import com.example.igojeferson.smartrestaurant.model.Pedido;

public class NovoPedidoActivity extends AppCompatActivity {

    public final static int CODE_NOVO_PEDIDO= 1005;

    private TextInputLayout tilDescricaoPedido;
    private TextInputLayout tilQuantidadePedido;
    private TextInputLayout tilValorPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);

        tilDescricaoPedido = (TextInputLayout) findViewById(R.id.tilDescricaoPedido);
        tilQuantidadePedido = (TextInputLayout) findViewById(R.id.tilQuantidadePedido);
        tilValorPedido = (TextInputLayout) findViewById(R.id.tilValorPedido);
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public void cadastrar(View v) {
        PedidoDAO pedidoDAO = new PedidoDAO(this);
        Pedido pedido = new Pedido();
        pedido.setDescricao(tilDescricaoPedido.getEditText().getText().toString());
        pedido.setQuantidade(Integer.parseInt(tilQuantidadePedido.getEditText().toString()));
        pedido.setValorUnitario(Double.parseDouble(tilValorPedido.getEditText().toString()));
        pedidoDAO.add(pedido);
        retornaParaTelaAnterior();
    }

    //retorna para tela de lista de torcedores
    public void retornaParaTelaAnterior() {
        Intent intentMessage = new Intent();
        setResult(CODE_NOVO_PEDIDO, intentMessage);
        finish();
    }
}
