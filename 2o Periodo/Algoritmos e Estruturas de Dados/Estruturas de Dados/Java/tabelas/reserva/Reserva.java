package tabelas.reserva;

import customException.*;

public class Reserva
{
    String[] tabela;
    int m;
    int r;
    int qt;

    public Reserva(int m, int r){
        this.m = m;
        this.r = r;
        this.qt = 0;

        this.tabela = new String[(m + r)];
    }
    
    public Reserva(){ this(13, 7); }

    private int hash(String s){
        int soma = 0;
        for(int i = 0; i < s.length(); i++) soma += s.charAt(i);
        return soma % m;
    }

    private boolean isFree(int i){ return tabela[i] == null; }

    private void setPos(String s, int i){ tabela[i] = s; }

    public void inserir(String s){
        int pos = hash(s);
        if(isFree(pos)){ setPos(s, pos); }
        else if(qt < r){
            for(int i = 0; i < r; i++){
                pos = m + i;
                if(isFree(pos)){
                    setPos(s, pos);
                    qt++;
                    i = r;
                }
            }
        }
        else{ throw new CustomException.Overflow("Hash Table Overflow"); }
    }

    public int pesquisar(String s){
        int j = -1;
        int pos = hash(s);
        if(!isFree(pos) && s.compareTo(tabela[pos]) == 0) j = pos;
        else{
            for(int i = 0; i < r && j == -1; i++){
                pos = m + i;
                if(!isFree(pos) && s.compareTo(tabela[pos]) == 0) j = pos;
            }
        }
        return j;
    }

    public String remover(String s){
        String out = null;
        int pos = pesquisar(s);
        if(pos != -1){
            out = tabela[pos];
            tabela[pos] = null;
            if(pos >= m) qt--;
        }
        return out;
    }

    public void imprimir(){
        System.out.println("===== TABELA HASH c/ RESERVA =====");
        for(int i = 0; i < (m + r); i++){
            String local = i < m ? "PRINCIPAL" : "RESERVA";
            String conteudo = isFree(i) ? "-----" : tabela[i];
            System.out.printf("%02dº\t%-11s\t%s\n", i+1, local, conteudo);
        }
    }
}
