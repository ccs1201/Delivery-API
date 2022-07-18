CREATE TABLE delivery.foto_produto
(
    produto_id   BIGINT       NOT NULL,
    nome_arquivo varchar(150) NOT NULL,
    descricao    varchar(150) NULL,
    content_type varchar(80)  NOT NULL,
    tamanho      int          NOT NULL,
    primary key (produto_id),
    CONSTRAINT foto_produto_FK FOREIGN KEY (produto_id) REFERENCES delivery.produto (id)
)
    ENGINE=InnoDB DEFAULT CHARSET =utf8;