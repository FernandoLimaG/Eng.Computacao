# Multiplicação sem MUL
.data
a:      .word 0
b:      .word 0
in_a:   .asciz "1º valor: "
in_b:   .asciz "2º valor: "
str:    .asciz "Multiplicação = "

.text
.globl main
main:
    la  s0,a
    la  s1,b

    # Valor de A:
    la  a0,in_a
    li  a7,4
    ecall

    li  a7,5
    ecall
    mv  s0,a0

    # Valor de B:
    la  a0,in_b
    li  a7,4
    ecall

    li  a7,5
    ecall
    mv  s1,a0

    # Iterador = valor de B
    mv  t0,s1
    li  a1,0

multiplicar:
    beqz t0,fim

    add a1,a1,s0
    addi t0,t0,-1

    j multiplicar

fim:
    la  a0,str
    li  a7,4
    ecall

    mv  a0,a1
    li  a7,1
    ecall

    li  a0,0
    li  a7,93
    ecall
