package estrutura.arvores.abp;

public class Abp
{
    private No raiz;

    public Abp () { raiz = null; }

    public void inserir (int x) throws Exception { raiz = inserir (x,raiz); }

    private No inserir (int x, No i) throws Exception {
        if (i == null) i = new No(x);
        else if (x < i.elemento) i.esq = inserir(x,i.esq);
        else if (x > i.elemento) i.dir = inserir(x,i.dir);
        else throw new Exception ("Duplicatas não são permitidas");
        return i;
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
        System.out.println("===== Árvore ABP =====");
        System.out.print("ABP: [");
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
            preOrdem(i.esq);
            preOrdem(i.dir);
        }
    }

    private void posOrdem (No i) {
        if (i != null) {
            posOrdem(i.esq);
            posOrdem(i.dir);
            System.out.print(i.elemento + " ");
        }
    }
}
