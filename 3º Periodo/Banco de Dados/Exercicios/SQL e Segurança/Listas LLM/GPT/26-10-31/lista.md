# Lista de Exercícios — Banco de Dados (DDL e DML)

**Quantidade:** 20 exercícios  
**Dificuldade:** Básico → Intermediário → Avançado  
**SGBD:** PostgreSQL (sintaxe SQL amplamente compatível com outros SGBDs).

**Objetivo:** Praticar a criação, manipulação e consulta de dados utilizando exclusivamente comandos DDL e DML, com progressão gradual da dificuldade.

A lista utilizará um único cenário: **Sistema de Gestão de uma Loja de Informática**.

Todos os exercícios serão realizados sobre as mesmas quatro tabelas, criadas e povoadas nos exercícios iniciais. Não será necessário criar outras tabelas ou bancos de dados.

---

## Parte I — Criação e povoamento (Exercícios 1–4)

**NÍVEL BÁSICO — DDL E INSERT**

### Exercício 1 — Criação das tabelas

Utilizando `CREATE TABLE`, crie as quatro tabelas abaixo, respeitando os atributos, tipos de dados e restrições definidos.

**Tabela: CLIENTE**

| Atributo | Tipo | Restrição |
|---|---|---|
| id_cliente | INTEGER | PRIMARY KEY |
| nome | VARCHAR(100) | NOT NULL |
| cidade | VARCHAR(60) | NOT NULL |
| estado | CHAR(2) | NOT NULL |
| email | VARCHAR(100) | UNIQUE |

**Tabela: PRODUTO**

| Atributo | Tipo | Restrição |
|---|---|---|
| id_produto | INTEGER | PRIMARY KEY |
| nome | VARCHAR(100) | NOT NULL |
| categoria | VARCHAR(50) | NOT NULL |
| preco | DECIMAL(10,2) | NOT NULL |
| estoque | INTEGER | NOT NULL |

**Tabela: PEDIDO**

| Atributo | Tipo | Restrição |
|---|---|---|
| id_pedido | INTEGER | PRIMARY KEY |
| id_cliente | INTEGER | FOREIGN KEY → CLIENTE |
| data_pedido | DATE | NOT NULL |
| status | VARCHAR(20) | NOT NULL |

**Tabela: ITEM_PEDIDO**

| Atributo | Tipo | Restrição |
|---|---|---|
| id_pedido | INTEGER | PRIMARY KEY (composta) e FOREIGN KEY → PEDIDO |
| id_produto | INTEGER | PRIMARY KEY (composta) e FOREIGN KEY → PRODUTO |
| quantidade | INTEGER | NOT NULL |
| preco_unitario | DECIMAL(10,2) | NOT NULL |

**Observações:** Utilize `CHECK` para impedir valores negativos em `preco`, `estoque` e `preco_unitario`, e para exigir `quantidade > 0`. Não utilize geração automática de IDs.

### Exercício 2 — Inserção de clientes e produtos

Utilizando `INSERT INTO`, insira os seguintes registros.

**CLIENTE**

| ID | Nome | Cidade | UF | Email |
|---|---|---|---|---|
| 1 | Ana Souza | Belo Horizonte | MG | ana@email.com |
| 2 | Bruno Lima | São Paulo | SP | bruno@email.com |
| 3 | Carla Mendes | Contagem | MG | carla@email.com |
| 4 | Daniel Rocha | Rio de Janeiro | RJ | daniel@email.com |
| 5 | Elisa Ferreira | Belo Horizonte | MG | elisa@email.com |
| 6 | Felipe Costa | Curitiba | PR | felipe@email.com |
| 7 | Gabriela Alves | Campinas | SP | gabi@email.com |
| 8 | Henrique Dias | Betim | MG | henrique@email.com |

**PRODUTO**

| ID | Nome | Categoria | Preço (R$) | Estoque |
|---|---|---|---|---|
| 1 | Notebook Dell | Computadores | 4500,00 | 10 |
| 2 | Mouse Logitech | Periféricos | 150,00 | 50 |
| 3 | Teclado Mecânico | Periféricos | 350,00 | 25 |
| 4 | Monitor LG 27 | Monitores | 1200,00 | 15 |
| 5 | SSD Kingston 1TB | Armazenamento | 420,00 | 30 |
| 6 | Memória RAM 16GB | Componentes | 280,00 | 40 |
| 7 | Placa de Vídeo RTX | Componentes | 3200,00 | 8 |
| 8 | Webcam Full HD | Periféricos | 250,00 | 20 |
| 9 | HD Externo 2TB | Armazenamento | 500,00 | 12 |
| 10 | Processador Ryzen 7 | Componentes | 1800,00 | 6 |

### Exercício 3 — Inserção de pedidos

Insira os seguintes registros na tabela `PEDIDO`.

| ID Pedido | ID Cliente | Data | Status |
|---|---|---|---|
| 101 | 1 | 2026-08-05 | Entregue |
| 102 | 2 | 2026-08-07 | Entregue |
| 103 | 1 | 2026-08-10 | Pendente |
| 104 | 3 | 2026-08-12 | Entregue |
| 105 | 4 | 2026-09-01 | Cancelado |
| 106 | 5 | 2026-09-03 | Entregue |
| 107 | 2 | 2026-09-10 | Pendente |
| 108 | 6 | 2026-09-15 | Entregue |
| 109 | 3 | 2026-10-02 | Pendente |
| 110 | 7 | 2026-10-05 | Entregue |
| 111 | 1 | 2026-10-12 | Entregue |
| 112 | 5 | 2026-10-18 | Pendente |

### Exercício 4 — Inserção dos itens dos pedidos

Insira os registros abaixo na tabela `ITEM_PEDIDO`.

| Pedido | Produto | Quantidade | Preço unitário (R$) |
|---|---|---|---|
| 101 | 1 | 1 | 4500,00 |
| 101 | 2 | 2 | 150,00 |
| 102 | 4 | 2 | 1200,00 |
| 102 | 3 | 1 | 350,00 |
| 103 | 7 | 1 | 3200,00 |
| 104 | 5 | 2 | 420,00 |
| 104 | 6 | 4 | 280,00 |
| 105 | 10 | 1 | 1800,00 |
| 106 | 2 | 3 | 150,00 |
| 106 | 8 | 2 | 250,00 |
| 107 | 1 | 1 | 4500,00 |
| 108 | 9 | 2 | 500,00 |
| 109 | 3 | 2 | 350,00 |
| 109 | 6 | 2 | 280,00 |
| 110 | 4 | 1 | 1200,00 |
| 110 | 8 | 1 | 250,00 |
| 111 | 5 | 1 | 420,00 |
| 111 | 7 | 1 | 3200,00 |
| 112 | 2 | 2 | 150,00 |
| 112 | 9 | 1 | 500,00 |

A partir deste ponto, **todos os exercícios devem utilizar exclusivamente essas quatro tabelas e seus respectivos atributos**.

---

## Parte II — Manipulação e consultas básicas (Exercícios 5–9)

**NÍVEL BÁSICO / INTERMEDIÁRIO — SELECT, WHERE, UPDATE E DELETE**

### Exercício 5 — Consultas com filtros

Utilizando `SELECT` e `WHERE`, escreva consultas que:

a) Retornem todos os clientes residentes em Minas Gerais.

b) Retornem os produtos cujo preço seja superior a R$ 1.000,00.

c) Retornem os produtos da categoria `Periféricos` que custem menos de R$ 300,00.

d) Retornem os pedidos realizados entre 01/09/2026 e 30/09/2026.

### Exercício 6 — Ordenação e operadores

Escreva consultas que:

a) Listem os produtos em ordem decrescente de preço.

b) Retornem os cinco produtos mais caros.

c) Retornem os clientes cujo nome comece com a letra `C` ou `G`.

d) Listem os produtos com estoque entre 10 e 30 unidades, ordenados alfabeticamente pelo nome.

### Exercício 7 — Atualização de registros

Utilizando `UPDATE`, realize as seguintes operações:

a) Aumente em 10% o preço de todos os produtos da categoria `Periféricos`.

b) Reduza em cinco unidades o estoque do produto de ID 5.

c) Altere o status do pedido 103 para `Entregue`.

d) Atualize a cidade do cliente 8 para `Belo Horizonte`.

**Atenção:** Utilize condições apropriadas para evitar alterações indevidas em outros registros.

### Exercício 8 — Exclusão de registros

Utilizando `DELETE`, realize:

a) Exclua o cliente que não possui nenhum pedido registrado.

b) Exclua os registros de pedidos cujo status seja `Cancelado`, excluindo previamente seus respectivos registros em `ITEM_PEDIDO`, de modo a respeitar as restrições de integridade referencial.

c) Confirme, por meio de consultas, se as exclusões foram realizadas corretamente.

### Exercício 9 — Alteração da estrutura de uma tabela

Utilizando `ALTER TABLE`:

a) Acrescente o atributo `telefone VARCHAR(20)` à tabela `CLIENTE`.

b) Defina o valor do telefone do cliente 1 como `'31999998888'`.

c) Altere o tamanho máximo do atributo `telefone` para 25 caracteres.

d) Remova o atributo `telefone` da tabela, retornando à estrutura original.

---

## Parte III — Consultas relacionais e agregações (Exercícios 10–15)

**NÍVEL INTERMEDIÁRIO — JOIN, GROUP BY, HAVING E FUNÇÕES DE AGREGAÇÃO**

### Exercício 10 — Consultas com INNER JOIN

Escreva consultas que:

a) Listem todos os pedidos exibindo `id_pedido`, `data_pedido`, `status` e `nome` do cliente.

b) Exibam os produtos presentes no pedido 101, incluindo nome, quantidade e preço unitário.

c) Retornem o nome de todos os clientes que realizaram pedidos com status `Pendente`, sem duplicações.

### Exercício 11 — Junções entre múltiplas tabelas

Utilizando `JOIN`, produza uma consulta que retorne:

- Identificador do pedido;
- Nome do cliente;
- Data do pedido;
- Nome do produto;
- Quantidade comprada;
- Preço unitário registrado na compra;
- Valor total de cada item (`quantidade × preco_unitario`).

Ordene o resultado por identificador do pedido e nome do produto.

### Exercício 12 — Funções de agregação

Utilizando `COUNT`, `SUM`, `AVG`, `MIN` e `MAX`, quando apropriado:

a) Determine a quantidade total de produtos cadastrados.

b) Calcule o preço médio dos produtos da categoria `Componentes`.

c) Determine o maior e o menor preço de produto cadastrado.

d) Calcule a quantidade total de unidades de produtos disponíveis em estoque.

e) Calcule o valor monetário total do estoque, considerando o preço atual de cada produto.

### Exercício 13 — Agrupamentos com GROUP BY

Escreva consultas que:

a) Contem quantos clientes existem em cada estado.

b) Retornem a quantidade de produtos cadastrados por categoria.

c) Calculem o preço médio dos produtos por categoria.

d) Contem quantos pedidos foram realizados por cada cliente que possui pelo menos um pedido.

### Exercício 14 — Filtragem de grupos com HAVING

Utilizando `GROUP BY` e `HAVING`:

a) Retorne apenas as categorias que possuam mais de dois produtos.

b) Liste os clientes que realizaram mais de um pedido.

c) Retorne os produtos cuja quantidade total vendida nos pedidos seja superior a três unidades.

d) Retorne as categorias cujo preço médio atual dos produtos seja superior a R$ 1.000,00.

### Exercício 15 — LEFT JOIN e ausência de relacionamentos

Escreva consultas que:

a) Retornem todos os clientes e a quantidade de pedidos realizados por cada um, incluindo clientes sem pedidos.

b) Identifiquem os produtos que nunca apareceram em nenhum pedido.

c) Retornem todos os clientes com ou sem pedidos, exibindo seu nome, identificador do pedido e status, quando existirem.

---

## Parte IV — Consultas avançadas e aplicabilidade prática (Exercícios 16–20)

**NÍVEL INTERMEDIÁRIO / AVANÇADO — SUBQUERIES, EXISTS, AGREGAÇÕES E RELATÓRIOS**

### Exercício 16 — Subconsultas

Utilizando subconsultas SQL:

a) Retorne os produtos cujo preço atual seja superior ao preço médio de todos os produtos.

b) Retorne os clientes que realizaram pelo menos um pedido com valor total superior a R$ 3.000,00.

c) Liste os produtos cujo preço seja superior à média de preços de sua própria categoria.

### Exercício 17 — Consultas com EXISTS e NOT EXISTS

Utilizando `EXISTS` e `NOT EXISTS`:

a) Identifique os clientes que possuem pelo menos um pedido `Entregue`.

b) Identifique os clientes que nunca realizaram pedidos com status `Pendente`.

c) Retorne os produtos que nunca foram incluídos em pedidos com status `Entregue`.

### Exercício 18 — Relatório financeiro de pedidos

Desenvolva uma consulta SQL que produza um relatório contendo:

- Identificador do pedido;
- Nome do cliente;
- Data da compra;
- Status do pedido;
- Quantidade total de unidades compradas;
- Valor total do pedido.

**Regras:**

a) Utilize os preços registrados em `ITEM_PEDIDO`, e não os preços atuais da tabela `PRODUTO`.

b) Inclua todos os pedidos existentes, independentemente do status.

c) Ordene pelo maior valor total de pedido.

### Exercício 19 — Ranking de clientes por faturamento

Desenvolva uma consulta que identifique os clientes que mais contribuíram para o faturamento da loja.

O relatório deve conter:

- Nome do cliente;
- Quantidade de pedidos entregues;
- Valor total gasto em pedidos entregues;
- Valor médio por pedido entregue.

**Restrições:**

a) Considere exclusivamente pedidos com status `Entregue`.

b) Clientes sem pedidos entregues não devem aparecer.

c) Ordene os clientes do maior para o menor valor total gasto.

d) Em caso de empate, ordene alfabeticamente pelo nome.

### Exercício 20 — Relatório gerencial completo

Uma loja de informática deseja identificar quais categorias de produtos apresentam melhor desempenho comercial.

Desenvolva **uma única consulta SQL** que apresente, para cada categoria que possua produtos vendidos em pedidos entregues:

- Nome da categoria;
- Quantidade de produtos distintos vendidos;
- Quantidade total de unidades vendidas;
- Faturamento total da categoria;
- Preço médio unitário efetivamente praticado nas vendas, ponderado pelas quantidades;
- Percentual de participação da categoria no faturamento total da loja.

**Regras:**

a) Considere apenas pedidos com status `Entregue`.

b) Utilize os valores de `preco_unitario` registrados em `ITEM_PEDIDO`.

c) O percentual deve considerar como denominador o faturamento de todos os pedidos entregues.

d) Ordene as categorias pelo maior faturamento.

e) Utilize apenas as quatro tabelas originalmente criadas, sem criar tabelas auxiliares.

---

## Resumo dos conteúdos praticados

| Conteúdo SQL | Exercícios |
|---|---|
| `CREATE TABLE`, `PRIMARY KEY`, `FOREIGN KEY`, `CHECK` | 1 |
| `INSERT INTO` | 2–4 |
| `SELECT`, `WHERE`, `LIKE`, `BETWEEN` | 5–6 |
| `ORDER BY`, `LIMIT` | 6 |
| `UPDATE`, `DELETE` | 7–8 |
| `ALTER TABLE` | 9 |
| `INNER JOIN` | 10–11 |
| `COUNT`, `SUM`, `AVG`, `MIN`, `MAX` | 12 |
| `GROUP BY` | 13 |
| `HAVING` | 14 |
| `LEFT JOIN` | 15 |
| Subconsultas | 16 |
| `EXISTS`, `NOT EXISTS` | 17 |
| Relatórios e agregações complexas | 18–20 |

**Observações finais:** Os exercícios foram organizados para serem realizados sequencialmente, utilizando a mesma base de dados. As alterações efetuadas nos exercícios 7 e 8 devem ser mantidas nos exercícios posteriores. O exercício 9 restaura a estrutura original da tabela `CLIENTE`.
