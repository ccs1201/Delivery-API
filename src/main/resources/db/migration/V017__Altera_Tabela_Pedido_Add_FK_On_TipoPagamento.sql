ALTER TABLE delivery.pedido DROP COLUMN tipo_pagamento;
ALTER TABLE delivery.pedido ADD tipo_pagamento_id BIGINT NOT NULL;

ALTER TABLE delivery.pedido ADD CONSTRAINT pedido_FK FOREIGN KEY (tipo_pagamento_id) REFERENCES delivery.tipo_pagamento(id);