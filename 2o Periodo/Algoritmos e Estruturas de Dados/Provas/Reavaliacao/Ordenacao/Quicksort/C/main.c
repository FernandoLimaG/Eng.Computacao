#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int* gera_vetor(int n){
    int* v = (int*)malloc(n * sizeof(int));
    if(v != NULL){
        int k = n*2;
        for(int i = 0; i < n; i++) v[i] = rand()%k;
    }
    return v;
}

void imprimir(int* v, int n){
    printf("[");
    for(int i = 0; i < n; i++){
        printf("%d", v[i]);
        if(i < n-1){ printf(", "); }
    }
    printf("]\n");
}

void swap(int* a, int* b){
    int tmp = *a;
    *a = *b;
    *b = tmp;
}

void quicksort(int* v, int esq, int dir){
    int i = esq;
    int j = dir;
    int pivo = v[(esq + dir)/2];

    while(i <= j){
        while(v[i] < pivo){ i++; }
        while(v[j] > pivo){ j--; }
        if(i <= j){
            swap(&v[i], &v[j]);
            i++;
            j--;
        }
    }

    if(esq < j){ quicksort(v, esq, j); }
    if(dir > i){ quicksort(v, i, dir); }
}

void ordenar(int* v, int n){ quicksort(v, 0, n); }

int main(){
    srand(time(NULL));
    const int n = 10;
    int* v = gera_vetor(n);
    if(v != NULL){
        printf("Vetor original: ");
        imprimir(v, n);
        ordenar(v, n);
        printf("Vetor ordenado: ");
        imprimir(v, n);
        free(v);
    }
    return 0;
}
