package estrutura.flexivel;

public class Lista
{
    int n;
    private Celula inicio;
    private Celula fim;

    public Lista () {
        this.inicio = new Celula();
        fim = inicio;
    }

    private void apontarFim() { fim = inicio; }

    private boolean isEmpty() { return (inicio == fim ? true : (inicio.prox == null ? true : false)); }

    public void inserirInicio (String s) {
        Celula tmp = new Celula(s);
        tmp.prox = inicio.prox;
        inicio.prox = tmp;
        if (tmp.prox == null) fim = tmp;
        tmp = null;
        n++;
    }

    public void inserirFim (String s) {
        fim.prox = new Celula (s);
        fim = fim.prox;
        n++;
    }

    public void inserirPos (String s, int i) {
        if (i > n) throw new IllegalArgumentException ("Posição de inserção inválida");

        if (i == 0) inserirInicio(s);
        else if (i == n) inserirFim(s);
        else {
            Celula tmp = inicio;
            for (int j = 0; tmp != null && j < i; j++, tmp = tmp.prox);
            Celula nova = new Celula (s);
            nova.prox = tmp.prox;
            tmp.prox = nova;
            n++;
        }
    }

    public String removerInicio () {
        if (isEmpty()) throw new RuntimeException ("Underflow");
        Celula tmp = inicio.prox;
        String out = tmp.elemento;
        inicio.prox = tmp.prox;
        tmp.prox = null;
        tmp = null;

        n--;

        if (isEmpty()) apontarFim();

        return out;
    }

    public String removerFim () {
        if (isEmpty()) throw new RuntimeException ("Underflow");
        String out = fim.elemento;

        Celula tmp;
        for (tmp = inicio; tmp != null && tmp.prox != fim; tmp = tmp.prox);
        tmp.prox = fim.prox;
        fim = tmp;
        tmp = null;

        n--; 

        return out;
    }

    public String removerPos (int i) {
        String out;
        if (isEmpty()) throw new RuntimeException ("Underflow");
        if (i > n) throw new IllegalArgumentException ("Posição de remoção inválida");

        if (isEmpty()) out = null;
        else if(i == 0) out = removerInicio();
        else if(i == n-1) out = removerFim();
        else {
            Celula tmp = inicio;
            for (int j = 0; tmp != null && j < i; j++, tmp = tmp.prox);
            Celula apagar = tmp.prox;
            out = apagar.elemento;
            tmp.prox = apagar.prox;
            apagar = null;
            n--;
        }
        return out;
    }

    public boolean pesquisar (String s) {
        boolean resp = false;
        if (!isEmpty()) {
            Celula tmp = inicio.prox;
            while (tmp != null && !resp) {
                if (tmp.elemento.compareTo(s) == 0) resp = true;
                tmp = tmp.prox;
            }
        }
        return resp;
    }

    public void imprimir () {
        System.out.println("===== Lista Flexível =====");
        System.out.print("Lista: [");
        imprimir (inicio.prox);
        System.out.println("]");
    }

    private void imprimir (Celula i) {
        if (i != null) {
            System.out.printf("%s%s", i.elemento, (i == fim ? "" : ", "));
            imprimir (i.prox);
        }
    }
}
