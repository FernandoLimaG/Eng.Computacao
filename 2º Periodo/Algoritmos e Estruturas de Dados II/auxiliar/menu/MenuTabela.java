package auxiliar.menu;

import auxiliar.Auxiliar;
import estrutura.tabela.*;

import java.util.Scanner;

public class MenuTabela
{
    //private Rehash rehash;
    private Reserva reserva;
    //private Indireta indireta;

    public MenuTabela (int main, int reserva) {
        //this.rehash = null;
        //this.indireta = null;
        this.reserva = new Reserva(main,reserva);
    }

    /*
    public MenuTabela (int tam) {
        this.reserva = null;
        this.rehash = new Rehash(tam);
        this.indireta = null;
    }

    public MenuTabela () {
        this.rehash = null;
        this.reserva = null;
        this.indireta = new Indireta();
    }
    */

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
                               //else if (rehash != null) rehash.inserir(nome);
                               //else indireta.inserir(nome);
                               break;
                    }
                    case 2:{
                               String nome = Auxiliar.lerString("Nome para remover: ",sc);
                               if (reserva != null) nome = reserva.remover(nome);
                               //else if (rehash != null) nome = rehash.remover(nome);
                               //else nome = indireta.remover(nome);

                               if (nome != null) System.out.println("[REMOVIDO]: " + nome);
                               else System.out.println("Nome não encontrado na tabela");
                               break;
                    }
                    case 3:{
                               int pos = -1;
                               String nome = Auxiliar.lerString("Nome para buscar: ",sc);
                               if (reserva != null) pos = reserva.pesquisar(nome);
                               //else if (rehash != null) pos = rehash.pesquisar(nome);
                               //else pos = indireta.pesquisar(nome);

                               if (pos != -1) System.out.println("Nome encontrado || Índice: " + pos);
                               else System.out.println("Nome não encontrado na tabela");
                               break;
                    }
                    case 4:{
                               if (reserva != null) reserva.imprimir();
                               //else if (rehash != null) rehash.imprimir();
                               //else indireta.imprimir();
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
        System.out.println("FIM: Tabela Hash");
    }
}
