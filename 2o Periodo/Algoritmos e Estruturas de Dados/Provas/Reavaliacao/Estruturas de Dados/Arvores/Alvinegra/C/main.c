#include <stdio.h>
#include <stdlib.h>

typedef struct no No;
typedef struct arvore Arvore;

struct no{
    int elemento;
    int cor;
    No *esq, *dir;
};

struct arvore{
    No* raiz;
};

void l_rec(No* i){
    if(i != NULL){
        l_rec(i->esq);
        l_rec(i->dir);
        free(i);
    }
}

void liberar(Arvore* a){ l_rec(a->raiz); }

void inicializar(Arvore* a){ a->raiz = NULL; }

No* novo_no(int elemento){
    No* novo = (No*)malloc(sizeof(No));
    if(novo != NULL){
        novo->elemento = elemento;
        novo->cor = true;
        novo->esq = novo->dir = NULL;
    }
    return novo;
}

No* i_rec(int x, No* bisavo, No* avo, No* pai, No* i){
    if(pai != NULL){
        if(i == NULL){
            i = novo_no(elemento);
            
            if(elemento < pai->elemento){ pai->esq = i; }
            else{ pai->dir = i; }
        } else{
            if(elemento < i->elemento){ i->esq = i_rec(x, avo, pai, i, i->esq); }
            else if(elemento > i->elemento){ i->dir = i_rec(x, avo, pai, i, i->dir); }
            else{ printf("Erro de insercao: Duplicata\n"); }
        }
        if(avo != NULL){
            if(pai->elemento > avo->elemento){ avo->dir = pai; }
            else{ avo->esq = pai; }
        }
    }
    return i;
}
