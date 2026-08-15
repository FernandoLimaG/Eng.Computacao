#ifndef TRIE_H
#define TRIE_H

typedef struct noTrie{
    char elemento;
    int folha;
    int ascii;
    struct noTrie** filhos;
} NoTrie;

typedef struct trie{
    NoTrie* raiz;
} Trie;

void trie_inicializar(Trie* t);
NoTrie* trie_novo_no(char c);
void trie_inserir(char* s, Trie* t);
void trie_inserir_rec(char* s, int i, NoTrie* no);
int trie_pesquisar(char* s, Trie* t);
int trie_pesquisar_rec(char* s, int i, NoTrie* no);
char* trie_remover_rec(char* s, int i, NoTrie* no);
char* trie_remover(char* s, Trie* t);
void trie_imprimir(Trie* t);
void trie_imprimir_rec(char* s, int i, NoTrie* no);
void trie_liberar_memoria(NoTrie* no);

#endif //TRIE_H
