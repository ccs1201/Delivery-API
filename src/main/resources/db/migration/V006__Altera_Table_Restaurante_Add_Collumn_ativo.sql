ALTER TABLE `delivery`.`restaurante`
    ADD COLUMN `ativo` TINYINT(1) NOT NULL DEFAULT 1 AFTER `municipio_id`;

update `delivery`.`restaurante` set ativo = true;
