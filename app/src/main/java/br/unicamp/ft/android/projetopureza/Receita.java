package br.unicamp.ft.android.projetopureza;

import java.io.Serializable;


//Classe Receita implementa a interface Serializable para poder enviar objetos via intent (savedInstanceState)
public class Receita implements Serializable {
    private int id;
    private String nome;
    private String ingredientes;
    private String tipo; // 1=salgado 2=doce;
    private String instrucoes;
    private String link;


    //CONSTRUCTOR (obrigatório da classe)
    public Receita(int id, String nome, String ingredientes, String tipo, String instrucoes, String link) {
        this.id = id;
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.tipo = tipo;
        this.instrucoes = instrucoes;
        this.link = link;
    }

    public Receita(String nome, String ingredientes, String tipo, String instrucoes, String link) {
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.tipo = tipo;
        this.instrucoes = instrucoes;
        this.link = link;
    }

    //GETTERS AND SETTERS (obrigatório da classe)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLink() {
        return link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getInstrucoes() {
        return instrucoes;
    }

    public void setInstrucoes(String instrucoes) {
        this.instrucoes = instrucoes;
    }

}
