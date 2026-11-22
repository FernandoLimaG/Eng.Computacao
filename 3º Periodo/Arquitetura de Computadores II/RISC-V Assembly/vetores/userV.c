#include <stdio.h>
#include <stdlib.h>

void printV(int* v, int n) {
    for(int i = 0; i < n; i++) printf("%d%s", v[i], (i < n - 1 ? ", " : ""));
}

int* geraV(int *n) {
    int *v;

    do{
        printf("Digite a capacidade do vetor [MIN: 2 | MAX: 10]: ");
        scanf("%d", n);
        if(*n <= 1 || *n > 10) printf("\n[ERRO]: Capacidade inválida, tente novamente\n\n");
    }while(*n <= 1 || *n > 10);

    v = (int*)malloc((*n) * sizeof(int));
    if(v == NULL) {
        printf("Erro de alocação de memória: FECHANDO PROGRAMA\n");
        exit(1);
    }

    for(int i = 0; i < *n; i++) {
        printf("%dº termo: ", i+1);
        scanf("%d", v+i);
    }

    return v;
}

int main(void) {
    int n;
    int *v = geraV(&n);

    // Impressão
    printf("Vetor gerado: [");
    printV(v,n);
    printf("]\n");

    return 0;
}
