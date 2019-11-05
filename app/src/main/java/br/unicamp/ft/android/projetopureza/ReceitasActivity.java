package br.unicamp.ft.android.projetopureza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReceitasActivity extends AppCompatActivity {

    List<Receita> receitas;
    int index;
    List<Receita> receitasSalgadas;
    List<Receita> receitasDoces;

    TextView titulo;
    TextView tipo;
    TextView ingredientes;
    TextView instrcoes;
    Button btnLink;
    Button btnProxima;
    Button btnAnterior;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas2);

        index = 0;
        receitas  = todasAsReceitas();

        titulo = findViewById(R.id.txtNomeReceita);
        tipo = findViewById(R.id.txtTipo);
        ingredientes = findViewById(R.id.txtIngredientes);
        instrcoes = findViewById(R.id.txtPreparo);
        btnLink = findViewById(R.id.buttonLink);
        btnProxima = findViewById(R.id.buttonProximaReceita);
        btnAnterior = findViewById(R.id.buttonAnteriorReceita);

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

