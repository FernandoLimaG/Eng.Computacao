/* === Banco de Dados: Lista 8 ==== */
-- Q1:A)
SELECT v.placa, v.modelo
	FROM veiculo v
	JOIN proprietario p ON v.cod_proprietario = p.cod_proprietario
	WHERE p.estado IN ('RJ','MG');

-- Q1:B)
SELECT v.placa, v.modelo
	FROM veiculo v
	WHERE v.cod_proprietario IN
	(SELECT p.cod_proprietario
		FROM proprietario p
		WHERE p.estado IN ('RJ','MG')
	);

-- Q2:A)
SELECT DISTINCT p.nome, p.cidade
	FROM proprietario p
	JOIN veiculo v ON p.cod_proprietario = v.cod_proprietario
	WHERE v.modelo LIKE ('GM%');

-- Q2:B)
SELECT DISTINCT p.nome, p.cidade 
	FROM proprietario p 
	WHERE p.cod_proprietario IN
	(SELECT v.cod_proprietario
		FROM veiculo v
		WHERE v.modelo LIKE ('GM%')
	);

-- Q3:A)
SELECT DISTINCT p.nome
	FROM proprietario p
	JOIN veiculo v ON p.cod_proprietario = v.cod_proprietario
	WHERE v.ano_fabricacao > '1988';

-- Q3:B)
SELECT DISTINCT p.nome
	FROM proprietario p
	WHERE p.cod_proprietario IN
	(SELECT v.cod_proprietario
	 	FROM veiculo v
	 	WHERE v.ano_fabricacao > '1988'
	);

-- Q4:
SELECT v.placa , v.modelo
	FROM veiculo v 
	JOIN proprietario p ON v.cod_proprietario = p.cod_proprietario
	WHERE p.estado = 'MG'
	AND v.valor_ipva > 
	(SELECT AVG(v2.valor_ipva)
		FROM veiculo v2
		JOIN proprietario p2 ON v2.cod_proprietario = p2.cod_proprietario
		WHERE p2.estado = 'SP'
	);

-- Q5:
SELECT v.ano_fabricacao , v.valor_ipva , v.placa
	FROM veiculo v
	WHERE v.valor_ipva =
	(SELECT MIN(v2.valor_ipva)
		FROM veiculo v2
		WHERE v.ano_fabricacao = v2.ano_fabricacao
	)
	ORDER BY ano_fabricacao;

-- Q6:
SELECT v.cod_proprietario , v.modelo , v.valor_ipva
	FROM veiculo v
	WHERE v.valor_ipva IN
	(SELECT MAX(v2.valor_ipva)
		FROM veiculo v2
		WHERE v.cod_proprietario = v2.cod_proprietario
	);

-- Q7:
SELECT v.placa , v.modelo
	FROM veiculo v
	JOIN proprietario p ON v.cod_proprietario = p.cod_proprietario
	WHERE v.valor_ipva > 
	(SELECT AVG(v2.valor_ipva)
		FROM veiculo v2
		JOIN proprietario p2 ON v2.cod_proprietario = p2.cod_proprietario
		WHERE p2.cidade = p.cidade
	);

-- Q8:
SELECT p.estado , v.placa , v.modelo , v.ano_fabricacao
	FROM veiculo v
	JOIN proprietario p  ON p.cod_proprietario = v.cod_proprietario
	WHERE v.ano_fabricacao 	IN
	(SELECT MAX(v2.ano_fabricacao)
		FROM veiculo v2
		JOIN proprietario p2 ON v2.cod_proprietario = p2.cod_proprietario
		WHERE p.estado = p2.estado
	);

-- Q9:
SELECT p.estado , COUNT(DISTINCT v.modelo) AS qtd_modelos 
	FROM proprietario p
	JOIN veiculo v ON p.cod_proprietario = v.cod_proprietario
	WHERE p.estado IN
	(SELECT p2.estado
		FROM proprietario p2
		JOIN veiculo v2 ON p2.cod_proprietario = v2.cod_proprietario
		GROUP BY p2.estado 
		HAVING COUNT(*) > 2
	)
	GROUP BY p.estado
	ORDER BY 1;

-- Q10:
SELECT p.nome , SUM(v.valor_ipva) AS sum_ipva
	FROM proprietario p
	JOIN veiculo v ON p.cod_proprietario = v.cod_proprietario
	WHERE p.cod_proprietario IN
	(SELECT p2.cod_proprietario
		FROM proprietario p2
		JOIN veiculo v2 ON v2.cod_proprietario = p2.cod_proprietario
		GROUP BY p2.cod_proprietario
		HAVING COUNT(*) > 1
	)
	GROUP BY p.cod_proprietario , p.nome 
	ORDER BY sum_ipva DESC;
