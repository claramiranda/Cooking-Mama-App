package br.unicamp.ft.android.projetopureza;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

//Conforme os requisitos, estamos usando alguns elementos do pacote widget
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadastroActivity extends AppCompatActivity {
    EditText edtNome;
    EditText edtIngredientes;
    Spinner spinnerTipo;
    EditText edtInstrucoes;
    EditText editTextLink;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    /*

*/

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

        addRecepieOnFirebase(receita);


        //Toast
        Toast.makeText(this, "Salvando o prato: " + receita.getNome(), Toast.LENGTH_LONG).show();

        //volta pra todas as receitas activity
       //Intent intent = new Intent(this, TodasReceitasActivity.class);


       //Envio da nova receita cadastrada por parâmetro via intent
      /*  Bundle bundle = new Bundle();
        bundle.putSerializable("receita", receita);
        intent.putExtras(bundle);
        startActivity(intent);*/
    }

    public void addRecepieOnFirebase(Receita receita){
        // Create a new user with a first and last name

        Map<String, Object> recepie = new HashMap<>();
        recepie.put("nome",receita.getNome());
        recepie.put("ingredientes",receita.getIngredientes());
        recepie.put("tipo",receita.getTipo());
        recepie.put("instrucoes",receita.getInstrucoes());

        // Add a new document with a generated ID
        db.collection("receitas")
                .add(recepie)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Receita:Firebase", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Receita:Firebase", "Error adding document", e);
                    }
                });
    }

}




