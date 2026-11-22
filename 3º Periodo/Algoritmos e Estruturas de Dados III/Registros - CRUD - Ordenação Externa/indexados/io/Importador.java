package io;

import entidade.Pessoa;
import auxiliar.Auxiliar;

import java.io.*;

public class Importador
{
    private final String dbPath;
    private final String csvPath;

    public Importador (String dbPath, String csvPath) {
        this.dbPath = dbPath;
        this.csvPath = csvPath;
    }

    public void csvToBin () throws IOException ()
    {
        int id = 0;

        BufferedReader br = new BufferedReader(new FileReader(csvPath));
        RandomAccesFile raf = new RandomAccessFile(new File(dbPath, "rw"));

        // Ignorar cabecalho
        raf.writeInt(id);
        String linha = br.readLine();

        while((linha = br.readLine()) != null) {
            String[] atributo = linha.split(",");
            String nome = atributo[0].trim();
            int idade = Integer.parseInt(atributo[1].trim());
            float altura = Float.parseFloat(atributo[2].trim());

            Pessoa p = new Pessoa(++id,nome,idade,altura);

            // Escrever Binário
            byte[] ba = p.toByteArray();
            int len = ba.length;
            raf.writeByte(' ');
            raf.writeInt(len);
            raf.write(ba);
        }

        // Atualização do cabeçalho - fix
        raf.seek(0);
        raf.writeInt(id);
        raf.close();
    }
}
