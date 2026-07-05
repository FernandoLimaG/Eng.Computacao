#include <stdio.h>
#include <stdlib.h>

#define nllErr "Err: Estrutura Nula\n"
#define mlcErr "FATAL-Err: Falha para alocar memoria\n"

typedef struct no{
    int elemento;
    struct no *esq, *dir;
} No;

typedef struct arvore{
    No* raiz;
} Arvore;

// Liberar Estrutura
void liberar_rec(No* i){
    if(i != NULL){
        liberar_rec(i->esq);
        liberar_rec(i->dir);
        free(i);
    }
}

void liberar_memoria(Arvore* a){ liberar_rec(a->raiz); }

void inicializar_estrutura(Arvore* a){
    a->raiz = NULL;
}

No* novo_no(int elemento){
    No* novo = (No*)malloc(sizeof(No));
    if(novo == NULL){
        printf(mlcErr);
    } else{
        novo->elemento = elemento;
        novo->esq = novo->dir = NULL;
    }
    return novo;
}

// Metodos abp
No* inserir_rec(int x, No* i, int* cerr){
    if(i == NULL){ i = novo_no(x); }
    else if(x < i->elemento){ i->esq = inserir_rec(x, i->esq, cerr); }
    else if(x > i->elemento){ i->dir = inserir_rec(x, i->dir, cerr); }
    else{
        printf("Erro: Duplicatas nao sao permitidos\n");
        *cerr = 1;
    }
    return i;
}

void inserir(int x, Arvore* a){
    int cerr = 0;
    a->raiz = inserir_rec(x, a->raiz, &cerr);
    if(cerr){
        liberar_memoria(a);
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

int eh_folha(No* i){ return i->esq == NULL && i->dir == NULL; }

void em_ordem(No* i){
    if(i != NULL){
        em_ordem(i->esq);
        printf("%d ", i->elemento);
        em_ordem(i->dir);
    }
}

void pre_ordem(No* i){
    if(i != NULL){
        printf("%d ", i->elemento);
        pre_ordem(i->esq);
        pre_ordem(i->dir);
    }
}

void pos_ordem(No* i){
    if(i != NULL){
        pos_ordem(i->esq);
        pos_ordem(i->dir);
        printf("%d ", i->elemento);
    }
}

void imprimir(Arvore* a){
    int op;
    do{
        printf("===== Caminhamento =====\n");
        printf("1. Em Ordem\n2. Pre Ordem\n3. Pos Ordem\nEscolha: ");
        scanf("%d", &op);
        if(op < 1 || op > 3){ printf("Opcao invalida, tente novamente\n"); }
    }while(op < 1 || op > 3);

    printf("\nCaminhamento ");
    switch(op){
        case 1:{
            printf("Em ordem: ");
            em_ordem(a->raiz);
            printf("\n");
            break;
        }
        case 2:{
            printf("Pre ordem: ");
            pre_ordem(a->raiz);
            printf("\n");
            break;
        }
        case 3:{
            printf("Pos ordem: ");
            pos_ordem(a->raiz);
            printf("\n");
            break;
        }
    }
}

int menu(){
    int op;
    printf("\n===== MENU PRINCIPAL =====\n");
    printf("1. Inserir\n2. Pesquisar\n3. Imprimir\n0. Sair\nEscolha: ");
    scanf("%d", &op);
    return op;
}

int main(){
    Arvore arvore;
    inicializar_estrutura(&arvore);

    // CLI do Usuario
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
                if(pesquisar(x, &arvore)){
                    printf("Elemento encontrado\n");
                } else{
                    printf("Elemento nao encontrado\n");
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
    }

    // Fim do programa
    liberar_memoria(&arvore);
    printf("FIM DO PROGRAMA\n");
    return 0;
}
