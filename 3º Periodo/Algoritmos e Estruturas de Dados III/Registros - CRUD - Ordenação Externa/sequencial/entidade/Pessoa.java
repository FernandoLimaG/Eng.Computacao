package entidade;

import java.io.*;
import java.util.ArrayList;

public class Pessoa
{
    private int id;
    private String nome;
    private int idade;
    private float altura;

    public Pessoa() { this(-1,"",-1,-0.1f); }

    public Pessoa(int id, String nome, int idade, float altura) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.altura = altura;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public float getAltura() { return altura; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setIdade(int idade) { this.idade = idade; }
    public void setAltura(float altura) { this.altura = altura; }

    @Override
    public String toString() {
        return  "ID: " + id +
                "\nNome: " + nome +
                "\nIdade: " + idade +
                "\nAltura: " + altura;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(id);
        dos.writeUTF(nome);
        dos.writeInt(idade);
        dos.writeFloat(altura);

        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(ba));

        id = dis.readInt();
        nome = dis.readUTF();
        idade = dis.readInt();
        altura = dis.readFloat();
    }

    public static void printFromArray(Pessoa[] pessoas) {
        for(Pessoa p : pessoas) System.out.println(p + "\n");
    }

    public static void printFromBinary(String dbPath) {
        ArrayList<Pessoa> lista = new ArrayList<>();

        try (DataInputStream dis = new DataInputStream(new FileInputStream(dbPath))) {
            dis.readInt();

            while (true) {
                char lapide = (char) dis.readByte();
                int len = dis.readInt();
                int id = dis.readInt();
                String nome = dis.readUTF();
                int idade = dis.readInt();
                float altura = dis.readFloat();

                if(lapide != '*') lista.add(new Pessoa(id,nome,idade,altura));
            }
        } catch (EOFException e) {}
        catch (IOException e) { e.printStackTrace(); }

        printFromArray(lista.toArray(new Pessoa[0]));
    }
}
