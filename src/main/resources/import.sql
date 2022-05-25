insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');
insert into cozinha (nome) values ('Brasileira');
insert into cozinha (nome) values ('Portuguesa');
insert into cozinha (nome) values ('Italiana');
insert into cozinha (nome) values ('Alemã');
insert into cozinha (nome) values ('Francesa');


insert into restaurante (nome, taxa_entrega, cozinha_id) values ('Thai Gourmet', 10, 1);
insert into restaurante (nome, taxa_entrega, cozinha_id) values ('Thai Delivery', 9.50, 1);
insert into restaurante (nome, taxa_entrega, cozinha_id) values ('Tuk Tuk Comida Indiana', 15, 2);
insert into restaurante (nome, taxa_entrega, cozinha_id) values ('Comida Brasileira', 10, 3);
insert into restaurante (nome, taxa_entrega, cozinha_id) values ('Comida Portuguesa', 9.50, 4);
insert into restaurante (nome, taxa_entrega, cozinha_id) values ('Comida  Italiana', 15, 5);
insert into restaurante (nome, taxa_entrega, cozinha_id) values ('Comida Alemã', 10, 6);
insert into restaurante (nome, taxa_entrega, cozinha_id) values ('Comida Francesa', 9.50, 7);

insert into forma_pagamento (descricao) values ('DINHEIRO');
insert into forma_pagamento (descricao) values ('CARTÃO CREDITO');
insert into forma_pagamento (descricao) values ('CARTÃO DEBITO');
insert into forma_pagamento (descricao) values ('BOLETO');
insert into forma_pagamento (descricao) values ('PIX');
insert into forma_pagamento (descricao) values ('VOUCHER');

insert into permissao (nome, descricao) values ('ADMINISTRADOR', 'Administradores do Sistema');
insert into permissao (nome, descricao) values ('USUARIO', 'Usuários do Sistema, aqueles que são restaurantes');
insert into permissao (nome, descricao) values ('CLIENTE', 'Clientes do Sistema, aqueles que fazem pedidos para os Restaurantes');
