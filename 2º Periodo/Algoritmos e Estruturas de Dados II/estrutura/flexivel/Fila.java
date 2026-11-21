package estrutura.flexivel;

public class Fila
{
    private Celula inicio;
    private Celula fim;

    public Fila () {
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
        Celula tmp = inicio.prox;
        String out = tmp.elemento;
        inicio.prox = tmp.prox;
        tmp.prox = null;
        tmp = null;

        if (inicio.prox == null) fim = inicio;

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
        System.out.println("===== Fila Flexível =====");
        System.out.print("Fila: [");
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

