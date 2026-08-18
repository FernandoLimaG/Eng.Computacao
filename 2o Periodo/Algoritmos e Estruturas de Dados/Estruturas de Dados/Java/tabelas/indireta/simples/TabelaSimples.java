package tabelas.indireta.simples;

import customException.*;

class Celula
{
    String elemento;
    Celula prox;

    public Celula(String elemento){
        this.elemento = elemento;
        this.prox = null;
    }

    public Celula(){ this(null); }
}

class Lista
{
    Celula primeiro;
    Celula ultimo;

    public Lista(){
        this.primeiro = new Celula();
        ultimo = primeiro;
    }

    public boolean isEmpty(){ return ultimo == primeiro; }

    public void inserir(String s){
        ultimo.prox = new Celula(s);
        ultimo = ultimo.prox;
    }

    public boolean pesquisar(String s){ return pesquisar(s, primeiro.prox); }

    private boolean pesquisar(String s, Celula i){
        boolean resp;
        if(i == null) resp = false;
        else if(s.compareTo(i.elemento) == 0) resp = true;
        else resp = pesquisar(s, i.prox);
        return resp;
    }

    public String remover(String s){
        String resp = null;
        if(isEmpty()) throw new CustomException.Underflow();
        
        Celula i;
        for(i = primeiro; i != null && i.prox.elemento.compareTo(s) != 0; i = i.prox);
        Celula tmp = i.prox;
        resp = tmp.elemento;
        i.prox = tmp.prox;

        if(ultimo == tmp) ultimo = primeiro;

        tmp.prox = null;
        i = tmp = null;

        return resp;
    }

    public void imprimir(){ imprimir(primeiro.prox); }

    private void imprimir(Celula i){
        if(i != null){
            System.out.print(i.elemento);
            if(i != ultimo) System.out.print(", ");
            imprimir(i.prox);
        }
    }
}

public class TabelaSimples
{
    Lista[] tabela;
    int tam;

    public TabelaSimples(int tam){
        this.tam = tam;
        this.tabela = new Lista[tam];

        for(int i = 0; i < tam; i++) tabela[i] = new Lista();
    }

    public TabelaSimples(){ this(20); }

    private int hash(String s){
        int soma = 0;
        for(int i = 0; i < s.length(); i++) soma += s.charAt(i);
        return soma % tam;
    }

    public void inserir(String s){
        int i = hash(s);
        tabela[i].inserir(s);
    }

    public String remover(String s){
        int i = hash(s);
        return tabela[i].remover(s);
    }

    public boolean pesquisar(String s){
        int i = hash(s);
        return tabela[i].pesquisar(s);
    }

    public void imprimir(){
        System.out.println("===== TABELA HASH INDIRETA COM LISTA SIMPLES =====");
        for(int i = 0; i < tam; i++){
            System.out.printf("%02dª lista: ", i+1);
            if(tabela[i].isEmpty()) System.out.println("-----");
            else{
                tabela[i].imprimir();
                System.out.println();
            }
        }
    }
}
