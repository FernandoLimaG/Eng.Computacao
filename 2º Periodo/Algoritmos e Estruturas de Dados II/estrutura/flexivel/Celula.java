package estrutura.flexivel;

public class Celula
{
    String elemento;
    Celula prox;

    public Celula (String elemento) {
        this.elemento = elemento;
        this.prox = null;
    }

    public Celula () { this(""); }
}
