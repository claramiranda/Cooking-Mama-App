package br.unicamp.ft.android.projetopureza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodasReceitasActivity extends AppCompatActivity {

    //Vetor do tipo receitas
   -- List<Receita> receitas;
    Receita receita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);

        //Lista de recetias
        ArrayList<Receita> receitas = todasAsReceitas();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        if (bundle != null){
            receita = (Receita) bundle.getSerializable("receita");
            receitas.add(receita);
        }
        else
        {
            Toast.makeText(this, "Intent null", Toast.LENGTH_LONG).show();
        }

        //inicialização da list view e do adapter
        ListView listview = (ListView) findViewById(R.id.listview);
        AdapterReceitas adapter = new AdapterReceitas(receitas, this);
        listview.setAdapter(adapter);
    }

    private ArrayList<Receita> todasAsReceitas() {
       return new ArrayList<>(Arrays.asList(
          new Receita("Brigadeiro", "leite condensado, chocolate e manteiga", "doce", "Juntar tudo numa panela, esquentar no microondas e mexer a cada 30 segundos", "https://www.tudogostoso.com.br/receita/114-brigadeiro.html"),
          new Receita("Pipoca", "milho, óleo e sal", "salgada", "Esquentar o óleo, adicionar o milho e fechar a panela. Abrir após 10 segundos sem estouros", "https://www.panelinha.com.br/receita/Pipoca"),
          new Receita("Pipoca Doce", "óleo, milho e açúcar", "doce", "Estourar a pipoca e reservar, após isso esquentar o açúcar até derreter misturar na pipoca", "https://www.tudogostoso.com.br/receita/53464-pipoca-doce-caramelada.html"),
          new Receita("Pão na chapa","pão e manteiga","salgada","Passar manteiga no pão e aquecer na frigideira","https://www.youtube.com/watch?v=WJX2ojV-A7w")
       ));
    }

    /*
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        //bundle.putCharSequenceArrayList("receitas",receitas);
        bundle.putParcelableArrayList("receitas",receitas);
        intent.putExtras(bundle);
        startActivity(intent);
    }

     */

}
