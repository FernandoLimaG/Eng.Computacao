.data
# Númericos
n:      .word 5
vetor:  .word 1,2,3,4,5

# String
begV:   .asciz "Vetor: ["
endV:   .ascis "]\n"
spcV:   .asciz ", "

.text
.globl main
main:
    # Declaração / Inicialização
    la  s0,n
    lw  s0,0(s0)
    la  s1,vetor

    addi t0,x0,4 # INT: 4-bytes
    mul t0,t0,s0 # t0 = 4 * 5
    mv  s2,t0    # s2 = 20 bytes
    addi t1,x0,-1
    mul t0,t0,t1
    mv  t1,sp    # Endereço inicial sp
    add sp,sp,t0 # Reservar 20 bytes na stack
    sw  s1,0(sp)

    la  a0,begV
    li  a7,4
    ecall

imprimir:
    beqz s0,fim

    lw  a0,0(t1)
    li  a7,1
    ecall

    la  a0,spcV
    li  a7,4
    ecall

    addi t1,t1,4 # Proxima posição na pilha
    addi s0,s0,-1

    j   imprimir

fim:
    # Impressão final
    lw  a0,0(t1)
    li  a7,1
    ecall

    add sp,sp,s2

    la  a0,endV
    li  a7,4
    ecall
