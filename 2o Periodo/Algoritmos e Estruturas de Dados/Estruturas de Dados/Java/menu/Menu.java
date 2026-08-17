package menu;

//Bibliotecas Custom + Padrão

//1.: Estruturas
//1.1.: Lineares
import lineares.lista.*;
import lineares.fila.*;
import lineares.pilha.*;

//1.2.: Flexiveis 
import flexiveis.lista.simples.*;
//import flexiveis.lista.dupla.*;
import flexiveis.fila.*;
import flexiveis.pilha.*;

//1.3.: Arvores
import arvores.abp.*;
import arvores.trie.*;

//1.4.: Tabelas
import tabelas.reserva.*;
import tabelas.rehash.*;
//import tabelas.indireta.*;

//2. Auxiliares
import auxiliar.*;
import customException.*;

//3. Padrão
import java.util.Scanner;

public class Menu
{
    public static int metodos(Scanner sc){
        System.out.println("===== MENU MÉTODOS =====");
        System.out.print("1. Inserir\n2. Remover\n3. Pesquisar\n4. Imprimir\n0. Sair\nEscolha: ");
        return Integer.parseInt(sc.nextLine());
    }

    public static int metodosLista(Scanner sc){
        System.out.println("===== MENU MÉTODOS =====");
        System.out.println("1. Inserir Inicio\n2. Inserir Fim\n3. Inserir em Posição"); 
        System.out.println("4. Remover Inicio\n5. Remover Fim\n6. Remover em Posição"); 
        System.out.print("7. Pesquisar\n8. Imprimir\n0. Sair\nEscolha: ");
        return Integer.parseInt(sc.nextLine());
    }

    public static int estruturas(Scanner sc){
        System.out.println("===== MENU ESTRUTURAS =====");
        System.out.print("1. Lineares\n2. Flexiveis\n3. Arvores\n4. Tabelas\n0. Sair\nEscolha: ");
        return Integer.parseInt(sc.nextLine());
    }

    public static void lineares(Scanner sc)
    {
        while(true){
            System.out.println("===== ESTRUTURAS LINEARES =====");
            int op = Auxiliar.lerInt("1. Lista\n2. Pilha\n3. Fila\n0. Sair\nEscolha: ", sc);
            System.out.println();
            if(op == 0) break;
            switch(op){
                case 1:{
                           Lista l = new Lista();
                           lista(l, sc);
                           break;
                }
                case 2:{
                           Pilha p = new Pilha();
                           pilha(p, sc);
                           break;
                }
                case 3:{
                           Fila f = new Fila();
                           fila(f, sc);
                           break;
                }
                default:{
                            Auxiliar.opcaoInvalida();
                            break;
                }
            }
            System.out.println();
        }
    }

    public static void flexiveis(Scanner sc)
    {
        while(true){
            System.out.println("===== ESTRUTURAS FLEXÍVEIS =====");
            int op = Auxiliar.lerInt("1. Lista Simples\n2. Lista Dupla\n3. Pilha\n4. Fila\n0. Sair\nEscolha: ", sc);
            System.out.println();
            if(op == 0) break;
            switch(op){
                case 1:{
                           ListaSimples l = new ListaSimples();
                           listaSimples(l, sc);
                           break;
                }
                case 2:{
                           /*
                           ListaFlex l = new ListaFlex();
                           listaflex(l, sc);
                           */
                           break;
                }
                case 3:{
                           PilhaFlex p = new PilhaFlex();
                           pilhaflex(p, sc);
                           break;
                }
                case 4:{
                           FilaFlex f = new FilaFlex();
                           filaflex(f, sc);
                           break;
                }
                default:{
                            Auxiliar.opcaoInvalida();
                            break;
                }
            }
            System.out.println();
        }
    }

    public static void lista(Lista l, Scanner sc)
    {
        while(true){
            int op = metodosLista(sc);
            System.out.println();
            if(op == 0) break;

            try{
                switch(op){
                    case 1:{
                               int x = Auxiliar.lerInt("Elemento para inserir: ", sc);
                               l.inserirInicio(x);
                               break;
                    }
                    case 2:{
                               int x = Auxiliar.lerInt("Elemento para inserir: ", sc);
                               l.inserirFim(x);
                               break;
                    }
                    case 3:{
                               int x = Auxiliar.lerInt("Elemento para inserir: ", sc);
                               int pos = Auxiliar.lerInt("Posição (índice) para inserir: ", sc);
                               l.inserirPos(x, pos);
                               break;
                    }
                    case 4:{
                               System.out.println("[REMOVIDO]: " + l.removerInicio());
                               break;
                    }
                    case 5:{
                               System.out.println("[REMOVIDO]: " + l.removerFim());
                               break;
                    }
                    case 6:{
                               int pos = Auxiliar.lerInt("Posição (índice) para remover: ", sc);
                               System.out.println("[REMOVIDO]: " + l.removerPos(pos));
                               break;
                    }
                    case 7:{
                               int x = Auxiliar.lerInt("Elemento para pesquisar: ", sc);
                               if(l.pesquisar(x)){ System.out.println("Elemento encontrado"); }
                               else{ System.out.println("Elemento não encontrado"); }
                               break;
                    }
                    case 8:{
                               l.imprimir();
                               break;
                    }
                    default:{
                                Auxiliar.opcaoInvalida();
                                break;
                    }
                }
            }catch(Exception e){ System.out.println(e.getMessage()); }
            System.out.println();
        }
        System.out.println("===== FIM: Lista Estática =====");
    }

    public static void pilhaflex(PilhaFlex p, Scanner sc)
    {
        while(true){
            int op = metodos(sc);
            System.out.println();
            if(op == 0) break;

            try{
                switch(op){
                    case 1:{
                               int x = Auxiliar.lerInt("Elemento para inserir: ", sc);
                               p.inserir(x);
                               break;
                    }
                    case 2:{
                               System.out.println("[REMOVIDO]: " + p.remover());
                               break;
                    }
                    case 3:{
                               int x = Auxiliar.lerInt("Elemento para pesquisar: ", sc);
                               if(p.pesquisar(x)){ System.out.println("Elemento encontrado"); }
                               else{ System.out.println("Elemento não encontrado"); }
                               break;
                    }
                    case 4:{
                               p.imprimir();
                               break;
                    }
                    default:{
                                Auxiliar.opcaoInvalida();
                                break;
                    }
                }
            }catch(Exception e){ System.out.println(e.getMessage()); }
            System.out.println();
        }
        System.out.println("===== FIM: Pilha Flexível =====");
    }

    public static void filaflex(FilaFlex f, Scanner sc)
    {
        while(true){
            int op = metodos(sc);
            System.out.println();
            if(op == 0) break;

            try{
                switch(op){
                    case 1:{
                               int x = Auxiliar.lerInt("Elemento para inserir: ", sc);
                               f.inserir(x);
                               break;
                    }
                    case 2:{
                               System.out.println("[REMOVIDO]: " + f.remover());
                               break;
                    }
                    case 3:{
                               int x = Auxiliar.lerInt("Elemento para pesquisar: ", sc);
                               if(f.pesquisar(x)){ System.out.println("Elemento encontrado"); }
                               else{ System.out.println("Elemento não encontrado"); }
                               break;
                    }
                    case 4:{
                               f.imprimir();
                               break;
                    }
                    default:{
                                Auxiliar.opcaoInvalida();
                                break;
                    }
                }
            }catch(Exception e){ System.out.println(e.getMessage()); }
            System.out.println();
        }
        System.out.println("===== FIM: Fila Flexível =====");
    }

    public static void pilha(Pilha p, Scanner sc)
    {
        while(true){
            int op = metodos(sc);
            System.out.println();
            if(op == 0) break;

            try{
                switch(op){
                    case 1:{
                               int x = Auxiliar.lerInt("Elemento para inserir: ", sc);
                               p.inserir(x);
                               break;
                    }
                    case 2:{
                               System.out.println("[REMOVIDO]: " + p.remover());
                               break;
                    }
                    case 3:{
                               int x = Auxiliar.lerInt("Elemento para pesquisar: ", sc);
                               if(p.pesquisar(x)){ System.out.println("Elemento encontrado"); }
                               else{ System.out.println("Elemento não encontrado"); }
                               break;
                    }
                    case 4:{
                               p.imprimir();
                               break;
                    }
                    default:{
                                Auxiliar.opcaoInvalida();
                                break;
                    }
                }
            }catch(Exception e){ System.out.println(e.getMessage()); }
            System.out.println();
        }
        System.out.println("===== FIM: Pilha Estática =====");
    }

    public static void fila(Fila f, Scanner sc)
    {
        while(true){
            int op = metodos(sc);
            System.out.println();
            if(op == 0) break;

            try{
                switch(op){
                    case 1:{
                               int x = Auxiliar.lerInt("Elemento para inserir: ", sc);
                               f.inserir(x);
                               break;
                    }
                    case 2:{
                               System.out.println("[REMOVIDO]: " + f.remover());
                               break;
                    }
                    case 3:{
                               int x = Auxiliar.lerInt("Elemento para pesquisar: ", sc);
                               if(f.pesquisar(x)){ System.out.println("Elemento encontrado"); }
                               else{ System.out.println("Elemento não encontrado"); }
                               break;
                    }
                    case 4:{
                               f.imprimir();
                               break;
                    }
                    default:{
                                Auxiliar.opcaoInvalida();
                                break;
                    }
                }
            }catch(Exception e){ System.out.println(e.getMessage()); }
            System.out.println();
        }
        System.out.println("===== FIM: Fila Estática =====");
    }
    // ======================== FIM: Estruturas Lineares ========================

    public static void listaSimples(ListaSimples l, Scanner sc)
    {
        while(true){
            int op = metodosLista(sc);
            System.out.println();
            if(op == 0) break;

            try{
                switch(op){
                    case 1:{
                               int x = Auxiliar.lerInt("Elemento para inserir: ", sc);
                               l.inserirInicio(x);
                               break;
                    }
                    case 2:{
                               int x = Auxiliar.lerInt("Elemento para inserir: ", sc);
                               l.inserirFim(x);
                               break;
                    }
                    case 3:{
                               int x = Auxiliar.lerInt("Elemento para inserir: ", sc);
                               int pos = Auxiliar.lerInt("Posição (índice) para inserir: ", sc);
                               l.inserirPos(x, pos);
                               break;
                    }
                    case 4:{
                               System.out.println("[REMOVIDO]: " + l.removerInicio());
                               break;
                    }
                    case 5:{
                               System.out.println("[REMOVIDO]: " + l.removerFim());
                               break;
                    }
                    case 6:{
                               int pos = Auxiliar.lerInt("Posição (índice) para remover: ", sc);
                               System.out.println("[REMOVIDO]: " + l.removerPos(pos));
                               break;
                    }
                    case 7:{
                               int x = Auxiliar.lerInt("Elemento para pesquisar: ", sc);
                               if(l.pesquisar(x)){ System.out.println("Elemento encontrado"); }
                               else{ System.out.println("Elemento não encontrado"); }
                               break;
                    }
                    case 8:{
                               l.imprimir();
                               break;
                    }
                    default:{
                                Auxiliar.opcaoInvalida();
                                break;
                    }
                }
            }catch(Exception e){ System.out.println(e.getMessage()); }
            System.out.println();
        }
        System.out.println("===== FIM: Lista Simplesmente Encadeada =====");
    }
    // ======================== FIM: Estruturas Flexiveis ========================
    
    public static void arvores(Scanner sc)
    {
        while(true){
            System.out.println("===== ÁRVORES =====");
            int op = Auxiliar.lerInt("1. Abp\n2. Avl\n3. Alvinegra\n4. Trie\n0. Sair\nEscolha: ", sc);
            System.out.println();
            if(op == 0) break;
            switch(op){
                case 1:{
                           Abp a = new Abp();
                           abp(a, sc);
                           break;
                }
                case 2:{
                           System.out.println("[INOP]");
                           break;
                } 
                case 3:{
                           System.out.println("[INOP]");
                           break;
                } 
                case 4:{
                           Trie a = new Trie();
                           trie(a, sc);
                           break;
                }
                default:{
                            Auxiliar.opcaoInvalida();
                            break;
                }
            }
            System.out.println();
        }
    }

    public static void abp(Abp a, Scanner sc)
    {
        while(true){
            int op = metodos(sc);
            System.out.println();
            if(op == 0) break;
            switch(op){
                case 1:{
                           int x = Auxiliar.lerInt("Elemento para inserir: ", sc);
                           a.inserir(x);
                           break;
                }
                case 2: {
                            System.out.println("[INOP]");
                            break;
                }
                case 3:{
                           int x = Auxiliar.lerInt("Elemento para pesquisar: ", sc);
                           if(a.pesquisar(x)){ System.out.println("Elemento encontrado"); }
                           else{ System.out.println("Elemento não encontrado"); }
                           break;
                }
                case 4:{
                           a.imprimir(sc);
                           break;
                }
                default:{
                            Auxiliar.opcaoInvalida();
                            break;
                }
            }
            System.out.println();
        }
        System.out.println("===== FIM: Arvore ABP =====");
    }

    public static void trie(Trie trie, Scanner sc)
    {
        while(true){
            int op = metodos(sc);
            System.out.println();
            if(op == 0) break;
            switch(op){
                case 1:{
                           String s = Auxiliar.lerString("Nome para inserir: ", sc);
                           trie.inserir(s);
                           break;
                }
                case 2: {
                            System.out.println("[INOP]");
                            break;
                }
                case 3:{
                           String s = Auxiliar.lerString("Nome para pesquisar: ", sc);
                           if(trie.pesquisar(s)){ System.out.println("Nome encontrado"); }
                           else{ System.out.println("Nome não encontrado"); }
                           break;
                }
                case 4:{
                           trie.imprimir();
                           break;
                }
                default:{
                            Auxiliar.opcaoInvalida();
                            break;
                }
            }
            System.out.println();
        }
        System.out.println("===== FIM: Arvore TRIE =====");
    }
    // ======================== FIM: ÁRVORES ========================

    public static void tabelas(Scanner sc)
    {
        while(true){
            System.out.println("===== TABELAS =====");
            int op = Auxiliar.lerInt("1. Hash-Reserva\n2. Hash-Rehash\n3. Indiretas\n0. Sair\nEscolha: ", sc);
            System.out.println();
            if(op == 0) break;
            switch(op){
                case 1:{
                           Reserva t = new Reserva();
                           reserva(t, sc);
                           break;
                }
                case 2:{
                           Rehash t = new Rehash();
                           rehash(t, sc);
                           break;
                } 
                case 3:{
                           System.out.println("[INOP]");
                           break;
                } 
                default:{
                            Auxiliar.opcaoInvalida();
                            break;
                }
            }
            System.out.println();
        }
    }

    public static void reserva(Reserva t, Scanner sc)
    {
        while(true){
            int op = metodos(sc);
            System.out.println();
            if(op == 0) break;
            try{
                switch(op){
                    case 1:{
                               String s = Auxiliar.lerString("Nome para inserir: ", sc);
                               t.inserir(s);
                               break;
                    }
                    case 2: {
                                String s = Auxiliar.lerString("Nome para remover: ", sc);
                                s = t.remover(s);
                                if(s == null) System.out.println("Nome não encontrado");
                                else  System.out.println("[REMOVIDO]: " + s);
                                break;
                    }
                    case 3:{
                               String s = Auxiliar.lerString("Nome para pesquisar: ", sc);
                               if(t.pesquisar(s) > -1){ System.out.println("Nome encontrado"); }
                               else{ System.out.println("Nome não encontrado"); }
                               break;
                    }
                    case 4:{
                               t.imprimir();
                               break;
                    }
                    default:{
                                Auxiliar.opcaoInvalida();
                                break;
                    }
                }
            }catch(Exception e){ System.out.println(e.getMessage()); }
            System.out.println();
        }
        System.out.println("===== FIM: Tabela Hash c/ Reserva =====");
    }

    public static void rehash(Rehash t, Scanner sc)
    {
        while(true){
            int op = metodos(sc);
            System.out.println();
            if(op == 0) break;
            try{
                switch(op){
                    case 1:{
                               String s = Auxiliar.lerString("Nome para inserir: ", sc);
                               t.inserir(s);
                               break;
                    }
                    case 2: {
                                String s = Auxiliar.lerString("Nome para remover: ", sc);
                                s = t.remover(s);
                                if(s == null) System.out.println("Nome não encontrado");
                                else  System.out.println("[REMOVIDO]: " + s);
                                break;
                    }
                    case 3:{
                               String s = Auxiliar.lerString("Nome para pesquisar: ", sc);
                               if(t.pesquisar(s) > -1){ System.out.println("Nome encontrado"); }
                               else{ System.out.println("Nome não encontrado"); }
                               break;
                    }
                    case 4:{
                               t.imprimir();
                               break;
                    }
                    default:{
                                Auxiliar.opcaoInvalida();
                                break;
                    }
                }
            }catch(Exception e){ System.out.println(e.getMessage()); }
            System.out.println();
        }
        System.out.println("===== FIM: Tabela Hash c/ Rehash =====");
    }
}
