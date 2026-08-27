#include "../include/auxiliar.h"
#include <stdio.h>

void RuntimeException(char* s){ printf("RuntimeException: %s\n", s); }
void IllegalArgumentException(char* s){ printf("IllegalArgumentException: %s\n", s); }
void mallocErr(char* s){ printf("%s malloc failed\n", s); }
void nullErr(char* s){ printf("param <%s> is NULL\n", s); }
void emptyErr(char* s){ printf("param <%s> is EMPTY\n", s); }
