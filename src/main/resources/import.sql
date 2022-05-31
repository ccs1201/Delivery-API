INSERT INTO cozinha VALUES (6,'Alemã'),(8,'Alienigena'),(3,'Brasileira'),(7,'Francesa'),(2,'Indiana'),(5,'Italiana'),(4,'Portuguesa'),(1,'Tailandesa');

insert into permissao (nome, descricao) values ('ADMINISTRADOR', 'Administradores do Sistema');
insert into permissao (nome, descricao) values ('USUARIO', 'Usuários do Sistema, aqueles que são restaurantes');
insert into permissao (nome, descricao) values ('CLIENTE', 'Clientes do Sistema, aqueles que fazem pedidos para os Restaurantes');

insert into tipo_pagamento (id, nome) values (1,'DINHEIRO'), (2,'CARTÃO CRÉDITO'), (3,'CARTÃO DÉBITO'), (4,'PIX'),(5,'BOLETO'), (6,'VOUCHER'), (7,'CUPOM DESCONTO');

INSERT INTO restaurante (id, nome, taxa_entrega, cozinha_id) values (1,'Thai Gourmet',0.00,1),(2,'Thai Delivery',9.50,1),(3,'Tuk Tuk Comida Indiana',15.00,2),(4,'Comida Brasileira',100.00,3),(5,'Comida Portuguesa',9.50,4),(6,'Comida  Italiana',0.00,5),(7,'Comida Alemã',10.00,6),(8,'Comida Francesa',9.50,7),(12,'Comida da Vovó',0.00,8),(20,'Restaurante Cozinha Francesa',11.99,5);

INSERT INTO restaurante_tipo_pagamento (restaurante_id, tipo_pgamento_id) VALUES (1,1),(1,2),(1,3),(1,4),(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(3,1),(3,2),(3,3),(3,4),(3,5),(3,6),(3,7),(4,1),(4,2);
INSERT INTO restaurante_tipo_pagamento (restaurante_id, tipo_pgamento_id) VALUES (4,4),(4,4),(4,5),(4,6),(4,7),(4,1),(4,2),(4,3),(4,4),(4,5),(4,6),(4,7),(5,1),(5,2),(5,3),(5,4),(5,5),(5,6),(5,7),(6,1);
INSERT INTO restaurante_tipo_pagamento (restaurante_id, tipo_pgamento_id) VALUES (6,2),(6,3),(6,4),(6,5),(6,6),(6,7),(7,1),(7,2),(7,3),(7,4),(7,5),(7,6),(7,7),(8,1),(8,2),(8,3),(8,4),(8,5),(8,6),(8,7);
INSERT INTO restaurante_tipo_pagamento (restaurante_id, tipo_pgamento_id) VALUES (12,1),(12,2),(12,3),(12,4),(12,5),(12,6),(12,7),(20,2),(20,3),(20,1),(20,4);