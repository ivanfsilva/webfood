create table pedido (
    id  bigserial not null,
    subtotal decimal (10, 2) not null,
    taxa_frete decimal (10, 2) not null,
    valor_total decimal (10, 2) not null,

    restaurante_id int8 not null,
    usuario_cliente_id int8 not null,
    forma_pagamento_id int8 not null,

    endereco_cidade_id int8  not null,
    endereco_cep varchar(9) not null,
    endereco_logradouro varchar(100) not null,
    endereco_numero varchar(20) not null,
    endereco_complemento varchar(60) not null,
    endereco_bairro varchar(60) not null,

    status varchar (10) not null,
    data_criacao timestamp not null,
    data_confirmacao timestamp not null,
    data_cancelamento timestamp not null,
    data_entrega timestamp not null,

    primary key (id)
);

alter table pedido add constraint fk_pedido_restaurante
    foreign key (restaurante_id) references restaurante;

alter table pedido add constraint fk_pedido_usuario_cliente
    foreign key (usuario_cliente_id) references usuario;

alter table pedido add constraint fk_pedido_forma_pagamento
    foreign key (forma_pagamento_id) references forma_pagamento;



create table item_pedido (
    id bigserial not null,
    quantidade int8  NOT NULL,
    preco_unitario decimal(10,2) not null,
    preco_total decimal(10,2) not null,
    observacao varchar(255) null,
    pedido_id bigint not null,
    produto_id bigint not null,

    UNIQUE (pedido_id, produto_id),
    primary key (id)
);

alter table item_pedido add constraint fk_item_pedido_pedido
    foreign key (pedido_id) references pedido;

alter table item_pedido add constraint fk_item_pedido_produto
    foreign key (produto_id) references produto;