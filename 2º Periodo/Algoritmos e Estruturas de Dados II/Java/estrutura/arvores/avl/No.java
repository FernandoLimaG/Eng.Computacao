package estrutura.arvores.avl;

public class No
{
    int nivel;
    int elemento;
    No esq,dir;

    public No (int elemento) {
        this.nivel = 1;
        this.elemento = elemento;
        esq = dir = null;
    }

    public int getNivel (No i) { return i == null ? 0 : i.nivel; }
    public void setNivel () { this.nivel = 1 + Math.max(getNivel(esq),getNivel(dir)); }
}
