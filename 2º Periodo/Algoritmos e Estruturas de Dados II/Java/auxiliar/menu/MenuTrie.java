package auxiliar.menu;

import auxiliar.Auxiliar;
import estrutura.arvores.trie.*;

import java.util.Scanner;

public class MenuTrie
{
    private Trie arvore;

    public MenuTrie () { this.arvore = new Trie(); }

    public void executar (Scanner sc) {
        while (true) {
            int op = Menu.metodos(sc);
            System.out.println();
            if (op == 0) break;
            try {
                switch (op) {
                    case 1: {
                                arvore.inserir(Auxiliar.lerString("Nome para inserir: ",sc));
                                break;
                    }
                    case 2: {
                                String alvo = Auxiliar.lerString("Nome para remover: ",sc);
                                alvo = arvore.remover(alvo);
                                if (alvo == null) System.out.println ("Elemento não encontrado na estrutura");
                                else System.out.println("[REMOVIDO]: " + alvo);
                                break;
                    }
                    case 3: {
                                if (arvore.pesquisar(Auxiliar.lerString("Nome para pesquisar: ",sc))) {
                                    System.out.println("Elemento encontrado na estrutura");
                                } else System.out.println ("Elemento não encontrado na estrutura");
                                break;
                    }
                    case 4: {
                                System.out.println("===== Árvore TRIE =====");
                                arvore.imprimir();
                                break;
                    }
                    default: {
                                 Auxiliar.opcaoInvalida();
                                 break;
                    }
                }
                System.out.println();
            } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
        }
        System.out.println("FIM: Árvore TRIE");
    }
}
