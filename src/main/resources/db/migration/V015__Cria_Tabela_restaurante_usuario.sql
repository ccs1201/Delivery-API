CREATE TABLE delivery.restaurante_usuario (
                                              restaurante_id BIGINT NOT NULL,
                                              usuario_id BIGINT NOT NULL,
                                              CONSTRAINT restaurante_usuario_PK PRIMARY KEY (restaurante_id,usuario_id),
                                              CONSTRAINT restaurante_usuario_FK FOREIGN KEY (usuario_id) REFERENCES delivery.usuario(id),
                                              CONSTRAINT restaurante_usuario_FK_1 FOREIGN KEY (restaurante_id) REFERENCES delivery.restaurante(id)
);