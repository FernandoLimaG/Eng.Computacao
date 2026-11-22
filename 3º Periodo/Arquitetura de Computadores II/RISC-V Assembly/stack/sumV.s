.data
n:      .word 0
v:      .word 0
outV:   .asciz "Vetor gerado: ["
inVi:   .asciz "º Elemento: "
spcV:   .asciz ", "
endV:   .asciz "]\n"
askN:   .asciz "Capacidade do vetor: "
outS: .asciz "Somatório de elementos do vetor: "

.text
.globl main
main:
    li  t0,4
    li  t1,-1

    mul t0,t0,t1

    # Coletar tamanho do vetor
    la  a0,askN
    li  a7,4
    ecall

    li  a7,5
    ecall
    mv  t1,a0
    mv  t2,a0

    # Calcular tamanho (bytes) do vetor
    # Bytes total = N_elemento * 4 * -1
    # '* -1' se deve a reserva de memoria da pilha
    mul t1,t1,t0
    add t1,t1,t0    
    mv  s1,t1       
    li  t0,-1
    mul s1,s1,t0    # Tamanho da pilha POSITIVO (para liberar memoria)

    add sp,sp,t1
    mv  t1,sp
    sw  t2,0(sp)
    addi t1,t1,4

    li  t3,1

fill_v:
    beqz t2,end_fill

    mv  a0,t3
    li  a7,1
    ecall

    la  a0,inVi
    li  a7,4
    ecall

    li  a7,5
    ecall
    sw  a0,0(t1)

    addi t3,t3,1
    addi t2,t2,-1
    addi t1,t1,4

    j fill_v

end_fill:
    # Reestabelecer os ponteiros
    mv  t1,sp
    lw  t2,0(sp)
    addi t1,t1,4

    li  s0,0    # Somatório

sumV:
    beqz t2,end_sum

    lw  t3,0(t1)
    add s0,s0,t3

    addi t1,t1,4
    addi t2,t2,-1

    j   sumV

end_sum:
    # Reestabelecer ponteiros
    mv  t1,sp
    lw  t2,0(sp)

    addi t1,t1,4
    addi t2,t2,-1

    # Impressão do vetor
    la  a0,outV
    li  a7,4
    ecall

print_v:
    beqz t2,end

    lw  a0,0(t1)
    li  a7,1
    ecall

    la  a0,spcV
    li  a7,4
    ecall

    addi t2,t2,-1
    addi t1,t1,4

    j   print_v

end:
    lw  a0,0(t1)
    li  a7,1
    ecall

    la  a0,endV
    li  a7,4
    ecall

    la  a0,outS
    li  a7,4
    ecall

    mv  a0,s0
    li  a7,1
    ecall

    # Liberar sp
    add sp,sp,s1

    li  a0,0
    li  a7,93
    ecall
