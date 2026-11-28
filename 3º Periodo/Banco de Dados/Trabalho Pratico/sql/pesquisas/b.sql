SELECT p.nome, e.descricao_evento, escala.quantidade_horas
    FROM profissional p
    INNER JOIN escala ON p.matricula = escala.id_profissional
    INNER JOIN evento e ON escala.id_evento = e.id_evento
    WHERE escala.quantidade_horas < 4
    ORDER BY escala.quantidade_horas;
