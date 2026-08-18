package lineares.lista;

import customException.*;

public class Lista
{
    int[] lista;
    int cap;
    int tam;

    public Lista(int cap){
        this.cap = cap;
        this.tam = 0;
        this.lista = new int[cap];
    }

    public Lista(){ this(10); }

    private boolean isEmpty(){ return tam == 0; }
    private boolean isFull(){ return tam == cap; }

    public void inserirInicio(int x){
        if(isFull()){ throw new CustomException.Overflow(); }
        for(int i = tam; i > 0; i++) lista[i] = lista[i-1];
        lista[0] = x;
        tam++;
    }

    public void inserirFim(int x){
        if(isFull()){ throw new CustomException.Overflow(); }
        lista[tam++] = x;
    }

    public void inserirPos(int x, int pos){
        if(isFull()){ throw new CustomException.Overflow(); }
        if(pos > tam || pos < 0){ throw new IllegalArgumentException("Índice fora dos limites da estrutura"); }
        if(pos == 0) inserirInicio(x);
        else if(pos == tam) inserirFim(x);
        else{
            for(int i = tam; i > pos; i--) lista[i] = lista[i-1];
            lista[pos] = x;
            tam++;
        }
    }

    public int removerInicio(){
        if(isEmpty()){ throw new CustomException.Underflow(); }
        int out = lista[0];
        tam--;
        for(int i = 0; i < tam; i++) lista[i] = lista[i+1];
        return out;
    }

    public int removerFim(){
        if(isEmpty()){ throw new CustomException.Underflow(); }
        return lista[--tam];
    }

    public int removerPos(int pos){
        int out;
        if(isEmpty()){ throw new CustomException.Underflow(); }
        if(pos > tam-1 || pos < 0){ throw new IllegalArgumentException("Índice fora dos limites da estrutura"); }
        if(pos == 0) out = removerInicio();
        else if(pos == tam-1) out = removerFim();
        else{
            out = lista[pos];
            for(int i = pos; i < tam; i++) lista[i] = lista[i+1];
            tam--;
        }
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
        else if(x == lista[i]) resp = true;
        else resp = pesquisar(x, i+1);
        return resp;
    }

    public void imprimir(){
        if(isEmpty()){ System.out.println("*** LISTA VAZIA ***"); }
        else{
            System.out.println("===== LISTA ESTATICA =====");
            System.out.print("LISTA: [");
            for(int i = 0; i < tam; i++){
                System.out.print(lista[i]);
                if(i < tam - 1){ System.out.print(", "); }
            }
            System.out.println("]");
        }
    }
}
