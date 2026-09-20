.data
# Númericos
n:          .word 0
vetor:      .word
iterador:   .word 0
minimo:     .word 2
maximo:     .word 10

# Strings (IO)
lerTamanho:     .asciz "Tamanho do vetor: "
lerElemento:    .asciz "º termo: "
abrirVetor:     .asciz "Vetor gerado: ["
fecharVetor:    .asciz "]\n"
espacamento:    .asciz ", "
newLine:        .asciz "\n"

erroTamanho:    .asciz "\nCapacidade inválida, tente novamente\n"

.text
.globl main
main:
    # Inicialização
    la s0,n         # endereço do tamanho do vetor
    lw s0,0(s0)     # valor do tamanho do vetor: 0 inicialmente
    la s1,vetor     # endereço do vetor
    la s2,minimo    # endereço de tamanho minimo
    lw s2,0(s2)     # salva o valor minimo em s2
    la s3,maximo    # endereço de tamanho maximo
    lw s3,0(s3)     # salva o valor maximo em s3
    la t6,iterador  # endereço do iterador loop
    lw t6,0(t6)     # valor do iterador: 0 inicialmente

    # Solicitar entradas do usuário
    la a0,lerTamanho
    li a7,4
    ecall
    
    # Ler tamanho
    li a7,5
    ecall
    add s0,s0,a0

    # Condicional de tamanho inválido <= 2
    ble s0,s2,erro

    # Condicional de tamanho inválid > 10
    bgt s0,s3,erro

    # Copiar o endereço de partida do vetor para um registrador
    mv t0,s1

    j preencher

erro:
    # Imprimir erro
    la a0,erroTamanho
    li a7,4
    ecall

    j main

preencher:
    # Após validação de capacidade, preenchemos o vetor
    # Paramos quando iterador = n do usuário
    beq t6,s0,continua
    mv t5,t6
    addi t5,t5,1

    # Imprimir o numero iterador+1
    add a0,t5,x0
    li a7,1
    ecall

    # Imprimir a string restante
    la a0,lerElemento
    li a7,4
    ecall

    # Ler elemento do usuário + salvar no vetor
    li a7,5
    ecall
    sw a0,0(s1)

    # Próxima posição do vetor = 4 bytes [INT]
    addi s1,s1,4
    addi t6,t6,1

    j preencher

continua:
    # Chamada para impressão do vetor
    la a0,abrirVetor
    li a7,4
    ecall

    # Zerar iterador novamente
    addi t6,x0,0
        
    # Percorrer ate n - 2
    addi s0,s0,-1

    j imprimir

imprimir:
    beq t6,s0,fim

    lw a0,0(t0)
    li a7,1
    ecall

    la a0,espacamento
    li a7,4
    ecall

    addi t6,t6,1
    addi t0,t0,4
    
    j imprimir

fim:
    lw a0,0(t0)
    li a7,1
    ecall

    la a0,fecharVetor
    li a7,4
    ecall

    li a0,0
    li a7,93
    ecall
