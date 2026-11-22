# Operações Aritméticas: Soma, Subtração, Multiplicação e Divisão (quociente e resto) de inteiros
.data
askA:       .asciz "Digite o valor de A: "
askB:       .asciz "Digite o valor de B: "
askOp:      .asciz "1. Soma\n2. Subtração\n3. Multiplicação\n4. Divisão\n0. Sair\nEscolha: "
str_resp:   .asciz "Resultado = "
str_resto:  .asciz "Resto = "
new_line:   .asciz "\n"
err_op:     .asciz "Opção inválida, tente novamente\n"
err_div:    .asciz "Divisão por zero encontrada.\nTente novamente\n\n"
str_fim:    .asciz "\nFIM DO PROGRAMA\n"

# ============================
#        MEMORY Sheet
# ============================
# s0: int A
# s1: int B
# s2: Resultado
# s3: 1
# s4: 2
# s5: 3
# s6: 4
# s7: 5
# ============================

.text
.globl main
main:
    # Valor maximo de escolha: 5
    li  s2,0
    li  s3,1
    li  s4,2
    li  s5,3
    li  s6,4
    li  s7,5

    #Coleta de valores A e B
    la  a0,askA
    li  a7,4
    ecall

    li  a7,5
    ecall
    mv  s0,a0   # s0 = A

    la  a0,askB
    li  a7,4
    ecall

    li  a7,5
    ecall
    mv  s1,a0   # s1 = B

    la  a0,new_line
    li  a7,4
    ecall

select_op:
    # Seletor de Operação
    la  a0,askOp
    li  a7,4
    ecall

    li  a7,5
    ecall   # a0 = Operação escolhida: [0,4]
    mv  t0,a0

    # Escolha inválida
    blt t0,x0,erro
    bgt t0,s7,erro

    # Escolhas válidas
    beq t0,x0,end
    beq t0,s3,soma
    beq t0,s4,sub
    beq t0,s5,pre_mult
    beq t0,s6,pre_div

erro:
    # Inválido: retorna para coleta de valores (inicio do programa)
    la  a0,err_op
    li  a7,4
    ecall

    j   main

erro_div:
    # Divisão por zero encontrada
    la  a0,err_div
    li  a7,4
    ecall

    j   main

soma:
    add s2,s0,s1

    j   resp

sub:
    neg s1,s1   # s1 = -s1 (subtração: B troca sinal)
    add s2,s0,s1

    j   resp

pre_mult:
    # 

    # Definimos resp (s2) com valor de A
    mv  s2,s0
    addi s1,s1,-1

mult_pos:
    beqz s1,resp

    add  s2,s2,s0
    addi s1,s1,-1

    j   mult

pre_div:
    # Garantir segurança do sistema
    #   1. Evitar divisão por 0
    #   2. Negar o valor de B para subtração sucessivas
    #   3. t1 = resto && s2(resp) = quociente
    beqz s1,erro_div

    neg t2,s1   # t2 = -B
    mv  t1,s0

div:
    # Subtrações Sucessivas
    blt t1,s1,resp_div

    add t1,t1,t2
    addi s2,s2,1

    j   div

resp:
    la  a0,new_line
    li  a7,4
    ecall

    la  a0,str_resp
    li  a7,4
    ecall

    mv  a0,s2
    li  a7,1
    ecall

    la  a0,new_line
    li  a7,4
    ecall

    li  a7,4
    ecall

    j main

resp_div:
    la  a0,new_line
    li  a7,4
    ecall

    # Quociente
    la  a0,str_resp
    li  a7,4
    ecall

    mv  a0,s2
    li  a7,1
    ecall

    la  a0,new_line
    li  a7,4
    ecall

    # Resto
    la  a0,str_resto
    li  a7,4
    ecall

    mv  a0,t1
    li  a7,1
    ecall

    la  a0,new_line
    li  a7,4
    ecall

    li  a7,4
    ecall

    j main

end:
    la  a0,str_fim
    li  a7,4
    ecall

    li  a0,0
    li  a7,93
    ecall
