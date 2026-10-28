.data
n:      .word 5
sum:    .word 0
out:    .asciz "Somatório = "

.text
.globl main
main:
    la  s0,n
    lw  s0,0(s0)
    la  s1,sum
    lw  s1,0(s1)

somar:
    beqz s0,fim

    add s1,s1,s0
    addi s0,s0,-1

    j somar

fim:
    la  a0,out
    li  a7,4
    ecall

    mv  a0,s1
    li  a7,1
    ecall

    li  a0,0
    li  a7,93
    ecall
