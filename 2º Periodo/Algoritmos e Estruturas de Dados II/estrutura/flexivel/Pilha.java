package estrutura.flexivel;

public class Pilha
{
    private Celula inicio;
    private Celula fim;

    public Pilha () {
        this.inicio = new Celula();
        fim = inicio;
    }

    private boolean isEmpty() { return fim == inicio; }

    public void inserir (String s) {
        fim.prox = new Celula (s);
        fim = fim.prox;
    }

    public String remover () {
        if (isEmpty()) throw new RuntimeException ("Underflow");
        String out = fim.elemento;

        Celula tmp;
        for (tmp = inicio; tmp != null && tmp.prox != fim; tmp = tmp.prox);
        tmp.prox = fim.prox;
        fim = tmp;
        tmp = null;
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
        System.out.println("===== Pilha Flexível =====");
        System.out.print("Pilha: [");
        imprimir (inicio.prox);
        System.out.println("]");
    }

    private void imprimir (Celula i) {
        if (i != null) {
            imprimir (i.prox);
            System.out.printf("%s%s", i.elemento, (i == inicio.prox ? "" : ", "));
        }
    }
}
