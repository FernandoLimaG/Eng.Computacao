package auxiliar;

import java.util.Scanner;

public class Auxiliar
{
    public static int lerInt (String s, Scanner sc)
    {
        System.out.print(s);
        return Integer.parseInt(sc.nextLine());
    }
    
    public static long lerLong (String s, Scanner sc)
    {
        System.out.print(s);
        return Long.parseLong(sc.nextLine());
    }

    public static float lerFloat (String s, Scanner sc)
    {
        System.out.print(s);
        return Float.parseFloat(sc.nextLine());
    }

    public static double lerDouble (String s, Scanner sc)
    {
        System.out.print(s);
        return Double.parseDouble(sc.nextLine());
    }

    public static String lerString (String s, Scanner sc)
    {
        System.out.print(s);
        return sc.nextLine();
    }

    public static void opcaoInvalida () {
        System.out.println("Opção inválida, tente novamente");
    }
}
