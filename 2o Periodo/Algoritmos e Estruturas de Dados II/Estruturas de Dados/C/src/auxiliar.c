#include "../include/auxiliar.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int lerInt(char* s)
{
    int op;
    printf("%s");
    scanf("%d", &op);
    return op;
}

char* lerStr(char* s)
{
    char buffer[301];
    char* str;
    int len;

    printf("%s");
    scanf(" %300[^\n]", buffer);
    len = strlen(buffer) + 1;
    str = (char*)malloc(len * sizeof(char));
    if(str != NULL) strcpy(str, buffer);
    return str;
}
