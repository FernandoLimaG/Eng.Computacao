package auxiliar.menu;

import auxiliar.Auxiliar;
import estrutura.tabela.Rehash;
import estrutura.tabela.Reserva;

import java.util.Scanner;

public class MenuTabela
{
    private Rehash rehash;
    private Reserva reserva;

    public MenuTabela (int main, int reserva) {
        this.rehash = null;
        this.reserva = new Reserva(main,reserva);
    }

    public MenuTabela (int tam) {
        this.reserva = null;
        this.rehash = new Rehash(tam);
    }

    public void executar (Scanner sc) {
        while (true) {
            int op = Menu.metodos(sc);
            System.out.println();
            if (op == 0) break;
            try {
                switch (op) {
                    case 1:{
                               String nome = Auxiliar.lerString("Nome para inserir: ",sc);
                               if (reserva != null) reserva.inserir(nome);
                               else rehash.inserir(nome);
                               break;
                    }
                    case 2:{
                               String nome = Auxiliar.lerString("Nome para remover: ",sc);
                               if (reserva != null) nome = reserva.remover(nome);
                               else nome = rehash.remover(nome);

                               if (nome != null) System.out.println("[REMOVIDO]: " + nome);
                               else System.out.println("Nome não encontrado na tabela");
                               break;
                    }
                    case 3:{
                               int pos = -1;
                               String nome = Auxiliar.lerString("Nome para buscar: ",sc);
                               if (reserva != null) pos = reserva.pesquisar(nome);
                               else pos = rehash.pesquisar(nome);

                               if (pos != -1) System.out.println("Nome encontrado || Índice: " + pos);
                               else System.out.println("Nome não encontrado na tabela");
                               break;
                    }
                    case 4:{
                               if (reserva != null) reserva.imprimir();
                               else rehash.imprimir();
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
        System.out.println("FIM: Tabela Hash c/ " + (reserva == null ? "Rehash" : "Reserva"));
    }
}
