package br.unicamp.ft.android.projetopureza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReceitasActivity extends AppCompatActivity {

    List<Receita> receitas;
    List<Receita> receitasSalgadas;
    List<Receita> receitasDoces;

    int index;
    String tipo_receita;

    TextView titulo;
    TextView tipo;
    TextView ingredientes;
    TextView instrcoes;
    Button btnLink;
    Button btnProxima;
    Button btnAnterior;
    Button btnVoltar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);


        index = 0;
        receitas  = todasAsReceitas();

        titulo = findViewById(R.id.txtNomeReceita);
        tipo = findViewById(R.id.txtTipo);
        ingredientes = findViewById(R.id.txtIngredientes);
        instrcoes = findViewById(R.id.txtPreparo);
        btnLink = findViewById(R.id.buttonLink);
        btnProxima = findViewById(R.id.buttonProximaReceita);
        btnAnterior = findViewById(R.id.buttonAnteriorReceita);
        btnVoltar = findViewById(R.id.buttonVoltar);

        Intent myIntent = getIntent();
        String teste = myIntent.getStringExtra("tipo");
        Toast.makeText(this, "Tipo: " + tipo_receita, Toast.LENGTH_SHORT).show();

        Receita r = receitas.get(index);
        titulo.setText(r.getNome());
        tipo.setText(r.getTipo());
        ingredientes.setText(r.getInstrucoes());
        instrcoes.setText(r.getInstrucoes());




    }

    public void atualizaCampos(){
        Receita r = receitas.get(index);
        titulo.setText(r.getNome());
        tipo.setText(r.getTipo());
        ingredientes.setText(r.getInstrucoes());
        instrcoes.setText(r.getInstrucoes());
    }

    public void onClickProxima(View v){
        this.index++;
        if (index >= receitas.size()){
            Toast.makeText(this, "Fim das receitas", Toast.LENGTH_SHORT).show();
        }
        else {
            atualizaCampos();
        }
    }

    public void onClickAnterior(View v){
        this.index = index-1;
        if (index < 0){
            Toast.makeText(this, "Fim das receitas", Toast.LENGTH_SHORT).show();
            this.index = 0;
        }
        else {
            atualizaCampos();
        }
    }

    public void onClickTutorial(View v){
        String link = receitas.get(index).getLink();

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(browserIntent);
    }

    public void onClickVoltarMenu(View v){
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


    private List<Receita> todasAsReceitas() {
        //Toast.makeText(this, "Salvando o prato: ", Toast.LENGTH_LONG).show();
        return new ArrayList<>(Arrays.asList(
                new Receita("Brigadeiro", "leite condensado, chocolate e manteiga", "doce", "Juntar tudo numa panela, esquentar no microondas e mexer a cada 30 segundos", "https://www.tudogostoso.com.br/receita/114-brigadeiro.html"),
                new Receita("Pipoca", "milho, óleo e sal", "salgada", "Esquentar o óleo, adicionar o milho e fechar a panela. Abrir após 10 segundos sem estouros", "https://www.panelinha.com.br/receita/Pipoca"),
                new Receita("Pipoca Doce", "óleo, milho e açúcar", "doce", "Estourar a pipoca e reservar, após isso esquentar o açúcar até derreter misturar na pipoca", "https://www.tudogostoso.com.br/receita/53464-pipoca-doce-caramelada.html"),
                new Receita("Pão na chapa","pão e manteiga","salgada","Passar manteiga no pão e aquecer na frigideira","https://www.youtube.com/watch?v=WJX2ojV-A7w")
        ));
    }
}

