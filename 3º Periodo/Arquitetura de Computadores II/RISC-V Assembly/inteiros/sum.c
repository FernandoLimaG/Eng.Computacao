#include <stdio.h>

enum
{
    
    TRUE
};

int main(void) {
    int a = 1;
    int b = 2;
    const int esperado = 3;

    //int resp = a+b;

    // printf("Resultado: %d\n Esperado: %d\nResultado: %s\n", resp, esperado, (resp == esperado ? "TRUE" : "FALSE"));
    printf("Resultado: %d\n", a+b);

    return 0;
}
