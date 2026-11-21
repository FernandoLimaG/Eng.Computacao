package estrutura.linear;

public class Fila
{
    int n;
    int cap;
    String[] fila;

    public Fila (int cap) {
        this.n = 0;
        this.cap = cap;
        this.fila = new String[cap];
    }

    private boolean isEmpty () { return n == 0; }
    private boolean isFull () { return n == cap; }

    public void inserir (String s) {
        if (isFull()) throw new RuntimeException ("Overflow");
        fila[n++] = s;
    }
    
    public String remover () {
        if (isEmpty()) throw new RuntimeException ("Underflow");
        String out = fila[0];
        this.n--;
        for (int i = 0; i < n; i++) fila[i] = fila[i+1];
        return out;
    }

    public boolean pesquisar (String s) {
        boolean resp = false;
        for (int i = 0; i < n && !resp; i++) {
            if (s.compareTo(fila[i]) == 0) resp = true;
        }
        return resp;
    }

    public void imprimir () {
        System.out.println("===== Fila Linear =====");
        System.out.print("Fila: [");
        for (int i = 0; i < n; i++) System.out.printf("%s%s", fila[i], (i == n-1 ? "" : ", "));
        System.out.println("]");
    }
}
