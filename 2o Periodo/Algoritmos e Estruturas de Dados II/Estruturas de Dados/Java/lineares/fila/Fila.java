package lineares.fila;

import customException.*;

public class Fila
{
    int[] fila;
    int cap;
    int tam;

    public Fila(int cap){
        this.cap = cap;
        this.tam = 0;
        this.fila = new int[cap];
    }

    public Fila(){ this(10); }

    private boolean isEmpty(){ return tam == 0; }
    private boolean isFull(){ return tam == cap; }

    public void inserir(int x){
        if(isFull()){ throw new CustomException.Overflow(); }
        fila[tam++] = x;
    }

    public int remover(){
        if(isEmpty()){ throw new CustomException.Underflow(); }
        int out = fila[0];
        tam--;
        for(int i = 0; i < tam; i++) fila[i] = fila[i+1];
        return out;
    }

    public boolean pesquisar(int x){
        boolean resp = false;
        if(!isEmpty()){ resp = pesquisar(x, 0); }
        return resp;
    }

    private boolean pesquisar(int x, int i){
        boolean resp;
        if(i == tam) resp = false;
        else if(x == fila[i]) resp = true;
        else resp = pesquisar(x, i+1);
        return resp;
    }

    public void imprimir(){
        if(isEmpty()){ System.out.println("*** FILA VAZIA ***"); }
        else{
            System.out.println("===== FILA ESTATICA =====");
            System.out.print("FILA: [");
            for(int i = 0; i < tam; i++){
                System.out.print(fila[i]);
                if(i < tam - 1){ System.out.print(", "); }
            }
            System.out.println("]");
        }
    }
}

