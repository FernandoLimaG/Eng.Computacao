package estrutura.tabela;

import estrutura.flexivel.Lista;
import estrutura.flexivel.Celula;

public class Indireta
{
    final int cap;
    Lista[] listas;

    public Indireta (int cap) {
        this.cap = cap;
        this.listas = new Lista[cap];
        for (int i = 0; i < cap; i++) listas[i] = new Lista();
    }

    private int hash (String s) {
        int soma = 0;
        for (int i = 0; i < s.length(); i++) soma += s.charAt(i);
        return soma % cap;
    }

    public void inserir (String s) throws Exception {
        int pos = hash(s);
        listas[pos].inserirFim(s);
    }

    public String remover (String s) {
        String resp = null;
        int pos = pesquisar(s);
        if (pos != -1) { resp = removerLista(s,listas[pos]); }
        return resp;
    }

    private String removerLista (String s, Lista l) {
        String resp;
        if (l.isEmpty()) resp = null;
        else {
            int i = 0;
            Celula tmp;
            for (tmp = l.getInicio(); tmp != null && (tmp.prox.elemento.compareTo(s) != 0); i++, tmp = tmp.prox);
            if (tmp.prox == l.getInicio().prox) resp = l.removerInicio();
            else if (tmp.prox == l.getFim()) resp = l.removerFim();
            else resp = l.removerPos(i);
        }
        return resp;
    }

    public int pesquisar (String s) {
        int pos = hash(s);
        if (!listas[pos].pesquisar(s)) pos = -1;
        return pos;
    }

    public void imprimir () {
        System.out.println("===== Tabela Hash Indireta =====");
        for (int i = 0; i < listas.length; i++) {
            System.out.printf("%02dª ",i+1);
            listas[i].imprimir(false);
        }
        System.out.println("================================");
    }
}
