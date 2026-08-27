// -- Custom Libs --
#include "../include/trie.h"
#include "../include/error.h"

// -- libC --
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

static void liberar(NoTrie* no)
{
    if(no != NULL){
        for(int i = 0; i < ASCII; i++){
            NoTrie* filho = trie_getFilho((char)i, no);
            if(filho != NULL) liberar(filho);
        }
        free(no->filhos);
        free(no);
    }
}

NoTrie* trie_getFilho(char c, NoTrie* no){ return no->filhos[(int)c]; }
NoTrie** trie_getFilhos(NoTrie* no){ return no->filhos; }
void trie_setFilho(NoTrie* filho, NoTrie* pai){ pai->filhos[(int)filho->elemento] = filho; }

void trie_liberar(Trie* t)
{
    if(t != NULL) liberar(t->raiz);
}

NoTrie* trie_novo_no(char c)
{
    NoTrie* novo = (NoTrie*)malloc(sizeof(NoTrie));
    if(novo != NULL){
        novo->elemento = c;
        novo->folha = 0;
        novo->filhos = (NoTrie**)malloc(ASCII * sizeof(NoTrie*));
        if(novo->filhos == NULL) free(novo);
    }
    return novo;
}

void trie_inicializar(Trie* t)
{
    if(t != NULL) t->raiz = trie_novo_no('\0');
}

int trie_ehFolha(NoTrie* no)
{
    int resp = 1;
    if(no != NULL){
        for(int i = 0; i < ASCII && resp; i++){
            if(no->filhos[i] != NULL) resp = 0;
        }
    }
    return resp;
}

static void inserir(char* s, int i, NoTrie* no)
{
    int len = strlen(s);
    char c = s[i];
    NoTrie* filho = trie_getFilho(c, no);

    if(filho == NULL){
        filho = trie_novo_no(c);
        trie_setFilho(filho, no);

        if(i == len - 1) filho->folha = 1;
        else inserir(s, i+1, filho);
    } else if(i < len - 1) inserir(s, i+1, filho);
    else IllegalArgumentException("Duplicatas não permitidas");
}

void trie_inserir(char* s, Trie* t)
{
    inserir(s, 0, t->raiz);
}

static int pesquisar(char* s, int i, NoTrie* no)
{
    int resp;
    int len = strlen(s);
    char c = s[i];
    NoTrie* filho = trie_getFilho(c, no);

    if(filho == NULL) resp = 0;
    else if(i == len - 1) resp = filho->folha;
    else if(i < len - 1) resp = pesquisar(s, i+1, filho);
    else{
        RuntimeException("Erro de pesquisa <TRIE>");
        resp = -1;
    }
    return resp;
}

int trie_pesquisar(char* s, Trie* t)
{
    return pesquisar(s, 0, t->raiz);
}

static void imprimir(char* s, int i, NoTrie* no)
{
    if(no->folha){
        s[i] = '\0';
        printf("%s\n", s);
    } else{
        for(int i = 0; i < ASCII; i++){
            NoTrie* filho = trie_getFilho((char)i, no);
            if(filho != NULL){
                s[i] = filho->elemento;
                imprimir(s, i+1, filho);
            }
        }
    }
}

void trie_imprimir(Trie* t)
{
    if(trie_ehFolha(t->raiz)) printf("*** Árvore TRIE vazia ***\n");
    else{
        char buffer[101];
        buffer[0] = '\0';
        printf("===== TRIE =====\n");
        imprimir(buffer, 0, t->raiz);
        printf("====================\n");
    }
}
