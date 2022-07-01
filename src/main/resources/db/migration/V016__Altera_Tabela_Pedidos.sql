ALTER TABLE delivery.pedido ADD restaurante_id BIGINT NOT NULL;
ALTER TABLE delivery.pedido ADD tipo_pagamento BIGINT NOT NULL;

ALTER TABLE delivery.pedido ADD CONSTRAINT FK_PEDIDO_ON_RESTAURANTE FOREIGN KEY (restaurante_id) REFERENCES delivery.restaurante(id);