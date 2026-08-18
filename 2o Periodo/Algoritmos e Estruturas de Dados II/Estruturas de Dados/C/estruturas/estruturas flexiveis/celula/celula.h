#ifndef CELULA_H
#define CELULA_H

typedef struct celula{
    int elemento;
    struct celula *prox;
} Celula;

Celula* nova_celula(int x);

// Erros de Memoria
void underflow();
void overflow();

#endif //CELULA_H
