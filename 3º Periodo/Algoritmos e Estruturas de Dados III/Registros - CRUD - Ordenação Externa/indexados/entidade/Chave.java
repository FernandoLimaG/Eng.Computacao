package entidade;

public class Chave
{
    private int id;
    private long pos;

    public Chave (int id, long pos) {
        this.id = id;
        this.pos = pos;
    }

    public int getId () { return id; }
    public long getPos () { return pos; }
}
