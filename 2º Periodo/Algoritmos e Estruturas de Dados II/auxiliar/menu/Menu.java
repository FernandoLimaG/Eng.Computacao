package auxiliar.menu;

import java.util.Scanner;

public class Menu
{
    public static int principal (Scanner sc)
    {
        System.out.println("===== Menu Principal =====");
        System.out.println("01. Lista Linear\n02. Pilha Linear\n03. Fila Linear");
        System.out.println("04. Lista Flexível\n05. Pilha Flexível\n06. Fila Flexível");
        System.out.println("07. Árvore ABP\n08. Árvore AVL\n09. Árvore Trie");
        System.out.println("10. Tabela Hash c/ Reserva\n11. Tabela Hash c/ Rehash\n12. Tabela Hash Indireta c/ Lista Simples");
        System.out.print("00. Sair\nEscolha: ");
        return Integer.parseInt(sc.nextLine());
    }

    public static int metodos (Scanner sc)
    {
        System.out.println("===== Menu Métodos =====");
        System.out.print("1. Inserir\n2. Remover\n3. Pesquisar\n4. Imprimir\n0. Sair\nEscolha: ");
        return Integer.parseInt(sc.nextLine());
    }

    public static int metodosLista (Scanner sc)
    {
        System.out.println("===== Menu Métodos Lista =====");
        System.out.println("1. Inserir no Inicio\n2. Inserir no Fim\n3. Inserir em Posição");
        System.out.println("4. Remover no Início\n5. Remover no Fim\n6. Remover em Posição");
        System.out.print("7. Pesquisar\n8. Imprimir\n0. Sair\nEscolha: ");
        return Integer.parseInt(sc.nextLine());
    }
}
