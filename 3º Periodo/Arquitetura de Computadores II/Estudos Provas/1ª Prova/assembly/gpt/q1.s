.data
x:      .word 0
y:      .word 0
maior:  .word 0
s_x:    .asciz "X = "
s_y:    .asciz "Y = "
s_inX:  .asciz "1º valor: "
s_inY:  .asciz "2º valor: "
s_maior:.asciz "Maior valor: "
s_menor:.asciz "Menor valor: "
nl:     .asciz "\n"

.text
.globl main
main:
    la  s0,x
    lw  s0,0(s0)
    la  s1,y
    lw  s1,0(s1)
    la  s2,maior
    lw  s2,0(s2)

    # Coletar os valores de X e Y
    la  a0,s_inX
    li  a7,4
    ecall

    li  a7,5
    ecall
    mv  s0,a0

    la  a0,s_inY
    li  a7,4
    ecall

    li  a7,5
    ecall
    mv  s1,a0

    # Imprimir valores de X e Y
    la  a0,s_x
    li  a7,4
    ecall

    mv  a0,s0
    li  a7,1
    ecall

    la  a0,nl
    li  a7,4
    ecall

    la  a0,s_y
    li  a7,4
    ecall

    mv  a0,s1
    li  a7,1
    ecall

    la  a0,nl
    li  a7,4
    ecall

    # Se y > x: Branch para MAIOR Y
    # Caso contrário: Continuamos pelo estado atual
    sub t0,s0,s1
    bltz t0,maiorY
    
    mv  s2,s0

    # Imprimir Maior e Menor
    la  a0,s_maior
    li  a7,4
    ecall

    mv  a0,s2
    li  a7,1
    ecall

    la  a0,nl
    li  a7,4
    ecall

    li  a0,0
    li  a7,93
    ecall

maiorY:
    
    mv  s2,s1

    # Imprimir Maior e Menor
    la  a0,s_maior
    li  a7,4
    ecall

    mv  a0,s2
    li  a7,1
    ecall

    la  a0,nl
    li  a7,4
    ecall

    li  a0,0
    li  a7,93
    ecall
