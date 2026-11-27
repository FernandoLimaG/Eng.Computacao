package auxiliar.menu;

import auxiliar.Auxiliar;
import estrutura.tabela.Indireta;

import java.util.Scanner;

public class MenuTabelaIndireta
{
    private Indireta tabela;

    public MenuTabelaIndireta (int cap) { this.tabela = new Indireta(cap); }

    public void executar (Scanner sc) {
        while (true) {
            int op = Menu.metodos(sc);
            System.out.println();
            if (op == 0) break;
            try {
                switch (op) {
                    case 1:{
                               String nome = Auxiliar.lerString("Nome para inserir: ",sc);
                               tabela.inserir(nome);
                               break;
                    }
                    case 2:{
                               String nome = Auxiliar.lerString("Nome para remover: ",sc);
                               nome = tabela.remover(nome);
                               if (nome != null) System.out.println("[REMOVIDO]: " + nome);
                               else System.out.println("Nome não encontrado na tabela");
                               break;
                    }
                    case 3:{
                               String nome = Auxiliar.lerString("Nome para buscar: ",sc);
                               int pos = tabela.pesquisar(nome);
                               if (pos != -1) System.out.println("Nome encontrado || Índice Tabela: " + pos);
                               else System.out.println("Nome não encontrado na tabela");
                               break;
                    }
                    case 4:{
                               tabela.imprimir();
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
        System.out.println("FIM: Tabela Hash Indireta");
    }
}
