alter table restaurante_tipo_pagamento add constraint UN_TipoPagamento_Restaurante UNIQUE (restaurante_id, tipo_pagamento_id);