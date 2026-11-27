package estrutura.tabela;

public class Reserva
{
    int n;
    int main;
    int reserva;
    String[] tabela;

    public Reserva (int main, int reserva) {
        this.n = 0;
        this.main = main;
        this.reserva = reserva;
        this.tabela = new String[(main + reserva)];
    }

    private int hash (String s) { return soma(s) % main; }
    
    private int soma (String s) {
        int soma = 0;
        for (int i = 0; i < s.length(); i++) soma += s.charAt(i);
        return soma;
    }

    private boolean mainFull () { return n == main; }
    private boolean allFull () { return n == (main + reserva); }
    private boolean isFree (int i) { return tabela[i] == null; }

    public void inserir (String s) {
        int pos = hash(s);
        if (isFree(pos)) tabela[pos] = s;
        else {
            for (int i = 0; i < reserva; i++) {
                pos = main + i;
                if(isFree(pos)) {
                    tabela[pos] = s;
                    break;
                }
            }
        }
        n++;
    }

    public int pesquisar (String s) {
        int resp = -1;
        int pos = hash(s);
        if (!isFree(pos)) {
            if (tabela[pos].compareTo(s) == 0) resp = pos;
            else {
                for (int i = 0; i < reserva && resp == -1; i++) {
                    pos = main + i;
                    if (!isFree(pos) && tabela[pos].compareTo(s) == 0) resp = pos;
                }
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
        for (int i = 0; i < (main + reserva); i++) {
            System.out.printf("%02d | %-11s | %-9s\n", i+1, (tabela[i] == null ? " --------- " : tabela[i]), (i < main ? "PRINCIPAL" : "RESERVA"));
        }
    }
}
