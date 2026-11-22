.data
n:      .word 6
vet:    .word 7,4,10,3,8,11
par:    .word 0
str:    .asciz "Quantidade de pares: "

.text
.globl main
main:
    # Inicialização
    la  s0,vet
    la  s1,n
    lw  s1,0(s1)
    la  s2,par
    lw  s2,0(s2)
    li  a1,2

    # Stack pointer - 6 inteiros
    addi sp,sp,-24

    # Ponteiros e iteradores
    mv  t0,s1
    mv  t1,sp

preencher:
    beqz t0,conta_par

    lw  t2,0(s0)
    sw  t2,0(t1)

    addi t1,t1,4
    addi s0,s0,4
    addi t0,t0,-1

    j preencher

conta_par:
    beqz s1,fim

    lw  t1,0(sp)
    rem t2,t1,a1

    # Caso ímpar
    bnez t2,iterar

    # Caso par permanece: acresce contador + itera os ponteiros
    addi s2,s2,1

iterar:
    addi sp,sp,4
    addi s1,s1,-1

    j conta_par

fim:
    # Liberar memória
    addi sp,sp,24

    la  a0,str
    li  a7,4
    ecall

    mv  a0,s2
    li  a7,1
    ecall

    li  a0,0
    li  a7,93
    ecall
