.data
askN:   .asciz "Tamanho do vetor: "
iniV:   .asciz "Vetor: ["
endV:   .asciz "]\n"
spcV:   .asciz ", "
askV:   .asciz "º termo: "
mult:  .asciz "Multiplicação dos termos do vetor: "

.text
.globl main
main:
    li  t0,4    # Vetor de inteiros: 4 bytes
    li  t1,-1   # Para alocar pilha (-sp)

    # Tamanho do vetor
    la  a0,askN
    li  a7,4
    ecall

    li  a7,5
    ecall
    mv  s0,a0
    mv  t1,a0

    mul s0,s0,t0    # s0 = Tamanho vetor [bits] -> liberar pilha
    mul t0,s0,t1    # t0 = -s0 -> Alocar pilha

    add sp,sp,t0

    mv  t0,sp       # t0 = ponteiro começo da pilha
    sw  t1,0(sp)    # 0(sp) = Tamanho do vetor

    li  t2,1

    addi t0,t0,4


fill_v:
    bgt t2,t1,end_fill

    mv  a0,t2
    li  a7,1
    ecall

    la  a0,askV
    li  a7,4
    ecall

    li  a7,5
    ecall
    sw  a0,0(t0)

    addi t2,t2,1
    addi t0,t0,4

    j fill_v

end_fill:
    mv  t0,sp
    lw  t1,0(sp)
    
    addi t1,t1,-1
    addi t0,t0,4

    lw  s1,0(t0)

    addi t0,t0,4

mul_v:
    beqz t1,end_mul

    lw  t2,0(t0)
    mul s1,s1,t2

    addi t1,t1,-1
    addi t0,t0,4

    j mul_v

end_mul:
    mv  t0,sp
    lw  t1,0(sp)

    addi t1,t1,-1
    addi t0,t0,4

    # Imprimir Vetor
    la  a0,iniV
    li  a7,4
    ecall

print_v:
    beqz t1,fim

    lw  a0,0(t0)
    li  a7,1
    ecall

    la  a0,spcV
    li  a7,4
    ecall

    addi t1,t1,-1
    addi t0,t0,4

    j   print_v

fim:
    lw  a0,0(t0)
    li  a7,1
    ecall

    la  a0,endV
    li  a7,4
    ecall

    la  a0,mult
    li  a7,4
    ecall

    mv  a0,s1
    li  a7,1
    ecall

    add sp,sp,s0

    li  a0,0
    li  a7,93
    ecall
