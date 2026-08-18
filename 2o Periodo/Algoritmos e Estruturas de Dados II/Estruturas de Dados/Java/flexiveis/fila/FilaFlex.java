package flexiveis.fila;

import flexiveis.celula.*;
import customException.*;

public class FilaFlex
{
    Celula primeiro;
    Celula ultimo;

    public FilaFlex(){
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

        Celula tmp = primeiro.prox;
        int out = tmp.elemento;
        primeiro.prox = tmp.prox;
        tmp.prox = null;
        tmp = null;

        if(primeiro.prox == null) ultimo = primeiro;

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
        if(isEmpty()){ System.out.println("*** FILA VAZIA ***"); }
        else{
            System.out.println("===== FILA FLEXÍVEL =====");
            System.out.print("FILA: [");
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
