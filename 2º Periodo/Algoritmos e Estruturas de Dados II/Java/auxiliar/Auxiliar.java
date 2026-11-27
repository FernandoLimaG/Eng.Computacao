package auxiliar;

import java.util.Random;
import java.util.Scanner;

public class Auxiliar
{
    public static void opcaoInvalida() { System.out.println ("Opção inválida, tente novamente"); }

    public static String lerString (String s, Scanner sc) {
        System.out.print(s);
        return sc.nextLine();
    }

    public static int lerInt (String s, Scanner sc) {
        System.out.print(s);
        return Integer.parseInt(sc.nextLine());
    }

    public static long lerLong (String s, Scanner sc) {
        System.out.print(s);
        return Long.parseLong(sc.nextLine());
    }

    public static float lerFloat (String s, Scanner sc) {
        System.out.print(s);
        return Float.parseFloat(sc.nextLine());
    }

    public static double lerDouble (String s, Scanner sc) {
        System.out.print(s);
        return Double.parseDouble(sc.nextLine());
    }

    public static int[] novoVetor () {
        int[] v = new int[10];
        Random rd = new Random();
        for (int i = 0; i < v.length; i++) v[i] = rd.nextInt(100);
        return v;
    }

    public static int[] novoVetor (Scanner sc) {
        int cap;
        int[] v;

        do {
            cap = lerInt("Capacidade do vetor: ",sc);
            if (cap < 2) System.out.println("Capacidade inválida - A capacidade deve ser MAIOR que 2 (dois).\nTente novamente\n");
        } while (cap < 2);

        v = new int[cap];
        for (int i = 0; i < v.length; i++) v[i] = lerInt((i+1) + "º elemento: ",sc);
        return v;
    }

    public static void imprimirVetor (int[] v) {
        System.out.print("[");
        for (int i = 0; i < v.length; i++) System.out.printf("%d%s",v[i],(i == v.length - 1 ? "" : ", "));
        System.out.println("]");
    }
}
