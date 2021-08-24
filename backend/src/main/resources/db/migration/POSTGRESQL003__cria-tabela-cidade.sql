create table cidade (
	id bigserial  primary key,
	nome varchar(80) NOT NULL,
	estado_id integer NOT NULL,
	
	foreign key(estado_id) references estado (id)
	
	
);