#include "trie.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void trie_liberar_memoria(NoTrie* no){
    if(no != NULL){
        for(int i = 0; i < no->ascii; i++){
            if(no->filhos != NULL && no->filhos[i] != NULL){ trie_liberar_memoria(no->filhos[i]); }
        }

        if(no->filhos != NULL) free(no->filhos);
        free(no);
    }
}

NoTrie* trie_novo_no(char c)
{
    NoTrie* novo = (NoTrie*)malloc(sizeof(NoTrie));
    if(novo != NULL){
        novo->elemento = c;
        novo->folha = 0;
        novo->ascii = 256;
        novo->filhos = (NoTrie**)malloc(novo->ascii * sizeof(NoTrie*));
        if(novo->filhos == NULL){
            printf("Erro de alocação para os filhos do NoTrie\n");
            free(novo);
            novo = NULL;
        }
    }
    return novo;
}

//Metodo auxiliar para remoção: retorna se No possui algum filho
int semFilhos(NoTrie* no)
{
    int resp = 1;
    for(int i = 0; i < no->ascii && resp; i++){
        if(no->filhos[i] != NULL) resp = 0;
    }
    return resp;
}

void trie_inicializar(Trie* t)
{
    if(t != NULL){ t->raiz = trie_novo_no('\0'); }
}

void trie_inserir_rec(char* s, int i, NoTrie* no)
{
    int len = strlen(s);
    char c = s[i];
    NoTrie* filho = no->filhos[c];

    if(filho == NULL){
        filho = trie_novo_no(c);
        no->filhos[c] = filho;

        if(i == len - 1){ filho->folha = 1; }
        else{ trie_inserir_rec(s, i+1, filho); }
    } else if(i < len - 1){ trie_inserir_rec(s, i+1, filho); }
    else{ printf("Erro de inserção: Duplicatas não permitidas\n"); }
}

void trie_inserir(char* s, Trie* t)
{
    if(s != NULL){ trie_inserir_rec(s, 0, t->raiz); }
}

int trie_pesquisar_rec(char* s, int i, NoTrie* no)
{
    int resp;
    int len = strlen(s);
    char c = s[i];
    NoTrie* filho = no->filhos[c];

    if(filho == NULL){ resp = 0; }
    else if(i == len - 1){ resp = filho->folha; }
    else if(i < len - 1){ resp = trie_pesquisar_rec(s, i+1, filho); }
    else{
        printf("Erro de pesquisa: RuntimeException\n");
        resp = 0;
    }
    return resp;
}

int trie_pesquisar(char* s, Trie* t)
{
    int resp = 0;
    if(t != NULL){ resp = trie_pesquisar_rec(s, 0, t->raiz); }
    return resp;
}

char* trie_remover_rec(char* s, int i, NoTrie* no)
{
    char* resp;
    int len = strlen(s);
    char c = s[i];
    NoTrie* filho = no->filhos[c];

    if(filho == NULL){ resp = NULL; }
    else if(i == len - 1){
        if(filho->folha){
            filho->folha = 0;
            resp = s;
        } else{ resp = NULL; }
    } else if(i < len - 1){
        resp = trie_remover_rec(s, i+1, filho);
        if(resp != NULL && semFilhos(filho) && !filho->folha){ no->filhos[c] = NULL; }
    }
    return resp;
}

char* trie_remover(char* s, Trie* t)
{
    char* resp = NULL;
    if(trie_pesquisar_rec(s, 0, t->raiz)){ resp = trie_remover_rec(s, 0, t->raiz); }
    return resp;
}

void trie_imprimir_rec(char* s, int i, NoTrie* no)
{
    if(no != NULL){
        if(no->folha){
            s[i] = '\0';
            printf("%s\n", s);
        } else{
            for(int j = 0; j < no->ascii; j++){
                if(no->filhos[j] != NULL){
                    s[i] = no->filhos[j]->elemento;
                    trie_imprimir_rec(s, i+1, no->filhos[j]);
                }
            }
        }
    }
}

void trie_imprimir(Trie* t)
{
    char buffer[151];
    buffer[0] = '\0';
    if(t != NULL){ trie_imprimir_rec(buffer, 0, t->raiz); }
}
