package estrutura.linear;

public class Pilha
{
    int n;
    final int cap;
    String[] pilha;

    public Pilha (int cap) {
        this.n = 0;
        this.cap = cap;
        this.pilha = new String[cap];
    }

    public void inserir (String s) {
        if (n == cap) throw new RuntimeException ("Overflow");
        pilha[n++] = s;
    }

    public String remover () {
        if (n == 0) throw new RuntimeException ("Underflow");
        return pilha[--n];
    }

    public boolean pesquisar (String s) {
        boolean resp = false;
        if (n != 0) {
            for (int i = 0; i < n && !resp; i++) {
                if (pilha[i].compareTo(s) == 0) resp = true;
            }
        }
        return resp;
    }

    public void imprimir () {
        System.out.println("===== Pilha Linear =====");
        System.out.print("Pilha: [");
        for (int i = 0; i < n; i++) System.out.printf("%s%s",pilha[i],(i == n - 1 ? "" : ", "));
        System.out.println("]");
    }
}
