#include "abp.h"
#include <stdio.h>
#include <stdlib.h>

void abp_liberar_memoria(NoAbp* no)
{
    if(no != NULL){
        abp_liberar_memoria(no->esq);
        abp_liberar_memoria(no->dir);
        free(no);
    }
}

void abp_inicializar(Abp* a)
{
    if(a != NULL){ a->raiz = NULL; }
}

NoAbp* abp_novo_no(int x)
{
    NoAbp* novo = (NoAbp*)malloc(sizeof(NoAbp));
    if(novo == NULL){ printf("Erro de Alocação: Novo No ABP\n"); }
    else{
        novo->elemento = x;
        novo->esq = novo->dir = NULL;
    }
    return novo;
}

NoAbp* abp_inserir_rec(int x, NoAbp* no)
{
    if(no == NULL) no = abp_novo_no(x);
    else if(x < no->elemento) no->esq = abp_inserir_rec(x, no->esq);
    else if(x > no->elemento) no->dir = abp_inserir_rec(x, no->dir);
    else{
        printf("Erro de inserção: Duplicatas não permitidas");
        no = NULL;
    }
    return no;
}

void abp_inserir(int x, Abp* a)
{
    if(a != NULL){ a->raiz = abp_inserir_rec(x, a->raiz); }
}

int abp_pesquisar_rec(int x, NoAbp* no)
{
    int resp;
    if(no == NULL) resp = 0;
    else if(x == no->elemento) resp = 1;
    else if(x < no->elemento) resp = abp_pesquisar_rec(x, no->esq);
    else resp = abp_pesquisar_rec(x, no->esq);
    return resp;
}

int abp_pesquisar(int x, Abp* a)
{
    int resp;
    if(a != NULL) resp = abp_pesquisar_rec(x, a->raiz);
    else resp = 0;
    return resp;
}

//Metodos auxiliares para remoção de elemento
NoAbp* getSucessor(NoAbp* i)
{
    i = i->dir;
    while(i != NULL && i->esq != NULL) i = i->esq;
    return i;
}

int abp_dois_filhos(NoAbp* i)
{
    int resp = 1;
    if(i->esq == NULL || i->dir == NULL) resp = 0;
    return resp;
}

NoAbp* abp_remover_rec(int x, NoAbp* i)
{
    NoAbp* resp;
    if(i == NULL) resp = NULL;
    else if(x < i->elemento) resp = abp_remover_rec(x, i->esq);
    else if(x > i->elemento) resp = abp_remover_rec(x, i->dir);
    else{
        if(abp_dois_filhos(i)){
            NoAbp* tmp = getSucessor(i);
            i->elemento = tmp->elemento;
            i->dir = abp_remover_rec(tmp->elemento, i->dir);
        } else{
            if(i->dir == NULL) resp = i->esq;
            if(i->esq == NULL) resp = i->dir;
            free(i);
        }
    }
    return i;
}

void abp_remover(int x, Abp* a)
{
    if(a != NULL && abp_pesquisar_rec(x, a->raiz)){
        a->raiz = abp_remover_rec(x, a->raiz);
        printf("Elemento removido\n");
    }
}

void abp_central(NoAbp* i)
{
    if(i != NULL){
        abp_central(i->esq);
        printf("%d ", i->elemento);
        abp_central(i->dir);
    }
}

void abp_pre(NoAbp* i)
{
    if(i != NULL){
        printf("%d ", i->elemento);
        abp_pre(i->esq);
        abp_pre(i->dir);
    }
}

void abp_pos(NoAbp* i)
{
    if(i != NULL){
        abp_pos(i->esq);
        abp_pos(i->dir);
        printf("%d ", i->elemento);
    }
}

void abp_imprimir(Abp* a)
{
    int op;
    do{
        printf("===== CAMINHAMENTO =====\n");
        printf("1. Em Ordem\n2. Pre Ordem\n3. Pos Ordem\n0. Retornar\nEscolha: ");
        scanf("%d", &op);
        if(op < 0 || op > 3) printf("Opção inválida, tente novamente\n\n");
    }while(op < 0 || op > 3);

    printf("\n");
    if(!op) return;

    printf("===== ABP =====\n");
    switch(op){
        case 1:printf("ABP EM ORDEM: "); abp_central(a->raiz); break;
        case 2:printf("ABP PRE ORDEM: "); abp_pre(a->raiz); break;
        case 3:printf("ABP PÓS ORDEM: "); abp_pos(a->raiz); break;
    }
    printf("\n");
}
