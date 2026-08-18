#ifndef ABP_H
#define ABP_H

typedef struct noAbp{
    int elemento;
    struct noAbp *esq, *dir;
} NoAbp;

typedef struct abp{
    NoAbp* raiz;
} Abp;

void abp_liberar_memoria(NoAbp* no);
void abp_inicializar(Abp* a);
NoAbp* abp_novo_no(int x);
//NoAbp* abp_inserir_rec(int x, NoAbp* i);
void abp_inserir(int x, Abp* a);
//int abp_pesquisar_rec(int x, NoAbp* i);
int abp_pesquisar(int x, Abp* a);
//NoAbp* abp_remover_rec(int x, NoAbp* i);
void abp_remover(int x, Abp* a);
void abp_imprimir(Abp* a);
//void abp_central(NoAbp* i);
//void abp_pre(NoAbp* i);
//void abp_pos(NoAbp* i);

#endif //ABP_H
