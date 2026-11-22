#include <stdio.h>
#include <stdlib.h>

long rec_fat(int a) { return (a == 1 ? a : a * rec_fat(a-1)); }

long it_fat(int a)
{
    long resp = a;
    for(int i = 1; i < 5; i++) resp *= i;
    return resp;
}

int main (void)
{
    int a;

    do{
        printf("Digite um elemento inteiro positivo: ");
        scanf("%d", &a);
        if(a <= 0) printf("Elemento deve ser maior que 0 (zero).\n");
    }while(a <= 0);

    printf("Fatorial RECURSIVO: %ld\nFatorial ITERATIVO: %ld\n", rec_fat(a), it_fat(a));
    return 0;
}
