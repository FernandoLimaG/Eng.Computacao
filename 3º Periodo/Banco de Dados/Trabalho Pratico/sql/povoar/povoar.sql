/* ===== TP: Povoar Tabelas =====
 * Delimiter: Cerimonial com registro único
 * Fonte: LLM GPT
 */
 INSERT INTO cerimonial
(cnpj, endereco_cerimonial, nome) VALUES
('48372561000192', 'Avenida do Contorno, 4280 - Belo Horizonte/MG', 'Essência Cerimonial');

INSERT INTO tipo_profissional
(id_tipo, descrição_tipo) VALUES
('TP01', 'Garçom'),
('TP02', 'Segurança'),
('TP03', 'Recepcionista'),
('TP04', 'Fotógrafo'),
('TP05', 'Decorador'),
('TP06', 'Faxineiro');

INSERT INTO profissional
(matricula, nome, salario, id_tipo) VALUES
('P01', 'Lucas Henrique Almeida', 2350.00, 'TP01'),
('P02', 'Mariana Cristina Souza', 2850.00, 'TP02'),
('P03', 'Pedro Augusto Santos', 2180.00, 'TP01'),
('P04', 'Ana Carolina Oliveira', 1950.00, 'TP03'),
('P05', 'Gabriel Ferreira Costa', 3800.00, 'TP04'),
('P06', 'Juliana Ribeiro Martins', 3150.00, 'TP05'),
('P07', 'Rafael Moreira Gomes', 1750.00, 'TP06');

INSERT INTO telefones
(matricula_profissional, telefone) VALUES
('P01', '31984726351'),
('P01', '31971643825'),
('P02', '31992517468'),
('P02', '31983629514'),
('P03', '31978451632'),
('P03', '31996382741'),
('P04', '31987524163'),
('P04', '31963472815'),
('P04', '31995281347'),
('P05', '31991837462'),
('P05', '31976549281'),
('P06', '31984613752'),
('P06', '31992746183'),
('P07', '31968351294'),
('P07', '31975492836');

INSERT INTO formatura
(id_formatura, curso, faculdade, nome_chefe, telefone_chefe, forma_pgto, valor, cnpj_cerimonial) VALUES
('F01', 'Engenharia de Computação', 'PUC Minas', 'Arthur Henrique Lima', '31982643571', 'PIX', 48500.00, '48372561000192'),
('F02', 'Direito', 'UFMG', 'Laura Beatriz Mendes', '31994718362', 'Boleto', 62750.00, '48372561000192'),
('F03', 'Medicina', 'Faculdade Ciências Médicas de Minas Gerais', 'Gustavo Henrique Lopes', '31973826415', 'Cartão de Crédito', 98500.00, '48372561000192'),
('F04', 'Administração', 'UNA', 'Isabela Martins Rocha', '31986274519', 'PIX', 35200.00, '48372561000192'),
('F05', 'Engenharia Civil', 'CEFET-MG', 'Felipe Augusto Dias', '31998462731', 'Boleto', 53750.00, '48372561000192');

INSERT INTO evento
(id_evento, descricao_evento, num_convidados, hora_inicio, hora_fim, data_evento, local_evento, id_formatura) VALUES
('E01', 'Colação de Grau', 180, '14:00:00', '17:00:00', '2026-12-04', 'Auditório Universitário - Belo Horizonte/MG', 'F01'),
('E02', 'Baile de Formatura', 280, '18:00:00', '23:00:00', '2026-12-12', 'Espaço Magnólia - Nova Lima/MG', 'F01'),
('E03', 'Colação de Grau', 220, '09:00:00', '12:00:00', '2026-12-08', 'Centro de Convenções - Contagem/MG', 'F02'),
('E04', 'Jantar de Formatura', 90, '19:00:00', '23:00:00', '2026-12-10', 'Salão Villa Verde - Belo Horizonte/MG', 'F03'),
('E05', 'Baile de Formatura', 320, '17:00:00', '23:00:00', '2026-12-18', 'Espaço Jardim Imperial - Nova Lima/MG', 'F03'),
('E06', 'Culto Ecumênico', 75, '15:00:00', '17:00:00', '2026-12-15', 'Auditório Central - Betim/MG', 'F04'),
('E07', 'Jantar de Formatura', 110, '18:00:00', '22:00:00', '2026-12-19', 'Salão Monte Azul - Contagem/MG', 'F05');

INSERT INTO escala
(id_profissional, id_evento, quantidade_horas) VALUES
('P04', 'E01', 3),
('P05', 'E01', 2),
('P01', 'E02', 5),
('P02', 'E02', 5),
('P03', 'E02', 4),
('P04', 'E03', 3),
('P05', 'E03', 2),
('P01', 'E04', 4),
('P06', 'E04', 2),
('P03', 'E05', 6),
('P02', 'E05', 6),
('P04', 'E06', 2),
('P07', 'E06', 1),
('P01', 'E07', 4),
('P07', 'E07', 3);

INSERT INTO aperitivo
(id_aperitivo, nome_aperitivo, descricao_aperitivo, tipo_aperitivo, preco_pessoa) VALUES
('A01', 'Coxinha de Frango', 'Massa crocante recheada com frango e requeijão', 'Salgado', 5.50),
('A02', 'Pão de Queijo', 'Pão de queijo mineiro artesanal', 'Salgado', 4.50),
('A03', 'Brigadeiro Gourmet', 'Brigadeiro preparado com chocolate meio amargo', 'Doce', 6.00),
('A04', 'Beijinho de Coco', 'Doce de leite condensado com coco ralado', 'Doce', 4.00),
('A05', 'Suco de Laranja', 'Suco natural de laranja', 'Bebida', 8.50),
('A06', 'Refrigerante', 'Refrigerante servido em copo de 300 ml', 'Bebida', 6.50),
('A07', 'Coquetel Tropical', 'Coquetel de abacaxi e maracujá sem álcool', 'Coquetel', 16.90),
('A08', 'Mini Sanduíche', 'Sanduíche de queijo, peito de peru e alface', 'Salgado', 9.50),
('A09', 'Mousse de Chocolate', 'Mousse individual de chocolate belga', 'Doce', 12.00),
('A10', 'Coquetel de Morango', 'Coquetel de morango com hortelã sem álcool', 'Coquetel', 18.50),
('A11', 'Canapé de Salmão', 'Torrada artesanal com salmão defumado', 'Salgado', 22.00),
('A12', 'Água Mineral', 'Água mineral sem gás de 500 ml', 'Bebida', 4.00);

INSERT INTO cardapio
(id_cardapio, nome_cardapio, tipo_cardapio) VALUES
('C01', 'Sabores de Minas', 'Tradicional'),
('C02', 'Recepção Tropical', 'Coquetel'),
('C03', 'Recepção Essencial', 'Econômico'),
('C04', 'Celebração Imperial', 'Premium'),
('C05', 'Recepção Clássica', 'Tradicional');

INSERT INTO composicao
(id_aperitivo, id_cardapio) VALUES
('A01', 'C01'),
('A02', 'C01'),
('A05', 'C01'),
('A07', 'C02'),
('A08', 'C02'),
('A10', 'C02'),
('A02', 'C03'),
('A04', 'C03'),
('A06', 'C03'),
('A07', 'C04'),
('A09', 'C04'),
('A11', 'C04'),
('A01', 'C05'),
('A03', 'C05'),
('A12', 'C05');

INSERT INTO buffet
(cnpj, nome, endereco, tempo_parceria, cnpj_cerimonial) VALUES
('32784196000135', 'Buffet Sabores de Minas', 'Rua Pium-í, 640 - Belo Horizonte/MG', 5, '48372561000192'),
('19638542000187', 'Buffet Maison Celebrare', 'Avenida João César de Oliveira, 2450 - Contagem/MG', 3, '48372561000192'),
('54281763000109', 'Buffet Villa Imperial', 'Alameda Oscar Niemeyer, 850 - Nova Lima/MG', 8, '48372561000192'),
('28754916000163', 'Buffet Jardim das Festas', 'Avenida Governador Valadares, 520 - Betim/MG', 2, '48372561000192'),
('61392847000125', 'Buffet Dona Mineira', 'Rua Grão Mogol, 480 - Belo Horizonte/MG', 4, '48372561000192'),
('35917682000146', 'Buffet Alto Sabor', 'Alameda da Serra, 1120 - Nova Lima/MG', 10, '48372561000192');

INSERT INTO cardapio_buffet
(cnpj_buffet, id_cardapio) VALUES
('32784196000135', 'C01'),
('32784196000135', 'C05'),
('19638542000187', 'C02'),
('19638542000187', 'C03'),
('54281763000109', 'C02'),
('54281763000109', 'C04'),
('54281763000109', 'C05'),
('28754916000163', 'C01'),
('28754916000163', 'C03'),
('61392847000125', 'C03'),
('61392847000125', 'C05'),
('35917682000146', 'C02'),
('35917682000146', 'C04');
