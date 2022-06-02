CREATE TABLE pedido
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    cliente_id        BIGINT                NOT NULL,
    sub_total         DECIMAL(5,2)          NOT NULL,
    taxa_entrega      DECIMAL(3,2)          NOT NULL,
    valor_total       DECIMAL(5,2)          NOT NULL,
    data_criacao      datetime              NOT NULL,
    data_confirmacao  datetime              NULL,
    data_cancelamento datetime              NULL,
    data_entrega      datetime              NULL,
    status_pedido     VARCHAR(10)           NOT NULL,
    logradouro        VARCHAR(150)          NOT NULL,
    numero            VARCHAR(10)           NOT NULL,
    complemento       VARCHAR(50)           NOT NULL,
    bairro            VARCHAR(100)          NOT NULL,
    cep               INT                   NOT NULL,
    municipio_id      INT                   NOT NULL,
    CONSTRAINT pk_pedido PRIMARY KEY (id)
);

ALTER TABLE pedido
    ADD CONSTRAINT FK_PEDIDO_ON_CLIENTE FOREIGN KEY (cliente_id) REFERENCES usuario (id);

ALTER TABLE pedido
    ADD CONSTRAINT FK_PEDIDO_ON_MUNICIPIO FOREIGN KEY (municipio_id) REFERENCES municipio (id);