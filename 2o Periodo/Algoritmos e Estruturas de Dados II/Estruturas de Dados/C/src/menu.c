#include "../include/menu.h"
#include <stdio.h>

// -- Geral --
int metodos(void)
{
    int op;
    printf("===== Métodos =====\n");
    printf("1. Inserir\n2. Remover\n3. Pesquisar\n4. Imprimir\n0. Sair\nEscolha: ");
    scanf("%d", &op);
    return op;
}

int estruturas(void)
{
    int op;
    printf("===== Estruturas =====\n");
    printf("1. TRIE\n0. Sair\nEscolha: ");
    scanf("%d", &op);
    return op;
}
