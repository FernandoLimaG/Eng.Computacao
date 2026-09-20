.data
resp:.word	3
true:.asciz	"\nTRUE"
false:.asciz	"\nFALSE"
out:.asciz	"\nResultado = "
esperado:.asciz	"Esperado = 3"

.text
main:
    # Inicializar: t0: int a = 1 | t1: int b = 2 | t2: int-resultado(a+b) | t3: resp(.data)
    addi t0,x0,1
    addi t1,x0,2
    la t3,resp
    lw t3,0(t3)

    # Add (a+b)
    add t2,t0,t1
    
    # ===== IMPRESSÃO =====
    # Deve ser:
    # 	Esperado = 3
    #	Resultado = {valor}
    #	Booleano "TRUE" se esta correto ou "FALSE" se errado
    
    # Impressão da string "Esperado = 3"
    la a0,esperado
    li a7,4
    ecall

    # Impressão da string "Resultado = "
    la a0,out
    li a7,4
    ecall
    
    # Impressão do resultado numérico
    mv a0,t2
    li a7,1
    ecall

    # Caso Add for igual "resp" -> jump [j] para "correto"
    # Caso Add for diferente -> continue para impressão abaixo
    beq t2,t3,correto

    la a0,false
    j continua

correto:
    # Logica com o ecall
    la a0,true

continua:
    # Impressão do resultado booleano com string
    li a7,4
    ecall

    # Saída (return 0) como em C
    li a0,0
    li a7,93
    ecall
