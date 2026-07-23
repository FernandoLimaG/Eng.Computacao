#include <stdio.h>
#include <stdlib.h>

#define underflowErr "Err: Underflow\n"
#define overflowErr "Err: Overflow\n"

typedef struct pilha{
    int* pilha;
    int cap;
    int tam;
} Pilha;

void inicializar_pilha(int cap, Pilha* p){
    p->cap = cap;
    p->tam = 0;
    p->pilha = (int*)malloc(cap * sizeof(int));
}

int isEmpty(Pilha* p){
    int resp = 0;
    if(p->tam == 0){ resp = 1; }
    return resp;
}

int isFull(Pilha* p){
    int resp = 0;
    if(p->tam == p->cap){ resp = 1; }
    return resp;
}

void inserir(int x, Pilha* p){
    if(isFull(p)){ printf(overflowErr); }
    else{ p->pilha[(p->tam)++] = x; }
}

void remover(Pilha* p){
    if(isEmpty(p)){ printf(underflowErr); }
    else{ printf("[REMOVIDO]: %d\n", p->pilha[--(p->tam)]); }
}

int pesquisar(int x, Pilha* p){
    int resp = 0;
    for(int i = 0; i < p->tam && !resp; i++){
        if(x == p->pilha[i]){ resp = 1; }
    }
    return resp;
}

void imprimir(Pilha* p){
    if(isEmpty(p)){ printf("Estrutura vazia\n"); }
    else{
        printf("PILHA: [");
        for(int i = (p->tam)-1; i >= 0; i--){
            printf("%d", p->pilha[i]);
            if(i > 0){ printf(", "); }
        }
        printf("]\n");
    }
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
    Pilha pilha;

    int cap;
    do{
        printf("Digite a capacidade maxima da pilha [MAX = 25]: ");
        scanf("%d", &cap);
        if(cap <= 0 || cap > 25){ printf("Capacidade inválida, tente novamente\n"); }
    }while(cap <= 0 || cap > 25);

    inicializar_pilha(cap, &pilha);

    while(1){
        int op = menu();
        printf("\n");
        if(!op) break;
        switch(op){
            case 1:{
                       int x;
                       printf("Elemento para inserir: ");
                       scanf("%d", &x);
                       inserir(x, &pilha);
                       break;
                   }
            case 2:{
                       remover(&pilha);
                       break;
                   }
            case 3:{
                       int x;
                       printf("Elemento para pesquisar: ");
                       scanf("%d", &x);
                       if(pesquisar(x, &pilha)){ printf("Elemento encontrado na estrutura\n"); }
                       else{ printf("Elemento não encontrado na estrutura\n"); }
                       break;
                   }
            case 4:{
                       imprimir(&pilha);
                       break;
                   }
            default:{
                        printf("Opção inválida, tente novamente\n");
                        break;
                    }
        }
        printf("\n");
    }

    printf("FIM DO PROGRAMA");
    free(pilha.pilha);
    return 0;
}
