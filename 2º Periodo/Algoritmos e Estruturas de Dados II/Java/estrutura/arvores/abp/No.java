package estrutura.arvores.abp;

public class No
{
    int elemento;
    No esq,dir;

    public No (int elemento) {
        this.elemento = elemento;
        esq = dir = null;
    }
}
