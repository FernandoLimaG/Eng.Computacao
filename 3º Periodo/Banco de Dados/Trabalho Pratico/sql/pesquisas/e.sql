SELECT e.id_evento, e.descricao_evento, COUNT(esc.id_profissional) AS qtd_profissionais
	FROM evento e
	JOIN escala esc ON e.id_evento = esc.id_evento
	GROUP BY e.id_evento, e.descricao_evento
	ORDER BY qtd_profissionais DESC;
