import auxiliar.menu.*;
import auxiliar.Auxiliar;
import estrutura.linear.*;
import estrutura.flexivel.*;
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
                case 1: menuListaLinear(sc); break;
                case 2: menuPilhaLinear(sc); break;
                case 3: menuFilaLinear(sc); break;
                case 4: menuListaFlexivel(sc); break;
                case 5: menuPilhaFlexivel(sc); break;
                case 6: menuFilaFlexivel(sc); break;
                case 7: menuAbp(sc); break;
                case 8: menuAvl(sc); break;
                case 9: menuTrie(sc); break;
                case 10: menuTabelaReserva(sc); break;
                         /*
                case 11: menuTabelaRehash(sc); break;
                case 12: menuTabelaIndireto(sc); break;
                        */
                default: Auxiliar.opcaoInvalida(); break;
            }
            System.out.println();
        }
        sc.close();
        System.out.println("FIM DO PROGRAMA");
    }

    private static void menuAbp (Scanner sc)
    {
        MenuAbp abp = new MenuAbp();
        abp.executar(sc);
    }

    private static void menuAvl (Scanner sc)
    {
        MenuAvl avl = new MenuAvl();
        avl.executar(sc);
    }

    private static void menuTrie (Scanner sc)
    {
        MenuTrie trie = new MenuTrie();
        trie.executar(sc);
    }

    private static void menuListaLinear (Scanner sc)
    {
        int cap;
        do {
            cap = Auxiliar.lerInt("Capacidade da Lista Linear: ",sc);
            if (cap <= 0) System.out.println("Capacidade inválida, tente novamente\n");
        } while (cap <= 0);
        MenuListaLinear lista = new MenuListaLinear(cap);
        lista.executar(sc);
    }

    private static void menuPilhaLinear (Scanner sc)
    {
        int cap;
        do {
            cap = Auxiliar.lerInt("Capacidade da Pilha Linear: ",sc);
            if (cap <= 0) System.out.println("Capacidade inválida, tente novamente\n");
        } while (cap <= 0);
        MenuPilhaLinear pilha = new MenuPilhaLinear(cap);
        pilha.executar(sc);
    }

    private static void menuFilaLinear (Scanner sc)
    {
        int cap;
        do {
            cap = Auxiliar.lerInt("Capacidade da Lista Linear: ",sc);
            if (cap <= 0) System.out.println("Capacidade inválida, tente novamente\n");
        } while (cap <= 0);
        MenuFilaLinear fila = new MenuFilaLinear(cap);
        fila.executar(sc);
    }

    private static void menuListaFlexivel (Scanner sc)
    {
        MenuListaFlexivel lista = new MenuListaFlexivel();
        lista.executar(sc);
    }

    private static void menuPilhaFlexivel (Scanner sc)
    {
        MenuPilhaFlexivel pilha = new MenuPilhaFlexivel();
        pilha.executar(sc);
    }

    private static void menuFilaFlexivel (Scanner sc)
    {
        MenuFilaFlexivel fila = new MenuFilaFlexivel();
        fila.executar(sc);
    }

    private static void menuTabelaReserva (Scanner sc)
    {
        int principal = Auxiliar.lerInt("Tamanho principal da tabela: ",sc);
        int reserva = Auxiliar.lerInt("Tamanho reserva da tabela: ",sc);
        MenuTabela tabela = new MenuTabela(principal,reserva);
        tabela.executar(sc);
    }

    /*
    private static void menuTabelaRehash (Scanner sc)
    {
        int tam = Auxiliar.lerInt("Tamanho da tabela: ",sc);
        MenuTabela tabela = new MenuTabela(tam);
        tabela.executar(sc);
    }

    private static void menuTabelaIndireta (Scanner sc)
    {
        MenuTabela tabela = new MenuTabela();
        tabela.executar(sc);
    }
    */
}
