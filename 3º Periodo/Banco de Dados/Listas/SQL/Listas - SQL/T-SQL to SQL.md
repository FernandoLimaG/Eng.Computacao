# T-SQL para SQL

>SQL Server(T-SQL) vs. PostgreSQL - Referência de Sintaxe


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

## DML - Manipulação de Dados
|Operação|SQL Server|PostgreSQL|
|---|---|---|
|Inserir | INSERT INTO tabela (col1, col2) VALUES (1, 'A'); | Igual|
|Atualizar | UPDATE tabela SET col1 = 2 WHERE id = 1; | Igual|
|Excluir registros | DELETE FROM tabela WHERE id = 1; | Igual|
|Excluir todos os registros | DELETE FROM tabela; | Igual|
|Esvaziar tabela | TRUNCATE TABLE tabela; | Igual|
|Inserir a partir de consulta | INSERT INTO destino SELECT * FROM origem; | Igual|
|Retornar dados inseridos | OUTPUT inserted.id | RETURNING id|