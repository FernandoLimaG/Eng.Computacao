package auxiliar;

import java.util.Scanner;

public class Auxiliar
{
    public static int lerInt(String s, Scanner sc){
        System.out.print(s);
        return Integer.parseInt(sc.nextLine());
    }

    public static float lerFloat(String s, Scanner sc){
        System.out.print(s);
        return Float.parseFloat(sc.nextLine());
    }

    public static double lerDouble(String s, Scanner sc){
        System.out.print(s);
        return Double.parseDouble(sc.nextLine());
    }

    public static String lerString(String s, Scanner sc){
        System.out.print(s);
        return sc.nextLine();
    }

    public static void opcaoInvalida(){ System.out.println("Opção inválida, tente novamente"); }

    public static int menu(Scanner sc){
        System.out.println("===== MENU PRINCIPAL =====");
        System.out.print("1. Inserir\n2. Pesquisar\n3. Imprimir\n0. Sair\nEscolha: ");
        return Integer.parseInt(sc.nextLine());
    }
}
