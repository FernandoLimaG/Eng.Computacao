.data
askN:   .asciz "Tamanho do vetor: "
iniV:   .asciz "Vetor: ["
endV:   .asciz "]\n"
spcV:   .asciz ", "
askV:   .asciz "º termo: "
menor:  .asciz "Menor termo: "

.text
.globl main
main:
    li  t0,4
    li  t1,-1

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

menor_v:
    beqz t1,end_menor

    lw  t2,0(t0)

    bgt t2,s1,proximo

    mv  s1,t2

    addi t0,t0,4
    addi t1,t1,-1

    j menor_v

proximo:    
    addi t0,t0,4
    addi t1,t1,-1

    j menor_v

end_menor:
    # Reestabelecer ponteiros
    mv  t0,sp
    lw  t1,0(sp)

    addi t0,t0,4
    
    # Começo impressão
    la  a0,iniV
    li  a7,4
    ecall

    li  t2,1

print_v:
    beq t1,t2,fim

    lw  a0,0(t0)
    li  a7,1
    ecall

    la  a0,spcV
    li  a7,4
    ecall

    addi t0,t0,4
    addi t1,t1,-1

    j print_v

fim:
    lw  a0,0(t0)
    li  a7,1
    ecall

    la  a0,endV
    li  a7,4
    ecall

    # Imprimir o menor valor
    la  a0,menor
    li  a7,4
    ecall

    mv  a0,s0
    li  a7,1
    ecall

    add sp,sp,s0

    li  a0,0
    li  a7,93
    ecall
