import io.*;
import auxiliar.*;
import entidade.*;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class App
{
    public static void main(String[] args)
    {
        final String dbPath = "data/db/data.db";
        final String csvPath = "data/csv/data.csv";

        Pessoa[] arr = null;
        ArrayList<Pessoa> lista = null;

        Scanner sc = new Scanner(System.in);

        while(true) {
            try {
                int op = Menu.principal(sc);
                System.out.println();
                if(op == 0) break;
                switch(op) {
                    case 1:{
                               arr = ImportadorCSV.toArray(csvPath);
                               lista = new ArrayList<>(Arrays.asList(arr));
                               break;
                    }
                    case 2:{
                               System.out.println("[INOP]: return NULL");
                               break;
                    }
                    case 3:{
                               if(arr == null || arr.length < 1) ExportadorDb.fromCSV(csvPath,dbPath);
                               else ExportadorDb.fromArray(arr,dbPath);
                               break;
                    }
                    case 4:{
                               menuCrud(lista,dbPath,sc);
                               arr = lista.toArray(new Pessoa[0]);  // Atualizar o arranjo para representar novos registros
                               break;
                    }
                    case 5:{
                               System.out.println("========== ENTIDADES ==========");
                               if(arr == null) Pessoa.printFromBinary(dbPath);
                               else Pessoa.printFromArray(arr);
                               break;
                    }
                }
                System.out.println();
            } catch (Exception e) { e.printStackTrace(); }
        }

        System.out.println("FIM DO PROGRAMA");
        sc.close();
    }

    static void menuCrud(ArrayList<Pessoa> lista, String dbPath, Scanner sc)
    {
        while (true) {
            try
            {
                int op = Menu.crud(sc);
                System.out.println();
                if(op == 0) break;
                switch(op) {
                    case 1:{
                               int id = Crud.create(dbPath,sc);
                               if(id != -1) {
                                   System.out.println("Registro gerado com sucesso");
                                   //lista = updateList(dbPath);
                               }
                               else {
                                   System.out.println("Falha para escrever novo registro");
                               }
                               break;
                    }
                    case 2:{
                               int id = Auxiliar.lerInt("Digite o ID que deseja buscar: ", sc);
                               Pessoa p = Crud.read(id, dbPath);
                               if(p == null) System.out.println("ID não encontrado");
                               else System.out.println("ID encontrado - Registro:\n" + p);
                               break;
                    }
                    case 3:{
                    }
                    case 4:{
                    }
                    default:{
                                Auxiliar.opcaoInvalida();
                                break;
                    }
                }
                System.out.println();
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    static void updateList (String dbPath) 
    {
    }
}
