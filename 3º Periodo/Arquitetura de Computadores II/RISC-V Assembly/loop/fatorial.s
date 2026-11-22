# ========== FATORIAL ==========
# - n:      Elemento do usuário
# - resp:   Valor obtido
# - input:  String entrada
# - output: String saida
# ==============================
.data
n:      .word 0
resp:   .word 1
input:  .asciz "Digite elemento inteiro positivo: "
output: .asciz "Resultado: "
erro:	.asciz "Entrada invalida - Elemento deve ser MAIOR que 0 (zero)\n"

.text
.globl main
main:
    # Declaração/Inicialização de variáveis
    la  t0,n
    lw  t0,0(t0)
    la  s0,resp
    lw  s0,0(s0)

inicio:
    # Syscall: Impressão de string
    la  a0,input
    li  a7,4
    ecall

    # Syscall: Coletar valor do usuário
    li  a7,5
    ecall
    mv  t0,a0 # t0 = n

    # Vai para fatorial se valor é maior que 0 (zero)
    bgtz t0,fatorial

    # Entrada inválida
    la  a0,erro
    li  a7,4
    ecall

    j   inicio
    
fatorial:
    beqz t0,fim

    mul s0,s0,t0  # s0 = 1 *= (5,4,3, ...) até fim da recursividade
    addi t0,t0,-1

    j   fatorial

fim:
    # Impressão de saída
    # 1º: String
    la  a0,output
    li  a7,4
    ecall

    # 2º: Int
    mv a0,s0
    li a7,1
    ecall

    # Fim do programa (return 0)
    li a0,0
    li a7,93
    ecall
