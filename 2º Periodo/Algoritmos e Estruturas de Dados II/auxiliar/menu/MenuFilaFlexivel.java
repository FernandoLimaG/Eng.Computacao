package auxiliar.menu;

import auxiliar.Auxiliar;
import estrutura.flexivel.Fila;
import estrutura.flexivel.Celula;

import java.util.Scanner;

public class MenuFilaFlexivel
{
    private Fila fila;

    public MenuFilaFlexivel () { fila = new Fila(); }

    public void executar (Scanner sc) {
        while (true) {
            int op = Menu.metodos(sc);
            System.out.println();
            if (op == 0) break;
            try {
                switch (op) {
                    case 1:{
                               fila.inserir(Auxiliar.lerString("Nome para inserir: ",sc));
                               break;
                    }
                    case 2:{
                               System.out.println("[REMOVIDO]: " + fila.remover());
                               break;
                    }
                    case 3:{
                               if (fila.pesquisar(Auxiliar.lerString("Nome para pesquisar: ",sc))) {
                                   System.out.println("Nome encontrado na estrutura");
                               } else System.out.println("Nome não encontrado na estrutura");
                               break;
                    }
                    case 4:{
                               fila.imprimir();
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
        System.out.println("FIM: Fila Flexível");
    }
}
