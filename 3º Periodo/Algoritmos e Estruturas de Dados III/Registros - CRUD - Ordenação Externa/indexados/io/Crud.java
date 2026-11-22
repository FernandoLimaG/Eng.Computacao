package io;

import entidade.Pessoa;
import auxiliar.Auxiliar;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Crud
{
    public static int create (String dbPath, Scanner sc)
    {
        // Inicializar ID = -1 para flag de erro
        int id = -1;

        // Criar a entidade - ID coletado depois
        String nome = Auxiliar.lerString("Nome: ", sc);
        int idade = Auxiliar.lerInt("Idade: ", sc);
        float altura = Auxiliar.lerFloat("Altura: ", sc);

        try (RandomAccessFile raf = new RandomAccessFile(new File(dbPath), "rw")) {
            id = raf.readInt();

            Pessoa p = new Pessoa(++id,nome,idade,altura);

            // Mover para o fim do arquivo
            raf.seek(raf.length());
            byte[] ba = p.toByteArray();
            int len = ba.length;

            raf.writeByte(' ');
            raf.writeInt(len);
            raf.write(ba);

            // Reescrever o cabeçalho
            raf.seek(0);
            raf.writeInt(id);
        } catch (IOException e) { e.printStackTrace(); }

        return id;
    }

    public static Pessoa read (int alvo, String dbPath)
    {
        try (RandomAccessFile raf = new RandomAccessFile(new File(dbPath), "r")) {
            if(raf.readInt() < alvo) return null;

            while(raf.getFilePointer() < raf.length()) {
                char lapide = (char) raf.readByte();
                int len = raf.readInt();

                if(lapide == '*') { raf.skipBytes(len); }
                else {
                    int id = raf.readInt();
                    if(id == alvo) {
                        String nome = raf.readUTF();
                        int idade = raf.readInt();
                        float altura = raf.readFloat();
                        return new Pessoa(id,nome,idade,altura);
                    } else { raf.skipBytes((len - 4)); }
                }
            }
        } catch (IOException e) { e.printStackTrace(); }

        return null;
    }
}
