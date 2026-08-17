package flexiveis.lista.simples;

import customException.*;
import flexiveis.celula.*;

public class ListaSimples
{
    Celula primeiro;
    Celula ultimo;

    //Atributo auxiliar para contabilizar quantidade de elementos
    int qtd;

    public ListaSimples(){
        primeiro = new Celula();
        ultimo = primeiro;
        qtd = 0;
    }

    private boolean isEmpty(){ return ultimo == primeiro; }

    public void inserirFim(int x){
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
        qtd++;
    }

    public void inserirInicio(int x){
        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if(isEmpty()) ultimo = tmp;
        tmp = null;
        qtd++;
    }

    public void inserirPos(int x, int pos){
        if(pos > qtd || pos < 0) throw new IllegalArgumentException("Posição fora dos limites da estrutura");

        if(pos == 0) inserirInicio(x);
        else if(pos == qtd) inserirFim(x);
        else{
            Celula tmp = primeiro;
            for(int i = 0; i < pos; i++, tmp = tmp.prox);
            Celula nova = new Celula(x);
            nova.prox = tmp.prox;
            tmp.prox = nova;
            nova = tmp = null;
            qtd++;
        }
    }

    public int removerFim(){
        if(isEmpty()){ throw new CustomException.Underflow(); }
        int out = ultimo.elemento;
        Celula tmp;
        for(tmp = primeiro; tmp != null && tmp.prox != ultimo; tmp = tmp.prox);
        ultimo = tmp;
        ultimo.prox = tmp.prox = null;
        tmp = null;
        qtd--;
        return out;
    }

    public int removerInicio(){
        if(isEmpty()){ throw new CustomException.Underflow(); }

        Celula tmp = primeiro.prox;
        int out = tmp.elemento;
        primeiro.prox = tmp.prox;
        tmp.prox = null;
        tmp = null;
        qtd--;

        if(primeiro.prox == null) ultimo = primeiro;

        return out;
    }

    public int removerPos(int pos){
        if(pos >= qtd || pos < 0) throw new IllegalArgumentException("Posição fora dos limites da estrutura");
        if(isEmpty()){ throw new CustomException.Underflow(); }

        int resp;
        if(pos == 0) resp = removerInicio();
        else if(pos == qtd - 1) resp = removerFim();
        else{
            Celula tmp = primeiro;
            for(int i = 0; i < pos && tmp != null; i++, tmp = tmp.prox);
            Celula out = tmp.prox;
            resp = out.elemento;
            tmp.prox = out.prox;
            out.prox = null;
            out = null;
        }
        return resp;
    }

    public boolean pesquisar(int x){
        boolean resp = false;
        if(!isEmpty()){ resp = pesquisar(x, primeiro.prox); }
        return resp;
    }

    private boolean pesquisar(int x, Celula i){
        boolean resp;
        if(i == null) resp = false;
        else if(x == i.elemento) resp = true;
        else resp = pesquisar(x, i.prox);
        return resp;
    }

    public void imprimir(){
        if(isEmpty()){ System.out.println("*** LISTA VAZIA ***"); }
        else{
            System.out.println("===== LISTA SIMPLESMENTE ENCADEADA =====");
            System.out.print("LISTA: [");
            imprimir(primeiro.prox);
            System.out.println("]");
        }
    }

    private void imprimir(Celula i){
    if(i != null){
        System.out.print(i.elemento);
        if(i != ultimo) System.out.print(", ");
        imprimir(i.prox);
    }
}
}
