package br.unicamp.ft.android.projetopureza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodasReceitasActivity extends AppCompatActivity {

    //Vetor do tipo receitas
    ArrayList<Receita> receitas = new ArrayList<>();
    Receita receita;

    Button btnVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todas_receitas);

        //Lista de recetias
        receitas = todasAsReceitas();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        if (bundle != null){
            receita = (Receita) bundle.getSerializable("receita");
            receitas.add(receita);
        }
        else
        {
            //Toast.makeText(this, "Intent null", Toast.LENGTH_LONG).show();
        }

        //inicialização da list view e do adapter
        ListView listview = (ListView) findViewById(R.id.listview);
        AdapterReceitas adapter = new AdapterReceitas(receitas, this);
        listview.setAdapter(adapter);

        btnVoltar = findViewById(R.id.buttonVoltar);
    }


    //Chama activity main e volta pro menu principal
    public void onClickVoltar(View v){
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        //bundle.putParcelableArrayList("receitas", new ArrayList<>(receitas));
       // intent.putExtras(bundle);
        startActivity(intent);
        /*
            Intent intent = new Intent(SendActivity.this, PickContactsActivity.class);
            Bundle bundle;
            bundle.putParcelableArrayList("data", cons); // Be sure con is not null here
            intent.putExtras(bundle);
         */


    }


    private ArrayList<Receita> todasAsReceitas() {
       return new ArrayList<>(Arrays.asList(
          new Receita("Brigadeiro", "leite condensado, chocolate e manteiga", "doce", "Juntar tudo numa panela, esquentar no microondas e mexer a cada 30 segundos", "https://www.tudogostoso.com.br/receita/114-brigadeiro.html"),
          new Receita("Pipoca", "milho, óleo e sal", "salgada", "Esquentar o óleo, adicionar o milho e fechar a panela. Abrir após 10 segundos sem estouros", "https://www.panelinha.com.br/receita/Pipoca"),
          new Receita("Pipoca Doce", "óleo, milho e açúcar", "doce", "Estourar a pipoca e reservar, após isso esquentar o açúcar até derreter misturar na pipoca", "https://www.tudogostoso.com.br/receita/53464-pipoca-doce-caramelada.html"),
          new Receita("Pão na chapa","pão e manteiga","salgada","Passar manteiga no pão e aquecer na frigideira","https://www.youtube.com/watch?v=WJX2ojV-A7w")
       ));
    }

}
