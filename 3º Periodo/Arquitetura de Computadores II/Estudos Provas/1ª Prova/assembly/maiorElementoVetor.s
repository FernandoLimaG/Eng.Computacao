.data
n:.             .word 0
maior:          .word 0
str_tamV:       .asciz "Tamanho do vetor: "
str_elemento:   .asciz "Maior elemento: "
str_termo:      .asciz "Elemento: "
str_inicioV:    .asciz "Vetor gerado: [ "
str_spc:        .asciz " "
str_fimV:       .asciz "]\n"

.text
.globl main
main:
    # Declarar variáveis
    la  s0,n
    lw  s0,0(s0)
    la  s1,maior
    lw  s1,0(s1)

    # Tamanho do vetor
    # Pergunta:
    la  a0,str_tamV
    li  a7,4
    ecall

    # Coletar entrada do teclado
    li  a7,5
    ecall
    mv  s0,a0

    # Alocar espaço na pilha
    li  t0,-1
    mul t0,t0,s0
    li  t1,4
    mul t0,t0,t1
    add sp,sp,t0
    mv  s2,t0
    
    # Varíaveis de controle
    #   1. Iterador = tamanho do vetor
    mv  t0,s0

    #   2. Ponteiro para pilha
    mv  t1,sp

preencher_vetor:
    # Condição de parada: Iterador == 0
    beqz t0,fim_preencher

    # Pergunta: "Elemento: "
    la  a0,str_termo
    li  a7,4
    ecall

    # Guarda valor do usuário na pilha via ponteiro
    li  a7,5
    ecall
    sw  a0,0(t1)

    # Mover o iterador: i -= 1
    addi t0,t0,-1

    # Mover o ponteiro: pilha += 4 bytes -> Próxima posição livre
    addi t1,t1,4

    # Loop
    j preencher_vetor

fim_preencher:
    # Reestabelecer os parâmetros de controle inicial
    mv  t0,s0
    mv  t1,sp

    # Varíavel resposta (1º termo do vetor maior por enquanto)
    lw  t2,0(t1)

    # Caminhar ponteiros para n-1
    addi t0,t0,-1
    addi t1,t1,4

maior_elemento:
    # Condição de parada: Iterador == 0
    beqz t0,fim_maior

    # Salva o elemento atual em um registrador
    lw  t3,0(t1)

    # Se o elemento atual for maior que o maior atual: branch para troca de valor do maior
    bgt t3,t2,trocar_maior

    # Mover ponteiro
    addi t1,t1,4

    # Mover iterador
    addi t0,t0,-1

    j   maior_elemento

trocar_maior:
    # Trocar o valor do maior (t2) para o valor do 
    mv  t2,t3

    # Mover ponteiro
    addi t1,t1,4

    # Mover iterador
    addi t0,t0,-1

    j   maior_elemento

fim_maior:
    # Salvar maior elemento
    mv  s1,t2

    # Reestabelecer ponteiro da pilha
    mv  t1,sp

    # Imprimir strings
    la  a0,str_inicioV
    li  a7,4
    ecall

printV:
    beqz s0,fim

    lw  a0,0(t1)
    li  a7,1
    ecall

    la  a0,str_spc
    li  a7,4
    ecall

    # Mover ponteiro e iterador
    addi t1,t1,4
    addi s0,s0,-1

    j   printV

fim:
    la  a0,str_fimV
    li  a7,4
    ecall

    # Imprimir o maior valor
    la  a0,str_elemento
    li  a7,4
    ecall

    mv  a0,t2
    li  a7,1
    ecall

    # Reestabelecer pilha
    add sp,sp,s2

    # Fim do programa: return 0
    li  a0,0
    li  a7,93
    ecall
