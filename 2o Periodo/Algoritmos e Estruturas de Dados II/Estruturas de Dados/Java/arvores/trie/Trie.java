package arvores.trie;

import auxiliar.*;

class No
{
    char elemento;
    boolean folha;
    final int ascii = 256;
    No[] filhos;

    public No(char elemento){
        this.elemento = elemento;
        this.folha = false;
        this.filhos = new No[ascii];
    }

    public No(){ this('\0'); }

    public No getFilho(char c){ return filhos[c]; }
    public No[] getFilhos(){ return filhos; }
    public void setFilho(No filho){ filhos[filho.elemento] = filho; }
}

public class Trie
{
    private No raiz;

    public Trie(){ this.raiz = new No(); }

    private void validarParametro(String s){
        if(s == null || s.isEmpty() || s.isBlank()){ throw new IllegalArgumentException("Parametro invalido"); }
    }

    public void inserir(String s){ inserir(s, 0, raiz); }

    private void inserir(String s, int i, No no){
        int len = s.length();
        char c = s.charAt(i);
        No filho = no.getFilho(c);

        if(filho == null){
            filho = new No(c);
            no.setFilho(filho);

            if(i == len - 1){ filho.folha = true; }
            else{ inserir(s, i+1, filho); }
        } else if(i < len - 1){ inserir(s, i+1, filho); }
        else{ throw new IllegalArgumentException("Duplicatas nao permitidas"); }
    }

    public boolean pesquisar(String s){
        validarParametro(s);
        return pesquisar(s, 0, raiz);
    }

    private boolean pesquisar(String s, int i, No no){
        boolean resp;
        int len = s.length();
        char c = s.charAt(i);
        No filho = no.getFilho(c);

        if(filho == null){ resp = false; }
        else if(i == len - 1){ resp = filho.folha; }
        else if(i < len - 1){ resp = pesquisar(s, i+1, filho); }
        else{ throw new RuntimeException("Erro de pesquisa"); }
        return resp;
    }

    public void imprimir(){
        System.out.println("===== ARVORE TRIE =====");
        imprimir("", raiz);
        System.out.println("=======================");
    }

    private void imprimir(String s, No no){
        if(no.folha){ System.out.println(s); }
        else{
            for(int i = 0; i < no.ascii; i++){
                No filho = no.getFilho((char)i);
                if(filho != null){ imprimir(s + filho.elemento, filho); }
            }
        }
    }
}
