#include <stdio.h>
#include <stdlib.h>

#define nllErr "Err: Estrutura nula\n"
#define mlcErr "FATAL-Err: Falha para alocar memoria\n"

typedef struct no{
    int elemento;
    int nivel;
    struct no *esq, *dir;
} No;

typedef struct arvore{
    No* raiz;
} Arvore;

void lib_rec(No* i){
    if(i != NULL){
        lib_rec(i->esq);
        lib_rec(i->dir);
        free(i);
    }
}

void liberar(Arvore* a){
    if(a != NULL){ lib_rec(a->raiz); }
}

void inicializar(Arvore* a){ a->raiz = NULL; }

No* novo_no(int x){
    No* novo = (No*)malloc(sizeof(No));
    if(novo == NULL){ printf(nllErr); }
    else{
        novo->elemento = x;
        novo->nivel = 1;
        novo->esq = novo->dir = NULL;
    }
    return novo;
}

/* ===== Metodos AVL =====
 * 1. Metodos ABP
 *  - 1.1. Inserir (publico + privado recursivo)
 *  - 1.2. Pesquisar (publico + privado recursivo)
 *  - 1.3. Imprimir (publico geral + privados caminhamentos: em ordem, pre ordem e pos ordem)
 *
 * 2. Metodos AVL (privados)
 *  - 2.1. Get Nivel
 *  - 2.2. Set Nivel
 *  - 2.3. Balanceamento
 *  - 2.4. Fator de Balanceamento
 *  - 2.5. Rotacoes
 */

int get_nivel(No* i){ return i == NULL ? 0 : i->nivel; }

void set_nivel(No* i){
    int nEsq = get_nivel(i->esq);
    int nDir = get_nivel(i->dir);
    i->nivel = 1 + (nEsq > nDir ? nEsq : nDir);
}

int fator_bal(No* i){ return get_nivel(i->dir) - get_nivel(i->esq); }

No* rotacaoDir(No* i){
    No* esq = i->esq;
    No* esqDir = esq->dir;

    esq->dir = i;
    i->esq = esqDir;

    set_nivel(i);
    set_nivel(esq);

    return esq;
}

No* rotacaoEsq(No* i){
    No* dir = i->dir;
    No* dirEsq = dir->esq;

    dir->esq = i;
    i->dir = dirEsq;

    set_nivel(i);
    set_nivel(dir);

    return dir;
}

No* rotacaoEsqDir(No* i){
    i->esq = rotacaoEsq(i->esq);
    return rotacaoDir(i);
}

No* rotacaoDirEsq(No* i){
    i->dir = rotacaoDir(i->dir);
    return rotacaoEsq(i);
}

No* balancear(No* i){
    if(i != NULL){
        int fb = fator_bal(i);

        // Caso 1: Balanceado
        if(fb >= -1 && fb <= 1){ set_nivel(i); }

        // Caso 2: Desbalanceado Dir
        else if(fb == 2){
            int fb_filhoDir = fator_bal(i->dir);
            if(fb_filhoDir == -1){ i = rotacaoDirEsq(i); }
            else{ i = rotacaoEsq(i); }
        }

        // Caso 3: Desbalanceado Esq
        else if(fb == -2){
            int fb_filhoEsq = fator_bal(i->esq);
            if(fb_filhoEsq == 1){ i = rotacaoEsqDir(i); }
            else{ i = rotacaoDir(i); }
        }

        // Caso 4: Erro de balanceamento
        else{ printf("Erro de balanceamento\n"); }
    }
    return i;
}

No* in_rec(int x, No* i, int* cerr){
    if(i == NULL){ i = novo_no(x); }
    else if(x < i->elemento){ i->esq = in_rec(x, i->esq, cerr); }
    else if(x > i->elemento){ i->dir = in_rec(x, i->dir, cerr); }
    else{
        printf("Erro: Duplicatas nao sao permitidas\n");
        *cerr = 1;
    }
    return balancear(i);
}

void inserir(int x, Arvore* a){
    int cerr = 0;
    a->raiz = in_rec(x, a->raiz, &cerr);
    if(cerr){
        liberar(a);
        exit(1);
    }
}

int p_rec(int x, No* i){
    int resp;
    if(i == NULL){ resp = 0; }
    else if(x == i->elemento){ resp = 1; }
    else if(x < i->elemento){ resp = p_rec(x, i->esq); }
    else{ resp = p_rec(x, i->dir); }
    return resp;
}

int pesquisar(int x, Arvore* a){ return p_rec(x, a->raiz); }

void ordem(No* i){
    if(i != NULL){
        ordem(i->esq);
        printf("%d ", i->elemento);
        ordem(i->dir);
    }
}

void pre(No* i){
    if(i != NULL){
        printf("%d ", i->elemento);
        pre(i->esq);
        pre(i->dir);
    }
}

void pos(No* i){
    if(i != NULL){
        pos(i->esq);
        pos(i->dir);
        printf("%d ", i->elemento);
    }
}

void imprimir(Arvore* a){
    int op;
    do{
        printf("===== Caminhamentos =====\n");
        printf("1. Em Ordem\n2. Pre Ordem\n3. Pos Ordem\nEscolha: ");
        scanf("%d", &op);
        if(op < 1 || op > 3){ printf("Opcao invalida, tente novamente\n"); }
    }while(op < 1 || op > 3);

    printf("\nCaminhamento ");
    switch(op){
        case 1:{
            printf("Em ordem: ");
            ordem(a->raiz);
            break;
        }
        case 2:{
            printf("Pre ordem: ");
            pre(a->raiz);
            break;
        }
        case 3:{
            printf("Pos ordem: ");
            pos(a->raiz);
            break;
        }
    }
    printf("\n");
}

int menu(){
    int op;
    printf("===== MENU PRINCIPAL =====\n");
    printf("1. Inserir\n2. Pesquisar\n3. Imprimir\n0. Sair\nEscolha: ");
    scanf("%d", &op);
    return op;
}

int main(){
    Arvore arvore;
    inicializar(&arvore);

    while(1){
        int op = menu();
        printf("\n");
        if(!op){ break; }
        switch(op){
            case 1:{
                int x;
                printf("Elemento para inserir: ");
                scanf("%d", &x);
                inserir(x, &arvore);
                break;
            }
            case 2:{
                int x;
                printf("Elemento para pesquisar: ");
                scanf("%d", &x);
                if(!pesquisar(x, &arvore)){
                    printf("Elemento nao encontrado\n");
                } else{
                    printf("Elemento encontrado\n");
                }
                break;
            }
            case 3:{
                imprimir(&arvore);
                break;
            }
            default:{
                printf("Opcao invalida, tente novamente\n");
                break;
            }
        }
        printf("\n");
    }

    printf("FIM DO PROGRAMA\n");
    liberar(&arvore);
    return 0;
}
