#ifndef TRIE_H
#define TRIE_H

#define ASCII 256

typedef struct noTrie
{
    char elemento;
    struct noTrie** filhos;
    int folha;
} NoTrie;

typedef struct trie
{
    NoTrie* raiz;
} Trie;

// -- Metodos NoTrie --
NoTrie* trie_getFilho(char c, NoTrie* pai);
NoTrie** trie_getFilhos(NoTrie* pai);
void trie_setFilho(NoTrie* filho, NoTrie* pai);
int trie_ehFolha(NoTrie* no);

// -- Metodos Arvore --
NoTrie* trie_novo_no(char c);
void trie_inicializar(Trie* t);
void trie_inserir(char* s, Trie* t);
//void trie_inserir_rec(char* s, Trie* t);
char* trie_remover(char* s, Trie* t);
//char* trie_remover_rec(char* s, Trie* t);
int trie_pesquisar(char* s, Trie* t);
//int trie_pesquisar_rec(char* s, Trie* t);
void trie_imprimir(Trie* t);
//void trie_imprimir_rec(Trie* t);
void trie_liberar(Trie* t);
//void trie_liberar_rec(Trie* t);

#endif //TRIE_H
