import java.util.Random;

public class Vetor
{
    public static int[] gerar(int cap, int l, int h){
        if(cap <= 0){ throw new IllegalArgumentException("Capacidade invalida"); }

        int[] v = new int[cap];
        Random random = new Random();
        
        for(int i = 0; i < cap; i++){ v[i] = random.nextInt((h - l) + 1) + l; } //Lower -> Higher bound INCLUSIVE

        return v;
    }

    public static int[] gerar(int cap){ return gerar(cap, 1, 100); }

    public static int[] gerar(){ return gerar(10, 1, 100); }

    public static void imprimir (int[] v)
    {
        int len = v.length;
        System.out.print("[");
        for (int i = 0; i < len; i++) System.out.printf("%d%s", v[i], (i == len - 1 ? "]\n" : ", "));
    }
}
