#include <stdio.h>
#include <stdlib.h>

void mallocErr(char* param){ printf("param:<%s> failled malloc\n", param); }
void nullErr(char* param){ printf("param:<%s> is null\n", param); }
void emptyErr(char* param){ printf("param:<%s> is empty\n", param); }

typedef struct celula Celula;
typedef struct lista Lista;

struct celula
{
    int elemento;
    Celula* prox;
};

struct lista
{
    Celula* primeiro;
    Celula* ultimo;
};

void liberar_celula(Celula* i)
{
    while(i != NULL){
        Celula* tmp = i;
        i = i->prox;
        free(tmp);
    }
}

void liberar_memoria(Lista* l)
{
    if(l != NULL){ liberar_celula(l->primeiro); }

    /* ----- IMPORTANTE -----
     * Não vamos liberar a estrutura "Lista"
     * pois ela não foi dinâmicamente alocada.
     */
}

Celula* nova_celula(int x)
{
    Celula* nova = (Celula*)malloc(sizeof(Celula));
    if(nova == NULL){ mallocErr("Nova Celula"); }
    else{
        nova->elemento = x;
        nova->prox = NULL;
    }
    return nova;
}

void inicializar(Lista* l)
{
    if(l != NULL){
        // Arbitramos valor '-1' para célula cabeça
        l->primeiro = nova_celula(-1);
        l->ultimo = l->primeiro;
    } else{ nullErr("Lista"); }
}

int isEmpty(Lista* l){ return l->primeiro == l->ultimo; }

/* ===== Métodos LISTA =====
 * 1. Inserção
 *  1.1. Inicio
 *  1.2. Fim
 *  1.3. Em Posição específica
 *
 * 2. Remoção
 *  2.1. Inicio
 *  2.2. Fim
 *  2.3. Em Posição específica
 *
 * 3. Pesquisar elemento
 * 4. Impressão (ordem PRIMEIRO->ULTIMO)
 */

void inserir(int x, Lista* l)
{
    l->ultimo->prox = nova_celula(x);
    if(l->ultimo->prox == NULL){ nullErr("Nova Celula | pos: Ultimo"); }
    else{ l->ultimo = l->ultimo->prox; }
}

void inserir_inicio(int x, Lista* l)
{
    Celula* tmp = nova_celula(x);
    tmp->prox = l->primeiro->prox;
    l->primeiro->prox = tmp;
    if(isEmpty(l)){ l->ultimo = l->primeiro->prox; }
}

void inserir_pos(int x, int i, Lista* l)
{
    if(isEmpty(l)){ printf("Posição inválida. Lista vazia\n"); }
    else if(!i){ inserir_inicio(x, l); }
    else{
        Celula* tmp = l->primeiro;
        for(int j = 0; j < i && tmp != NULL; j++, tmp = tmp->prox);
        Celula* nova = nova_celula(x);
        nova->prox = tmp->prox;
        tmp->prox = nova;
    }
}

int remover(Lista* l)
{
    if(isEmpty(l)){ emptyErr("Lista: UNDERFLOW\n"); exit(1); }

    int out = l->ultimo->elemento;
    Celula* ptr;
    for(ptr = l->primeiro; ptr->prox != l->ultimo; ptr = ptr->prox);
    l->ultimo = ptr;
    l->ultimo->prox = ptr = NULL;
    return out;
}

int remover_inicio(Lista* l)
{
    Celula* tmp = l->primeiro->prox;
    int out = tmp->elemento;
    l->primeiro->prox = tmp->prox;
    tmp = NULL;
    return out;
}

int remover_pos(int i, Lista* l)
{
    int out;
    if(isEmpty(l)){ emptyErr("Lista"); exit(1); }
    else if(i == 0){ out = remover_inicio(l); }
    else{
        Celula* tmp = l->primeiro;
        for(int j = 0; j < i && tmp != NULL; j++, tmp = tmp->prox);
        out = tmp->prox->elemento;
        Celula* ptr = tmp->prox;
        tmp->prox = ptr->prox;
        ptr->prox = NULL;
    }
    return out;
}

int pesquisar_rec(int x, Celula* i)
{
    int resp;
    if(i == NULL){ resp = 0; }
    else if(x == i->elemento){ resp = 1; }
    else{ resp = pesquisar_rec(x, i->prox); }
    return resp;
}

int pesquisar(int x, Lista* l)
{
    int resp;
    if(isEmpty(l)){ resp = 0; }
    else{ resp = pesquisar_rec(x, l->primeiro->prox); }
    return resp;
}

void imprimir(Lista* l)
{
    if(isEmpty(l)){ emptyErr("LISTA"); }
    else{
        printf("LISTA ENCADEADA: [");
        for(Celula* i = l->primeiro->prox; i != NULL; i = i->prox){
            printf("%d", i->elemento);
            if(i->prox != NULL){ printf(", "); }
        }
        printf("]\n");
    }
}

int menu()
{
    int op;
    printf("===== MENU PRINCIPAL =====\n");
    printf("1. Menu de Inserção\n2. Menu de Remoção\n3. Pesquisar\n4. Imprimir\n0. Sair\nEscolha: ");
    scanf("%d", &op);
    return op;
}

void menu_inserir(Lista* l)
{
    int op, elemento;
    do{
        printf("===== MENU DE INSERÇÃO =====\n");
        printf("1. Inicio\n2. No fim\n3. Em posição específica\nEscolha: ");
        scanf("%d", &op);
        if(op < 1 || op > 3){ printf("Opção inválida, tente novamente\n\n"); }
    }while(op < 1 || op > 3);

    printf("\nValor para inserir: ");
    scanf("%d", &elemento);

    switch(op){
        case 1: inserir_inicio(elemento, l); break;
        case 2: inserir(elemento, l); break;
        case 3:{ 
                   int i;
                   printf("Posição de inserção: ");
                   scanf("%d", &i);
                   inserir_pos(elemento, i, l);
                   break;
               }
    }
}

void menu_remover(Lista* l)
{
    int op;
    do{
        printf("===== MENU DE REMOÇÃO =====\n");
        printf("1. Inicio\n2. No fim\n3. Em posição específica\nEscolha: ");
        scanf("%d", &op);
        if(op < 1 || op > 3){ printf("Opção inválida, tente novamente\n\n"); }
    }while(op < 1 || op > 3);

    switch(op){
        case 1: remover_inicio(l); break;
        case 2: remover(l); break;
        case 3:{ 
                   int i;
                   printf("Posição de inserção: ");
                   scanf("%d", &i);
                   remover_pos(i, l);
                   break;
               }
    }
}

int main()
{
    Lista lista;
    inicializar(&lista);

    while(1){
        int op = menu();
        printf("\n");
        if(!op) break;
        switch(op){
            case 1: menu_inserir(&lista); break;
            case 2: menu_remover(&lista); break;
            case 3:{
                       int x;
                       printf("Valor para pesquisar: ");
                       scanf("%d", &x);
                       if(pesquisar(x, &lista)){ printf("Elemento encontrado\n"); }
                       else{ printf("Elemento não encontrado\n"); }
                       break;
                   }
            case 4: imprimir(&lista); break;
            default: printf("Opção inválida, tente novamente\n\n"); break;
        }
        printf("\n");
    }
    liberar_memoria(&lista);
    printf("FIM DO PROGRAMA\n");
    return 0;
}
