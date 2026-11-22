package estrutura.linear;

public class Lista
{
    int i;
    int cap;
    String[] lista;

    public Lista (int cap) {
        this.i = 0;
        this.cap = cap;
        this.lista = new String[cap];
    }

    private boolean isEmpty() { return i == 0; }
    private boolean isFull() { return i == cap; }

    public void inserirInicio (String s) {
        if (isFull()) throw new RuntimeException ("Overflow");
        for(int j = i; j > 0; j--) lista[j] = lista[j-1];
        lista[0] = s;
        i++;
    }

    public void inserirFim (String s) {
        if (isFull()) throw new RuntimeException ("Overflow");
        lista[i++] = s;
    }

    public void inserirPos (String s, int pos) {
        if (isFull()) throw new RuntimeException ("Overflow");
        if (pos < 0 || pos > i) throw new IllegalArgumentException ("Posição para remoção inválida");

        if (pos == 0) inserirInicio(s);
        else if(pos == i) inserirFim(s);
        else {
            for(int j = i; j > pos; j--) lista[j] = lista[j-1];
            lista[pos] = s;
            i++;
        }
    }

    public String removerInicio () {
        if (isEmpty()) throw new RuntimeException ("Underflow");

        String out = lista[0];
        for(int j = 0; j < i - 1; j++) lista[j] = lista[j+1];
        i--;

        return out;
    }

    public String removerFim () {
        if (isEmpty()) throw new RuntimeException ("Underflow");
        return lista[--i];
    }

    public String removerPos (int pos) {
        String out;

        if (isEmpty()) throw new RuntimeException ("Underflow");
        if (pos < 0 || pos >= i) throw new IllegalArgumentException ("Posição para remoção inválida");

        if (pos == 0) out = removerInicio();
        else if (pos == i-1) out = removerFim();
        else {
            out = lista[pos];
            for (int j = pos; j < i - 1; j++) lista[j] = lista[j+1];
            i--;
        }
        return out;
    }

    public boolean pesquisar (String s) {
        boolean resp = false;
        for (int j = 0; j < i && !resp; j++) {
            if (lista[j] == s) resp = true;
        }
        return resp;
    }

    public void imprimir () {
        System.out.println("===== Lista Linear =====");
        System.out.print("Lista = [");
        for (int j = 0; j < i; j++) System.out.printf("%s%s", lista[j], (j < i - 1 ? ", " : ""));
        System.out.println("]");
    }
}
