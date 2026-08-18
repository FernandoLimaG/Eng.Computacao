#ifndef VETOR_H
#define VETOR_H

// ===== GERAR VETORES =====
// Vetores de entrada do usuário
int* new_int_arr(int n);
char** new_str_arr(int n);
float* new_float_arr(int n);
double* new_double_arr(int n);

// Vetores aleatórios
int* random_int_arr(int n, int inf, int sup);
char** random_str_arr(int n, int inf, int sup);
float* random_float_arr(int n, int inf, int sup);
double* random_double_arr(int n, int inf, int sup);

// ===== METODOS VETORES =====
void print_int_arr(int* v, int n);
//void print_int_arr(int* v, int n);
//void print_int_arr(int* v, int n);
//void print_int_arr(int* v, int n);

#endif //VETOR_H
