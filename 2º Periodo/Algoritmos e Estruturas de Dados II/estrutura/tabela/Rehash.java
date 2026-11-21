package estrutura.tabela;

public class Rehash
{
    int n;
    int tam;
    String[] tabela;

    public Rehash (int tam) {
        this.n = 0;
        this.tam = tam;
        this.tabela = new String[tam];
    }

    private int hash (String s) { return soma(s) % tam; }
    
    private int rehash (String s) {
        int soma = soma(s);
        return ++soma % tam;
    }
    
    private int soma (String s) {
        int soma = 0;
        for (int i = 0; i < s.length(); i++) soma += s.charAt(i);
        return soma;
    }

    private boolean isFree (int i) { return tabela[i] == null; }

    public void inserir (String s) {
        int pos = hash(s);
        if (isFree(pos)) tabela[pos] = s;
        else {
            pos = rehash(s);
            if(isFree(pos)) tabela[pos] = s;
            else throw new RuntimeException ("Hash e Rehash com colisão - Inserção mal sucedida");
        }
        n++;
    }

    public int pesquisar (String s) {
        int resp = -1;
        int pos = hash(s);
        if (!isFree(pos)) {
            if (tabela[pos].compareTo(s) == 0) resp = pos;
            else {
                pos = rehash(s);
                if (!isFree(pos) && tabela[pos].compareTo(s) == 0) resp = pos;
            }
        }
        return resp;
    }

    public String remover (String s) {
        String resp;
        int pos = pesquisar (s);
        if (pos == -1) resp = null;
        else {
            resp = tabela[pos];
            tabela[pos] = null;
            n--;
        }
        return resp;
    }

    public void imprimir () {
        System.out.println("===== Tabela Hash =====");
        for (int i = 0; i < tam; i++) {
            String local = (tabela[i] == null ? " ---- " : (hash(tabela[i]) == i ? "HASH" : "REHASH"));
            System.out.printf("%02d | %-11s | %-6s\n", i+1, (tabela[i] == null ? " --------- " : tabela[i]), local);
        }
    }
}
