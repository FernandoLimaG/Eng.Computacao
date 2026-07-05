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

class Arvore
{
    private No raiz;

    public Arvore(){ this.raiz = null; }

    public void inserir(int x){ raiz = inserir(x, raiz); }

    private No inserir(int x, No i){
        if(i == null){ i = new No(x); }
        else if(x < i.elemento){ i.esq = inserir(x, i.esq); }
        else if(x > i.elemento){ i.dir = inserir(x, i.dir); }
        else{ throw new IllegalArgumentException("Erro: Duplicatas nao permitidas"); }
        return i;
    }

    public boolean pesquisar(int x){ return pesquisar(x, raiz); }

    private boolean pesquisar(int x, No i){
        boolean resp;
        if(i == null){ resp = false; }
        else if(x == i.elemento){ resp = true; }
        else if(x < i.elemento){ resp = pesquisar(x, i.esq); }
        else{ resp = pesquisar(x, i.dir); }
        return resp;
    }

    public void imprimir(int op){
        switch(op){
            case 1:
                System.out.print("\nEm Ordem: ");
                emOrdem(raiz);
                break;
            case 2:
                System.out.print("\nPre Ordem: ");
                preOrdem(raiz);
                break;
            case 3:
                System.out.print("\nPos Ordem: ");
                posOrdem(raiz);
                break;
        }
        System.out.println();
    }

    private void emOrdem(No i){
        if(i != null){
            emOrdem(i.esq);
            System.out.print(i.elemento + "");
            emOrdem(i.dir);
        }
    }

    private void preOrdem(No i){
        if(i != null){
            System.out.print(i.elemento + "");
            preOrdem(i.esq);
            preOrdem(i.dir);
        }
    }

    private void posOrdem(No i){
        if(i != null){
            posOrdem(i.esq);
            posOrdem(i.dir);
            System.out.print(i.elemento + "");
        }
    }
}

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Arvore arvore = new Arvore();

        arvore.inserir(1);
        arvore.inserir(4);
        arvore.inserir(2);
        arvore.inserir(5);
        arvore.inserir(6);

        System.out.println("PESQUISAR '2': " + arvore.pesquisar(2));
        System.out.println("PESQUISAR '-1': " + arvore.pesquisar(-1));

        arvore.imprimir(1);
        arvore.imprimir(2);
        arvore.imprimir(3);
        
        sc.close();
    }
}
