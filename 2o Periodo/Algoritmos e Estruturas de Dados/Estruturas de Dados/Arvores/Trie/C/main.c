#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define ascii 256

typedef struct no No;
typedef struct trie Trie;

void nullErr(char* s){ printf("param <%s> is 'NULL'\n", s); }
void emptyErr(char* s){ printf("param <%s> is 'EMPTY'\n", s); }
void mallocErr(char* s){ printf("param <%s> 'failed malloc'\n", s); }

struct no
{
    char elemento;
    No** filhos;
    int folha;
};

struct trie
{
    No* raiz;
};

void liberar(No* no)
{
    for(int i = 0; i < ascii; i++){
        if(no->filhos[i] != NULL){ liberar(no->filhos[i]); }
    }
    free(no);
}

void liberar_memoria(Trie* a)
{
    if(a != NULL){ liberar(a->raiz); }
    else{ printf("Estrutura não alocada\n"); }
}

No* novo_no(char c)
{
    No* novo = (No*)malloc(sizeof(No));
    if(novo == NULL){ nullErr("Novo Nó"); }
    else{
        novo->elemento = c;
        novo->folha = 0;
        novo->filhos = (No**)malloc((ascii) * sizeof(No*));
        if(novo->filhos == NULL){ nullErr("Nós filhos"); exit(1); }
        else{
            for(int i = 0; i < ascii; i++) novo->filhos[i] = NULL;
        }
    }
    return novo;
}

int isEmpty(Trie* t){
    int resp = 1;
    for(int i = 0; i < ascii && !resp; i++){
        if(t->raiz->filhos[i] != NULL){ resp = 0; }
    }
    return resp;
}


No* get_filho(char c, No* no){ return no->filhos[c]; }

void set_filho(No* filho, No* no){ no->filhos[(int)filho->elemento] = filho; }

void inicializar(Trie* a)
{
    if(a != NULL){ a->raiz = novo_no('\0'); }
}

void inserir_rec(char* s, int i, No* no)
{
    int len = strlen(s);
    char c = s[i];

    if(no->filhos[c] == NULL){
        no->filhos[c] = novo_no(c);
        if(i == len - 1){ no->filhos[c]->folha = 1; }
        else{ inserir_rec(s, i+1, no->filhos[c]); }
    }else if(i < len - 1){ inserir_rec(s, i+1, no->filhos[c]); }
    else{ printf("Duplicatas não são permitidas\n"); }
}

void inserir(char* s, Trie* t){ inserir_rec(s, 0, t->raiz); }

int pesquisar_rec(char* s, int i, No* no)
{
    int resp;
    int len = strlen(s);
    char c = s[i];

    if(no->filhos[c] == NULL){ resp = 0; }
    else if(i == len - 1){ resp = no->folha; }
    else{ resp = pesquisar_rec(s, i+1, no->filhos[c]); }
    return resp;
}

int pesquisar(char* s, Trie* t){ return pesquisar_rec(s, 0, t->raiz); }

/* -- FIX: Reformular metodo de impressao -- */

int main()
{
    Trie trie;
    inicializar(&trie);

    // Testes
    inserir("Fernando", &trie);
    inserir("Rosana", &trie);
    inserir("Fernando Gonçalves", &trie);

    char* resp;
    if(pesquisar("Fernando", &trie)){ resp = "achou\n"; }
    else{ resp = "ñ achou\n"; }
    printf("%s", resp);

    if(pesquisar("NEGATIVO", &trie)){ resp = "achou"; }
    else{ resp = "ñ achou\n"; }
    printf("%s", resp);

    liberar_memoria(&trie);
    return 0;
}
