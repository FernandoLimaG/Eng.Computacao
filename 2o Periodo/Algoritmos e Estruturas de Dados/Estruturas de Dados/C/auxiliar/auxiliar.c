#include "auxiliar.h"
#include "../estruturas/arvores/abp/abp.h"
//#include "../estruturas/arvores/avl/avl.h"
//#include "../estruturas/arvores/alvinegra/alvinegra.h"
#include "../estruturas/arvores/trie/trie.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void opcaoInvalida(){ printf("Opção inválida, tente novamente\n"); }

int menuMetodos()
{
    int op;
    printf("===== MENU METODOS =====\n");
    printf("1. Inserir\n2. Remover\n3. Pesquisar\n4. Imprimir\n0. Sair\nEscolha: ");
    scanf("%d", &op);
    return op;
}

int menuEstruturas()
{
    int op;
    printf("===== MENU ESTRUTURAS =====\n");
    printf("1. Estruturas Estaticas\n2. Esturutras Flexiveis\n3. Arvores\n4. Tabelas\n0. Retornar\nEscolha: ");
    scanf("%d", &op);
    return op;
}

void menuAbp(Abp* abp)
{
    while(1){
        int op = menuMetodos();
        printf("\n");
        if(!op) break;
        switch(op){
            case 1:{
                       int x = lerInt("Elemento para inserir: ");
                       abp_inserir(x, abp);
                       break;
                   }
            case 2:{
                       int x = lerInt("Elemento para remover: ");
                       abp_remover(x, abp);
                       break;
                   }
            case 3:{
                       int x = lerInt("Elemento para pesquisar: ");
                       if(abp_pesquisar(x, abp)){ printf("Elemento encontrado\n"); }
                       else{ printf("Elemento não encontrado\n"); }
                       break;
                   }
            case 4:{
                       abp_imprimir(abp);
                       break;
                   }
            default:{
                        opcaoInvalida();
                        break;
                    }
        }
        printf("\n");
    }
    printf("===== FIM: ABP =====\n");
}

void menuTrie(Trie* trie)
{
    while(1){
        int op = menuMetodos();
        printf("\n");
        if(!op) break;
        switch(op){
            case 1:{
                       char* x = lerString("Elemento para inserir: ");
                       trie_inserir(x, trie);
                       free(x);
                       break;
                   }
            case 2:{
                       char* x = lerString("Elemento para remover: ");
                       char* resp = trie_remover(x, trie);
                       if(resp != NULL) printf("REMOVIDO: %s\n", resp);
                       else printf("Nome não encontrado\n");
                       free(x);
                       break;
                   }
            case 3:{
                       char* x = lerString("Elemento para pesquisar: ");
                       if(trie_pesquisar(x, trie)){ printf("Nome encontrado\n"); }
                       else{ printf("Nome não encontrado\n"); }
                       free(x);
                       break;
                   }
            case 4:{
                       printf("===== TRIE =====\n");
                       trie_imprimir(trie);
                       break;
                   }
            default:{
                        opcaoInvalida();
                        break;
                    }
        }
        printf("\n");
    }
    printf("===== FIM: TRIE =====\n");
}

char* lerString(char* s)
{
    char buffer[101];
    char* str;

    printf("%s", s);
    scanf(" %100[^\n]", buffer);

    int len = strlen(buffer) + 1;
    str = (char*)malloc(len * sizeof(char));
    if(str != NULL){ strcpy(str, buffer); }
    return str;
}

int lerInt(char* s)
{
    int op;
    printf("%s", s);
    scanf("%d", &op);
    return op;
}
