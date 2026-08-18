package tabelas.rehash;

import customException.*;

public class Rehash
{
    String[] tabela;
    int tam;

    public Rehash(int tam){
        this.tam = tam;
        this.tabela = new String[tam];
    }
    
    public Rehash(){ this(20); }
    
    private int soma(String s){
        int soma = 0;
        for(int i = 0; i < s.length(); i++) soma += s.charAt(i);
        return soma;
    }

    private int hash(String s){
        int soma = soma(s);
        return soma % tam;
    }

    private int rehash(String s){
        int soma = soma(s);
        return ++soma % tam;
    }

    private boolean isFree(int i){ return tabela[i] == null; }

    private void setPos(String s, int i){ tabela[i] = s; }

    public void inserir(String s){
        int pos = hash(s);
        if(isFree(pos)){ setPos(s, pos); }
        else{
            pos = rehash(s);
            if(isFree(pos)){ setPos(s, pos); }
            else{ throw new CustomException.Overflow("Tabela Hash Overflow"); }
        }
    }

    public int pesquisar(String s){
        int j = -1;
        int pos = hash(s);
        if(!isFree(pos) && s.compareTo(tabela[pos]) == 0) j = pos;
        else{
            pos = rehash(s);
            if(!isFree(pos) && s.compareTo(tabela[pos]) == 0) j = pos;
        }
        return j;
    }

    public String remover(String s){
        String out = null;
        int pos = pesquisar(s);
        if(pos != -1){
            out = tabela[pos];
            tabela[pos] = null;
        }
        return out;
    }

    public void imprimir(){
        System.out.println("===== TABELA HASH c/ REHASH =====");
        for(int i = 0; i < tam; i++){
            String tipo = (isFree(i) ? "-----" : (i == hash(tabela[i]) ? "HASH" : "REHASH"));
            String conteudo = isFree(i) ? "-----" : tabela[i];
            System.out.printf("%02dº\t%-11s\t%s\n", i+1, tipo, conteudo);
        }
    }
}
