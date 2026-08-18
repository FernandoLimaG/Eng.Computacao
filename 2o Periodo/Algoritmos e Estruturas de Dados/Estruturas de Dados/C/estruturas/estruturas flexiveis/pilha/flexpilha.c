#include <stdio.h>
#include <stdlib.h>
#include "flexpilha.h"
#include "../celula/celula.h"

#define FLAG -999999

int is_empty(FlexPilha* p) return p->ultimo == p->primeiro ? 1 : 0;

void flex_pilha_inicializar(FlexPilha* p){
    if(p != NULL){
        //Implementação de Pilha com célula cabeça e valor abitrário "-1"
        p->primeiro = nova_celula(-1);
        p->ultimo = p->primeiro;
    }
}

void flex_pilha_inserir(int x, FlexPilha* p){
    if(p != NULL){
        p->ultimo->prox = nova_celula(x);
        p->ultimo = p->ultimo->prox;
    }
}

int flex_pilha_remover(FlexPilha* p)
{
    int out;
    if(p == NULL || is_empty(p)){ out = FLAG; }
    else{
        out = p->ultimo->elemento;
        Celula* tmp;
        for(tmp = p->primeiro; tmp != NULL && tmp->prox != p->ultimo; tmp = tmp->prox);
        p->ultimo = tmp;
        p->ultimo->prox = tmp->prox = NULL;
        tmp = NULL;
    }
    return out;
}

int p_rec(int x, Celula* i)
{
    int resp;
    if(i == NULL) resp = 0;
    else if(x == i->elemento) resp = 1;
    else resp = p_rec(x, i->prox);
    return resp;
}

int flex_pilha_pesquisar(int x, FlexPilha* p)
{
    int resp;
    if(p == NULL || is_empty(p)){ out = FLAG; }
    else resp = p_rec(int x, p->primeiro->prox);
    return resp;
}

void print_rec(Celula* i)
{
    if(i != NULL){
        print_rec(i->prox);
        printf("%d ", i->elemento);
    }
}

void flex_pilha_imprimir(FlexPilha* p)
{
    if(p == NULL) printf("Pilha Flexível NULL\n");
    else if(is_empty(p)) printf("*** PILHA VAZIA ***\n");
    else{
        printf("===== PILHA FLEXÍVEL ======\n");
        print_rec(p->primeiro->prox);
        printf("\n");
    }
}
