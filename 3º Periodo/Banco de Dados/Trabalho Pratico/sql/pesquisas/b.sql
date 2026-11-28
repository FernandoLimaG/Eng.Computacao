SELECT p.nome, e.descricao_evento, e.data_evento, e.local_evento, escala.quantidade_horas
    FROM profissional p
    INNER JOIN escala escala ON p.id_profissional = escala.id_profissional
    WHERE escala.quantidade_horas < 4
    INNER JOIN evento e ON escala.id_evento = e.id_evento;
