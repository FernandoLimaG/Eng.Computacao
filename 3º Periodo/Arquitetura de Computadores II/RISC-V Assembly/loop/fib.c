#include <stdio.h>

void printFib(int n) {
    int a = 0;
    int b = 1;
    for(int i = 0; i < n; i++) {
        int c = a+b;
        a = b;
        b = c;
        printf("%d%s", a, (i == n - 1 ? "" : ", "));
    }
}

int main(void) {
    int limite;
    printf("Digite o limite da sequencia de Fibonacci: ");
    scanf("%d", &limite);
    printf("Fibonnaci até %d: ", limite);
    printFib(limite);
    return 0;
}
