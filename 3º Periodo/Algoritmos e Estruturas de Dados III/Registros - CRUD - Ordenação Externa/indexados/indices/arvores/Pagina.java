package indices.arvores;

import entidade.Chave;

import java.io.*;

class Pagina
{
    int n;
    Pagina[] filhos;
    Chave[] chaves;
    boolean folha;

    public Pagina (int ordem, boolean folha) {
        this.n = 0;
        this.folha = folha;
        this.filhos = new Pagina[ordem];
        this.chaves = new Chave[ordem-1];
    }

    public boolean isEmpty () { return n == 0; }

    private boolean isFull () { return n == chaves.length; }

    /*
    public long buscar (int alvo) {
        long resp = -1;
        for (int i = 0; i < n && resp = -1; i++) {
            int id = chaves[i].getId();
            if (id == alvo) resp = id;
        }
        return resp;
    }
    */
}
