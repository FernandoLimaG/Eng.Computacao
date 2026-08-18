#ifndef FLEXPILHA_H
#define FLEXPILHA_H

#include "../celula/celula.h"

typedef struct pilha_flex{
    Celula* primeiro;
    Celula* ultimo;
} FlexPilha;

void flex_pilha_inicializar(FlexPilha* p);
void flex_pilha_inserir(int x, FlexPilha* p);
int flex_pilha_remover(FlexPilha* p);
int flex_pilha_pesquisar(int x, FlexPilha* p);
void flex_pilha_imprimir(FlexPilha* p);

#endif //FLEXPILHA_H
