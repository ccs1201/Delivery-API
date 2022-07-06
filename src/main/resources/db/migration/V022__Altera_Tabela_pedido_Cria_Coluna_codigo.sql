ALTER TABLE delivery.pedido ADD codigo varchar(36) NOT NULL after id;
update pedido set codigo = uuid();
alter table pedido add constraint UN_pedido_codigo unique (id);
