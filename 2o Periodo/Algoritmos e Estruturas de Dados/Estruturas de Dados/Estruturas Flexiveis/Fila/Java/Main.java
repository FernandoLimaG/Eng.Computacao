import java.util.Scanner;

/* Blocos de Excecao customizadas */
class UnderflowException extends RuntimeException
{
    public UnderflowException(){ super("Underflow"); }
}

class Celula
{
    int elemento;
    Celula prox;
    
    public Celula(int elemento){
        this.elemento = elemento;
        this.prox = null;
    }

    public Celula(){ this(-1); }
}

class Fila
{
    Celula primeiro, ultimo;

    public Fila(){
        this.primeiro = new Celula();
        ultimo = primeiro;
    }

    private boolean isEmpty(){ return ultimo == primeiro; }

    private void emptyList(){ System.out.println("Fila vazia"); }

    public void inserir(int x){
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    public int remover(){
        if(isEmpty()){ throw new UnderflowException(); }
        Celula tmp = primeiro.prox;
        int resp = tmp.elemento;
        primeiro.prox = tmp.prox;
        tmp.prox = null;

        if(tmp == ultimo){ ultimo = primeiro; }

        return resp;
    }

    public boolean pesquisar(int x){
        boolean resp = false;
        if(isEmpty()){ emptyList(); }
        else{ resp = pesquisar(x, primeiro.prox); }
        return resp;
    }

    private boolean pesquisar(int x, Celula i){
        boolean resp;
        if(i == null){ resp = false; }
        else if(x == i.elemento){ resp = true; }
        else{ resp = pesquisar(x, i.prox); }
        return resp;
    }

    public void imprimir(){
        if(isEmpty()){ emptyList(); }
        else{
            System.out.print("FILA: [");
            imprimir(primeiro.prox);
            System.out.println("]");
        }
    }

    private void imprimir(Celula i){
        if(i != null){
            System.out.print(i.elemento);
            if(i.prox != null){ System.out.print(", "); }
            imprimir(i.prox);
        }
    }
}

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Fila fila = new Fila();

        while(true){
            int op = menu(sc);
            System.out.println();
            if(op == 0) break;
            switch(op){
                case 1:{
                           System.out.print("Valor para inserir: ");
                           fila.inserir(sc.nextInt());
                           break;
                }
                case 2:{
                           try{ System.out.println("[REMOVIDO]: " + fila.remover()); }
                           catch(UnderflowException e){ System.out.println(e.getMessage()); }
                           break;
                }
                case 3:{
                           System.out.print("Valor para pesquisar: ");
                           if(fila.pesquisar(sc.nextInt())){
                               System.out.println("Valor encontrado");
                           } else{
                               System.out.println("Valor não encontrado");
                           }
                           break;
                }
                case 4:{
                           fila.imprimir();
                           break;
                }
                default:{
                            System.out.println("Opção inválida, tente novamente\n");
                            break;
                }
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
