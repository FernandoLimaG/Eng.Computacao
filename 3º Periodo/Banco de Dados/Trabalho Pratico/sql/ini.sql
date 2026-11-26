CREATE TABLE tipo_profissional (
	id_tipo VARCHAR(10) PRIMARY KEY,
	descrição_tipo VARCHAR(300)
);

CREATE TABLE profissional (
	matricula VARCHAR(10) PRIMARY KEY,
	nome VARCHAR(150),
	salario DECIMAL(10,2),
    id_tipo VARCHAR(10) NOT NULL,

    FOREIGN KEY (id_tipo)
        REFERENCES tipo_profissional (id_tipo)
);

CREATE TABLE telefones (
	matricula_profissional VARCHAR(10),
	telefone VARCHAR(11),
	
	PRIMARY KEY (matricula_profissional,telefone),
	
    FOREIGN KEY (matricula_profissional)
		REFERENCES profissional (matricula)
);

CREATE TABLE cerimonial (
    endereco_cerimonial VARCHAR(500),
    nome VARCHAR(150),
    cnpj VARCHAR(14) PRIMARY KEY
);

CREATE TABLE formatura (
	curso VARCHAR(100),
	faculdade VARCHAR(100),
	nome_chefe VARCHAR(150),
	telefone_chefe VARCHAR(11),
	forma_pgto VARCHAR(20),
	valor DECIMAL(10,2),
	id_formatura VARCHAR(10) PRIMARY KEY,
    cnpj_cerimonial VARCHAR (14) NOT NULL,

    FOREIGN KEY (cnpj_cerimonial)
        REFERENCES cerimonial (cnpj)
);

CREATE TABLE evento (
	id_evento VARCHAR(10) PRIMARY KEY,
	descricao_evento VARCHAR(300),
	num_convidados INT,
	hora_inicio TIME,
	hora_fim TIME,
	data_evento DATE,
	local_evento VARCHAR(300),
    id_formatura VARCHAR(10) NOT NULL,

    FOREIGN KEY (id_formatura)
        REFERENCES formatura (id_formatura)
);

CREATE TABLE escala (
	id_profissional VARCHAR(10),
	id_evento VARCHAR(10),
	quantidade_horas INT,
	
	PRIMARY KEY (id_profissional,id_evento),

	FOREIGN KEY (id_profissional)
		REFERENCES profissional (matricula),

	FOREIGN KEY (id_evento)
		REFERENCES evento (id_evento)
);

CREATE TABLE aperitivo (
	preco_pessoa DECIMAL(10,2),
	descricao_aperitivo VARCHAR(300),
	tipo_aperitivo VARCHAR(100),
	nome_aperitivo VARCHAR(150),
	id_aperitivo VARCHAR(10) PRIMARY KEY
);

CREATE TABLE cardapio (
	nome_cardapio VARCHAR(150),
	tipo_cardapio VARCHAR(150),
	id_cardapio VARCHAR(10) PRIMARY KEY
);

CREATE TABLE composicao (
	id_aperitivo VARCHAR(10),
	id_cardapio VARCHAR(10),
	
	PRIMARY KEY (id_aperitivo,id_cardapio),
	
	FOREIGN KEY (id_aperitivo)
		REFERENCES aperitivo (id_aperitivo),
	
	FOREIGN KEY (id_cardapio)
		REFERENCES cardapio (id_cardapio)
);

CREATE TABLE buffet (
	cnpj VARCHAR(14) PRIMARY KEY,
	nome VARCHAR(150),
	endereco VARCHAR(500),
	tempo_parceria INT,
    cnpj_cerimonial VARCHAR(14) NOT NULL,

    FOREIGN KEY (cnpj_cerimonial)
        REFERENCES cerimonial (cnpj)
);

CREATE TABLE cardapio_buffet (
	cnpj_buffet VARCHAR(14),
	id_cardapio VARCHAR(10),
	
	PRIMARY KEY (cnpj_buffet,id_cardapio),
	
	FOREIGN KEY (cnpj_buffet)
		REFERENCES buffet (cnpj),
	
	FOREIGN KEY (id_cardapio)
		REFERENCES cardapio (id_cardapio)
);
