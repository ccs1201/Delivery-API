alter table restaurante_tipo_pagamento drop index UN_TipoPagamento_Restaurante;
alter table restaurante_tipo_pagamento add constraint UN_TipoPagamento_Restaurante UNIQUE (restaurante_id, tipo_pagamento_id);

alter table produto drop index UN_Produto_Restaurante;
alter table produto add constraint UN_Produto_Restaurante UNIQUE (restaurante_id, nome);
