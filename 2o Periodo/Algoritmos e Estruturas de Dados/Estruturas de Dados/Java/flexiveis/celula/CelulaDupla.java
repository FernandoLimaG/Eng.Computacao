package flexiveis.celula;

public class CelulaDupla
{
    public int elemento;
    public CelulaDupla prox;
    public CelulaDupla ant;

    public CelulaDupla(int elemento){
        this.elemento = elemento;
        prox = ant = null;
    }

    public CelulaDupla(){ this(-1); }

    public boolean ehPrimeiro(){ return ant == null && prox != null; }
    public boolean ehUltimo(){ return prox == null && ant != null; }
}
