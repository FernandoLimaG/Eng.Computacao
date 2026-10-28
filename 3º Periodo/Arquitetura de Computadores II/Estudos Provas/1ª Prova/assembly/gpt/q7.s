.data
n:      .word 5
vetor:  .word 12,3,27,9,18
maior:  .word 0
str:    .asciz "Maior elemento: "

.text
.globl main
main:
    # Alocar memória - 20 bytes
    addi sp,sp,-20
    la  s0,vetor
    la  s1,n
    lw  s1,0(s1)
    la  s2,maior
    lw  s2,0(s2)
    
    # Salvar ponteiro para pilha
    mv  t0,sp
    mv  t1,s1

preencher:
    beqz t1,continua

    lw  t2,0(s0)
    sw  t2,0(t0)

    addi t0,t0,4
    addi s0,s0,4
    addi t1,t1,-1

    j preencher

continua:
    # Resetar ponteiros
    mv  t0,sp
    lw  t1,0(t0)

    # Ja lemos a primeira posição - Reposicionar para indice 1
    addi t0,t0,4
    addi s1,s1,-1

encontrar_maior:
    beqz s1,fim

    lw  t2,0(t0)

    # t1 > t2
    bge t1,t2,iterar

    # Se chegou aqui, t1 < t2
    mv  t1,t2
    mv  s2,t1

iterar:
    addi t0,t0,4
    addi s1,s1,-1

    j encontrar_maior

fim:
    # Impressão do resultado
    la  a0,str
    li  a7,4
    ecall

    mv  a0,s2
    li  a7,1
    ecall

    li  a0,0
    li  a7,93
    ecall
