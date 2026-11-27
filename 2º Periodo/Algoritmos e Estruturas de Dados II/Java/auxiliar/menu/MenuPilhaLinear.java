package auxiliar.menu;

import auxiliar.Auxiliar;
import estrutura.linear.Pilha;

import java.util.Scanner;

public class MenuPilhaLinear
{
    private Pilha pilha;

    public MenuPilhaLinear (int cap) { pilha = new Pilha(cap); }

    public void executar (Scanner sc) {
        while (true) {
            int op = Menu.metodos(sc);
            System.out.println();
            if (op == 0) break;
            try {
                switch (op) {
                    case 1:{
                               pilha.inserir(Auxiliar.lerString("Nome para inserir: ",sc));
                               break;
                    }
                    case 2:{
                               System.out.println("[REMOVIDO]: " + pilha.remover());
                               break;
                    }
                    case 3:{
                               if (pilha.pesquisar(Auxiliar.lerString("Nome para pesquisar: ",sc))) {
                                   System.out.println("Nome encontrado na estrutura");
                               } else System.out.println("Nome não encontrado na estrutura");
                               break;
                    }
                    case 4:{
                               pilha.imprimir();
                               break;
                    }
                    default:{
                                Auxiliar.opcaoInvalida();
                                break;
                    }
                }
                System.out.println();
            } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
        }
        System.out.println("FIM: Pilha Linear");
    }
}
