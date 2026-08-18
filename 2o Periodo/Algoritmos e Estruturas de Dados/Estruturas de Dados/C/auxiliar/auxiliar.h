#ifndef AUXILIAR_H
#define AUXILIAR_H

#include "../estruturas/arvores/abp/abp.h"
//#include "../estruturas/arvores/avl/avl.h"
//#include "../estruturas/arvores/alvinegra/alvinegra.h"
#include "../estruturas/arvores/trie/trie.h"


void opcaoInvalida();
int menuMetodos();
int menuEstruturas();
char* lerString(char* s);
int lerInt(char* s);
void menuAbp(Abp* a);
//void menuAvl(Avl* a);
//void menuAlvinegra(Alvinegra* a);
void menuTrie(Trie* t);

#endif //AUXILIAR_H
