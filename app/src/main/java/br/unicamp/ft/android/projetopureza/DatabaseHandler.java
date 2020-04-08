package br.unicamp.ft.android.projetopureza;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHandler{
    FirebaseFirestore db;
    //ArrayList<Receita> receitas = new ArrayList<>();




    public DatabaseHandler() {
        this.db = FirebaseFirestore.getInstance();
    }


    public void addRecepieOnDatabase(Receita receita){

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

