package br.unicamp.ft.android.projetopureza;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todas_receitas);

        //Lista de receitas
        //receitas = todasAsReceitas();
        readDataFromDatabase();

        //inicialização da list view e do adapter
        ListView listview = (ListView) findViewById(R.id.listview);
        AdapterReceitas adapter = new AdapterReceitas(receitas, this);
        listview.setAdapter(adapter);

        btnVoltar = findViewById(R.id.buttonVoltar);
    }

    //Chama activity main e volta pro menu principal
    public void onClickVoltar(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    //aqui eu preciso implementar uma assync task, pq tem um delay entre a chamada do método e o retorno dos dados do database
    public void readDataFromDatabase() {
        db.collection("receitas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("LerReceitas:FIREBASE", document.getId() + " => " + document.getData());
                                Receita receita = document.toObject(Receita.class);
                                Log.d("RECEITA:FIREBASE=", receita.getNome());
                                receitas.add(receita);
                            }
                        } else {
                            Log.w("LerReceitas:FIREBASE", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
