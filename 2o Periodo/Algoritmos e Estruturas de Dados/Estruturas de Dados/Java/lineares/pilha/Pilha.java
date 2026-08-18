package lineares.pilha;

import customException.*;

public class Pilha
{
    int[] pilha;
    int cap;
    int tam;

    public Pilha(int cap){
        this.cap = cap;
        this.tam = 0;
        this.pilha = new int[cap];
    }

    public Pilha(){ this(10); }

    private boolean isEmpty(){ return tam == 0; }
    private boolean isFull(){ return tam == cap; }

    public void inserir(int x){
        if(isFull()){ throw new CustomException.Overflow(); }
        pilha[tam++] = x;
    }

    public int remover(){
        if(isEmpty()){ throw new CustomException.Underflow(); }
        return pilha[--tam];
    }

    public boolean pesquisar(int x){
        boolean resp = false;
        if(!isEmpty()){ resp = pesquisar(x, 0); }
        return resp;
    }

    private boolean pesquisar(int x, int i){
        boolean resp;
        if(i == tam) resp = false;
        else if(x == pilha[i]) resp = true;
        else resp = pesquisar(x, i+1);
        return resp;
    }

    public void imprimir(){
        if(isEmpty()){ System.out.println("*** PILHA VAZIA ***"); }
        else{
            System.out.println("===== PILHA ESTATICA =====");
            System.out.print("PILHA: [");
            for(int i = tam-1; i >= 0; i--){
                System.out.print(pilha[i]);
                if(i > 0){ System.out.print(", "); }
            }
            System.out.println("]");
        }
    }
}
