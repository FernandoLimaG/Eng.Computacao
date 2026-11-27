package estrutura.arvores.trie;

class No
{
    public final int hash = 256;
    public char letra;
    public No[] filhos;
    public boolean folha;

    public No (char letra) {
        this.letra = letra;
        this.filhos = new No[hash];
        this.folha = false;
    }

    public No () { this('\0'); }

    public boolean temFilhos () {
        boolean resp = false;
        for(int i = 0; i < filhos.length && !resp; i++) {
            if (filhos[i] != null) resp = true;
        }
        return resp;
    }

    public No getFilho (char c) { return filhos[c]; }

    public No[] getFilhos () { return filhos; }

    public void setFilho (No filho) { this.filhos[filho.letra] = filho; }
}
