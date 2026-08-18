#include <stdio.h>
#include <stdlib.h>
#include "celula.h"

Celula* nova_celula(int x)
{
    Celula* nova = (Celula*)malloc(sizeof(Celula));
    if(nova == NULL){ printf("Err: Falha para alocar memória de nova célula\n"); }
    else{
        nova->elemento = x;
        nova->prox = NULL;
    }
    return nova;
}

void underflow() printf("Erro: Underflow\n");
void overflow() printf("Erro: Overflow\n");
