main:
#Soma dos valores de um vetor
    li s0, 0x100
    li t0, 5
    sw t0,0(s0)
    li t0, 5
    sw t0,4(s0)
    li t0, 4
    sw t0,8(s0)
    li t0, 3
    sw t0,12(s0)
    li t0, 2
    sw t0,16(s0)
    li t0, 1
    sw t0,20(s0)
    addi a0, s0,4           # a0 = endereço base do vetor
    lw a1, 0(s0)            # a1 = tamanho do vetor (5)
    jal ra, sum_vec         # chama a função de soma

# --- FUNÇÃO: sum_vec ---
sum_vec:
    add  t0, zero, zero      # t0 = soma = 0
    add  t1, zero, zero      # t1 = i = 0

sum_loop:
    bge  t1, a1, sum_end     # se i >= n, encerra o loop
    slli t2, t1, 2           # t2 = i * 4 (ajuste de word)
    add  t2, a0, t2          # t2 = endereço de v[i]
    lw   t3, 0(t2)           # t3 = valor de v[i]
    add  t0, t0, t3          # soma += v[i]
    addi t1, t1, 1           # i++
    j    sum_loop

sum_end:
    add  a0, t0, zero        # move o resultado para a0
    j fim

fim:
    j fim                    # loop infinito de segurança
