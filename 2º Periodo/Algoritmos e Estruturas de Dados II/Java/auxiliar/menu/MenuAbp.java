package auxiliar.menu;

import auxiliar.Auxiliar;
import estrutura.arvores.abp.*;

import java.util.Scanner;

public class MenuAbp
{
    public Abp arvore;

    public MenuAbp() { this.arvore = new Abp(); }

    public void executar (Scanner sc) {
        while (true) {
            int op = Menu.metodos(sc);
            System.out.println();
            if (op == 0) break;
            try {
                switch (op) {
                    case 1:{
                               arvore.inserir(Auxiliar.lerInt("Elemento para inserir: ",sc));
                               break;
                    }
                    case 2:{
                               System.out.println("[INOP]");
                               break;
                    }
                    case 3:{
                               if (arvore.pesquisar(Auxiliar.lerInt("Elemento para pesquisar: ",sc))) {
                                   System.out.println("\nElemento encontrado");
                               } else System.out.println("\nElemento não encontrado");
                               break;
                    }
                    case 4:{
                               System.out.println("=== Caminhamentos ===");
                               int print = Auxiliar.lerInt("1. Em Ordem\n2. Pré Ordem\n3. Pós Ordem\n0. Sair\nEscolha: ",sc);
                               if (print != 0) {
                                   System.out.println();
                                   if (print < 1 || print > 3) Auxiliar.opcaoInvalida();
                                   else arvore.imprimir(print);
                               }
                               break;
                    }
                    default: Auxiliar.opcaoInvalida(); break;
                }
                System.out.println();
            } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
        }
        System.out.println("FIM: Árvore ABP");
    }
}
