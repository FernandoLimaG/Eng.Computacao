import auxiliar.menu.*;
import auxiliar.Auxiliar;
import estrutura.linear.*;
import estrutura.flexivel.*;
import estrutura.ordenacao.*;
import estrutura.arvores.abp.*;
import estrutura.arvores.avl.*;
import estrutura.arvores.trie.*;
import estrutura.tabela.*;

import java.util.Scanner;

public class Main
{
    public static void main (String[] args)
    {
        Scanner sc = new Scanner (System.in);

        while (true) {
            int op = Menu.principal(sc);
            System.out.println();
            if (op == 0) break;
            switch (op) {
                case 1: menuEstruturas(sc); break;
                case 2: menuOrdenacao(sc); break;
                default: Auxiliar.opcaoInvalida(); break;
            }
        }
        sc.close();
        System.out.println("FIM DO PROGRAMA");
    }

    private static void menuOrdenacao (Scanner sc)
    {
        int tipo;
        int[] vetor;

        System.out.println("Para ordenação usaremos vetores - Escolha: ");
        do {
            tipo = Auxiliar.lerInt("1. Criar e Preencher o vetor\n2. Gerar vetor aleatório\n0. Retornar\nEscolha: ",sc);
        } while (tipo < 0 || tipo > 2);

        if (tipo == 0) return;
        if (tipo == 1) vetor = Auxiliar.novoVetor(sc);
        else vetor = Auxiliar.novoVetor();

        Ordenador sort = new Ordenador(vetor);
        System.out.println();

        while (true) {
            String algoritmo;
            double tempo = -0.1;

            int op = Menu.ordenacao(sc);
            System.out.println();
            if (op == 0) break;
            try {
                switch (op) {
                    case 1:{
                               long ini = System.nanoTime(); 
                               vetor = sort.bubble();
                               long fim = System.nanoTime();
                               tempo = (fim - ini) / 1_000_000.00;
                               algoritmo = "Bubble Sort";
                               break;
                    }
                    case 2:{
                               long ini = System.nanoTime(); 
                               vetor = sort.selection();
                               long fim = System.nanoTime();
                               tempo = (fim - ini) / 1_000_000.00;
                               algoritmo = "Selection Sort";
                               break;
                    }
                    case 3:{
                               long ini = System.nanoTime(); 
                               vetor = sort.insertion();
                               long fim = System.nanoTime();
                               tempo = (fim - ini) / 1_000_000.00;
                               algoritmo = "Insertion Sort";
                               break;
                    }
                    case 4:{
                               long ini = System.nanoTime();  
                               vetor = sort.mergesort();
                               long fim = System.nanoTime();
                               tempo = (fim - ini) / 1_000_000.00;
                               algoritmo = "Merge Sort";
                               break;
                    }
                    case 5:{
                               long ini = System.nanoTime();  
                               vetor = sort.heapsort();
                               long fim = System.nanoTime();
                               tempo = (fim - ini) / 1_000_000.00;
                               algoritmo = "Heap Sort";
                               break;
                    }
                    case 6:{
                               long ini = System.nanoTime(); 
                               vetor = sort.quicksort();
                               long fim = System.nanoTime();
                               tempo = (fim - ini) / 1_000_000.00;
                               algoritmo = "Quick Sort";
                               break;
                    }
                    default:{
                                algoritmo = null;
                                Auxiliar.opcaoInvalida();
                                break;
                    }
                }

                if (algoritmo != null) {
                    System.out.println("===== Ordenação: " + algoritmo + " =====");
                    System.out.print("Original: ");
                    Auxiliar.imprimirVetor(sort.getOriginal());
                    System.out.print("Ordenado: ");
                    Auxiliar.imprimirVetor(vetor);
                    System.out.println("Tempo de execução: " + tempo + " ms");
                }
                System.out.println();
            } catch (Exception e) {
                System.out.println ("Erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void menuEstruturas (Scanner sc)
    {
        while (true) {
            int cap = 0;
            int op = Menu.estruturas(sc);
            System.out.println();
            if (op == 0) break;
            try {
                switch (op) {
                    case 1:{
                               MenuListaLinear lista = new MenuListaLinear(coletarCapacidade(sc));
                               lista.executar(sc);
                               break;
                    }
                    case 2:{
                               MenuPilhaLinear pilha = new MenuPilhaLinear(coletarCapacidade(sc));
                               pilha.executar(sc);
                               break;
                    }
                    case 3:{
                               MenuFilaLinear fila = new MenuFilaLinear(coletarCapacidade(sc));
                               fila.executar(sc);
                               break;
                    }
                    case 4:{
                               MenuListaFlexivel lista = new MenuListaFlexivel();
                               lista.executar(sc);
                               break;
                    }
                    case 5:{
                               MenuPilhaFlexivel pilha = new MenuPilhaFlexivel();
                               pilha.executar(sc);
                               break;
                    }
                    case 6:{
                               MenuFilaFlexivel fila = new MenuFilaFlexivel();
                               fila.executar(sc);
                               break;
                    }
                    case 7:{
                               MenuAbp abp = new MenuAbp();
                               abp.executar(sc);
                               break;
                    }
                    case 8:{
                               MenuAvl avl = new MenuAvl();
                               avl.executar(sc);
                               break;
                    }
                    case 9:{
                               MenuTrie trie = new MenuTrie();
                               trie.executar(sc);
                               break;
                    }
                    case 10:{
                                int principal = Auxiliar.lerInt("Capacidade principal: ",sc);
                                int reserva = Auxiliar.lerInt("Capacidade principal: ",sc);
                                MenuTabela tabela = new MenuTabela(principal,reserva);
                                tabela.executar(sc);
                                break;
                    }
                    case 11:{
                                MenuTabela tabela = new MenuTabela(coletarCapacidade(sc));
                                tabela.executar(sc);
                                break;
                    }
                    case 12:{
                                MenuTabelaIndireta tabela = new MenuTabelaIndireta(coletarCapacidade(sc));
                                tabela.executar(sc);
                                break;
                    }
                    default: Auxiliar.opcaoInvalida(); break;
                }
                System.out.println();
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static int coletarCapacidade (Scanner sc)
    {
        int cap;
        do {
            cap = Auxiliar.lerInt("Capacidade máxima: ",sc);
            if (cap <= 0) System.out.println("Capacidade inválida, tente novamente\n");
        } while (cap <= 0);
        return cap;
    }
}
