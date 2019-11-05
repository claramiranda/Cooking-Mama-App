package br.unicamp.ft.android.projetopureza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buttonNovaReceita;
    Button buttonDoces;
    Button buttonSalgados;
    Button buttonVerTodas;


    //Primeira funcao a ser chamada no ciclo de vida da activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonNovaReceita = findViewById(R.id.buttonNovaReceita);
        buttonDoces = findViewById(R.id.buttonDoces);
        buttonSalgados = findViewById(R.id.buttonSalgados);
        buttonVerTodas = findViewById(R.id.buttonVerTodas);
    }

    public void onClickNovaReceita(View v){
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);

    }

    public void onClickVerTodas(View v){
        Intent intent = new Intent(this, TodasReceitasActivity.class);
        startActivity(intent);

    }

    public void onClickSalgados(View v){
        Intent intent = new Intent(this, ReceitasActivity.class);
        startActivity(intent);

    }

    public void onClickDoces(View v){
        Intent intent = new Intent(this, ReceitasActivity.class);
        startActivity(intent);

    }


}
