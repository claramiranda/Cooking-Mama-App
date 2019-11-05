package br.unicamp.ft.android.projetopureza;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//Conforme os requisitos, estamos usando alguns elementos do pacote widget
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {
    EditText edtNome;
    EditText edtIngredientes;
    Spinner spinnerTipo;
    EditText edtInstrucoes;
    EditText editTextLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //associando os atributos da classe com os componentes do XML
        edtNome = findViewById(R.id.editTextNome);
        edtIngredientes = findViewById(R.id.editTextIngredientes);
        edtInstrucoes = findViewById(R.id.editTextInstrucoes);
        editTextLink = findViewById(R.id.editTextLink);

        //Definindo um adapter pro Spinner conseguir ler as informações do strings.xml
        spinnerTipo = findViewById(R.id.spinnerTipo);
        ArrayAdapter<String> spinnerTipoArrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                        getResources().getStringArray(R.array.tipos_de_receitas));
                        spinnerTipo.setAdapter(spinnerTipoArrayAdapter);
    }


    //Método on click do botão salvar
    public void onClickButtonSalvar(View v){

        //Cria objeto do tipo receita
        Receita receita = new Receita(
                edtNome.getText().toString(),
                edtIngredientes.getText().toString(),
                spinnerTipo.getSelectedItem().toString(),
                edtInstrucoes.getText().toString(),
                editTextLink.getText().toString()
        );

        //Toast
        Toast.makeText(this, "Salvando o prato: " + receita.getNome(), Toast.LENGTH_LONG).show();

        //volta pra todas as receitas activity
       Intent intent = new Intent(this, TodasReceitasActivity.class);
        //

       /* Intent it = new Intent(this, TodasReceitasActivity.class);
        it.putExtra("receita", receita);
        startActivity(it);*/


       //Envio da nova receita cadastrada por parâmetro via intent
        Bundle bundle = new Bundle();
        bundle.putSerializable("receita", receita);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}




