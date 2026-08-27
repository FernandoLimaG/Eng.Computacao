#include "../include/master_header.h"

int main(void)
{
    Trie t;
    trie_inicializar(&t);

    while(1){
        int op = metodos();
        printf("\n");
        if(!op) break;
        switch(op){
            case 1:{
                       char* s = lerStr("Nome para inserir: ");
                       if(s == NULL) NullErr("new_<NOME::TRIE>_string");
                       else trie_inserir(s, &t);
                       break;
                   }
            case 2:{
                       printf("[INOP]\n");
                       break;
                   }
            case 3:{
                       char* s = lerStr("Nome para pesquisar: ");
                       if(s == NULL) NullErr("new_<PESQUISAR::TRIE>_string");
                       else{
                           int resp = trie_pesquisar(s, &t);
                           if(resp != -1){
                               if(resp) printf("Nome encontrado\n");
                               else printf("Nome não encontrado\n");
                           }
                       }
                       break;
                   }
            case 4:{
                       trie_imprimir(&t);
                       break;
                   }
            default:{ OpcaoInvalida(); break; }
        }
        printf("\n");
    }

    trie_liberar(&t);
    printf("FIM DO PROGRAMA\n");
    return 0;
}
