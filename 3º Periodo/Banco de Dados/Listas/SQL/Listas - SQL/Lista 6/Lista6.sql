/* ===== Banco de Dados: Lista 6 ===== */
/* --- Q1 --- */
select nome , estado
	from proprietario
	where estado in ('MG','SP','PR')
	order by estado asc , nome asc
	
/* --- Q2 --- */
select placa , modelo
	from veiculo
	where modelo like 'FORD%'
	
/* --- Q3 --- */
select placa , modelo , ano_fabricacao , valor_ipva 
	from veiculo
	where ano_fabricacao not in ('1997') and valor_ipva between 300 and 500

/* --- Q4 --- */
select veiculo
	from veiculo
	where valor_ipva is null
	order by placa asc
	
/* --- Q5 --- */
select proprietario
	from proprietario
	where cidade in ('RIO DE JANEIRO')
	
/* --- Q6 --- */
select placa , ano_fabricacao
	from veiculo
	where placa like 'G%1'
	
/* --- Q7 --- */
select modelo , valor_ipva , placa , ano_fabricacao 
	from veiculo
	where ano_fabricacao in ('1988', '1990', '1996' , '1997') 
	and modelo like 'FORD%' 
	and valor_ipva < 200 
	or valor_ipva > 800

/* --- Q8 --- */
select modelo , valor_ipva
	from veiculo
	where modelo like 'FIAT%'
	and valor_ipva between 100 and 800
	
/* --- Q9 --- */
insert into proprietario (cod_proprietario,nome,cidade,estado)
	values (3618,'Laura Martins','BELO HORIZONTE', 'MG');
	
insert into veiculo (placa , modelo, ano_fabricacao , valor_ipva , cod_proprietario)
	values ('ABC1234','FIAT 500','2022',null,3618);

/* --- Q10 --- */
update veiculo
	set valor_ipva = 200
	where placa = 'ABC1234';
