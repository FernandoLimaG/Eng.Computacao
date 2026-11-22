.data
n:       .word 8
atual:   .word 0
proximo: .word 1
soma:    .word 0
imprimir:.asciz "Termo de Fibonnaci: "

.text
.globl main
main:
    la  s0, n
    lw  s0, 0(s0)
    la  t1, atual
    lw  t1, 0(t1)
    la  t2, proximo
    lw  t2, 0(t2)
    la  t3, soma
    lw  t3, 0(t3)

fib:
    beqz s0, fim

    add t3, t1, t2
    mv  t1, t2
    mv  t2, t3

    addi s0, s0, -1

    j   fib

fim:
    la  a0, imprimir
    li  a7, 4
    ecall

    mv  a0, t1
    li  a7, 1
    ecall

    li  a0, 0
    li  a7, 93
    ecall
