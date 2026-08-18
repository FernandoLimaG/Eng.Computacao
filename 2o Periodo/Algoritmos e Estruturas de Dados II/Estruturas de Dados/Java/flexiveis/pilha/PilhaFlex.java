package flexiveis.pilha;

import customException.*;
import flexiveis.celula.*;

public class PilhaFlex
{
    Celula primeiro;
    Celula ultimo;

    public PilhaFlex(){
        primeiro = new Celula();
        ultimo = primeiro;
    }

    private boolean isEmpty(){ return ultimo == primeiro; }

    public void inserir(int x){
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    public int remover(){
        if(isEmpty()){ throw new CustomException.Underflow(); }
        int out = ultimo.elemento;
        Celula tmp;
        for(tmp = primeiro; tmp != null && tmp.prox != ultimo; tmp = tmp.prox);
        ultimo = tmp;
        ultimo.prox = tmp.prox = null;
        tmp = null;
        return out;
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
        if(isEmpty()){ System.out.println("*** PILHA VAZIA ***"); }
        else{
            System.out.println("===== PILHA FLEXÍVEL =====");
            System.out.print("PILHA: [");
            imprimir(primeiro.prox);
            System.out.println("]");
        }
    }

    private void imprimir(Celula i){
        if(i != null){
            imprimir(i.prox);
            System.out.print(i.elemento + " ");
        }
    }
}
