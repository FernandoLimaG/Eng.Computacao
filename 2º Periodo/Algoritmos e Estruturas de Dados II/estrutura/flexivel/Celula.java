package estrutura.flexivel;

public class Celula
{
    public String elemento;
    public Celula prox;

    public Celula (String elemento) {
        this.elemento = elemento;
        this.prox = null;
    }

    public Celula () { this(""); }
}
