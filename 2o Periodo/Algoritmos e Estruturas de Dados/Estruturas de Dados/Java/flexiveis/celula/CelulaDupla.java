package flexiveis.celula;

public class CelulaDupla
{
    int elemento;
    CelulaDupla prox;
    CelulaDupla ant;

    public CelulaDupla(int elemento){
        this.elemento = elemento;
        prox = ant = null;
    }

    public CelulaDupla(){ this(-1); }

    public boolean ehPrimeiro(){ return ant == null && prox != null; }
    public boolean ehUltimo(){ return prox == null && ant != null; }
}
