.data
dec:        .word 0
n:          .word 0
ask:        .asciz "Digite o valor máximo: "
resp:       .asciz "Quantidade de Decrementos: "

.text
.globl main
main:
    la  t0,n
    lw  t0,0(t0)
    la  a1,dec
    lw  a1,0(a1)

    la  a0,ask
    li  a7,4
    ecall

    li  a7,5
    ecall
    mv  t0,a0

loop:
    beqz t0,fim

    addi t0,t0,-1
    addi a1,a1,1

    j loop

fim:
    # Impressão do resultado
    la  a0,resp
    li  a7,4
    ecall

    mv  a0,a1
    li  a7,1
    ecall

    li  a0,0
    li  a7,93
    ecall
