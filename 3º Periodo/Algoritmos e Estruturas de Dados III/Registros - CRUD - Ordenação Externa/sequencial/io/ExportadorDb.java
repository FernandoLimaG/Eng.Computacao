package io;

import entidade.Pessoa;
import auxiliar.Auxiliar;

import java.io.*;

public class ExportadorDb
{
    public static void fromArray (Pessoa[] lista, String dbPath)
    {
        if (lista == null || lista.length < 1) {
            System.out.println("Entidades não carregadas");
            return;
        }

        System.out.println("Destino: " + dbPath);

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(dbPath))) {
            byte[] ba;

            // Escrever cabeçalho: Ultimo ID cadastrado
            dos.writeInt(lista[lista.length - 1].getId());

            for(Pessoa p : lista) {
                ba = p.toByteArray();
                int len = ba.length;

                // Escrever Metadados
                //  - Lápide:   1 byte (' ') ou ('*')
                //  - Tamanho:  4 bytes [int]
                dos.writeByte(' ');
                dos.writeInt(len);

                // Escrever Registro
                //  - ID
                //  - Nome
                //  - Idade
                //  - Altura
                dos.write(ba);
            }

            System.out.println("Arquivo binário carregado com sucesso!");

        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void fromCSV (String csvPath, String dbPath)
    {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(dbPath))) {
            byte[] ba;
            Pessoa[] lista = ImportadorCSV.toArray(csvPath);

            if(lista == null) {
                System.out.println("Falha ao gerar entidades");
                return;
            }

            // Escrever cabeçalho: Ultimo ID cadastrado
            dos.writeInt(lista[lista.length - 1].getId());

            for(Pessoa p : lista) {
                ba = p.toByteArray();
                int len = ba.length;

                // Escrever Metadados
                //  - Lápide:   1 byte (' ') ou ('*')
                //  - Tamanho:  4 bytes [int]
                dos.writeByte(' ');
                dos.writeInt(len);

                // Escrever Registro
                //  - ID
                //  - Nome
                //  - Idade
                //  - Altura
                dos.write(ba);
            }

            System.out.println("Arquivo binário carregado com sucesso!");

        } catch (IOException e) { e.printStackTrace(); }
    }
}
