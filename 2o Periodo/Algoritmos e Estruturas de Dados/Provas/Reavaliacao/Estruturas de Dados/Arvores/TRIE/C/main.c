#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_HASH 256
#define nllErr "Err: Estrutura nula\n"
#define mlcErr "FATAL-Err: Falha para alocar memoria\n"

typedef struct no{
    unsigned char elemento;
    struct no** filhos;
    int folha;
} No;

typedef struct arvore{
    No* raiz;
} Arvore;

void l_rec(No* i){
    if(i != NULL){
        for(int j = 0; j < MAX_HASH; j++){
            l_rec(i->filhos[j]);
        }
        free(i->filhos);
        free(i);
    }
}

void liberar(Arvore* a){ l_rec(a->raiz); }

No* novo_no(char c){
    No* novo = (No*)malloc(sizeof(No));
    if(novo == NULL){ printf(mlcErr); }
    else{
        novo->elemento = c;
        novo->folha = 0;
        novo->filhos = (No**)malloc(MAX_HASH * sizeof(No*));
        if(novo->filhos == NULL){ printf(mlcErr); }
        else{
            for(int i = 0; i < MAX_HASH; i++) novo->filhos[i] = NULL;
        }
    }
    return novo;
}

void inicializar(Arvore* a){
    a->raiz = novo_no('\0');
}

No* get_filho(unsigned char c, No* no){ return no->filhos[c]; }

No* set_filho(No* filho, No* no){ no->filhos[filho->elemento] = filho; }

void i_rec(char* s, int i, No* no){
    int len = strlen(s);
    unsigned char c = s[i];
    No* filho = get_filho(c, no);

    // 1. Filho nao existe
    if(filho == NULL){
        filho = novo_no(c);
        set_filho(filho, no);

        if(i == len - 1){ filho->folha = 1; }
        else{ i_rec(s, i+1, filho); }
    } else if(filho != NULL && i < len - 1){ i_rec(s, i+1, filho); }
    else{ printf("Erro de insercao\n"); }
}

void inserir(char* s, Arvore* a){ i_rec(s, 0, a->raiz); }

int p_rec(char* s, int i, No* no){
    unsigned char c = s[i];
    int len = strlen(s);
    int resp;
    No* filho = get_filho(c, no);

    if(filho == NULL){ resp = 0; }
    else{
        if(i == len - 1){ resp = filho->folha; }
        else if(i < len - 1){ resp = p_rec(s, i+1, filho); }
        else{ printf("Erro de pesquisa"); }
    }
    return resp;
}

int pesquisar(char* s, Arvore* a){ return p_rec(s, 0, a->raiz); }

void print_rec(char* s, int i, No* no){
    if(no != NULL){
        if(no->folha){
            s[i] = '\0';
            printf("%s\n", s);
        }

        for(int j = 0; j < MAX_HASH; j++){
            No* filho = get_filho(j, no);
            if(filho != NULL){
                s[i] = filho->elemento;
                print_rec(s, i+1, filho);
            }
        }
    }
}

void imprimir(Arvore* a){
    char buffer[1001];
    print_rec(buffer, 0, a->raiz);
}

char* nova_string(char* prompt){
    int len;
    char buffer[101];
    char* str;

    printf(prompt);
    scanf(" %100[^\n]", buffer);
    len = strlen(buffer) + 1;

    str = (char*)malloc(len * sizeof(char));
    if(str == NULL){ printf("Falha para alocar memoria da string\n"); }
    else{
        for(int i = 0; i < len; i++) str[i] = buffer[i];
        str[len - 1] = '\0';
    }
    return str;
}

int menu(){
    int op;
    printf("===== MENU PRINCIPAL =====\n");
    printf("1. Inserir\n2. Pesquisar\n3. Imprimir\n0. Sair\nEscolha: ");
    scanf("%d", &op);
    return op;
}

int main(){
    Arvore arvore;
    inicializar(&arvore);

    while(1){
        int op = menu();
        printf("\n");
        if(!op){ break; }
        switch(op){
            case 1:{
                char* str = nova_string("String para inserir: ");
                if(str != NULL){
                    inserir(str, &arvore);
                }
                break;
            }
            case 2:{
                char* str = nova_string("String para pesquisar: ");
                if(str != NULL){
                    if(pesquisar(str, &arvore)){
                        printf("Nome encontrado\n");
                    } else{
                        printf("Nome nao encontrado\n");
                    }
                }
                break;
            }
            case 3:{
                imprimir(&arvore);
                break;
            }
            default:{
                printf("Opcao invalida, tente novamente\n");
                break;
            }
        }
        printf("\n");
    }

    printf("FIM DO PROGRAMA\n");
    liberar(&arvore);
    return 0;
}
