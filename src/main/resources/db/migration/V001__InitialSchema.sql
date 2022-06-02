 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT ;
 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS ;
 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION ;
 SET NAMES UTF8MB4 ;
 SET @OLD_TIME_ZONE=@@TIME_ZONE ;
 SET TIME_ZONE='-03:00' ;
 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 ;
 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 ;
 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' ;
 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 ;

--
-- Table structure for table `cozinha`
--

DROP TABLE IF EXISTS `cozinha`;
 SET @saved_cs_client     = @@character_set_client ;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cozinha` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_21inunwxqp3wdrnbji4sp1vli` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 SET character_set_client = @saved_cs_client ;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
 SET @saved_cs_client     = @@character_set_client ;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `estado` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `sigla` varchar(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 SET character_set_client = @saved_cs_client ;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
 SET @saved_cs_client     = @@character_set_client ;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `grupo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 SET character_set_client = @saved_cs_client ;

--
-- Table structure for table `grupo_permissao`
--

DROP TABLE IF EXISTS `grupo_permissao`;
 SET @saved_cs_client     = @@character_set_client ;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `grupo_permissao` (
  `grupo_id` bigint NOT NULL,
  `permissao_id` bigint NOT NULL,
  KEY `FKh21kiw0y0hxg6birmdf2ef6vy` (`permissao_id`),
  KEY `FKta4si8vh3f4jo3bsslvkscc2m` (`grupo_id`),
  CONSTRAINT `FKh21kiw0y0hxg6birmdf2ef6vy` FOREIGN KEY (`permissao_id`) REFERENCES `permissao` (`id`),
  CONSTRAINT `FKta4si8vh3f4jo3bsslvkscc2m` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 SET character_set_client = @saved_cs_client ;

--
-- Table structure for table `municipio`
--

DROP TABLE IF EXISTS `municipio`;
 SET @saved_cs_client     = @@character_set_client ;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `municipio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `estado_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo507uki7f73gchnn0sk0edjps` (`estado_id`),
  CONSTRAINT `FKo507uki7f73gchnn0sk0edjps` FOREIGN KEY (`estado_id`) REFERENCES `estado` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 SET character_set_client = @saved_cs_client ;

--
-- Table structure for table `permissao`
--

DROP TABLE IF EXISTS `permissao`;
 SET @saved_cs_client     = @@character_set_client ;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `permissao` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  `nome` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kgx7ng07rgem07n056nqc7i1q` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 SET character_set_client = @saved_cs_client ;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
 SET @saved_cs_client     = @@character_set_client ;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `produto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `valor` decimal(19,2) NOT NULL,
  `restaurante_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hdot1xprktyi4sf2onvllkmkd` (`nome`),
  KEY `FKb9jhjyghjcn25guim7q4pt8qx` (`restaurante_id`),
  CONSTRAINT `FKb9jhjyghjcn25guim7q4pt8qx` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 SET character_set_client = @saved_cs_client ;

--
-- Table structure for table `restaurante`
--

DROP TABLE IF EXISTS `restaurante`;
 SET @saved_cs_client     = @@character_set_client ;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `restaurante` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_cadastro` datetime NOT NULL,
  `data_ultima_atualizacao` datetime NOT NULL,
  `endereco_bairro` varchar(255) DEFAULT NULL,
  `endereco_cep` int DEFAULT NULL,
  `endereco_complemento` varchar(255) DEFAULT NULL,
  `endereco_logradouro` varchar(255) DEFAULT NULL,
  `endereco_numero` varchar(255) DEFAULT NULL,
  `nome` varchar(60) NOT NULL,
  `taxa_entrega` decimal(19,2) NOT NULL,
  `cozinha_id` bigint NOT NULL,
  `municipio_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK76grk4roudh659skcgbnanthi` (`cozinha_id`),
  KEY `FK469uc8uun9vl9tg6swiifh0l2` (`municipio_id`),
  CONSTRAINT `FK469uc8uun9vl9tg6swiifh0l2` FOREIGN KEY (`municipio_id`) REFERENCES `municipio` (`id`),
  CONSTRAINT `FK76grk4roudh659skcgbnanthi` FOREIGN KEY (`cozinha_id`) REFERENCES `cozinha` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 SET character_set_client = @saved_cs_client ;

--
-- Table structure for table `restaurante_tipo_pagamento`
--

DROP TABLE IF EXISTS `restaurante_tipo_pagamento`;
 SET @saved_cs_client     = @@character_set_client ;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `restaurante_tipo_pagamento` (
  `restaurante_id` bigint NOT NULL,
  `tipo_pagamento_id` bigint NOT NULL,
  KEY `FKhtoijs4bjuw9fo6wksmi4pyu2` (`tipo_pagamento_id`),
  KEY `FK5ewi04k9cbll90g3new30dia1` (`restaurante_id`),
  CONSTRAINT `FK5ewi04k9cbll90g3new30dia1` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`),
  CONSTRAINT `FKhtoijs4bjuw9fo6wksmi4pyu2` FOREIGN KEY (`tipo_pagamento_id`) REFERENCES `tipo_pagamento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 SET character_set_client = @saved_cs_client ;

--
-- Table structure for table `tipo_pagamento`
--

DROP TABLE IF EXISTS `tipo_pagamento`;
 SET @saved_cs_client     = @@character_set_client ;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tipo_pagamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 SET character_set_client = @saved_cs_client ;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
 SET @saved_cs_client     = @@character_set_client ;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_cadastro` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 SET character_set_client = @saved_cs_client ;

--
-- Table structure for table `usuario_grupos`
--

DROP TABLE IF EXISTS `usuario_grupos`;
 SET @saved_cs_client     = @@character_set_client ;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuario_grupos` (
  `usuario_id` bigint NOT NULL,
  `grupo_id` bigint NOT NULL,
  PRIMARY KEY (`usuario_id`,`grupo_id`),
  KEY `FKn1t1y2qk5hn6vdh0d4s4wsu67` (`grupo_id`),
  CONSTRAINT `FK158r9y55ufwykh675ddt8kb43` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKn1t1y2qk5hn6vdh0d4s4wsu67` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 SET character_set_client = @saved_cs_client ;
 SET TIME_ZONE=@OLD_TIME_ZONE ;

 SET SQL_MODE=@OLD_SQL_MODE ;
 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS ;
 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS ;
 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT ;
 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS ;
 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION ;
 SET SQL_NOTES=@OLD_SQL_NOTES ;

-- Dump completed on 2022-06-01 16:31:10
