alter table tipo_pagamento add column last_update datetime null;
update tipo_pagamento set tipo_pagamento.last_update = utc_timestamp;
alter table tipo_pagamento modify last_update datetime not null;