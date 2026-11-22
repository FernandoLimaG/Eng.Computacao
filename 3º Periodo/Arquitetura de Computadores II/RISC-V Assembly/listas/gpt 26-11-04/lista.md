# Lista de Exercícios — RISC-V Assembly

Arquitetura de Computadores II | 20 questões

Objetivo: desenvolver algoritmos em RISC-V Assembly utilizando operações com números inteiros, entrada e saída de dados, estruturas condicionais, laços de repetição, vetores, procedimentos, pilha (stack) e alocação dinâmica de memória (heap).

Orientações gerais:

- Utilize o simulador RARS e a arquitetura RISC-V RV32.
- Trabalhe com números inteiros de 32 bits, salvo indicação contrária.
- Utilize `ecall` para leitura, impressão e demais serviços disponibilizados pelo simulador.
- Utilize instruções de desvio, operações aritméticas e acesso à memória conforme a necessidade de cada exercício.
- Quando solicitado, implemente os algoritmos utilizando a stack ou a heap, sem substituir essas estruturas por vetores estáticos.
- Não é necessário tratar entradas inválidas, exceto quando explicitamente solicitado.

## Parte I — Operações básicas, condicionais e loops

### Questão 1 — Operações aritméticas

Desenvolva um programa que leia dois números inteiros, A e B, e realize as seguintes operações:

a) Calcule e imprima a soma dos números.

b) Calcule e imprima a diferença entre A e B.

c) Calcule e imprima o produto dos números.

d) Calcule e imprima o quociente inteiro da divisão de A por B.

e) Calcule e imprima o resto da divisão de A por B.

Considere que B será diferente de zero.

### Questão 2 — Classificação de números inteiros

Desenvolva um programa que leia um número inteiro N e determine:

a) Se o número é positivo, negativo ou zero.

b) Se o número é par ou ímpar.

c) Se o número é múltiplo de 3.

d) O valor absoluto do número.

Imprima os resultados de cada classificação.

### Questão 3 — Maior, menor e intermediário

Desenvolva um programa que leia três números inteiros distintos, A, B e C.

a) Determine o maior número.

b) Determine o menor número.

c) Determine o número intermediário.

d) Imprima os três números em ordem crescente.

Não utilize vetores ou estruturas de ordenação prontas.

### Questão 4 — Somatórios e operações com sequências

Desenvolva um programa que leia um inteiro positivo N e calcule:

a) A soma de todos os números inteiros de 1 até N.

b) A soma de todos os números pares entre 1 e N.

c) A soma de todos os números ímpares entre 1 e N.

d) A quantidade de números divisíveis por 3 no intervalo.

Utilize laços de repetição para realizar os cálculos.

### Questão 5 — Fatorial e sequência de Fibonacci

Desenvolva um programa que leia um inteiro N maior ou igual a zero.

a) Calcule e imprima o fatorial de N utilizando um laço de repetição.

b) Imprima os N primeiros termos da sequência de Fibonacci, iniciando em 0 e 1.

c) Calcule a soma dos termos da sequência de Fibonacci gerada.

Considere entradas suficientemente pequenas para evitar overflow de 32 bits.

## Parte II — Vetores e manipulação de memória

### Questão 6 — Leitura e análise de vetor

Desenvolva um programa que leia 10 números inteiros e os armazene em um vetor declarado na seção `.data`.

a) Imprima todos os elementos na ordem de inserção.

b) Determine o maior e o menor elemento.

c) Calcule a soma dos elementos.

d) Calcule a média inteira dos elementos.

e) Informe quantos elementos são maiores que a média calculada.

### Questão 7 — Inversão de vetor

Desenvolva um programa que leia 8 números inteiros e os armazene em um vetor.

a) Imprima o vetor original.

b) Inverta a posição dos elementos diretamente no mesmo vetor, sem utilizar um segundo vetor.

c) Imprima o vetor após a inversão.

d) Determine se o vetor original era um palíndromo, isto é, se sua sequência de elementos era igual quando percorrida nos dois sentidos.

### Questão 8 — Busca e contagem em vetor

Desenvolva um programa que leia 10 números inteiros e os armazene em um vetor.

Em seguida, leia um número X.

a) Realize uma busca sequencial por X.

b) Imprima o índice da primeira ocorrência de X, ou -1 caso não exista.

c) Conte quantas vezes X aparece no vetor.

d) Imprima todos os índices onde X foi encontrado.

Considere índices iniciando em zero.

### Questão 9 — Ordenação de vetor

Desenvolva um programa que leia 10 números inteiros e os armazene em um vetor.

a) Implemente o algoritmo Bubble Sort para ordenar os números em ordem crescente.

b) Imprima o vetor ordenado.

c) Conte quantas trocas de elementos foram realizadas durante a ordenação.

d) Determine o segundo maior valor distinto do vetor, caso exista.

Não utilize procedimentos externos de ordenação.

### Questão 10 — Operações com matriz

Desenvolva um programa que armazene uma matriz 3×3 de números inteiros na memória.

a) Leia os nove elementos da matriz.

b) Imprima os elementos da diagonal principal.

c) Calcule a soma da diagonal secundária.

d) Determine o maior elemento da matriz.

e) Calcule a soma de todos os elementos de cada linha, imprimindo os três resultados separadamente.

Considere a matriz armazenada de forma linear, com os elementos organizados por linhas.

## Parte III — Stack e procedimentos

### Questão 11 — Armazenamento de inteiros na stack

Desenvolva um programa que leia 5 números inteiros e os armazene exclusivamente na stack.

a) Reserve o espaço necessário na pilha.

b) Armazene cada número na região reservada.

c) Imprima os números na ordem em que foram lidos.

d) Imprima os números na ordem inversa.

e) Calcule a soma dos cinco números armazenados.

Ao final, restaure o ponteiro da stack para seu valor original.

### Questão 12 — Inversão de sequência utilizando stack

Desenvolva um programa que leia um inteiro N, entre 1 e 10, seguido de N números inteiros.

a) Empilhe os números, um por vez.

b) Desempilhe os elementos e imprima-os na ordem inversa da leitura.

c) Durante o desempilhamento, determine o maior número armazenado.

d) Ao final, imprima o maior valor encontrado.

Utilize a stack como uma estrutura LIFO, realizando operações de empilhamento e desempilhamento.

### Questão 13 — Procedimentos e passagem de parâmetros

Desenvolva um programa que leia dois números inteiros A e B.

Implemente três procedimentos independentes:

a) `soma`: recebe A e B e retorna a soma.

b) `maior`: recebe A e B e retorna o maior valor.

c) `potencia`: recebe A e B e retorna A elevado a B, considerando B maior ou igual a zero.

O programa principal deverá chamar os três procedimentos e imprimir seus resultados.

Requisitos:

- Utilize `jal` para chamar os procedimentos.
- Utilize `ret` para retornar ao programa principal.
- Utilize os registradores de argumentos e retorno conforme a convenção de chamadas RISC-V.

### Questão 14 — Fatorial recursivo utilizando stack

Desenvolva um programa que leia um número inteiro N, maior ou igual a zero, e calcule seu fatorial utilizando recursividade.

a) Implemente um procedimento recursivo `fatorial`.

b) Utilize a stack para preservar o endereço de retorno e os valores necessários em cada chamada.

c) Estabeleça corretamente o caso base da recursão.

d) Imprima o resultado do cálculo.

e) Garanta que o ponteiro da stack seja restaurado corretamente após todas as chamadas.

Considere N suficientemente pequeno para evitar overflow.

### Questão 15 — Máximo divisor comum com recursividade

Desenvolva um programa que leia dois números inteiros positivos, A e B, e calcule seu máximo divisor comum (MDC).

a) Implemente o algoritmo de Euclides utilizando recursividade.

b) Crie um procedimento `mdc` que receba os dois valores.

c) Utilize a stack para preservar as informações necessárias durante as chamadas recursivas.

d) Retorne o resultado ao programa principal.

e) Calcule e imprima também o mínimo múltiplo comum (MMC) utilizando o MDC encontrado.

## Parte IV — Heap e alocação dinâmica

### Questão 16 — Vetor dinamicamente alocado

Desenvolva um programa que leia um inteiro positivo N, representando a quantidade de elementos de um vetor.

a) Aloque dinamicamente na heap espaço suficiente para N números inteiros de 32 bits.

b) Leia e armazene os N elementos na região alocada.

c) Imprima todos os elementos.

d) Calcule a soma e a média inteira dos números.

e) Determine o maior e o menor elemento.

Utilize o serviço de alocação dinâmica (`sbrk`) disponibilizado pelo RARS.

### Questão 17 — Vetores dinâmicos e operações entre vetores

Desenvolva um programa que leia um inteiro positivo N.

a) Aloque dinamicamente dois vetores A e B, ambos com capacidade para N números inteiros.

b) Leia os elementos dos dois vetores.

c) Aloque um terceiro vetor C, também com N elementos.

d) Armazene em C a soma dos elementos correspondentes de A e B.

e) Imprima o vetor C.

f) Calcule o produto escalar entre A e B, definido por:

\\[ P=\sum\_{i=0}^{N-1} A[i]\cdot B[i] \\]

Imprima o produto escalar calculado.

### Questão 18 — Matriz alocada dinamicamente

Desenvolva um programa que leia dois números inteiros positivos, L e C, representando a quantidade de linhas e colunas de uma matriz.

a) Aloque dinamicamente espaço para uma matriz de L×C elementos inteiros.

b) Leia e armazene todos os elementos.

c) Calcule a soma de cada linha.

d) Calcule a soma de cada coluna.

e) Determine o maior elemento da matriz e sua posição (linha e coluna).

f) Imprima a soma total dos elementos.

Armazene os elementos em uma região contínua de memória, organizados por linhas.

## Parte V — Integração de conceitos

### Questão 19 — Ordenação com heap e procedimentos

Desenvolva um programa que leia um inteiro positivo N e, em seguida, N números inteiros.

a) Aloque dinamicamente na heap um vetor de N elementos.

b) Leia e armazene os números.

c) Implemente um procedimento `ordenar` que receba o endereço inicial do vetor e sua quantidade de elementos.

d) Dentro do procedimento, implemente o algoritmo Selection Sort para ordenar os elementos em ordem crescente.

e) Implemente um segundo procedimento `buscar` que realize uma busca sequencial por um valor informado pelo usuário.

f) Imprima o vetor ordenado e o índice do elemento procurado, ou -1 caso não exista.

Os procedimentos devem seguir a convenção de chamadas RISC-V e preservar os registradores necessários.

### Questão 20 — Sistema de processamento de números inteiros

Desenvolva um programa que integre leitura, impressão, loops, procedimentos, stack e heap.

O programa deverá apresentar o seguinte menu:

```
1 - Inserir números
2 - Imprimir números
3 - Ordenar números
4 - Buscar número
5 - Calcular estatísticas
0 - Encerrar
```

Requisitos:

a) Na primeira opção, leia a quantidade N de elementos, aloquе dinamicamente um vetor na heap e armazene os números informados pelo usuário.

b) Na segunda opção, imprima todos os elementos armazenados.

c) Na terceira opção, ordene os elementos utilizando o algoritmo Bubble Sort.

d) Na quarta opção, leia um número X e realize uma busca sequencial, imprimindo o índice de sua primeira ocorrência ou -1 caso não exista.

e) Na quinta opção, calcule e imprima a soma, a média inteira, o maior e o menor elemento do vetor.

f) Implemente cada operação do menu como um procedimento independente.

g) Utilize a stack para preservar endereços de retorno e registradores necessários durante as chamadas dos procedimentos.

h) Utilize um loop principal para manter o menu em execução até que o usuário escolha a opção 0.

Considere que o usuário deverá inserir os números antes de executar as demais operações. A opção de inserção será realizada uma única vez.

## Distribuição dos conteúdos

| Conteúdo                                          | Questões |
| ------------------------------------------------- | -------- |
| Leitura, impressão e operações com inteiros       | 1–5      |
| Condicionais e loops                              | 1–10     |
| Vetores e endereçamento de memória                | 6–10     |
| Stack e procedimentos                             | 11–15    |
| Recursividade                                     | 14–15    |
| Heap e alocação dinâmica                          | 16–20    |
| Integração de procedimentos, memória e algoritmos | 19–20    |

As questões estão organizadas em ordem progressiva de complexidade. As primeiras trabalham instruções e controle de fluxo; as intermediárias exigem manipulação de memória e aplicação da convenção de chamadas; as últimas combinam os conceitos em programas mais completos, mantendo algoritmos compatíveis com o nível de Arquitetura de Computadores II.
