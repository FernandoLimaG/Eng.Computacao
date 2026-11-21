package estrutura.arvores.avl;

public class Avl
{
    private No raiz;

    public Avl () { this.raiz = null; }

    public void inserir (int x) throws Exception { raiz = inserir(x,raiz); }

    private No inserir (int x, No i) throws Exception {
        if (i == null) i = new No(x);
        else if (x < i.elemento) i.esq = inserir(x,i.esq);
        else if (x > i.elemento) i.dir = inserir(x,i.dir);
        else throw new IllegalArgumentException ("Duplicatas não são permitidas");
        return balancear(i);
    }

    public boolean pesquisar (int x) throws Exception { return pesquisar(x,raiz); }

    private boolean pesquisar (int x, No i) throws Exception {
        boolean resp;
        if (i == null) resp = false;
        else if (x == i.elemento) resp = true;
        else if (x < i.elemento) resp = pesquisar(x,i.esq);
        else resp = pesquisar(x,i.dir);
        return resp;
    }

    public void imprimir (int op) {
        System.out.println("===== Árvore AVL =====");
        System.out.print("AVL: [ ");
        switch (op) {
            case 1: emOrdem(raiz); break;
            case 2: preOrdem(raiz); break;
            case 3: posOrdem(raiz); break;
        }
        System.out.println("]");
    }

    private void emOrdem (No i) {
        if (i != null) {
            emOrdem(i.esq);
            System.out.print(i.elemento + " ");
            emOrdem(i.dir);
        }
    }

    private void preOrdem (No i) {
        if (i != null) {
            System.out.print(i.elemento + " ");
            posOrdem(i.esq);
            posOrdem(i.dir);
        }
    }

    private void posOrdem (No i) {
        if (i != null) {
            posOrdem(i.esq);
            posOrdem(i.dir);
            System.out.print(i.elemento + " ");
        }
    }

    // ===== Métodos Privados =====
    private No balancear (No no) throws Exception {
        if (no != null) {
            int fator = no.getNivel(no.dir) - no.getNivel(no.esq);

            if (Math.abs(fator) <= 1) no.setNivel();

            else if (fator == 2) {
                int fatorDir = no.getNivel(no.dir.dir) - no.getNivel(no.dir.esq);
                if (fatorDir == -1) no = rotacaoDirEsq(no);
                else no = rotacaoEsq(no);
            } else if (fator == -2) {
                int fatorEsq = no.getNivel(no.esq.dir) - no.getNivel(no.esq.esq);
                if (fatorEsq == 1) no = rotacaoEsqDir(no);
                else no = rotacaoDir(no);
            } else throw new Exception ("Erro de balanceamento do nó");
        }
        return no;
    }

    private No rotacaoDir (No no) {
        No esq = no.esq;
        No esqDir = esq.dir;

        esq.dir = no;
        no.esq = esqDir;

        no.setNivel();
        esq.setNivel();
        return esq;
    }

    private No rotacaoEsq (No no) {
        No dir = no.dir;
        No dirEsq = dir.esq;

        dir.esq = no;
        no.dir = dirEsq;

        no.setNivel();
        dir.setNivel();
        return dir;
    }

    private No rotacaoDirEsq (No no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private No rotacaoEsqDir (No no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }
}
