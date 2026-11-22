-- Q1:
CREATE TABLE cliente (
	id_cliente INT PRIMARY KEY,
	nome varchar(100) NOT NULL,
	cidade varchar(60) NOT NULL,
	estado varchar(2) NOT NULL,
	email varchar(100) UNIQUE
);

CREATE TABLE produto (
	id_produto INT PRIMARY KEY,
	nome varchar(100) NOT NULL,
	categoria varchar(50) NOT NULL,
	preco decimal(10,2) NOT NULL CHECK (preco >= 0),
	estoque INT NOT NULL CHECK (estoque >= 0)
);

CREATE TABLE pedido (
	id_pedido INT PRIMARY KEY,
	id_cliente INT,
	data_pedido date NOT NULL,
	status varchar(20) NOT NULL,
	
	-- Restrições e Link chave secundária
	CONSTRAINT fk_id_cliente
	FOREIGN KEY (id_cliente)
	REFERENCES cliente(id_cliente)
);

CREATE TABLE item_pedido (
	id_pedido INT,
	id_produto INT,
	quantidade INT CHECK (quantidade > 0),
	preco_unitario DECIMAL(10,2) NOT NULL CHECK (preco_unitario >= 0),
	
	-- Definições de PK e Fk
	PRIMARY KEY(id_pedido, id_produto),
	
	CONSTRAINT fk_id_produto
		FOREIGN KEY (id_produto)
		REFERENCES produto(id_produto),
		
	CONSTRAINT fk_id_pedido
		FOREIGN KEY (id_pedido)
		REFERENCES pedido(id_pedido)
);

-- Q2:
INSERT INTO cliente (id_cliente,nome,cidade,estado,email)
VALUES
	(1,'Ana Souza','Belo Horizonte','MG','ana@email.com'),
	(2,'Bruno Lima','São Paulo','SP','bruno@email.com'),
	(3,'Carla Mendes','Contagem','MG','carla@email.com'),
	(4,'Daniel Rocha','Rio de Janeiro','RJ','daniel@email.com'),
	(5,'Elisa Ferreira','Belo Horizonte','MG','elisa@email.com'),
	(6,'Felipe Costa','Curitiba','PR','felipe@email.com'),
	(7,'Gabriela Alves','Campinas','SP','gabi@email.com'),
	(8,'Henrique Dias','Betim','MG','henrique@email.com');


INSERT INTO produto (id_produto,nome,categoria,preco,estoque)
VALUES
	(1,'Notebook Dell','Computadores',4500.00,10),
	(2,'Mouse Logitech','Periféricos',150.00,50),
	(3,'Teclado Mecânico','Periféricos',350.00,25),
	(4,'Monitor LG 27','Monitores',1200.00,15),
	(5,'SSD Kingston 1TB','Armazenamento',420.00,30),
	(6,'Memória RAM 16GB','Componentes',280.00,40),
	(7,'Placa de Vídeo RTX','Componentes',3200.00,8),
	(8,'Webcam Full HD','Periféricos',250.00,20),
	(9,'HD Externo 2TB','Armazenamento',500.00,12),
	(10,'Processador Ryzen 7','Componentes',1800.00,6);

-- Q3:
INSERT INTO pedido (id_pedido,id_cliente,data_pedido,status)
VALUES
	(101,1,'2026-08-05','Entregue'),
	(102,2,'2026-08-07','Entregue'),
	(103,1,'2026-08-10','Pendente'),
	(104,3,'2026-08-12','Entregue'),
	(105,4,'2026-09-01','Cancelado'),
	(106,5,'2026-09-03','Entregue'),
	(107,2,'2026-09-10','Pendente'),
	(108,6,'2026-09-15','Entregue'),
	(109,3,'2026-10-02','Pendente'),
	(110,7,'2026-10-05','Entregue'),
	(111,1,'2026-10-12','Entregue'),
	(112,5,'2026-10-18','Pendente');

-- Q4:
INSERT INTO item_pedido (id_pedido,id_produto,quantidade,preco_unitario)
VALUES
	(101,1,1,4500.00),
	(101,2,2,150.00),
	(102,4,2,1200.00),
	(102,3,1,350.00),
	(103,7,1,3200.00),
	(104,5,2,420.00),
	(104,6,4,280.00),
	(105,10,1,1800.00),
	(106,2,3,150.00),
	(106,8,2,250.00),
	(107,1,1,4500.00),
	(108,9,2,500.00),
	(109,3,2,350.00),
	(109,6,2,280.00),
	(110,4,1,1200.00),
	(110,8,1,250.00),
	(111,5,1,420.00),
	(111,7,1,3200.00),
	(112,2,2,150.00),
	(112,9,1,500.00);

/* PARTE II: Manipulação e Consulta */
-- Q.5:
-- A:
SELECT *
	FROM cliente
	WHERE estado = 'MG';

-- B:
SELECT *
	FROM produto
	WHERE preco > 1000;

-- C:
SELECT *
	FROM produto
	WHERE categoria = 'Periféricos'
	AND preco < 300;

-- D:
SELECT *
	FROM pedido
	WHERE data_pedido BETWEEN '2026-09-01' AND '2026-09-30';

-- Q.6:
-- A:
SELECT *
	FROM produto
	ORDER BY preco DESC;

-- B:
SELECT *
	FROM produto
	ORDER BY preco DESC
	LIMIT 5;

-- C:
SELECT *
	FROM cliente
	WHERE nome LIKE 'C%'
	OR nome LIKE 'G%';

-- Versão com REGEX
SELECT *
	FROM cliente
	WHERE nome ~'^[CG]';

-- D:
SELECT *
	FROM produto
	WHERE estoque BETWEEN 10 AND 30
	ORDER BY nome;

-- Q.7:
-- A:
UPDATE produto
	SET preco = preco * 1.10
	WHERE categoria = 'Periféricos';

-- B:
UPDATE produto
	SET estoque = estoque - 5
	WHERE id_produto = 5;

-- C:
UPDATE pedido
	SET status = 'Entregue'
	WHERE id_pedido = 103;

-- D:
UPDATE cliente
	SET cidade = 'Belo Horizonte'
	WHERE id_cliente = 8;

-- Q.8:
-- A:
-- Consulta prévia para garantir segurança do delete
SELECT * 
	FROM cliente c
	WHERE NOT EXISTS (
		SELECT 1
		FROM pedido p
		WHERE p.id_cliente = c.id_cliente
	);

-- Delete
DELETE 
	FROM cliente c
	WHERE NOT EXISTS (
		SELECT 1
		FROM pedido p
		WHERE p.id_cliente = c.id_cliente
	);

-- B:
-- Substituir CONSTRAINT para CASCADE: -On Delete | -On Update
ALTER TABLE item_pedido
	DROP CONSTRAINT fk_id_pedido;

ALTER TABLE item_pedido
	ADD CONSTRAINT fk_id_pedido
	FOREIGN KEY (id_pedido)
	REFERENCES pedido(id_pedido)
	ON DELETE CASCADE
	ON UPDATE CASCADE;

DELETE FROM pedido
	WHERE status = 'Cancelado';

-- C:
SELECT *
	FROM pedido
	WHERE status = 'Cancelado';

-- Q9:
-- A:
ALTER TABLE cliente
	ADD telefone VARCHAR(20);

-- B:
UPDATE cliente
	SET telefone = '(31)999998888'
	WHERE id_cliente = 1;

-- C:
ALTER TABLE cliente
	ALTER COLUMN telefone
	TYPE VARCHAR(25);

-- D:
ALTER TABLE cliente
	DROP COLUMN telefone;

-- Q10:
-- A:
SELECT c.id_cliente , c.nome , p.data_pedido , p.status 
	FROM cliente c , pedido p
	WHERE c.id_cliente = p.id_cliente
	ORDER BY 2,1;

-- B:
SELECT p.nome , ip.quantidade , ip.preco_unitario
	FROM item_pedido ip , produto p
	WHERE ip.id_produto = p.id_produto 
	AND ip.id_pedido = 101;

-- C:
SELECT DISTINCT c.nome
	FROM cliente c , pedido p
	WHERE c.id_cliente = p.id_cliente 
	AND p.status = 'Pendente';
	
-- Q11:
SELECT p.id_pedido, 
	c.nome,
	p.data_pedido,
	prod.nome,
	ip.quantidade,
	ip.preco_unitario,
	(ip.preco_unitario * ip.quantidade ) AS valor_total
	FROM pedido p , cliente c , produto prod , item_pedido ip 
	WHERE p.id_cliente = c.id_cliente 
	AND c.id_cliente = p.id_cliente
	AND ip.id_pedido = p.id_pedido 
	AND ip.id_produto = prod.id_produto
	ORDER BY p.id_pedido , prod.nome;

-- Q12:
-- A:
SELECT COUNT(*) AS produtos_cadastrados
	FROM produto;

-- B:
SELECT AVG(preco) AS preco_medio_componentes
	FROM produto
	WHERE categoria = 'Componentes';

-- C:
SELECT MAX(preco) AS maior_valor , MIN(preco) AS menor_valor
	FROM produto;

-- D:
SELECT SUM(estoque) AS qtd_produtos
	FROM produto;

-- E:
SELECT SUM((estoque * preco)) AS valor_estoque
	FROM produto;

-- Q13:
-- A:
SELECT estado, COUNT(*) AS qtd_clientes
	FROM cliente
	GROUP BY estado;

-- B:
SELECT categoria, COUNT(*) AS qtd_produto
	FROM produto
	GROUP BY categoria;

-- C:
-- Notação: JOIN
SELECT p.nome , SUM(ip.quantidade) AS qtd_vendida
	FROM produto p
	JOIN item_pedido ip ON p.id_produto = ip.id_produto
	GROUP BY p.nome 
	HAVING SUM(ip.quantidade) > 3;

-- Notação: WHERE
SELECT p.nome , SUM(ip.quantidade) AS qtd_vendida
	FROM produto p , item_pedido ip
	WHERE p.id_produto = ip.id_produto
	GROUP BY p.nome 
	HAVING SUM(ip.quantidade) > 3;

-- D:
SELECT categoria , AVG(preco) AS preco_medio
	FROM produto
	GROUP BY categoria
	HAVING AVG(preco) > 1000;

-- Q15:
-- A:
SELECT c.nome , COUNT(p) AS qtd_pedidos
	FROM cliente c
	LEFT JOIN pedido p ON c.id_cliente = p.id_cliente
	GROUP BY c.nome
	ORDER BY 1;

-- B:
SELECT p.nome AS produto_sem_pedido
	FROM produto p
	LEFT JOIN item_pedido ip ON p.id_produto = ip.id_produto
	WHERE ip.id_produto IS NULL
	GROUP BY p.nome
	ORDER BY 1;

-- C:
SELECT c.nome AS Clientes , p.id_pedido , p.status
	FROM cliente c
	LEFT JOIN pedido p ON p.id_cliente = c.id_cliente
	ORDER BY 1;

-- Q16:
-- A:
SELECT *
	FROM produto
	WHERE preco > (
		SELECT AVG(preco) 
			FROM produto
);

-- B:
SELECT c.id_cliente , c.nome AS cliente
	FROM cliente c
	JOIN pedido p ON p.id_cliente = c.id_cliente
	JOIN item_pedido ip ON ip.id_pedido = p.id_pedido
	GROUP BY c.id_cliente , c.nome , p.id_pedido
	HAVING SUM(ip.quantidade * ip.preco_unitario) > 3000;

-- C:
SELECT p
	FROM produto p
	WHERE p.preco > (
		SELECT AVG(p2.preco)
			FROM produto p2
			WHERE p2.categoria = p.categoria
	);

-- Q17:
-- A:
SELECT *
	FROM cliente c
	WHERE EXISTS (
		SELECT 1
		FROM pedido p
		WHERE p.id_cliente = c.id_cliente
		AND p.status = 'Entregue'
	);

-- B:
SELECT *
	FROM cliente
	WHERE NOT EXISTS (
		SELECT 1
			FROM pedido
			WHERE cliente.id_cliente = pedido.id_cliente
			AND status = 'Pendente'
	);

-- C:
SELECT prod.id_produto , prod.nome
	FROM produto prod
	WHERE NOT EXISTS (
		SELECT 1
			FROM pedido ped
			JOIN item_pedido ip ON ip.id_pedido = ped.id_pedido
			WHERE prod.id_produto = ip.id_produto
			AND ped.status = 'Entregue'
	);

-- Q18:
SELECT p.id_pedido , 
	   c.nome ,
	   p.data_pedido , 
	   p.status , 
	   SUM(ip.quantidade) AS qtd_total ,
	   SUM(ip.quantidade * ip.preco_unitario) AS "valor_total(R$)"
	FROM pedido p
	LEFT JOIN cliente c ON c.id_cliente = p.id_cliente
	LEFT JOIN item_pedido ip ON p.id_pedido = ip.id_pedido
	GROUP BY p.id_pedido , c.nome , p.data_pedido , ip.quantidade
	ORDER BY "valor_total(R$)" DESC;

-- Q19:
SELECT c.nome , 
COUNT(DISTINCT p.id_pedido) AS quantidade, 
SUM(ip.preco_unitario * ip.quantidade) AS "valor_total(R$)" ,
AVG(sub.valor_pedido) AS "valor_médio(R$)"
	FROM cliente c
	JOIN pedido p ON c.id_cliente = p.id_cliente
	JOIN item_pedido ip ON p.id_pedido = ip.id_pedido
	JOIN
	(
		SELECT id_pedido , SUM(preco_unitario * quantidade) AS valor_pedido
			FROM item_pedido
			GROUP BY id_pedido
	) sub ON p.id_pedido = sub.id_pedido
	WHERE p.status = 'Entregue'
	GROUP BY c.id_cliente , c.nome
	ORDER BY "valor_total(R$)" DESC , c.nome ASC;

-- Q20:
SELECT prod.categoria ,
COUNT(DISTINCT prod.id_produto) AS qtd_distintos_vendidos ,
SUM(ip.quantidade) AS qtd_total_vendidos ,
SUM(ip.quantidade * ip.preco_unitario) AS "faturamento_total(R$)" ,
SUM(ip.quantidade * ip.preco_unitario) / SUM(ip.quantidade) AS "preço_médio_unitário(R$)" ,
(SUM(ip.preco_unitario * ip.quantidade) * 100.0) / (
        SELECT SUM(ip_all.preco_unitario * ip_all.quantidade)
        FROM pedido p_all
        JOIN item_pedido ip_all ON p_all.id_pedido = ip_all.id_pedido
        WHERE p_all.status = 'Entregue'
    ) AS percentual_participacao
	FROM produto prod
	JOIN item_pedido ip ON prod.id_produto = ip.id_produto
	JOIN pedido ped ON ip.id_pedido = ped.id_pedido
	WHERE ped.status = 'Entregue'
	GROUP BY prod.categoria
	ORDER BY "faturamento_total(R$)" DESC;
