#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int* gera_vetor(int n){
    int* v = (int*)malloc(n * sizeof(int));
    if(v != NULL){
        for(int i = 0; i < n; i++){
            v[i] = rand() % (n+1);
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

void insercao(int* v, int n){
    for(int i = 1; i < n; i++){
        int j = i-1;
        int tmp = v[i];
        while(j >= 0 && v[j] > tmp){
            v[j+1] = v[j];
            j--;
        }
        v[j+1] = tmp;
    }
}

int main(){
    srand(time(NULL));
    int* v = gera_vetor(10);
    printf("VETOR ORIGINAL: ");
    imprimir(v, 10);
    insercao(v, 10);
    printf("VETOR ORDENADO: ");
    imprimir(v, 10);
    free(v);
    return 0;
}
