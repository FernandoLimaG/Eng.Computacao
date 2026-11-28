SELECT c.nome_cardapio, SUM(a.preco_pessoa) AS custo_por_pessoa
	FROM cardapio c
	INNER JOIN composicao co ON c.id_cardapio = co.id_cardapio
	INNER JOIN aperitivo a ON co.id_aperitivo = a.id_aperitivo
	GROUP BY c.id_cardapio, c.nome_cardapio
	HAVING SUM(a.preco_pessoa) < 30;
