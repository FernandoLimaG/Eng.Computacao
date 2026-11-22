import io.*;
import auxiliar.*;
import entidade.*;
import indices.arvores.*;

import java.io.*;
import java.util.*;

public class App
{
    public static void main (String[] args)
    {
        final String dbPath = "data/db/data.db";
        final String csvPath = "data/csv/data.csv";
        final String indexPath = "data/db/index.db";

        // CLI IO
        Scanner sc = new Scanner(System.in);

        // Estruturas do programa
        // DEBUG: Inicialização com estrutura padrao ordem 10 e saida padrão index.db
        B arvoreB = new B(10, indexPath);
        Importador importador = new Importador(dbPath, csvPath);

        while (true) {
            int op = Menu.principal(sc);
            System.out.println();
            if (op == 0) break;
            try {
                switch (op) {
                    case 1:{
                               importador.csvToBin();
                }
                System.out.println();
            } catch (IOException e) { e.printStackTrace(); }
            catch (Exception e) { System.out.println (e.getMessage()); }
        }
    }
}
