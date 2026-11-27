package auxiliar.menu;

import auxiliar.Auxiliar;
import estrutura.flexivel.Lista;
import estrutura.flexivel.Celula;

import java.util.Scanner;

public class MenuListaFlexivel
{
    private Lista lista;

    public MenuListaFlexivel () { lista = new Lista(); }

    public void executar (Scanner sc) {
        while (true) {
            int op = Menu.metodosLista(sc);
            System.out.println();
            if (op == 0) break;
            try {
                switch (op) {
                    case 1: {
                                lista.inserirInicio(Auxiliar.lerString("Nome para inserir: ",sc));
                                break;
                    }
                    case 2: {
                                lista.inserirFim(Auxiliar.lerString("Nome para inserir: ",sc));
                                break;
                    }
                    case 3: {
                                String nome = Auxiliar.lerString("Nome para inserir: ",sc);
                                int pos = Auxiliar.lerInt("Posição de inserção: ",sc);
                                lista.inserirPos(nome,pos);
                                break;
                    }
                    case 4: {
                                System.out.println("[REMOVIDO]: " + lista.removerInicio());
                                break;
                    }
                    case 5: {
                                System.out.println("[REMOVIDO]: " + lista.removerFim());
                                break;
                    }
                    case 6: {
                                int pos = Auxiliar.lerInt("Posição para remoção: ",sc);
                                System.out.println("[REMOVIDO]: " + lista.removerPos(pos));
                                break;
                    }
                    case 7: {
                                if (lista.pesquisar(Auxiliar.lerString("Nome para pesquisar: ",sc))) {
                                    System.out.println("Elemento encontrado na estrutura");
                                } else System.out.println ("Elemento não encontrado na estrutura");
                                break;
                    }
                    case 8: {
                                lista.imprimir();
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
        System.out.println("FIM: Lista Flexivel");
    }
}
