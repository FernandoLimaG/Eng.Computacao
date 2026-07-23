import java.util.Scanner;

class No
{
    int elemento;
    No esq, dir;

    public No(int elemento){
        this.elemento = elemento;
        esq = dir = null;
    }

    public boolean isFolha(){ return esq == null && dir == null; }
}

class Arvore
{
    private No raiz;

    public Arvore(){ this.raiz = null; }

    private int menorElemento(No i){ return i.dir.elemento < i.esq.elemento ? i.dir.elemento : i.esq.elemento; }

    public void inserir(int x){ this.raiz = inserir(x, raiz); }

    private No inserir(int x, No i){
        if(i == null){ i = new No(x); }
        else if(x < i.elemento){ i.esq = inserir(x, i.esq); }
        else if(x > i.elemento){ i.dir = inserir(x, i.dir); }
        else{ throw new IllegalArgumentException("Duplicatas não são permitidos"); }
        return i;
    }

    public void remover(int x){
        raiz = remover(x, raiz);
        if(raiz == null){ System.out.println("Elemento não encontrado"); }
    }

    private No remover(int x, No i){
        if(i != null){
            if(x < i.elemento){ i.esq = remover(x, i.esq); }
            else if(x > i.elemento){ i.dir = remover(x, i.dir); }
            else{
                System.out.println("[REMOVIDO]: " + i.elemento);
                if(i.isFolha()){ i = null; }
                else if(i.esq == null){ i = i.dir; }
                else if(i.dir == null){ i = i.esq; }
                else{
                    i.elemento = menorElemento(i.dir);
                    remover(i.elemento, i.dir);
                }
            }
        }
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

    public void imprimir(Scanner sc){
        int op;
        do{
            System.out.println("===== CAMINHAMENTO =====");
            System.out.print("1. Em ordem\n2. Pre Ordem\n3. Pos Ordem\nEscolha: ");
            op = sc.nextInt();
            if(op < 1 || op > 3){ System.out.println("Opção de caminhamento inválida, tente novamente\n"); }
        }while(op < 1 || op > 3);

        System.out.print("\nBST: [");

        switch(op){
            case 1: emOrdem(raiz); break;
            case 2: preOrdem(raiz); break;
            case 3: posOrdem(raiz); break;
        }
        System.out.println("]");
    }

    private void emOrdem(No i){
        if(i != null){
            emOrdem(i.esq);
            System.out.print(i.elemento);
            if(i.esq != null || i.dir != null){ System.out.print(", "); }
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

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Arvore arvore = new Arvore();

        while(true){
            int op = menu(sc);
            System.out.println();
            if(op == 0) break;
            switch(op){
                case 1:{
                           System.out.print("Elemento para inserir: ");
                           arvore.inserir(sc.nextInt());
                           break;
                }
                case 2:{
                           System.out.print("Elemento para remover: ");
                           arvore.remover(sc.nextInt());
                           break;
                }
                case 3:{
                           System.out.print("Elemento para pesquisar: ");
                           if(arvore.pesquisar(sc.nextInt())){ System.out.println("Elemento encontrado"); }
                           else{ System.out.println("Elemento não encontrado"); }
                           break;
                }
                case 4:{ arvore.imprimir(sc); break; }
                default:{ System.out.println("Opção inválida, tente novamente\n"); break; }
            }
            System.out.println();
        }

        System.out.println("FIM DO PROGRAMA");
        sc.close();
    }

    static int menu(Scanner sc)
    {
        System.out.println("===== MENU PRINCIPAL =====");
        System.out.print("1. Inserir\n2. Remover\n3. Pesquisar\n4. Imprimir\n0. Sair\nEscolha: ");
        return sc.nextInt();
    }
}
