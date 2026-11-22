# Inverter vetor
.data:
n:      .word 5
rev:    .word 0
vetor:  .word 1,2,3,4,5
spc:    .asciz " "
str_og: .asciz "Vetor original: " 
str_rv: .asciz "\nVetor inverso: "

.text
.globl main
main:
    la  s0,n
    lw  s0,0(s0)
    la  s1,vetor
    la  s2,rev

    li  t0,4
    li  t1,-1
    mul t0,t0,s0
    mul t0,t0,t1
    add sp,sp,t0

    mv  t0,s0
    mv  t1,s1
    mv  a1,s2

vetor_pilha:
    beqz t0,fim_pilha

    lw  t2,0(t1)
    sw  t2,0(sp)

    addi t0,t0,-1
    addi t1,t1,4
    addi sp,sp,4

    j vetor_pilha

fim_pilha:
    # Mover ponteiros
    mv  t0,s0
    mv  t1,s1
    mv  a2,sp
    addi a2,a2,-4

preencher_rev:
    beqz t0,fim_rev

    lw  t1,0(a2)
    sw  t1,0(a1)

    addi a2,a2,-4
    addi a1,a1,4
    addi t0,t0,-1

    j preencher_rev

fim_rev:
    # Reset do iterador
    mv  t0,s0

    # Imprimir Original
    la  a0,str_og
    li  a7,4
    ecall

printOG:
    beqz t0,pre_printREV

    lw  a0,0(s1)
    li  a7,1
    ecall

    la  a0,spc
    li  a7,4
    ecall

    addi s1,s1,4
    addi t0,t0,-1

    j   printOG

pre_printREV:
    la  a0,str_rv
    li  a7,4
    ecall

print_rev:
    beqz s0,fim

    lw  a0,0(s2)
    li  a7,1
    ecall

    la  a0,spc
    li  a7,4
    ecall

    addi s2,s2,4
    addi s0,s0,-1

    j   print_rev

fim:
    li  a0,0
    li  a7,93
    ecall
