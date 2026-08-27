#ifndef ERROR_H
#define ERROR_H

void RuntimeException(char* s);
void IllegalArgumentException(char* s);
void MallocErr(char* s);
void NullErr(char* s);
void EmptyErr(char* s);
void OpcaoInvalida(void);

#endif //ERROR_H
