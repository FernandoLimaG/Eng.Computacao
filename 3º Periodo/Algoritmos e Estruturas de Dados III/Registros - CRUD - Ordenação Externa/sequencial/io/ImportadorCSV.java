package io;

import entidade.Pessoa;
import auxiliar.Auxiliar;

import java.io.*;
import java.util.ArrayList;

public class ImportadorCSV
{
    public static Pessoa[] toArray (String csvPath)
    {
        int id = 0;
        ArrayList<Pessoa> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
            // Ignorar cabecalho
            String linha = br.readLine();

            while((linha = br.readLine()) != null) {
                String[] atributo = linha.split(",");
                String nome = atributo[0].trim();
                int idade = Integer.parseInt(atributo[1].trim());
                float altura = Float.parseFloat(atributo[2].trim());

                lista.add(new Pessoa(++id,nome,idade,altura));
            }
        } catch (EOFException e) { System.out.println("FIM DA LEITURA DO ARQUIVO CSV"); }
        catch (IOException e) { e.printStackTrace(); }

        return lista.toArray(new Pessoa[0]);
    }
}
