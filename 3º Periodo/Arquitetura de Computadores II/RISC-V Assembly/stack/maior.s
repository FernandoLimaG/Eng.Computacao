.data
n:      .word 0
v:      .word 0
outV:   .asciz "Vetor gerado: ["
inVi:   .asciz "º Elemento: "
spcV:   .asciz ", "
endV:   .asciz "]\n"
askN:   .asciz "Capacidade do vetor: "
outS:   .asciz "Maior elemento do vetor: "

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
    
    # Bytes do vetor
    mul t0,t0,t1
    addi t0,t0,-4
    mv  s0,t0

    add sp,sp,s0
    sw  a0,0(sp)

    mv  t0,sp
    lw  t1,0(sp)
    li  t2,1

    addi t0,t0,4

fill_v:
    beqz t1,end_fill

    mv  a0,t2
    li  a7,1
    ecall

    la  a0,inVi
    li  a7,4
    ecall

    li  a7,5
    ecall
    sw  a0,0(t0)

    addi t0,t0,4
    addi t1,t1,-1
    addi t2,t2,1

    j   fill_v

end_fill:
    mv  t0,sp
    lw  t1,0(sp)

    lw  s1,4(sp)

    addi t0,t0,8
    addi t1,t1,-1

maior:
    beqz t1,end_maior

    lw  t2,0(t0)

    bgt t2,s1,troca_maior

    addi t0,t0,4
    addi t1,t1,-1

    j   maior

troca_maior:
    mv  s1,t2

    addi t0,t0,4
    addi t1,t1,-1

    j   maior

end_maior:
    mv  t0,sp
    lw  t1,0(sp)

    la  a0,outV
    li  a7,4
    ecall

    addi t0,t0,4
    addi t1,t1,-1

print_v:
    beqz t1,fim

    lw  a0,0(t0)
    li  a7,1
    ecall

    la  a0,spcV
    li  a7,4
    ecall

    addi t0,t0,4
    addi t1,t1,-1

    j   print_v

fim:
    lw  a0,0(t0)
    li  a7,1
    ecall

    la  a0,endV
    li  a7,4
    ecall

    la  a0,outS
    li  a7,4
    ecall

    mv  a0,s1
    li  a7,1
    ecall

    li  t0,-1
    mul s0,s0,t0
    add sp,sp,s0

    li  a0,0
    li  a7,93
    ecall
