#include <stdio.h>

void printV(int *v, int tam) {
    for(int *t = v; t < v + tam; t++) printf("%d%s", *t, (t == v + tam-1 ? "" : ", "));
}

int main(void) {
    int vetor[] = {1,2,3,4,5};
    int n = 5;
    printf("Vetor = [");
    printV(vetor,5);
    printf("]\n");
    return 0;
}
