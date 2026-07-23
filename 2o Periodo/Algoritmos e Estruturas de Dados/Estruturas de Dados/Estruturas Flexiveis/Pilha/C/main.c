#include <stdio.h>
#include <stdlib.h>

#define Overflow "Err: Overflow\n"
#define Underflow "Err: Underflow\n"

typedef struct celula Celula;
typedef struct pilha Pilha;

void nullErr(char* s){ printf("[param]<%s> is NULL\n", s); }
void emptyErr(char* s){ printf("[param]<%s> is EMPTY\n", s); }

struct celula{
    int elemento;
    Celula* prox;
};

struct pilha{
    Celula* primeiro;
    Celula* ultimo;
};

void liberar_celula(Celula* i){
    while(i != NULL){
        Celula* tmp = i;
        i = i->prox;
        free(tmp);
    }
}

void liberar_memoria(Pilha* p){
    if(p != NULL){ liberar_celula(p->primeiro); }
}

int isEmpty(Pilha* p){ return p->primeiro == p->ultimo; }

Celula* nova_celula(int x){
    Celula* nova = (Celula*)malloc(sizeof(Celula));
    if(nova == NULL){ nullErr("Nova Celula"); }
    else{
        nova->elemento = x;
        nova->prox = NULL;
    }
    return nova;
}

void inicializar(Pilha* p){
    if(p != NULL){
        p->primeiro = nova_celula(-1); //Arbitramos valor -1 como flag de celula cabeça
        p->ultimo = p->primeiro;
    }
}

void inserir(int x, Pilha* p){
    p->ultimo->prox = nova_celula(x);
    p->ultimo = p->ultimo->prox;
}

int remover(Pilha* p){
    int resp;
    if(isEmpty(p)){
        printf(Underflow);
        liberar_memoria(p);
        exit(1);
    } else{
        resp = p->ultimo->elemento;
        Celula* tmp;
        for(tmp = p->primeiro; tmp->prox != p->ultimo; tmp = tmp->prox);
        p->ultimo = tmp;
        tmp->prox = NULL;
    }
    return resp;
}

int pesquisar_rec(int x, Celula* i){
    int resp;
    if(i == NULL){ resp = 0; }
    else if(x == i->elemento){ resp = 1; }
    else{ resp = pesquisar_rec(x, i->prox); }
    return resp;
}

int pesquisar(int x, Pilha* p){
    int resp = 0;
    if(isEmpty(p)){ emptyErr("Pilha"); }
    else{ resp = pesquisar_rec(x, p->primeiro->prox); }
    return resp;
}

void imprimir_rec(Celula* i){
    if(i != NULL){
        imprimir_rec(i->prox);
        printf("%d ", i->elemento);
    }
}

void imprimir(Pilha* p){
    if(isEmpty(p)){ emptyErr("Pilha"); }
    else{
        printf("PILHA: ");
        imprimir_rec(p->primeiro->prox);
        printf("\n");
    }
}

int user_entry(char* metodo){
    int elemento;
    printf("Digite o elemento para %s: ", metodo);
    scanf("%d", &elemento);
    return elemento;
}

int menu(){
    int op;
    printf("===== MENU PRINCIPAL =====\n");
    printf("1. Inserir\n2. Remover\n3. Pesquisar\n4. Imprimir\n0. Sair\nEscolha: ");
    scanf("%d", &op);
    return op;
}

int main(){
    Pilha pilha;
    inicializar(&pilha);

    while(1){
        int op = menu();
        printf("\n");
        if(!op) break;
        switch(op){
            case 1:{
                       int x = user_entry("inserir");
                       inserir(x, &pilha);
                       break;
                   }
            case 2:{
                       printf("[REMOVIDO]: %d\n", remover(&pilha));
                       break;
                   }
            case 3:{
                       int x = user_entry("pesquisar");
                       if(!pesquisar(x, &pilha)){
                           printf("Elemento não encontrado\n");
                       } else{
                           printf("Elemento encontrado\n");
                       }
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
    liberar_memoria(&pilha);
    printf("FIM DO PROGRAMA\n");
    return 0;
}
