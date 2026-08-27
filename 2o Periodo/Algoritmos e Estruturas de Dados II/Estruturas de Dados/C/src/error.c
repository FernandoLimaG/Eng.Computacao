#include "../include/error.h"
#include <stdio.h>

void RuntimeException(char* s){ printf("RuntimeException: %s\n", s); }
void IllegalArgumentException(char* s){ printf("IllegalArgumentException: %s\n", s); }
void MallocErr(char* s){ printf("param <%s> failed memory allocation\n", s); }
void NullErr(char* s){ printf("param <%s> is NULL\n", s); }
void EmptyErr(char* s){ printf("param <%s> is EMPTY\n", s); }
void OpcaoInvalida(void){ printf("Opção inválida, tente novamente\n"); }
