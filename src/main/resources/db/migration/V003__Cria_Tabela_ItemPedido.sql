CREATE TABLE item_pedido
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    produto_id     BIGINT                NOT NULL,
    quantidade     INT                   NOT NULL,
    valor_unitario DECIMAL(4,2)          NOT NULL,
    valor_total    DECIMAL(5,2)          NOT NULL,
    observacao     VARCHAR(100)          NULL,
    pedido_id      BIGINT                NOT NULL,
    CONSTRAINT pk_itempedido PRIMARY KEY (id)
);

ALTER TABLE item_pedido
    ADD CONSTRAINT FK_ITEMPEDIDO_ON_PEDIDO FOREIGN KEY (pedido_id) REFERENCES pedido (id);

ALTER TABLE item_pedido
    ADD CONSTRAINT FK_ITEMPEDIDO_ON_PRODUTO FOREIGN KEY (produto_id) REFERENCES produto (id);