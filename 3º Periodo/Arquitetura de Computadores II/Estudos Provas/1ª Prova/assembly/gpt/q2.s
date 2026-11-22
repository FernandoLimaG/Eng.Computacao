.data
x:      .word 0
mod:    .word 0
s_mod:  .asciz "|"
s_eq:   .asciz " = "
input:  .asciz  "Digite um valor inteiro com sinal: "

.text
.globl main
main:
    la  s0,x
    lw  s0,0(s0)
    la  s1,mod
    lw  s1,0(s1)

    la  a0,input
    li  a7,4
    ecall

    li  a7,5
    ecall
    mv  s0,a0

    bltz s0,ltz

    mv  s1,s0

    j imprimir

ltz:
    addi t0,x0,-1
    mul s1,s0,t0

imprimir:
    # |
    la  a0,s_mod
    li  a7,4
    ecall

    # valor original
    mv  a0,s0
    li  a7,1
    ecall

    # |
    la  a0,s_mod
    li  a7,4
    ecall
    
    # = 
    la  a0,s_eq
    li  a7,4
    ecall
    
    # módulo
    mv  a0,s1
    li  a7,1
    ecall

    li  a0,0
    li  a7,93
    ecall
