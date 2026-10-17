import java.util.Random;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        int len;
        int[] v;
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Tamanho do vetor: ");
            len = Integer.parseInt(sc.nextLine());
            if (len < 2) System.out.println("Capacidade inválida\nDigite um valor MAIOR que 1 (um)");
        } while (len < 2);

        while(true){
            int op = menu(sc);
            System.out.println();
            if (op == 0) break;

            // Vetor clone: Mantém original intacto para comparação e impressão
            v = Vetor.gerar(len);
            int[] ord = v.clone();
            String s = "";

            switch(op) {
                case 1: Ordenacao.bolha(ord); s = "Bolha"; break;
                case 2: Ordenacao.selecao(ord); s = "Seleção"; break;
                case 3: Ordenacao.insercao(ord); s = "Inserção"; break;
                case 4: Ordenacao.heapsort(ord); s = "Heapsort"; break;
                case 5: Ordenacao.mergesort(ord); s = "Mergesort"; break;
                case 6: Ordenacao.quicksort(ord); s = "Quicksort"; break;
                default: System.out.println("Opção inválida, tente novamente"); break;
            }

            System.out.println("========== VETORES GERADOS ==========");
            System.out.print("Vetor original: ");
            Vetor.imprimir(v);
            System.out.print("Vetor ordenado por " + s + ": ");
            Vetor.imprimir(ord);
            System.out.println();
        }

        sc.close();
        System.out.println("FIM DO PROGRAMA");
    }

    private static int menu (Scanner sc)
    {
        System.out.println("===== Menu de Ordenação =====");
        System.out.print("1. Bolha\n2. Seleção\n3. Inserção\n4. Heapsort\n5. Mergesort\n6. Quicksort\n0. Sair\nEscolha: ");
        return Integer.parseInt(sc.nextLine());
    }
}
