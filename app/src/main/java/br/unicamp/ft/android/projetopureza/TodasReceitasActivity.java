package br.unicamp.ft.android.projetopureza;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodasReceitasActivity extends AppCompatActivity {

    //Vetor do tipo receitas
    ArrayList<Receita> receitas = new ArrayList<>();

    //firebase
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    //layout
    Button btnVoltar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todas_receitas);

        btnVoltar = findViewById(R.id.buttonVoltar);
        textView = findViewById(R.id.txt_view_loading);

        //chamada
        DownloadDataFromDatabase myAsyncTask = new DownloadDataFromDatabase();
        myAsyncTask.execute();
    }

    //método que alimenta a recycler view
    public void carregaInterface(ArrayList<Receita> recepies){
        Log.d("carregaInterface", "chamou o layout");
        //Toast.makeText(TodasReceitasActivity.this, "Chamou o layout", Toast.LENGTH_SHORT).show();

        ListView listview = (ListView) findViewById(R.id.listview);
        AdapterReceitas adapter = new AdapterReceitas(recepies, this);
        listview.setAdapter(adapter);

        textView.setText("Conteúdo carregado");
    }

    //Chama activity main e volta pro menu principal
    public void onClickVoltar(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    class DownloadDataFromDatabase extends AsyncTask<Void, Void,  ArrayList<Receita>> {

        @Override
        protected ArrayList<Receita> doInBackground(Void... voids) {

            Log.d("Async:doInBackground", "entrou no doInBackground");
            //codigo que o firebase me pediu pra implementar pra parar de dar warning
            FirebaseFirestore firestore = FirebaseFirestore.getInstance();
            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                    .setTimestampsInSnapshotsEnabled(true)
                    .build();
            firestore.setFirestoreSettings(settings);

            //leitura do banco
            db.collection("receitas")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("Firestore:readRecepie", document.getId() + " => " + document.getData());

                                    Receita receita = document.toObject(Receita.class);
                                    Log.d("Salvando a receita:", receita.getNome());

                                    receitas.add(receita);
                                    publishProgress();
                                }
                            } else {
                                Log.w("Firestore", "Error getting documents.", task.getException());
                            }
                        }
                    });

            //dummie code que eu usei pra validar o tamanho da lista
            /*int tamanho =  + receitas.size();
            String texto = Integer.toString(tamanho);
            Log.d("doInBackground", "vai retornar as receitas agr: " + texto);*/


            //O problema está aqui, ele tá retornando essa lista antes de terminar a leitura do metodo interno
            Log.d("Async:doInBackground", "vai retornar o doInBackground");
            return receitas;
        }


        //Aparentemente esse método nao está sendo chamado
        protected void onProgressUpdate(){
            Log.d("Async:onProgressUpdate", "chamou o update");
            textView.setText("obtendo conteudo do firebase");
        }

        @Override
        protected void onPostExecute(ArrayList<Receita> rec){
            Log.d("Async:onPostExecute", "chamou o pós");

            //Não consegui popular a recycler direto daqui entao chamo o método que faz isso na main thread
            carregaInterface(rec);
        }

        @Override
        protected void onPreExecute(){
            textView.setText("Carregando conteúdo...");
            Log.d("Async:onPreExecute", "chamou o pré");
        }

    }

}

