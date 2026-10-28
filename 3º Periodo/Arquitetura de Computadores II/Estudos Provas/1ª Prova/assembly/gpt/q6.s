.data
vetor:  .word 5,8,2,10,4
n:      .word 5
soma:   .word 0
str:    .asciz "Somatório dos elementos: "

.text
.globl main
main:
    la  s0,n
    lw  s0,0(s0)
    la  t0,vetor
    la  a1,soma
    lw  a1,0(a1)
    addi sp,sp,-20
    mv  s2,sp

    # Preencher o vetor
    mv  t1,s0

preencher:
    beqz t1,fim

    lw  t2,0(t0)
    sw  t2,0(s2)

    add a1,a1,t2
    addi s2,s2,4
    addi t0,t0,4
    addi t1,t1,-1

    j preencher

fim:
    # Liberar memória
    addi sp,sp,20

    la  a0,str
    li  a7,4
    ecall

    mv  a0,a1
    li  a7,1
    ecall

    li  a0,0
    li  a7,93
    ecall
