#include <stdio.h>
#include <stdlib.h>

void nullErr(char* s){ printf("param:<%s> is 'NULL'\n", s); }
void mallocErr(char* s){ printf("param:<%s> failed malloc\n", s); }
void underflowErr(char* s){ printf("param <%s> 'UNDERFLOW'\n", s); }

typedef struct celula Celula;
typedef struct fila Fila;

struct celula
{
    int elemento;
    Celula* prox;
};

struct fila
{
    Celula *primeiro, *ultimo;
};

void liberar(Celula* i)
{
    while(i != NULL){
        Celula* tmp = i;
        i = i->prox;
        free(tmp);
    }
}

void liberar_memoria(Fila* f)
{
    if(f != NULL){ liberar(f->primeiro); }
}

Celula* nova_celula(int x)
{
    Celula* nova = (Celula*)malloc(sizeof(Celula));
    if(nova == NULL){ mallocErr("Nova Célula"); }
    else{
        nova->elemento = x;
        nova->prox = NULL;
    }
    return nova;
}

void inicializar(Fila* f)
{
    if(f != NULL){
        f->primeiro = nova_celula(-1);
        f->ultimo = f->primeiro;
    }
}

int is_empty(Fila* f){ return f->primeiro->prox == NULL; }

void inserir(int x, Fila* f)
{
    f->ultimo->prox = nova_celula(x);
    f->ultimo = f->ultimo->prox;
}

int remover(Fila* f)
{
    if(is_empty(f)){
        underflowErr("Fila");
        liberar_memoria(f);
        exit(1);
    }
        
    Celula* tmp = f->primeiro->prox;
    int resp = tmp->elemento;
    f->primeiro->prox = tmp->prox;
    tmp->prox = NULL;
    tmp = NULL;
    return resp;
}

int pesquisar_rec(int x, Celula* i)
{
    int resp;
    if(i == NULL){ resp = 0; }
    else if(x == i->elemento){ resp = 1; }
    else{ resp = pesquisar_rec(x, i->prox); }
    return resp;
}

int pesquisar(int x, Fila* f)
{
    int resp;
    if(is_empty(f)){ resp = 0; }
    else{ resp = pesquisar_rec(x, f->primeiro->prox); }
    return resp;
}

void imprimir_rec(Celula* i)
{
    if(i != NULL){
        printf("%d", i->elemento);
        if(i->prox != NULL){ printf(", "); }
        imprimir_rec(i->prox);
    }
}

void imprimir(Fila* f)
{
    printf("FILA: [");
    imprimir_rec(f->primeiro->prox);
    printf("]\n");
}

int menu()
{
    int op;
    printf("===== MENU PRINCIPAL =====\n");
    printf("1. Inserir\n2. Remover\n3. Pesquisar\n4. Imprimir\n0. Sair\nEscolha: ");
    scanf("%d", &op);
    return op;
}

int main()
{
    Fila fila;
    inicializar(&fila);

    while(1){
        int op = menu();
        printf("\n");
        if(!op) break;
        switch(op){
            case 1:{
                       int x;
                       printf("Elemento para inserir: ");
                       scanf("%d", &x);
                       inserir(x, &fila);
                       break;
                   }
            case 2:{
                       printf("[REMOVIDO]: %d\n", remover(&fila));
                       break;
                   }
            case 3:{
                       int x;
                       printf("Elemento para pesquisar: ");
                       scanf("%d", &x);
                       if(pesquisar(x, &fila)){
                           printf("Elemento encontrado\n");
                       } else{
                           printf("Elemento não encontrado\n");
                       }
                       break;
                   }
            case 4:{
                       imprimir(&fila);
                       break;
                   }
            default:{
                        printf("Opção inválida, tente novamente\n\n");
                        break;
                    }
        }
        printf("\n");
    }
    liberar_memoria(&fila);
    printf("FIM DO PROGRAMA\n");
    return 0;
}
