package estrutura.arvores.trie;

public class Trie
{
    private No raiz;

    public Trie () { this.raiz = new No(); }

    public void inserir (String s) throws Exception { inserir (s,0,raiz); }

    private void inserir (String s, int i, No no) throws Exception {
        char c = s.charAt(i);
        int len = s.length();
        No filho = no.getFilho(c);

        if (filho == null) {
            filho = new No (c);
            no.setFilho(filho);
        }

        if (i == len - 1) {
            if (filho.folha) throw new Exception ("Erro de inserção: Proibida a inserção de duplicatas");
            else filho.folha = true;
        } else inserir (s,i+1,filho);
    }

    public String remover (String s) throws Exception { return remover (s,0,raiz); }

    private String remover (String s, int i, No no) throws Exception {
        String out;
        char c = s.charAt(i);
        int len = s.length();
        No filho = no.getFilho(c);

        if (filho != null) {
            if (i == len - 1) {
                if (filho.folha) {
                    filho.folha = false;
                    out = s;
                } else out = null;
            } else if (i < len - 1) out = remover (s,i+1,filho);
            else {
                if (!filho.folha && !filho.temFilhos()) {
                    no.filhos[c] = null;
                    out = s;
                } else out = null;
            }
        } else out = null;

        return out;
    }

    public boolean pesquisar (String s) throws Exception { return pesquisar (s,0,raiz); }

    private boolean pesquisar (String s, int i, No no) throws Exception {
        boolean resp;
        int len = s.length();
        char c = s.charAt(i);
        No filho = no.getFilho(c);

        if (filho == null) resp = false;
        else if (i == len - 1) resp = filho.folha;
        else if (i < len - 1) resp = pesquisar (s,i+1,filho);
        else throw new Exception ("Erro de pesquisa");
        return resp;
    }

    public void imprimir () { imprimir ("",raiz); }

    private void imprimir (String s, No no) {
        if (no.folha) System.out.println(s);

        for (int i = 0; i < no.filhos.length; i++) {
            No filho = no.getFilho((char)i);
            if (filho != null) imprimir(s + filho.letra, filho);
        }
    }
}
