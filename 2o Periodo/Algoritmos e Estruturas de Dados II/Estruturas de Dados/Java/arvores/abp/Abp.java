package arvores.abp;

import auxiliar.*;
import java.util.Scanner;

class No
{
    int elemento;
    No esq, dir;

    public No(int elemento){
        this.elemento = elemento;
        esq = dir = null;
    }
}

public class Abp
{
    private No raiz;

    public Abp(){ raiz = null; }

    public void inserir(int x){ raiz = inserir(x, raiz); }

    private No inserir(int x, No i){
        if(i == null) i = new No(x);
        else if(x < i.elemento) i.esq = inserir(x, i.esq);
        else if(x > i.elemento) i.dir = inserir(x, i.dir);
        else throw new IllegalArgumentException("Duplicatas não permitidas");
        return i;
    }

    public boolean pesquisar(int x){ return pesquisar(x, raiz); }

    private boolean pesquisar(int x, No i){
        boolean resp;
        if(i == null) resp = false;
        else if(x == i.elemento) resp = true;
        else if(x < i.elemento) resp = pesquisar(x, i.esq);
        else resp = pesquisar(x, i.dir);
        return resp;
    }

    public void imprimir(Scanner sc){
        int op;
        do{
            System.out.println("===== CAMINHAMENTOS =====");
            op = Auxiliar.lerInt("1. Em Ordem\n2. Pre Ordem\n3. Pos Ordem\n0. Retornar\nEscolha: ", sc);
            if(op < 0 || op > 3){ Auxiliar.opcaoInvalida(); }
        }while(op < 0 || op > 3);

        System.out.println();

        if(op != 0){
            switch(op){
                case 1 -> emOrdem(raiz);
                case 2 -> preOrdem(raiz);
                case 3 -> posOrdem(raiz);
            }
            System.out.println();
        }
    }

    private void emOrdem(No i){
        if(i != null){
            emOrdem(i.esq);
            System.out.print(i.elemento + " ");
            emOrdem(i.dir);
        }
    }

    private void preOrdem(No i){
        if(i != null){
            System.out.print(i.elemento + " ");
            preOrdem(i.esq);
            preOrdem(i.dir);
        }
    }

    private void posOrdem(No i){
        if(i != null){
            posOrdem(i.esq);
            posOrdem(i.dir);
            System.out.print(i.elemento + " ");
        }
    }
}
