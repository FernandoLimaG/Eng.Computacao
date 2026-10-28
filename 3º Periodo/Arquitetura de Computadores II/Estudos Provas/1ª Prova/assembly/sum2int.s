.data
x:      .word   0
y:      .word   0
string: .asciz  "Soma = "
input:  .asciz  "Digite o termo: "

.text
.globl main
main:
    la  s0,x
    la  s1,y
    
    la  a0,input
    li  a7,4
    ecall

    li  a7,5
    ecall
    mv  s0,a0

    la  a0,input
    li  a7,4
    ecall

    li  a7,5
    ecall
    mv  s1,a0

    add s2,s0,s1

    la  a0,string
    li  a7,4
    ecall

    mv  a0,s2
    li  a7,1
    ecall

    li  a0,0
    li  a7,93
    ecall
