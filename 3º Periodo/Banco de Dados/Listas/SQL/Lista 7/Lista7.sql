/* ===== Lista 7 ===== */
/* Q1 */
SELECT p.nome,v.modelo
	FROM veiculo v, proprietario p
	WHERE v.cod_proprietario = p.cod_proprietario
	AND p.estado = 'SP'
	ORDER BY p.nome,v.modelo;

/* Q2 */
SELECT p.nome,p.estado,v.placa,v.modelo,v.valor_ipva
	FROM veiculo v, proprietario p
	WHERE v.cod_proprietario = p.cod_proprietario
	AND v.valor_ipva >= 200 AND v.valor_ipva <= 450
	AND (p.estado = 'MG' OR p.estado = 'SP' OR p.estado = 'PR')
	ORDER BY p.estado,v.placa;

/* Q3 */
SELECT p.nome,v.placa
	FROM proprietario p RIGHT OUTER JOIN veiculo v
	ON v.cod_proprietario = p.cod_proprietario
	ORDER BY p.nome,v.placa;

/* Q4 */
SELECT p.nome,v.placa
	FROM proprietario p LEFT OUTER JOIN veiculo v
	ON v.cod_proprietario = p.cod_proprietario
	ORDER BY p.nome,v.placa;

/* Q5 */
SELECT p.cidade,SUM(v.valor_ipva) AS Somatorio
	FROM proprietario p, veiculo v
	WHERE v.cod_proprietario = p.cod_proprietario
	GROUP BY p.cidade
	ORDER BY somatorio DESC;

/* Q6 */
SELECT  p.estado, COUNT(v.placa) AS qtd_veiculos
	FROM proprietario p, veiculo v
	WHERE V.cod_proprietario = p.cod_proprietario
	GROUP BY p.estado
	HAVING COUNT(v.placa) > 3
	ORDER BY p.estado;

/* Q7 */
SELECT p.nome, COUNT(v.placa) AS qtd_veiculos
	FROM proprietario p, veiculo v
	WHERE v.cod_proprietario = p.cod_proprietario
	GROUP BY p.cod_proprietario, p.nome
	HAVING COUNT(v.placa) > 1
	ORDER BY qtd_veiculos DESC;

/* Q8 */
SELECT p.estado, COUNT(DISTINCT v.modelo) AS qtd_modelos
	FROM proprietario p, veiculo v
	WHERE v.cod_proprietario = p.cod_proprietario
	GROUP BY p.estado
	ORDER BY p.estado;

/* Q9 */
SELECT v.modelo, SUM(v.valor_ipva) AS somatorio
	FROM veiculo v
	WHERE v.modelo LIKE 'FORD%'
	GROUP BY v.modelo
	ORDER BY somatorio ASC;

/* Q10 */
SELECT AVG(v.valor_ipva) AS media_ipva
	FROM proprietario p, veiculo v
	WHERE v.cod_proprietario = p.cod_proprietario
	AND p.estado = 'MG';
