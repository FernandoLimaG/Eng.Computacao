#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct tabela{
    char** tabela;
    int m;
    int r;
} Tabela;

void liberar(Tabela* t){
    int tam = t->m + t->r;
    for(int i = 0; i < tam; i++){
        if(t->tabela[i] != NULL){ free(t->tabela[i]); }
    }
    free(t->tabela);
}

void inicializar(int m, int r, Tabela* t){
    int total = m + r;
    if(t != NULL){
        t->m = m;
        t->r = r;
        t->tabela = (char**)malloc(total * sizeof(char*));
        if(t->tabela == NULL){
            printf("Erro: Falha de alocacao de memoria para tabela hash\n");
        } else{
            for(int i = 0; i < total; i++) t->tabela[i] = NULL;
        }
    }
}

char* nova_str(char* prompt){
    int len;
    char* str;
    char buffer[101];

    printf(prompt);
    scanf(" %100[^\n]", buffer);

    len = strlen(buffer) + 1;
    str = (char*)malloc(len * sizeof(str));

    if(str != NULL){
        for(int i = 0; i < len - 1; i++) str[i] = buffer[i];
        str[len - 1] = '\0';
    }
    
    return str;
}

int is_pos_free(int pos, char** t){ return t[pos] == NULL ? 1 : 0; }

int hash(char* s, int tam){
    int soma = 0;
    for(int i = 0; i < strlen(s); i++) soma += s[i];
    return soma % tam;
}

void inserir(char* s, Tabela* t){
    int pos = hash(s, t->m);

    if(is_pos_free(pos, t->tabela)){
        t->tabela[pos] = s;
    } else{
        for(int i = 0; i < t->r; i++){
            pos = i + t->m;
            if(is_pos_free(pos, t->tabela)){
                t->tabela[pos] = s;
                i = t->r;
            }
        }
    }
}

int pesquisar(char* s, Tabela* t){
    int resp = 0;
    int pos = hash(s, t->m);
    if(t->tabela[pos] != NULL && !strcmp(t->tabela[pos], s)){ resp = 1; }
    else{
        for(int i = 0; i < t->r && !resp; i++){
            pos = i + t->m;
            if(t->tabela[pos] != NULL && !strcmp(t->tabela[pos], s)){ resp = 1; }
        }
    }
    return resp;
}

int pesquisar_posicao(char* s, int i, char** t){ return t[i] != NULL && !strcmp(t[i], s) ? 1 : 0; }

char* remover(char* s, Tabela* t){
    int pos = hash(s, t->m);
    char* out = NULL;
    if(pesquisar_posicao(s, pos, t->tabela)){
        t->tabela[pos] = NULL;
        out = s;
    } else{
        for(int i = 0; i < t->r && out == NULL; i++){
            pos = i + t->m;
            if(pesquisar_posicao(s, pos, t->tabela)){
                t->tabela[pos] = NULL;
                out = s;
            }
        }
    }
    return out;
}

void imprimir(Tabela* t){
    for(int i = 0; i < (t->m + t->r); i++){
        char* area = i < t->m ? "PRINCIPAL" : "RESERVA";
        char* conteudo = t->tabela[i] == NULL ? "EMPTY" : t->tabela[i];
        printf("%3d %15s %15s\n", i+1, area, conteudo);
    }
}

int menu(){
    int op;
    printf("===== MENU PRINCIPAL =====\n");
    printf("1. Inserir\n2. Remover\n3. Pesquisar\n4. Imprimir\n0. Sair\nEscolha: ");
    scanf("%d", &op);
    return op;
}

int main(){
    int m, r;

    do{
        printf("Digite a capacidade da area principal seguido da capacidade da reserva da tabela hash: ");
        scanf("%d %d", &m, &r);
        if(m <= 0 || r <= 0){ printf("Valores invalidos para tabela\nTente novamente\n\n"); }
    }while(m <= 0 || r <= 0);

    Tabela tabela;
    inicializar(m, r, &tabela);

    while(1){
        int op = menu();
        printf("\n");
        if(!op){ break; }
        switch(op){
            case 1:{
                char* s = nova_str("Nome para adicionar: ");
                if(s != NULL){ inserir(s, &tabela); }
                break;
            }
            case 2:{
                char* s = nova_str("Nome para remover: ");
                if(s != NULL){
                    char* out = remover(s, &tabela);
                    if(out != NULL){ printf("[REMOVIDO]: %s\n", out); }
                    else{ printf("Elemento nao encontrado\n"); }
                    free(out);
                }
                break;
            }
            case 3:{
                char* s = nova_str("Nome para pesquisar: ");
                if(s != NULL){
                    if(pesquisar(s, &tabela)){
                        printf("Elemento encontrado\n");
                    } else{
                        printf("Elemento nao encontrado\n");
                    }
                }
                break;
            }
            case 4:{
                imprimir(&tabela);
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
    liberar(&tabela);
    return 0;
}
