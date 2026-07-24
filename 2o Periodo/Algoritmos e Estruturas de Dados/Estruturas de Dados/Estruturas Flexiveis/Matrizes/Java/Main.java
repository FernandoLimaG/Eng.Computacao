import java.util.Scanner;

class OverflowException extends RuntimeException
{
    public OverflowException(){ super("Err: Overflow"); }
}

class UnderflowException extends RuntimeException
{
    public UnderflowException(){ super("Err: Underflow"); }
}

class Celula
{
    int elemento;
    Celula esq, dir, sup, inf;

    public Celula(){ this(0); }

    public Celula(int elemento){
        this.elemento = elemento;
        esq = dir = sup = inf = null;
    }

    public Celula(
            int elemento,
            Celula esq,
            Celula dir,
            Celula sup,
            Celula inf
            )
    {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.sup = sup;
        this.inf = inf;
    }

    public boolean isInicio(){ return esq == null && sup == null && dir != null && inf != null; }
    public boolean isFim(){ return esq != null && sup != null && dir == null && inf == null; }
}

class Matriz
{
    private Celula inicio;
    private Celula fim;
    private int linhas;
    private int colunas;
    private int qtd;

    public Matriz(int lin, int col){
        linhas = lin;
        colunas = col;
        qtd = 0;
        inicio = null;
        fim = null;

        // Celula referencia
        Celula inicioLinhaAnterior = null;

        for(int i = 0; i < this.linhas; i++){
            Celula inicioLinhaAtual = null;
            Celula celulaEsq = null;
            Celula celulaSuperior = inicioLinhaAnterior;

            for(int j = 0; j < this.colunas; j++){
                Celula nova = new Celula();
                if(celulaEsq != null){
                    celulaEsq.dir = nova;
                    nova.esq = celulaEsq;
                } else{ inicioLinhaAtual = nova; }
                
                if(celulaSuperior != null){
                    celulaSuperior.inf = nova;
                    nova.sup = celulaSuperior;
                    celulaSuperior = celulaSuperior.dir;
                }

                celulaEsq = nova;
                fim = nova;
            }
            if(this.inicio == null){ this.inicio = inicioLinhaAtual; }
            inicioLinhaAnterior = inicioLinhaAtual;
        }
    }

    public Matriz(){ this(3, 3); }

    private boolean isEmpty(){ return qtd == 0; }
    private boolean isFull(){ return qtd == (this.linhas * this.colunas); }

    public void inserir(int x){
        boolean inserido = false;
        if(isFull()){ throw new OverflowException(); }
        for(Celula lin = inicio; lin != null && !inserido; lin = lin.inf){
            for(Celula col = lin; col != null && !inserido; col = col.dir){
                if(col.elemento == 0){
                    col.elemento = x;
                    inserido = true;
                }
            }
        }
        qtd++;
    }

    public int remover(){
        if(isEmpty()){ throw new UnderflowException(); }

        Celula removida = inicio;
        int pos = qtd - 1;
        int lin = pos / colunas;
        int col = pos % colunas;

        for(int i = 0; i < lin; i++) removida = removida.inf;
        for(int i = 0; i < col; i++) removida =removida.dir;

        int removido = removida.elemento;
        removida.elemento = 0;
        qtd--;

        return removido;
    }

    public boolean pesquisar(int x){
        boolean resp = false;
        Celula ptr = inicio;
        for(int i = 0; i < linhas && ptr != null && !resp; i++, ptr = ptr.inf){
            Celula valor = ptr;
            for(int j = 0; j < linhas && valor != null && !resp; j++, valor = valor.dir){
                if(valor.elemento == x){ resp = !resp; }
            }
        }
        return resp;
    }

    public void imprimir(){
        System.out.println("===== MATRIZ =====");
        for(Celula lin = inicio; lin != null; lin = lin.inf){
            System.out.print("| ");
            for(Celula valor = lin; valor != null; valor = valor.dir){
                if(valor.dir == null){ System.out.println(valor.elemento + " |"); }
                else{ System.out.printf("%-6d", valor.elemento); }
            }
        }
    }
}

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Matriz matriz = new Matriz(2,2);

        while(true){
            int op = menu(sc);
            System.out.println();
            if(op == 0) break;
            try{
                switch(op){
                    case 1:{
                               System.out.print("Valor para inserir: ");
                               matriz.inserir(sc.nextInt());
                               break;
                    }
                    case 2:{
                               System.out.println("[REMOVIDO]: " + matriz.remover());
                               break;
                    }
                    case 3:{
                               System.out.print("Valor para pesquisar: ");
                               if(matriz.pesquisar(sc.nextInt())){
                                   System.out.println("Elemento encontrado");
                               } else{
                                   System.out.println("Elemento não encontrado");
                               }
                               break;
                    }
                    case 4:{
                               matriz.imprimir();
                               break;
                    }
                    default:{
                                System.out.println("Opção inválida, tente novamente\n");
                                break;
                    }
                }
            } catch(Exception e){ System.out.println(e.getMessage()); }
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
