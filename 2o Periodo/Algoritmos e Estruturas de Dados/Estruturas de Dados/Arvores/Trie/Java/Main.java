/* ========== ÁRVORES TRIE ========== */
import java.util.Scanner;

class No
{
    private char elemento;
    private No[] filhos;
    private boolean folha;
    private final int ascii = 256;

    public No(char elemento){
        this.elemento = elemento;
        this.folha = false;
        this.filhos = new No[ascii];

        for(int i = 0; i < ascii; i++) filhos[i] = null;
    }

    public No(){ this('\0'); }

    public No getFilho(char c){ return filhos[c]; }
    
    public No[] getFilhos(){ return filhos; }

    public void setFilho(No filho){ filhos[filho.elemento] = filho; }

    public void setFolha(){ this.folha = true; }
    
    public boolean isFolha(){ return this.folha; }

    public int getTamFilhos(){ return this.ascii; }

    public char getElemento(){ return this.elemento; }
}

class Arvore
{
    private No raiz;

    public Arvore(){ this.raiz = new No(); }

    public void inserir(String s){ inserir(s, 0, raiz); }

    private void inserir(String s, int i, No no){
        int len = s.length();
        char c = s.charAt(i);
        No filho = no.getFilho(c);

        if(filho == null){
            filho = new No(c);
            no.setFilho(filho);

            if(i == len - 1){ filho.setFolha(); }
            else{ inserir(s, i+1, filho); }
        } else if(i < len - 1){ inserir(s, i+1, filho); }
        else{ throw new IllegalArgumentException("Duplicata não permitida"); }
    }

    public boolean pesquisar(String s){ return pesquisar(s, 0, raiz); }

    private boolean pesquisar(String s, int i, No no){
        int len = s.length();
        char c = s.charAt(i);
        No filho = no.getFilho(c);
        boolean resp;

        if(filho == null){ resp = false; }
        else if(i == len - 1){ resp = filho.isFolha(); }
        else if(i < len - 1){ resp = pesquisar(s, i+1, filho); }
        else{ throw new RuntimeException("Erro de pesquisa"); }
        return resp;
    }

    public void imprimir(){ imprimir("", raiz); }

    private void imprimir(String s, No no){
        if(no.isFolha()){ System.out.println(s); }
        else{
            for(int i = 0; i < no.getTamFilhos(); i++){
                No filho = no.getFilho((char)i);
                if(filho != null){ imprimir(s + filho.getElemento(), filho); }
            }
        }
    }
}

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Arvore trie = new Arvore();

        while(true){
            int op = menu(sc);
            System.out.println();

            if(op == 0) break;

            switch(op){
                case 1:{
                           System.out.print("Nome para inserir: ");
                           trie.inserir(sc.nextLine());
                           break;
                }
                case 2:{
                           System.out.print("Nome para pesquisar: ");
                           String s = sc.nextLine();
                           if(trie.pesquisar(s)){ System.out.println("Nome encontrado"); }
                           else{ System.out.println("Nome não encontrado"); }
                           break;
                }
                case 3:{
                           trie.imprimir();
                           break;
                }
                default:{
                            System.out.println("Opção inválida, tente novamente\n");
                            break;
                }
            }
            System.out.println();
        }
        System.out.println("FIM DO PROGRAMA: Trie Tree - JAVA");
        sc.close();
    }

    static int menu(Scanner sc)
    {
        System.out.println("===== MENU PRINCIPAL =====");
        System.out.print("1. Inserir\n2. Pesquisar\n3. Imprimimr\n0. Sair\nEscolha: ");
        return Integer.parseInt(sc.nextLine());
    }
}
