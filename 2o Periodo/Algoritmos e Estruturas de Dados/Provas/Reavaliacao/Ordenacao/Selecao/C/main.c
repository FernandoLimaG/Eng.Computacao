#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int* gera_vetor(int n){
    int* v = (int*)malloc(n * sizeof(int));
    if(v != NULL){
        int j = n*2;
        for(int i = 0; i < n; i++){
            v[i] = rand()%j;
        }
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

void ordenar(int* v, int n){
    for(int i = 0; i < n-1; i++){
        int menor = i;
        for(int j = i+1; j < n; j++){
            if(v[j] < v[menor]){ menor = j; }
        }
        swap(&v[i], &v[menor]);
    }
}

int main(){
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
