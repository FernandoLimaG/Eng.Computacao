/* ===== Somatório vetor =====
 * Método: clock()
 * Linguagem: C
 * Instâncias: [100, 1000, 10000]
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void preencher_vetor(int *v, int n)
{
    for (int* p = v; p < v + n; p++) *p = rand() % n+1;
}

int main (int argc, char* argv[])
{
    int soma = 0;
    clock_t tempo;
    const int n = atoi(argv[1]);

    // Seed pseudo-aleatoriedade do vetor
    srand(time(NULL));

    // Vetor pseudo-aleatório de tamanho 100, 1000 ou 10000
    int *v = (int*)malloc(n * sizeof(int));
    if (v == NULL) {
        printf("Erro de alocação de memória\n");
        exit(1);
    }
    preencher_vetor(v,n);

    // Medição
    tempo = clock();
    for (int i = 0; i < n; i++) soma += v[i];
    tempo = clock() - tempo;

    printf("Medição 'clock()' %d instâncias: %f[s]\n", n, (float) tempo / CLOCKS_PER_SEC);

    free(v);

    return 0;
}
