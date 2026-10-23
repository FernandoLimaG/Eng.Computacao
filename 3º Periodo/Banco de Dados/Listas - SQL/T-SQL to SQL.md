# T-SQL para SQL

SQL Server(T-SQL) vs. PostgreSQL - Referência de Sintaxe

## Autoria

- Fernando de Lima Gonçalves
- Engenharia de Computação
- Pontifícia Universidade Católica de Minas Gerais - Campi Lourdes

---

## DQL - Consultas

|Operação|SQL Server|PostgreSQL|
|---|---|---|
|Consultar Tabela|SELECT * FROM tabela;|Igual|
|Consultar colunas|	SELECT col1, col2 FROM tabela;|Igual|
|Filtrar|SELECT * FROM tabela WHERE id = 1;|Igual|
|Ordenar crescente|ORDER BY coluna ASC|Igual|
|Ordenar decrescente|ORDER BY coluna DESC|Igual|
|Limitar resultados	| SELECT TOP 10 * FROM tabela;	| SELECT * FROM tabela LIMIT 10;|
|Eliminar duplicatas | SELECT DISTINCT coluna FROM tabela; | Igual|
|Agrupar | GROUP BY coluna | Igual|
|Filtrar grupos |HAVING COUNT(*) > 2 |Igual|
|Contar	| COUNT(*) | Igual|
|Somar	| SUM(coluna) |Igual|
|Média	| AVG(coluna) |Igual|
|Mínimo / Máximo | MIN(coluna) / MAX(coluna) | Igual|
