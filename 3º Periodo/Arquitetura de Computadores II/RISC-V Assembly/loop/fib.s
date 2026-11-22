#  ========== Descrição ==========
# Impressão da sequência de Fibonacci pela entrada do usuário.
# Variáveis e Registradores utilizados:
#    - n:            s0 && t0-> Entrada do usuário do n-ésimo termo a ser impresso.
#    - vetor:        s1 && t5-> Array dos valores.
#    - separador:    Sem alocação prévia.
#    - solicitar:    Sem alocação prévia.
#    - imprimir:     Sem alocação prévia.
#    - int atual:    t1 -> Valor que vai ser impresso.
#    - int segundo:  t2 -> Segundo termo de fibonacci.
#    - int próximo:  t3 -> Próximo termo de fibonacci obtido via cálculo.

.data
n:          .word 0
vetor:      .word 0

erro:	    .asciz "Entrada inválida, digite outro valor\n"
separador:  .asciz ", "
solicitar:  .asciz "Digite o limite da sequência de Fibonacci: "
imprimir:   .asciz "Sequência de Fibbonacci: "

.text
.globl main
main:
    la s0,n
    la s1,vetor
    addi t1,x0,0
    addi t2,x0,1

    # Solicitar limite
    la a0,solicitar
    li a7,4
    ecall

    # Ler entrada
    li a7,5
    ecall
    mv s0,a0
    mv t0,a0 # Variável temporária para armazenar o contador.

    blez s0,erroEntrada

    mv t5,s1 # Variável temporária para armazenar o vetor.

    j fibonacci

erroEntrada:
   # Imprimir entrada errada
   la a0,erro
   li a7,4
   ecall
   
   j main

fibonacci:
    # Método de cálculo dos termos de fibonacci
    # "beqz" garante parada quando n = 0.
    beqz t0,continua

    add t3,t2,t1
    mv t1,t2
    mv t2,t3

    sw t1,0(s1)

    addi s1,s1,4
    addi t0,t0,-1

    j fibonacci

continua:
    # Intermédio para impressão
    la a0,imprimir
    li a7,4
    ecall

    addi s0,s0,-1

    j printV

printV:
    # Loop de impressão do vetor
    beq t0,s0,fim

    lw a0,0(t5)
    li a7,1
    ecall
    
    la a0,separador
    li a7,4
    ecall

    addi t0,t0,1 # i++
    addi t5,t5,4 # (&v)++

    j printV

fim:
    # Impressão último termo + fim do programa
    lw a0,0(t5)
    li a7,1
    ecall

    li a0,0
    li a7,93
    ecall
