#include "vetor.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "../include/auxiliar.h"

int* create_int_arr(int n)
{
    int* v = NULL;
    
    if(n <= 0){ IllegalArgumentException("Capacidade inválida"); }
    else{
        v = (int*)malloc(n * sizeof(int));
        
        if(v == NULL){ mallocErr("User_[int]_array"); }
        else{
            for(int i = 0; i < n; i++){
                printf("%dº elemento: ", i+1);
                scanf("%d", &v[i]);
            }
            printf("Vetor de inteiros gerado com sucesso\n");
        }
    }
    return v;
}

int* random_int_arr(int n, int inf, int sup)
{
    int* v = NULL;
    
    if(n <= 0){ IllegalArgumentException("Capacidade inválida"); }
    else if(inf == sup){ IllegalArgumentException("Limites não estabelecem sistema funcional"); }
    else if(inf > sup){
        printf("Limite superior < limite inferior || Realizando troca inferior <-> superior\n");
        int tmp = inf;
        inf = sup;
        sup = tmp;
    } else{
        v = (int*)malloc(n * sizeof(int));
        
        if(v == NULL){ mallocErr("User_[int]_array"); }
        else{
            for(int i = 0; i < n; i++) v[i] = (rand() % sup - inf + i) + inf;
            printf("Vetor de inteiros gerado com sucesso\n");
        }
    }
    return v;
}

void print_int_arr(int* v, int n)
{
    if(v == NULL) IllegalArgumentException("Vetor NULO\n");
    else if(n <= 0) IllegalArgumentException("Tamanho inválido\n");
    else{
        printf("[");
        for(int i = 0; i < n; i++){
            printf("%d", v[i]);
            if(i < n-1) printf(", ");
        }
        printf("]\n");
    }
}
