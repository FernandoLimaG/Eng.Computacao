package auxiliar;

import java.util.Scanner;

public class Menu
{
    public static int principal (Scanner sc)
    {
        System.out.println("========== MENU PRINCIPAL ==========");
        System.out.print("1. Importar CSV\n2. Importar DB\n3. Exportar DB\n4. CRUD\n5. Imprimir Entidades\n0. Sair\nEscolha: ");
        return Integer.parseInt(sc.nextLine());
    }

    public static int crud (Scanner sc)
    {
        System.out.println("========== CRUD ==========");
        System.out.print("1. [C]reate\n2. [R]ead\n3. [U]pdate\n4. [D]elete\n0. Retornar\nEscolha: ");
        return Integer.parseInt(sc.nextLine());
    }
}
