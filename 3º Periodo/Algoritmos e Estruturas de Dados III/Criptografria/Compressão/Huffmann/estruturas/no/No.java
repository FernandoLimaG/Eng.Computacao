package estruturas.no;

public class No
{
    No esq,dir;
    char elemento;
    int frequencia;
    // boolean folha;

    public No (int frequencia, char elemento) {
        this.frequencia = frequencia;
        this.elemento = elemento;
        this.esq = dir = null;
        this.folha = true;
    }

    public No (No esq, No dir) {
        this.elemento = '\0';
        this.frequencia = esq.frequencia + dir.frequencia;
        this.folha = false;
        this.esq = esq;
        this.dir = dir;
    }

    public ehFolha () { return esq == null && dir == null; }
}
