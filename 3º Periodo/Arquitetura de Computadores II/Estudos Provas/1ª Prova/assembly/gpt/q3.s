# ========== MAIOR ELEMENTO: 3 Entrada ==========
# x em s0
# y em s1
# z em s2

.data
x:      .word 10
y:      .word 5
z:      .word 1
maior:  .word 0
s_big:  .asciz "Maior valor: "

.text
.globl main
main:
    la  s0,x
    lw  s0,0(s0)
    la  s1,y
    lw  s1,0(s1)
    la  s2,z
    lw  s2,0(s2)
    la  s3,maior
    lw  s3,0(s3)

    # X - Y
    sub t0,s0,s1
    
    # X - Y < 0 -> Y > X
    bltz t0,YmaiorX
    
    # X - Z < 0 -> Z > X > Y
    sub t0,s0,s2

    bltz t1,Zmaior

    mv  s3,s0

    j imprimir

imprimir:

    la  a0,s_big
    li  a7,4
    ecall

    mv  a0,s3
    li  a7,1
    ecall

    li  a0,0
    li  a7,93
    ecall

YmaiorX:
    # se: Y > X
    # se: Y - Z < 0 -> Z < Y < X
    sub t0,s1,s2

    bltz t0,Zmaior

    mv  s3,s1

    j imprimir

Zmaior:
    # Caso Z > todos
    mv  s3,s2

    j imprimir
