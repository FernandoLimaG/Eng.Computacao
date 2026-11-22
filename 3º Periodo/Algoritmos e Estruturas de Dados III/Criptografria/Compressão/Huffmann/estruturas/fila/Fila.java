package estruturas.fila;

import estruturas.no.No;

public class Fila
{
    No[] fila;
    int quantidade;

    public Fila (int cap) {
        this.fila = new No[cap];
        this.quantidade = 0;
    }

    public void inserir (No no) {
        if (this.quantidade == 0) { fila[quantidade++] = no; }
        else {
            int i = this.quantidade;
            while (i >= 0 && fila[i].quantidade < no.quantidade) {
                fila[i] = filha[i+1];
                i--;
            }
            fila[i+1] = no;
            this.quantidade++;
        }
    }

    public No getMenor () {
        No menor;
        if (quantidade == 0) menor = null;
        else {
            menor = fila[0];
            for (int i = 0; i < quantidade-1; i--) fila[i] = fila[i+1];
            fila[--quantidade] = null;
        }
        return menor;
    }
}
