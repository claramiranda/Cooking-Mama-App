package br.unicamp.ft.android.projetopureza;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/*
*   Essa classe cuida da manipulação da tela Todas As Receita, é o adapter personalizado que permite que apareçam vários campos na list view
*/

public class AdapterReceitas extends BaseAdapter {

    private final List<Receita> receitas;
    private final Activity activity;

    //Construtor
    public AdapterReceitas(List<Receita> receitas, Activity act) {
        this.receitas = receitas;
        this.activity = act;
    }


    //Métodos obrigatórios da classe Adapter
    @Override
    public int getCount() {
        return receitas.size();
    }

    @Override
    public Object getItem(int position) {
        return receitas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //Método que monta a exibição de cada item da lista
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        activity.getLayoutInflater();

        //Infla o componente
        View view = activity.getLayoutInflater().inflate(R.layout.lista_receita_personalizada, parent, false);
        Receita receita = receitas.get(position);

        //Carrega os campos
        TextView nome = (TextView) view.findViewById(R.id.lista_receita_personalizada_nome);
        TextView tipo = (TextView) view.findViewById(R.id.lista_receita_personalizada_tipo);
        TextView ingredientes = (TextView)view.findViewById(R.id.lista_receita_personalizada_ingredientes);
        TextView instrucoes = (TextView)view.findViewById(R.id.lista_receita_personalizada_instrucoes);

        //Exibe as informaçoes
        nome.setText(receita.getNome());
        tipo.setText(receita.getTipo());
        ingredientes.setText(receita.getIngredientes());
        instrucoes.setText(receita.getInstrucoes());

        return view;
    }
}
