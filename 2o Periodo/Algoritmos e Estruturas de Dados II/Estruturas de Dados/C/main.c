#include "auxiliar/auxiliar.h"
#include "estruturas/arvores/abp/abp.h"
//#include "estruturas/arvores/avl/avl.h"
//#include "estruturas/arvores/alvinegra/alvinegra.h"
#include "estruturas/arvores/trie/trie.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void arvores()
{
    while(1){
        printf("===== ÁRVORES =====\n");
        int op = lerInt("1. ABP\n2. AVL\n3. ALVINEGRA\n4. TRIE\n0. Retornar\nEscolha: ");
        
        printf("\n");
        if(!op) break;

        switch(op){
            case 1:{
                       Abp a;
                       abp_inicializar(&a);
                       menuAbp(&a);
                       abp_liberar_memoria(a.raiz);
                       break;
                   }
                   /*
            case 2:{
                       Avl a;
                       avl_inicializar(&a);
                       menuAvl(&a);
                       avl_liberar_memoria(a.raiz);
                       break;
                   }
            case 3:{
                       Alvinegra a;
                       alvinegra_inicializar(&a);
                       menuAlvinegra(&a);
                       alvinegra_liberar_memoria(a.raiz);
                       break;
                   }
                   */
            case 4:{
                       Trie a;
                       trie_inicializar(&a);
                       menuTrie(&a);
                       trie_liberar_memoria(a.raiz);
                       break;
                   }
            default:{
                        opcaoInvalida();
                        break;
                    }
        }
        printf("\n");
    }
    printf("========== FIM: ÁRVORES ==========\n");
}


int main()
{
    while(1){
        printf("===== ALGORITMOS E ESTRUTURA DE DADOS =====\n");
        int op = menuEstruturas();
        
        printf("\n");
        if(!op) break;

        switch(op){
            case 1: printf("[INOP]"); break;
            case 2: printf("[INOP]"); break;
            case 3: arvores(); break;
            case 4: printf("[INOP]"); break;
            default: opcaoInvalida(); break;
        }
        printf("\n");
    }

    printf("FIM DO PROGRAMA\n");
    return 0;
}
