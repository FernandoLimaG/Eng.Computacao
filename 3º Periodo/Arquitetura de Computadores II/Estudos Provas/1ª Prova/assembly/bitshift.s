.data
x:  .word 10
sc: .asciz "Valor original: "
sl: .asciz "Shift lógico de 1 bit a esquerda: "
sr: .asciz "Shift lógico de 1 bit a direita: "
nl: .asciz "\n"

.text
.globl main
main:
    # Valor base
    la  s0,x
    lw  s0,0(s0)

    slli s1,s0,1
    srli s2,s0,1

    la  a0,sc
    li  a7,4
    ecall

    mv  a0,s0
    li  a7,1
    ecall

    la  a0,nl
    li  a7,4
    ecall

    la  a0,sl
    li  a7,4
    ecall

    mv  a0,s1
    li  a7,1
    ecall

    la  a0,nl
    li  a7,4
    ecall

    la  a0,sr
    li  a7,4
    ecall

    mv  a0,s2
    li  a7,1
    ecall

    li  a0,0
    li  a7,93
    ecall
