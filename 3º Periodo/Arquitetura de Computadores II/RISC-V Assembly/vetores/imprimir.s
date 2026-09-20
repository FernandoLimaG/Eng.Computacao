.data
n:      .word 5
vetor:  .word 1,2,3,4,5
string: .asciz "Vetor: ["
espaco: .asciz ", "
fechar: .asciz "]\n" 

.text
.globl main
main:
    # Inicialização
    la t0,n
    lw t0,0(t0)
    la t1,vetor
    addi t3,x0,0

    # Impreessão
    la a0,string
    li a7,4
    ecall

    # Vamos usar o loop ate n-1
    addi t0,t0,-1

    j imprimir

imprimir:
    # Loop de impressão feita por chamada recursiva - saida caso contado = n
    beq t3,t0,fim

    # Imprimir o termo do vetor
    lw t2,0(t1)
    mv a0,t2
    li a7,1
    ecall

    # Impressão do espaçamento - Formatação
    la a0,espaco
    li a7,4
    ecall

    # Incrementar contador + mover ponteiro do vetor em 4 bytes [INT = 4 bytes]
    addi t1,t1,4
    addi t3,t3,1

    j imprimir

fim:
    # Imprimir o último termo do vetor
    lw t2,0(t1)
    mv a0,t2
    li a7,1
    ecall
    
    # Imprimir formatação final do vetor
    la a0,fechar
    li a7,4
    ecall

    # Fim do programa - return 0
    li a0,0
    li a7,93
    ecall
