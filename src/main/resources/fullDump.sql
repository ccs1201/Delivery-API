-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: delivery
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cozinha`
--

DROP TABLE IF EXISTS `cozinha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cozinha` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_21inunwxqp3wdrnbji4sp1vli` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cozinha`
--

LOCK TABLES `cozinha` WRITE;
/*!40000 ALTER TABLE `cozinha` DISABLE KEYS */;
INSERT INTO `cozinha` VALUES (6,'Alemã'),(8,'Alienigena'),(3,'Brasileira'),(7,'Francesa'),(2,'Indiana'),(5,'Italiana'),(4,'Portuguesa'),(1,'Tailandesa');
/*!40000 ALTER TABLE `cozinha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `sigla` varchar(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES (1,'Acre','AC'),(2,'Alagoas','AL'),(3,'Amazonas','AM'),(4,'Amapá','AP'),(5,'Bahia','BA'),(6,'Ceará','CE'),(7,'Distrito Federal','DF'),(8,'Espírito Santo','ES'),(9,'Goiás','GO'),(10,'Maranhão','MA'),(11,'Minas Gerais','MG'),(12,'Mato Grosso do Sul','MS'),(13,'Mato Grosso','MT'),(14,'Pará','PA'),(15,'Paraíba','PB'),(16,'Pernambuco','PE'),(17,'Piauí','PI'),(18,'Paraná','PR'),(19,'Rio de Janeiro','RJ'),(20,'Rio Grande do Norte','RN'),(21,'Rondônia','RO'),(22,'Roraima','RR'),(23,'Rio Grande do Sul','RS'),(24,'Santa Catarina','SC'),(25,'Sergipe','SE'),(26,'São Paulo','SP'),(27,'Tocantins','TO');
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_permissao`
--

DROP TABLE IF EXISTS `grupo_permissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo_permissao` (
  `grupo_id` bigint NOT NULL,
  `permissao_id` bigint NOT NULL,
  KEY `FKh21kiw0y0hxg6birmdf2ef6vy` (`permissao_id`),
  KEY `FKta4si8vh3f4jo3bsslvkscc2m` (`grupo_id`),
  CONSTRAINT `FKh21kiw0y0hxg6birmdf2ef6vy` FOREIGN KEY (`permissao_id`) REFERENCES `permissao` (`id`),
  CONSTRAINT `FKta4si8vh3f4jo3bsslvkscc2m` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_permissao`
--

LOCK TABLES `grupo_permissao` WRITE;
/*!40000 ALTER TABLE `grupo_permissao` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo_permissao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `municipio`
--

DROP TABLE IF EXISTS `municipio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `municipio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `estado_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo507uki7f73gchnn0sk0edjps` (`estado_id`),
  CONSTRAINT `FKo507uki7f73gchnn0sk0edjps` FOREIGN KEY (`estado_id`) REFERENCES `estado` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2068 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `municipio`
--

LOCK TABLES `municipio` WRITE;
/*!40000 ALTER TABLE `municipio` DISABLE KEYS */;
INSERT INTO `municipio` VALUES (1,'Afonso Cláudio',8),(2,'Água Doce do Norte',8),(3,'Águia Branca',8),(4,'Alegre',8),(5,'Alfredo Chaves',8),(6,'Alto Rio Novo',8),(7,'Anchieta',8),(8,'Apiacá',8),(9,'Aracruz',8),(10,'Atilio Vivacqua',8),(11,'Baixo Guandu',8),(12,'Barra de São Francisco',8),(13,'Boa Esperança',8),(14,'Bom Jesus do Norte',8),(15,'Brejetuba',8),(16,'Cachoeiro de Itapemirim',8),(17,'Cariacica',8),(18,'Castelo',8),(19,'Colatina',8),(20,'Conceição da Barra',8),(21,'Conceição do Castelo',8),(22,'Divino de São Lourenço',8),(23,'Domingos Martins',8),(24,'Dores do Rio Preto',8),(25,'Ecoporanga',8),(26,'Fundão',8),(27,'Governador Lindenberg',8),(28,'Guaçuí',8),(29,'Guarapari',8),(30,'Ibatiba',8),(31,'Ibiraçu',8),(32,'Ibitirama',8),(33,'Iconha',8),(34,'Irupi',8),(35,'Itaguaçu',8),(36,'Itapemirim',8),(37,'Itarana',8),(38,'Iúna',8),(39,'Jaguaré',8),(40,'Jerônimo Monteiro',8),(41,'João Neiva',8),(42,'Laranja da Terra',8),(43,'Linhares',8),(44,'Mantenópolis',8),(45,'Marataízes',8),(46,'Marechal Floriano',8),(47,'Marilândia',8),(48,'Mimoso do Sul',8),(49,'Montanha',8),(50,'Mucurici',8),(51,'Muniz Freire',8),(52,'Muqui',8),(53,'Nova Venécia',8),(54,'Pancas',8),(55,'Pedro Canário',8),(56,'Pinheiros',8),(57,'Piúma',8),(58,'Ponto Belo',8),(59,'Presidente Kennedy',8),(60,'Rio Bananal',8),(61,'Rio Novo do Sul',8),(62,'Santa Leopoldina',8),(63,'Santa Maria de Jetibá',8),(64,'Santa Teresa',8),(65,'São Domingos do Norte',8),(66,'São Gabriel da Palha',8),(67,'São José do Calçado',8),(68,'São Mateus',8),(69,'São Roque do Canaã',8),(70,'Serra',8),(71,'Sooretama',8),(72,'Vargem Alta',8),(73,'Venda Nova do Imigrante',8),(74,'Viana',8),(75,'Vila Pavão',8),(76,'Vila Valério',8),(77,'Vila Velha',8),(78,'Vitória',8),(79,'Acrelândia',1),(80,'Assis Brasil',1),(81,'Brasiléia',1),(82,'Bujari',1),(83,'Capixaba',1),(84,'Cruzeiro do Sul',1),(85,'Epitaciolândia',1),(86,'Feijó',1),(87,'Jordão',1),(88,'Mâncio Lima',1),(89,'Manoel Urbano',1),(90,'Marechal Thaumaturgo',1),(91,'Plácido de Castro',1),(92,'Porto Acre',1),(93,'Porto Walter',1),(94,'Rio Branco',1),(95,'Rodrigues Alves',1),(96,'Santa Rosa do Purus',1),(97,'Sena Madureira',1),(98,'Senador Guiomard',1),(99,'Tarauacá',1),(100,'Xapuri',1),(101,'Água Branca',2),(102,'Anadia',2),(103,'Arapiraca',2),(104,'Atalaia',2),(105,'Barra de Santo Antônio',2),(106,'Barra de São Miguel',2),(107,'Batalha',2),(108,'Belém',2),(109,'Belo Monte',2),(110,'Boca da Mata',2),(111,'Branquinha',2),(112,'Cacimbinhas',2),(113,'Cajueiro',2),(114,'Campestre',2),(115,'Campo Alegre',2),(116,'Campo Grande',2),(117,'Canapi',2),(118,'Capela',2),(119,'Carneiros',2),(120,'Chã Preta',2),(121,'Coité do Nóia',2),(122,'Colônia Leopoldina',2),(123,'Coqueiro Seco',2),(124,'Coruripe',2),(125,'Craíbas',2),(126,'Delmiro Gouveia',2),(127,'Dois Riachos',2),(128,'Estrela de Alagoas',2),(129,'Feira Grande',2),(130,'Feliz Deserto',2),(131,'Flexeiras',2),(132,'Girau do Ponciano',2),(133,'Ibateguara',2),(134,'Igaci',2),(135,'Igreja Nova',2),(136,'Inhapi',2),(137,'Jacaré dos Homens',2),(138,'Jacuípe',2),(139,'Japaratinga',2),(140,'Jaramataia',2),(141,'Jequiá da Praia',2),(142,'Joaquim Gomes',2),(143,'Jundiá',2),(144,'Junqueiro',2),(145,'Lagoa da Canoa',2),(146,'Limoeiro de Anadia',2),(147,'Maceió',2),(148,'Major Isidoro',2),(149,'Mar Vermelho',2),(150,'Maragogi',2),(151,'Maravilha',2),(152,'Marechal Deodoro',2),(153,'Maribondo',2),(154,'Mata Grande',2),(155,'Matriz de Camaragibe',2),(156,'Messias',2),(157,'Minador do Negrão',2),(158,'Monteirópolis',2),(159,'Murici',2),(160,'Novo Lino',2),(161,'Olho d`Água das Flores',2),(162,'Olho d`Água do Casado',2),(163,'Olho d`Água Grande',2),(164,'Olivença',2),(165,'Ouro Branco',2),(166,'Palestina',2),(167,'Palmeira dos Índios',2),(168,'Pão de Açúcar',2),(169,'Pariconha',2),(170,'Paripueira',2),(171,'Passo de Camaragibe',2),(172,'Paulo Jacinto',2),(173,'Penedo',2),(174,'Piaçabuçu',2),(175,'Pilar',2),(176,'Pindoba',2),(177,'Piranhas',2),(178,'Poço das Trincheiras',2),(179,'Porto Calvo',2),(180,'Porto de Pedras',2),(181,'Porto Real do Colégio',2),(182,'Quebrangulo',2),(183,'Rio Largo',2),(184,'Roteiro',2),(185,'Santa Luzia do Norte',2),(186,'Santana do Ipanema',2),(187,'Santana do Mundaú',2),(188,'São Brás',2),(189,'São José da Laje',2),(190,'São José da Tapera',2),(191,'São Luís do Quitunde',2),(192,'São Miguel dos Campos',2),(193,'São Miguel dos Milagres',2),(194,'São Sebastião',2),(195,'Satuba',2),(196,'Senador Rui Palmeira',2),(197,'Tanque d`Arca',2),(198,'Taquarana',2),(199,'Teotônio Vilela',2),(200,'Traipu',2),(201,'União dos Palmares',2),(202,'Viçosa',2),(203,'Amapá',4),(204,'Calçoene',4),(205,'Cutias',4),(206,'Ferreira Gomes',4),(207,'Itaubal',4),(208,'Laranjal do Jari',4),(209,'Macapá',4),(210,'Mazagão',4),(211,'Oiapoque',4),(212,'Pedra Branca do Amaparí',4),(213,'Porto Grande',4),(214,'Pracuúba',4),(215,'Santana',4),(216,'Serra do Navio',4),(217,'Tartarugalzinho',4),(218,'Vitória do Jari',4),(219,'Alvarães',3),(220,'Amaturá',3),(221,'Anamã',3),(222,'Anori',3),(223,'Apuí',3),(224,'Atalaia do Norte',3),(225,'Autazes',3),(226,'Barcelos',3),(227,'Barreirinha',3),(228,'Benjamin Constant',3),(229,'Beruri',3),(230,'Boa Vista do Ramos',3),(231,'Boca do Acre',3),(232,'Borba',3),(233,'Caapiranga',3),(234,'Canutama',3),(235,'Carauari',3),(236,'Careiro',3),(237,'Careiro da Várzea',3),(238,'Coari',3),(239,'Codajás',3),(240,'Eirunepé',3),(241,'Envira',3),(242,'Fonte Boa',3),(243,'Guajará',3),(244,'Humaitá',3),(245,'Ipixuna',3),(246,'Iranduba',3),(247,'Itacoatiara',3),(248,'Itamarati',3),(249,'Itapiranga',3),(250,'Japurá',3),(251,'Juruá',3),(252,'Jutaí',3),(253,'Lábrea',3),(254,'Manacapuru',3),(255,'Manaquiri',3),(256,'Manaus',3),(257,'Manicoré',3),(258,'Maraã',3),(259,'Maués',3),(260,'Nhamundá',3),(261,'Nova Olinda do Norte',3),(262,'Novo Airão',3),(263,'Novo Aripuanã',3),(264,'Parintins',3),(265,'Pauini',3),(266,'Presidente Figueiredo',3),(267,'Rio Preto da Eva',3),(268,'Santa Isabel do Rio Negro',3),(269,'Santo Antônio do Içá',3),(270,'São Gabriel da Cachoeira',3),(271,'São Paulo de Olivença',3),(272,'São Sebastião do Uatumã',3),(273,'Silves',3),(274,'Tabatinga',3),(275,'Tapauá',3),(276,'Tefé',3),(277,'Tonantins',3),(278,'Uarini',3),(279,'Urucará',3),(280,'Urucurituba',3),(281,'Abaíra',5),(282,'Abaré',5),(283,'Acajutiba',5),(284,'Adustina',5),(285,'Água Fria',5),(286,'Aiquara',5),(287,'Alagoinhas',5),(288,'Alcobaça',5),(289,'Almadina',5),(290,'Amargosa',5),(291,'Amélia Rodrigues',5),(292,'América Dourada',5),(293,'Anagé',5),(294,'Andaraí',5),(295,'Andorinha',5),(296,'Angical',5),(297,'Anguera',5),(298,'Antas',5),(299,'Antônio Cardoso',5),(300,'Antônio Gonçalves',5),(301,'Aporá',5),(302,'Apuarema',5),(303,'Araças',5),(304,'Aracatu',5),(305,'Araci',5),(306,'Aramari',5),(307,'Arataca',5),(308,'Aratuípe',5),(309,'Aurelino Leal',5),(310,'Baianópolis',5),(311,'Baixa Grande',5),(312,'Banzaê',5),(313,'Barra',5),(314,'Barra da Estiva',5),(315,'Barra do Choça',5),(316,'Barra do Mendes',5),(317,'Barra do Rocha',5),(318,'Barreiras',5),(319,'Barro Alto',5),(320,'Barro Preto (antigo Gov. Lomanto Jr.)',5),(321,'Barrocas',5),(322,'Belmonte',5),(323,'Belo Campo',5),(324,'Biritinga',5),(325,'Boa Nova',5),(326,'Boa Vista do Tupim',5),(327,'Bom Jesus da Lapa',5),(328,'Bom Jesus da Serra',5),(329,'Boninal',5),(330,'Bonito',5),(331,'Boquira',5),(332,'Botuporã',5),(333,'Brejões',5),(334,'Brejolândia',5),(335,'Brotas de Macaúbas',5),(336,'Brumado',5),(337,'Buerarema',5),(338,'Buritirama',5),(339,'Caatiba',5),(340,'Cabaceiras do Paraguaçu',5),(341,'Cachoeira',5),(342,'Caculé',5),(343,'Caém',5),(344,'Caetanos',5),(345,'Caetité',5),(346,'Cafarnaum',5),(347,'Cairu',5),(348,'Caldeirão Grande',5),(349,'Camacan',5),(350,'Camaçari',5),(351,'Camamu',5),(352,'Campo Alegre de Lourdes',5),(353,'Campo Formoso',5),(354,'Canápolis',5),(355,'Canarana',5),(356,'Canavieiras',5),(357,'Candeal',5),(358,'Candeias',5),(359,'Candiba',5),(360,'Cândido Sales',5),(361,'Cansanção',5),(362,'Canudos',5),(363,'Capela do Alto Alegre',5),(364,'Capim Grosso',5),(365,'Caraíbas',5),(366,'Caravelas',5),(367,'Cardeal da Silva',5),(368,'Carinhanha',5),(369,'Casa Nova',5),(370,'Castro Alves',5),(371,'Catolândia',5),(372,'Catu',5),(373,'Caturama',5),(374,'Central',5),(375,'Chorrochó',5),(376,'Cícero Dantas',5),(377,'Cipó',5),(378,'Coaraci',5),(379,'Cocos',5),(380,'Conceição da Feira',5),(381,'Conceição do Almeida',5),(382,'Conceição do Coité',5),(383,'Conceição do Jacuípe',5),(384,'Conde',5),(385,'Condeúba',5),(386,'Contendas do Sincorá',5),(387,'Coração de Maria',5),(388,'Cordeiros',5),(389,'Coribe',5),(390,'Coronel João Sá',5),(391,'Correntina',5),(392,'Cotegipe',5),(393,'Cravolândia',5),(394,'Crisópolis',5),(395,'Cristópolis',5),(396,'Cruz das Almas',5),(397,'Curaçá',5),(398,'Dário Meira',5),(399,'Dias d`Ávila',5),(400,'Dom Basílio',5),(401,'Dom Macedo Costa',5),(402,'Elísio Medrado',5),(403,'Encruzilhada',5),(404,'Entre Rios',5),(405,'Érico Cardoso',5),(406,'Esplanada',5),(407,'Euclides da Cunha',5),(408,'Eunápolis',5),(409,'Fátima',5),(410,'Feira da Mata',5),(411,'Feira de Santana',5),(412,'Filadélfia',5),(413,'Firmino Alves',5),(414,'Floresta Azul',5),(415,'Formosa do Rio Preto',5),(416,'Gandu',5),(417,'Gavião',5),(418,'Gentio do Ouro',5),(419,'Glória',5),(420,'Gongogi',5),(421,'Governador Mangabeira',5),(422,'Guajeru',5),(423,'Guanambi',5),(424,'Guaratinga',5),(425,'Heliópolis',5),(426,'Iaçu',5),(427,'Ibiassucê',5),(428,'Ibicaraí',5),(429,'Ibicoara',5),(430,'Ibicuí',5),(431,'Ibipeba',5),(432,'Ibipitanga',5),(433,'Ibiquera',5),(434,'Ibirapitanga',5),(435,'Ibirapuã',5),(436,'Ibirataia',5),(437,'Ibitiara',5),(438,'Ibititá',5),(439,'Ibotirama',5),(440,'Ichu',5),(441,'Igaporã',5),(442,'Igrapiúna',5),(443,'Iguaí',5),(444,'Ilhéus',5),(445,'Inhambupe',5),(446,'Ipecaetá',5),(447,'Ipiaú',5),(448,'Ipirá',5),(449,'Ipupiara',5),(450,'Irajuba',5),(451,'Iramaia',5),(452,'Iraquara',5),(453,'Irará',5),(454,'Irecê',5),(455,'Itabela',5),(456,'Itaberaba',5),(457,'Itabuna',5),(458,'Itacaré',5),(459,'Itaeté',5),(460,'Itagi',5),(461,'Itagibá',5),(462,'Itagimirim',5),(463,'Itaguaçu da Bahia',5),(464,'Itaju do Colônia',5),(465,'Itajuípe',5),(466,'Itamaraju',5),(467,'Itamari',5),(468,'Itambé',5),(469,'Itanagra',5),(470,'Itanhém',5),(471,'Itaparica',5),(472,'Itapé',5),(473,'Itapebi',5),(474,'Itapetinga',5),(475,'Itapicuru',5),(476,'Itapitanga',5),(477,'Itaquara',5),(478,'Itarantim',5),(479,'Itatim',5),(480,'Itiruçu',5),(481,'Itiúba',5),(482,'Itororó',5),(483,'Ituaçu',5),(484,'Ituberá',5),(485,'Iuiú',5),(486,'Jaborandi',5),(487,'Jacaraci',5),(488,'Jacobina',5),(489,'Jaguaquara',5),(490,'Jaguarari',5),(491,'Jaguaripe',5),(492,'Jandaíra',5),(493,'Jequié',5),(494,'Jeremoabo',5),(495,'Jiquiriçá',5),(496,'Jitaúna',5),(497,'João Dourado',5),(498,'Juazeiro',5),(499,'Jucuruçu',5),(500,'Jussara',5),(501,'Jussari',5),(502,'Jussiape',5),(503,'Lafaiete Coutinho',5),(504,'Lagoa Real',5),(505,'Laje',5),(506,'Lajedão',5),(507,'Lajedinho',5),(508,'Lajedo do Tabocal',5),(509,'Lamarão',5),(510,'Lapão',5),(511,'Lauro de Freitas',5),(512,'Lençóis',5),(513,'Licínio de Almeida',5),(514,'Livramento de Nossa Senhora',5),(515,'Luís Eduardo Magalhães',5),(516,'Macajuba',5),(517,'Macarani',5),(518,'Macaúbas',5),(519,'Macururé',5),(520,'Madre de Deus',5),(521,'Maetinga',5),(522,'Maiquinique',5),(523,'Mairi',5),(524,'Malhada',5),(525,'Malhada de Pedras',5),(526,'Manoel Vitorino',5),(527,'Mansidão',5),(528,'Maracás',5),(529,'Maragogipe',5),(530,'Maraú',5),(531,'Marcionílio Souza',5),(532,'Mascote',5),(533,'Mata de São João',5),(534,'Matina',5),(535,'Medeiros Neto',5),(536,'Miguel Calmon',5),(537,'Milagres',5),(538,'Mirangaba',5),(539,'Mirante',5),(540,'Monte Santo',5),(541,'Morpará',5),(542,'Morro do Chapéu',5),(543,'Mortugaba',5),(544,'Mucugê',5),(545,'Mucuri',5),(546,'Mulungu do Morro',5),(547,'Mundo Novo',5),(548,'Muniz Ferreira',5),(549,'Muquém de São Francisco',5),(550,'Muritiba',5),(551,'Mutuípe',5),(552,'Nazaré',5),(553,'Nilo Peçanha',5),(554,'Nordestina',5),(555,'Nova Canaã',5),(556,'Nova Fátima',5),(557,'Nova Ibiá',5),(558,'Nova Itarana',5),(559,'Nova Redenção',5),(560,'Nova Soure',5),(561,'Nova Viçosa',5),(562,'Novo Horizonte',5),(563,'Novo Triunfo',5),(564,'Olindina',5),(565,'Oliveira dos Brejinhos',5),(566,'Ouriçangas',5),(567,'Ourolândia',5),(568,'Palmas de Monte Alto',5),(569,'Palmeiras',5),(570,'Paramirim',5),(571,'Paratinga',5),(572,'Paripiranga',5),(573,'Pau Brasil',5),(574,'Paulo Afonso',5),(575,'Pé de Serra',5),(576,'Pedrão',5),(577,'Pedro Alexandre',5),(578,'Piatã',5),(579,'Pilão Arcado',5),(580,'Pindaí',5),(581,'Pindobaçu',5),(582,'Pintadas',5),(583,'Piraí do Norte',5),(584,'Piripá',5),(585,'Piritiba',5),(586,'Planaltino',5),(587,'Planalto',5),(588,'Poções',5),(589,'Pojuca',5),(590,'Ponto Novo',5),(591,'Porto Seguro',5),(592,'Potiraguá',5),(593,'Prado',5),(594,'Presidente Dutra',5),(595,'Presidente Jânio Quadros',5),(596,'Presidente Tancredo Neves',5),(597,'Queimadas',5),(598,'Quijingue',5),(599,'Quixabeira',5),(600,'Rafael Jambeiro',5),(601,'Remanso',5),(602,'Retirolândia',5),(603,'Riachão das Neves',5),(604,'Riachão do Jacuípe',5),(605,'Riacho de Santana',5),(606,'Ribeira do Amparo',5),(607,'Ribeira do Pombal',5),(608,'Ribeirão do Largo',5),(609,'Rio de Contas',5),(610,'Rio do Antônio',5),(611,'Rio do Pires',5),(612,'Rio Real',5),(613,'Rodelas',5),(614,'Ruy Barbosa',5),(615,'Salinas da Margarida',5),(616,'Salvador',5),(617,'Santa Bárbara',5),(618,'Santa Brígida',5),(619,'Santa Cruz Cabrália',5),(620,'Santa Cruz da Vitória',5),(621,'Santa Inês',5),(622,'Santa Luzia',5),(623,'Santa Maria da Vitória',5),(624,'Santa Rita de Cássia',5),(625,'Santa Teresinha',5),(626,'Santaluz',5),(627,'Santana',5),(628,'Santanópolis',5),(629,'Santo Amaro',5),(630,'Santo Antônio de Jesus',5),(631,'Santo Estêvão',5),(632,'São Desidério',5),(633,'São Domingos',5),(634,'São Felipe',5),(635,'São Félix',5),(636,'São Félix do Coribe',5),(637,'São Francisco do Conde',5),(638,'São Gabriel',5),(639,'São Gonçalo dos Campos',5),(640,'São José da Vitória',5),(641,'São José do Jacuípe',5),(642,'São Miguel das Matas',5),(643,'São Sebastião do Passé',5),(644,'Sapeaçu',5),(645,'Sátiro Dias',5),(646,'Saubara',5),(647,'Saúde',5),(648,'Seabra',5),(649,'Sebastião Laranjeiras',5),(650,'Senhor do Bonfim',5),(651,'Sento Sé',5),(652,'Serra do Ramalho',5),(653,'Serra Dourada',5),(654,'Serra Preta',5),(655,'Serrinha',5),(656,'Serrolândia',5),(657,'Simões Filho',5),(658,'Sítio do Mato',5),(659,'Sítio do Quinto',5),(660,'Sobradinho',5),(661,'Souto Soares',5),(662,'Tabocas do Brejo Velho',5),(663,'Tanhaçu',5),(664,'Tanque Novo',5),(665,'Tanquinho',5),(666,'Taperoá',5),(667,'Tapiramutá',5),(668,'Teixeira de Freitas',5),(669,'Teodoro Sampaio',5),(670,'Teofilândia',5),(671,'Teolândia',5),(672,'Terra Nova',5),(673,'Tremedal',5),(674,'Tucano',5),(675,'Uauá',5),(676,'Ubaíra',5),(677,'Ubaitaba',5),(678,'Ubatã',5),(679,'Uibaí',5),(680,'Umburanas',5),(681,'Una',5),(682,'Urandi',5),(683,'Uruçuca',5),(684,'Utinga',5),(685,'Valença',5),(686,'Valente',5),(687,'Várzea da Roça',5),(688,'Várzea do Poço',5),(689,'Várzea Nova',5),(690,'Varzedo',5),(691,'Vera Cruz',5),(692,'Vereda',5),(693,'Vitória da Conquista',5),(694,'Wagner',5),(695,'Wanderley',5),(696,'Wenceslau Guimarães',5),(697,'Xique-Xique',5),(698,'Abaiara',6),(699,'Acarape',6),(700,'Acaraú',6),(701,'Acopiara',6),(702,'Aiuaba',6),(703,'Alcântaras',6),(704,'Altaneira',6),(705,'Alto Santo',6),(706,'Amontada',6),(707,'Antonina do Norte',6),(708,'Apuiarés',6),(709,'Aquiraz',6),(710,'Aracati',6),(711,'Aracoiaba',6),(712,'Ararendá',6),(713,'Araripe',6),(714,'Aratuba',6),(715,'Arneiroz',6),(716,'Assaré',6),(717,'Aurora',6),(718,'Baixio',6),(719,'Banabuiú',6),(720,'Barbalha',6),(721,'Barreira',6),(722,'Barro',6),(723,'Barroquinha',6),(724,'Baturité',6),(725,'Beberibe',6),(726,'Bela Cruz',6),(727,'Boa Viagem',6),(728,'Brejo Santo',6),(729,'Camocim',6),(730,'Campos Sales',6),(731,'Canindé',6),(732,'Capistrano',6),(733,'Caridade',6),(734,'Cariré',6),(735,'Caririaçu',6),(736,'Cariús',6),(737,'Carnaubal',6),(738,'Cascavel',6),(739,'Catarina',6),(740,'Catunda',6),(741,'Caucaia',6),(742,'Cedro',6),(743,'Chaval',6),(744,'Choró',6),(745,'Chorozinho',6),(746,'Coreaú',6),(747,'Crateús',6),(748,'Crato',6),(749,'Croatá',6),(750,'Cruz',6),(751,'Deputado Irapuan Pinheiro',6),(752,'Ererê',6),(753,'Eusébio',6),(754,'Farias Brito',6),(755,'Forquilha',6),(756,'Fortaleza',6),(757,'Fortim',6),(758,'Frecheirinha',6),(759,'General Sampaio',6),(760,'Graça',6),(761,'Granja',6),(762,'Granjeiro',6),(763,'Groaíras',6),(764,'Guaiúba',6),(765,'Guaraciaba do Norte',6),(766,'Guaramiranga',6),(767,'Hidrolândia',6),(768,'Horizonte',6),(769,'Ibaretama',6),(770,'Ibiapina',6),(771,'Ibicuitinga',6),(772,'Icapuí',6),(773,'Icó',6),(774,'Iguatu',6),(775,'Independência',6),(776,'Ipaporanga',6),(777,'Ipaumirim',6),(778,'Ipu',6),(779,'Ipueiras',6),(780,'Iracema',6),(781,'Irauçuba',6),(782,'Itaiçaba',6),(783,'Itaitinga',6),(784,'Itapagé',6),(785,'Itapipoca',6),(786,'Itapiúna',6),(787,'Itarema',6),(788,'Itatira',6),(789,'Jaguaretama',6),(790,'Jaguaribara',6),(791,'Jaguaribe',6),(792,'Jaguaruana',6),(793,'Jardim',6),(794,'Jati',6),(795,'Jijoca de Jericoacoara',6),(796,'Juazeiro do Norte',6),(797,'Jucás',6),(798,'Lavras da Mangabeira',6),(799,'Limoeiro do Norte',6),(800,'Madalena',6),(801,'Maracanaú',6),(802,'Maranguape',6),(803,'Marco',6),(804,'Martinópole',6),(805,'Massapê',6),(806,'Mauriti',6),(807,'Meruoca',6),(808,'Milagres',6),(809,'Milhã',6),(810,'Miraíma',6),(811,'Missão Velha',6),(812,'Mombaça',6),(813,'Monsenhor Tabosa',6),(814,'Morada Nova',6),(815,'Moraújo',6),(816,'Morrinhos',6),(817,'Mucambo',6),(818,'Mulungu',6),(819,'Nova Olinda',6),(820,'Nova Russas',6),(821,'Novo Oriente',6),(822,'Ocara',6),(823,'Orós',6),(824,'Pacajus',6),(825,'Pacatuba',6),(826,'Pacoti',6),(827,'Pacujá',6),(828,'Palhano',6),(829,'Palmácia',6),(830,'Paracuru',6),(831,'Paraipaba',6),(832,'Parambu',6),(833,'Paramoti',6),(834,'Pedra Branca',6),(835,'Penaforte',6),(836,'Pentecoste',6),(837,'Pereiro',6),(838,'Pindoretama',6),(839,'Piquet Carneiro',6),(840,'Pires Ferreira',6),(841,'Poranga',6),(842,'Porteiras',6),(843,'Potengi',6),(844,'Potiretama',6),(845,'Quiterianópolis',6),(846,'Quixadá',6),(847,'Quixelô',6),(848,'Quixeramobim',6),(849,'Quixeré',6),(850,'Redenção',6),(851,'Reriutaba',6),(852,'Russas',6),(853,'Saboeiro',6),(854,'Salitre',6),(855,'Santa Quitéria',6),(856,'Santana do Acaraú',6),(857,'Santana do Cariri',6),(858,'São Benedito',6),(859,'São Gonçalo do Amarante',6),(860,'São João do Jaguaribe',6),(861,'São Luís do Curu',6),(862,'Senador Pompeu',6),(863,'Senador Sá',6),(864,'Sobral',6),(865,'Solonópole',6),(866,'Tabuleiro do Norte',6),(867,'Tamboril',6),(868,'Tarrafas',6),(869,'Tauá',6),(870,'Tejuçuoca',6),(871,'Tianguá',6),(872,'Trairi',6),(873,'Tururu',6),(874,'Ubajara',6),(875,'Umari',6),(876,'Umirim',6),(877,'Uruburetama',6),(878,'Uruoca',6),(879,'Varjota',6),(880,'Várzea Alegre',6),(881,'Viçosa do Ceará',6),(882,'Brasília',7),(883,'Abadia de Goiás',9),(884,'Abadiânia',9),(885,'Acreúna',9),(886,'Adelândia',9),(887,'Água Fria de Goiás',9),(888,'Água Limpa',9),(889,'Águas Lindas de Goiás',9),(890,'Alexânia',9),(891,'Aloândia',9),(892,'Alto Horizonte',9),(893,'Alto Paraíso de Goiás',9),(894,'Alvorada do Norte',9),(895,'Amaralina',9),(896,'Americano do Brasil',9),(897,'Amorinópolis',9),(898,'Anápolis',9),(899,'Anhanguera',9),(900,'Anicuns',9),(901,'Aparecida de Goiânia',9),(902,'Aparecida do Rio Doce',9),(903,'Aporé',9),(904,'Araçu',9),(905,'Aragarças',9),(906,'Aragoiânia',9),(907,'Araguapaz',9),(908,'Arenópolis',9),(909,'Aruanã',9),(910,'Aurilândia',9),(911,'Avelinópolis',9),(912,'Baliza',9),(913,'Barro Alto',9),(914,'Bela Vista de Goiás',9),(915,'Bom Jardim de Goiás',9),(916,'Bom Jesus de Goiás',9),(917,'Bonfinópolis',9),(918,'Bonópolis',9),(919,'Brazabrantes',9),(920,'Britânia',9),(921,'Buriti Alegre',9),(922,'Buriti de Goiás',9),(923,'Buritinópolis',9),(924,'Cabeceiras',9),(925,'Cachoeira Alta',9),(926,'Cachoeira de Goiás',9),(927,'Cachoeira Dourada',9),(928,'Caçu',9),(929,'Caiapônia',9),(930,'Caldas Novas',9),(931,'Caldazinha',9),(932,'Campestre de Goiás',9),(933,'Campinaçu',9),(934,'Campinorte',9),(935,'Campo Alegre de Goiás',9),(936,'Campo Limpo de Goiás',9),(937,'Campos Belos',9),(938,'Campos Verdes',9),(939,'Carmo do Rio Verde',9),(940,'Castelândia',9),(941,'Catalão',9),(942,'Caturaí',9),(943,'Cavalcante',9),(944,'Ceres',9),(945,'Cezarina',9),(946,'Chapadão do Céu',9),(947,'Cidade Ocidental',9),(948,'Cocalzinho de Goiás',9),(949,'Colinas do Sul',9),(950,'Córrego do Ouro',9),(951,'Corumbá de Goiás',9),(952,'Corumbaíba',9),(953,'Cristalina',9),(954,'Cristianópolis',9),(955,'Crixás',9),(956,'Cromínia',9),(957,'Cumari',9),(958,'Damianópolis',9),(959,'Damolândia',9),(960,'Davinópolis',9),(961,'Diorama',9),(962,'Divinópolis de Goiás',9),(963,'Doverlândia',9),(964,'Edealina',9),(965,'Edéia',9),(966,'Estrela do Norte',9),(967,'Faina',9),(968,'Fazenda Nova',9),(969,'Firminópolis',9),(970,'Flores de Goiás',9),(971,'Formosa',9),(972,'Formoso',9),(973,'Gameleira de Goiás',9),(974,'Goianápolis',9),(975,'Goiandira',9),(976,'Goianésia',9),(977,'Goiânia',9),(978,'Goianira',9),(979,'Goiás',9),(980,'Goiatuba',9),(981,'Gouvelândia',9),(982,'Guapó',9),(983,'Guaraíta',9),(984,'Guarani de Goiás',9),(985,'Guarinos',9),(986,'Heitoraí',9),(987,'Hidrolândia',9),(988,'Hidrolina',9),(989,'Iaciara',9),(990,'Inaciolândia',9),(991,'Indiara',9),(992,'Inhumas',9),(993,'Ipameri',9),(994,'Ipiranga de Goiás',9),(995,'Iporá',9),(996,'Israelândia',9),(997,'Itaberaí',9),(998,'Itaguari',9),(999,'Itaguaru',9),(1000,'Itajá',9),(1001,'Itapaci',9),(1002,'Itapirapuã',9),(1003,'Itapuranga',9),(1004,'Itarumã',9),(1005,'Itauçu',9),(1006,'Itumbiara',9),(1007,'Ivolândia',9),(1008,'Jandaia',9),(1009,'Jaraguá',9),(1010,'Jataí',9),(1011,'Jaupaci',9),(1012,'Jesúpolis',9),(1013,'Joviânia',9),(1014,'Jussara',9),(1015,'Lagoa Santa',9),(1016,'Leopoldo de Bulhões',9),(1017,'Luziânia',9),(1018,'Mairipotaba',9),(1019,'Mambaí',9),(1020,'Mara Rosa',9),(1021,'Marzagão',9),(1022,'Matrinchã',9),(1023,'Maurilândia',9),(1024,'Mimoso de Goiás',9),(1025,'Minaçu',9),(1026,'Mineiros',9),(1027,'Moiporá',9),(1028,'Monte Alegre de Goiás',9),(1029,'Montes Claros de Goiás',9),(1030,'Montividiu',9),(1031,'Montividiu do Norte',9),(1032,'Morrinhos',9),(1033,'Morro Agudo de Goiás',9),(1034,'Mossâmedes',9),(1035,'Mozarlândia',9),(1036,'Mundo Novo',9),(1037,'Mutunópolis',9),(1038,'Nazário',9),(1039,'Nerópolis',9),(1040,'Niquelândia',9),(1041,'Nova América',9),(1042,'Nova Aurora',9),(1043,'Nova Crixás',9),(1044,'Nova Glória',9),(1045,'Nova Iguaçu de Goiás',9),(1046,'Nova Roma',9),(1047,'Nova Veneza',9),(1048,'Novo Brasil',9),(1049,'Novo Gama',9),(1050,'Novo Planalto',9),(1051,'Orizona',9),(1052,'Ouro Verde de Goiás',9),(1053,'Ouvidor',9),(1054,'Padre Bernardo',9),(1055,'Palestina de Goiás',9),(1056,'Palmeiras de Goiás',9),(1057,'Palmelo',9),(1058,'Palminópolis',9),(1059,'Panamá',9),(1060,'Paranaiguara',9),(1061,'Paraúna',9),(1062,'Perolândia',9),(1063,'Petrolina de Goiás',9),(1064,'Pilar de Goiás',9),(1065,'Piracanjuba',9),(1066,'Piranhas',9),(1067,'Pirenópolis',9),(1068,'Pires do Rio',9),(1069,'Planaltina',9),(1070,'Pontalina',9),(1071,'Porangatu',9),(1072,'Porteirão',9),(1073,'Portelândia',9),(1074,'Posse',9),(1075,'Professor Jamil',9),(1076,'Quirinópolis',9),(1077,'Rialma',9),(1078,'Rianápolis',9),(1079,'Rio Quente',9),(1080,'Rio Verde',9),(1081,'Rubiataba',9),(1082,'Sanclerlândia',9),(1083,'Santa Bárbara de Goiás',9),(1084,'Santa Cruz de Goiás',9),(1085,'Santa Fé de Goiás',9),(1086,'Santa Helena de Goiás',9),(1087,'Santa Isabel',9),(1088,'Santa Rita do Araguaia',9),(1089,'Santa Rita do Novo Destino',9),(1090,'Santa Rosa de Goiás',9),(1091,'Santa Tereza de Goiás',9),(1092,'Santa Terezinha de Goiás',9),(1093,'Santo Antônio da Barra',9),(1094,'Santo Antônio de Goiás',9),(1095,'Santo Antônio do Descoberto',9),(1096,'São Domingos',9),(1097,'São Francisco de Goiás',9),(1098,'São João d`Aliança',9),(1099,'São João da Paraúna',9),(1100,'São Luís de Montes Belos',9),(1101,'São Luíz do Norte',9),(1102,'São Miguel do Araguaia',9),(1103,'São Miguel do Passa Quatro',9),(1104,'São Patrício',9),(1105,'São Simão',9),(1106,'Senador Canedo',9),(1107,'Serranópolis',9),(1108,'Silvânia',9),(1109,'Simolândia',9),(1110,'Sítio d`Abadia',9),(1111,'Taquaral de Goiás',9),(1112,'Teresina de Goiás',9),(1113,'Terezópolis de Goiás',9),(1114,'Três Ranchos',9),(1115,'Trindade',9),(1116,'Trombas',9),(1117,'Turvânia',9),(1118,'Turvelândia',9),(1119,'Uirapuru',9),(1120,'Uruaçu',9),(1121,'Uruana',9),(1122,'Urutaí',9),(1123,'Valparaíso de Goiás',9),(1124,'Varjão',9),(1125,'Vianópolis',9),(1126,'Vicentinópolis',9),(1127,'Vila Boa',9),(1128,'Vila Propício',9),(1129,'Açailândia',10),(1130,'Afonso Cunha',10),(1131,'Água Doce do Maranhão',10),(1132,'Alcântara',10),(1133,'Aldeias Altas',10),(1134,'Altamira do Maranhão',10),(1135,'Alto Alegre do Maranhão',10),(1136,'Alto Alegre do Pindaré',10),(1137,'Alto Parnaíba',10),(1138,'Amapá do Maranhão',10),(1139,'Amarante do Maranhão',10),(1140,'Anajatuba',10),(1141,'Anapurus',10),(1142,'Apicum-Açu',10),(1143,'Araguanã',10),(1144,'Araioses',10),(1145,'Arame',10),(1146,'Arari',10),(1147,'Axixá',10),(1148,'Bacabal',10),(1149,'Bacabeira',10),(1150,'Bacuri',10),(1151,'Bacurituba',10),(1152,'Balsas',10),(1153,'Barão de Grajaú',10),(1154,'Barra do Corda',10),(1155,'Barreirinhas',10),(1156,'Bela Vista do Maranhão',10),(1157,'Belágua',10),(1158,'Benedito Leite',10),(1159,'Bequimão',10),(1160,'Bernardo do Mearim',10),(1161,'Boa Vista do Gurupi',10),(1162,'Bom Jardim',10),(1163,'Bom Jesus das Selvas',10),(1164,'Bom Lugar',10),(1165,'Brejo',10),(1166,'Brejo de Areia',10),(1167,'Buriti',10),(1168,'Buriti Bravo',10),(1169,'Buriticupu',10),(1170,'Buritirana',10),(1171,'Cachoeira Grande',10),(1172,'Cajapió',10),(1173,'Cajari',10),(1174,'Campestre do Maranhão',10),(1175,'Cândido Mendes',10),(1176,'Cantanhede',10),(1177,'Capinzal do Norte',10),(1178,'Carolina',10),(1179,'Carutapera',10),(1180,'Caxias',10),(1181,'Cedral',10),(1182,'Central do Maranhão',10),(1183,'Centro do Guilherme',10),(1184,'Centro Novo do Maranhão',10),(1185,'Chapadinha',10),(1186,'Cidelândia',10),(1187,'Codó',10),(1188,'Coelho Neto',10),(1189,'Colinas',10),(1190,'Conceição do Lago-Açu',10),(1191,'Coroatá',10),(1192,'Cururupu',10),(1193,'Davinópolis',10),(1194,'Dom Pedro',10),(1195,'Duque Bacelar',10),(1196,'Esperantinópolis',10),(1197,'Estreito',10),(1198,'Feira Nova do Maranhão',10),(1199,'Fernando Falcão',10),(1200,'Formosa da Serra Negra',10),(1201,'Fortaleza dos Nogueiras',10),(1202,'Fortuna',10),(1203,'Godofredo Viana',10),(1204,'Gonçalves Dias',10),(1205,'Governador Archer',10),(1206,'Governador Edison Lobão',10),(1207,'Governador Eugênio Barros',10),(1208,'Governador Luiz Rocha',10),(1209,'Governador Newton Bello',10),(1210,'Governador Nunes Freire',10),(1211,'Graça Aranha',10),(1212,'Grajaú',10),(1213,'Guimarães',10),(1214,'Humberto de Campos',10),(1215,'Icatu',10),(1216,'Igarapé do Meio',10),(1217,'Igarapé Grande',10),(1218,'Imperatriz',10),(1219,'Itaipava do Grajaú',10),(1220,'Itapecuru Mirim',10),(1221,'Itinga do Maranhão',10),(1222,'Jatobá',10),(1223,'Jenipapo dos Vieiras',10),(1224,'João Lisboa',10),(1225,'Joselândia',10),(1226,'Junco do Maranhão',10),(1227,'Lago da Pedra',10),(1228,'Lago do Junco',10),(1229,'Lago dos Rodrigues',10),(1230,'Lago Verde',10),(1231,'Lagoa do Mato',10),(1232,'Lagoa Grande do Maranhão',10),(1233,'Lajeado Novo',10),(1234,'Lima Campos',10),(1235,'Loreto',10),(1236,'Luís Domingues',10),(1237,'Magalhães de Almeida',10),(1238,'Maracaçumé',10),(1239,'Marajá do Sena',10),(1240,'Maranhãozinho',10),(1241,'Mata Roma',10),(1242,'Matinha',10),(1243,'Matões',10),(1244,'Matões do Norte',10),(1245,'Milagres do Maranhão',10),(1246,'Mirador',10),(1247,'Miranda do Norte',10),(1248,'Mirinzal',10),(1249,'Monção',10),(1250,'Montes Altos',10),(1251,'Morros',10),(1252,'Nina Rodrigues',10),(1253,'Nova Colinas',10),(1254,'Nova Iorque',10),(1255,'Nova Olinda do Maranhão',10),(1256,'Olho d`Água das Cunhãs',10),(1257,'Olinda Nova do Maranhão',10),(1258,'Paço do Lumiar',10),(1259,'Palmeirândia',10),(1260,'Paraibano',10),(1261,'Parnarama',10),(1262,'Passagem Franca',10),(1263,'Pastos Bons',10),(1264,'Paulino Neves',10),(1265,'Paulo Ramos',10),(1266,'Pedreiras',10),(1267,'Pedro do Rosário',10),(1268,'Penalva',10),(1269,'Peri Mirim',10),(1270,'Peritoró',10),(1271,'Pindaré-Mirim',10),(1272,'Pinheiro',10),(1273,'Pio XII',10),(1274,'Pirapemas',10),(1275,'Poção de Pedras',10),(1276,'Porto Franco',10),(1277,'Porto Rico do Maranhão',10),(1278,'Presidente Dutra',10),(1279,'Presidente Juscelino',10),(1280,'Presidente Médici',10),(1281,'Presidente Sarney',10),(1282,'Presidente Vargas',10),(1283,'Primeira Cruz',10),(1284,'Raposa',10),(1285,'Riachão',10),(1286,'Ribamar Fiquene',10),(1287,'Rosário',10),(1288,'Sambaíba',10),(1289,'Santa Filomena do Maranhão',10),(1290,'Santa Helena',10),(1291,'Santa Inês',10),(1292,'Santa Luzia',10),(1293,'Santa Luzia do Paruá',10),(1294,'Santa Quitéria do Maranhão',10),(1295,'Santa Rita',10),(1296,'Santana do Maranhão',10),(1297,'Santo Amaro do Maranhão',10),(1298,'Santo Antônio dos Lopes',10),(1299,'São Benedito do Rio Preto',10),(1300,'São Bento',10),(1301,'São Bernardo',10),(1302,'São Domingos do Azeitão',10),(1303,'São Domingos do Maranhão',10),(1304,'São Félix de Balsas',10),(1305,'São Francisco do Brejão',10),(1306,'São Francisco do Maranhão',10),(1307,'São João Batista',10),(1308,'São João do Carú',10),(1309,'São João do Paraíso',10),(1310,'São João do Soter',10),(1311,'São João dos Patos',10),(1312,'São José de Ribamar',10),(1313,'São José dos Basílios',10),(1314,'São Luís',10),(1315,'São Luís Gonzaga do Maranhão',10),(1316,'São Mateus do Maranhão',10),(1317,'São Pedro da Água Branca',10),(1318,'São Pedro dos Crentes',10),(1319,'São Raimundo das Mangabeiras',10),(1320,'São Raimundo do Doca Bezerra',10),(1321,'São Roberto',10),(1322,'São Vicente Ferrer',10),(1323,'Satubinha',10),(1324,'Senador Alexandre Costa',10),(1325,'Senador La Rocque',10),(1326,'Serrano do Maranhão',10),(1327,'Sítio Novo',10),(1328,'Sucupira do Norte',10),(1329,'Sucupira do Riachão',10),(1330,'Tasso Fragoso',10),(1331,'Timbiras',10),(1332,'Timon',10),(1333,'Trizidela do Vale',10),(1334,'Tufilândia',10),(1335,'Tuntum',10),(1336,'Turiaçu',10),(1337,'Turilândia',10),(1338,'Tutóia',10),(1339,'Urbano Santos',10),(1340,'Vargem Grande',10),(1341,'Viana',10),(1342,'Vila Nova dos Martírios',10),(1343,'Vitória do Mearim',10),(1344,'Vitorino Freire',10),(1345,'Zé Doca',10),(1346,'Acorizal',13),(1347,'Água Boa',13),(1348,'Alta Floresta',13),(1349,'Alto Araguaia',13),(1350,'Alto Boa Vista',13),(1351,'Alto Garças',13),(1352,'Alto Paraguai',13),(1353,'Alto Taquari',13),(1354,'Apiacás',13),(1355,'Araguaiana',13),(1356,'Araguainha',13),(1357,'Araputanga',13),(1358,'Arenápolis',13),(1359,'Aripuanã',13),(1360,'Barão de Melgaço',13),(1361,'Barra do Bugres',13),(1362,'Barra do Garças',13),(1363,'Bom Jesus do Araguaia',13),(1364,'Brasnorte',13),(1365,'Cáceres',13),(1366,'Campinápolis',13),(1367,'Campo Novo do Parecis',13),(1368,'Campo Verde',13),(1369,'Campos de Júlio',13),(1370,'Canabrava do Norte',13),(1371,'Canarana',13),(1372,'Carlinda',13),(1373,'Castanheira',13),(1374,'Chapada dos Guimarães',13),(1375,'Cláudia',13),(1376,'Cocalinho',13),(1377,'Colíder',13),(1378,'Colniza',13),(1379,'Comodoro',13),(1380,'Confresa',13),(1381,'Conquista d`Oeste',13),(1382,'Cotriguaçu',13),(1383,'Cuiabá',13),(1384,'Curvelândia',13),(1385,'Curvelândia',13),(1386,'Denise',13),(1387,'Diamantino',13),(1388,'Dom Aquino',13),(1389,'Feliz Natal',13),(1390,'Figueirópolis d`Oeste',13),(1391,'Gaúcha do Norte',13),(1392,'General Carneiro',13),(1393,'Glória d`Oeste',13),(1394,'Guarantã do Norte',13),(1395,'Guiratinga',13),(1396,'Indiavaí',13),(1397,'Ipiranga do Norte',13),(1398,'Itanhangá',13),(1399,'Itaúba',13),(1400,'Itiquira',13),(1401,'Jaciara',13),(1402,'Jangada',13),(1403,'Jauru',13),(1404,'Juara',13),(1405,'Juína',13),(1406,'Juruena',13),(1407,'Juscimeira',13),(1408,'Lambari d`Oeste',13),(1409,'Lucas do Rio Verde',13),(1410,'Luciára',13),(1411,'Marcelândia',13),(1412,'Matupá',13),(1413,'Mirassol d`Oeste',13),(1414,'Nobres',13),(1415,'Nortelândia',13),(1416,'Nossa Senhora do Livramento',13),(1417,'Nova Bandeirantes',13),(1418,'Nova Brasilândia',13),(1419,'Nova Canaã do Norte',13),(1420,'Nova Guarita',13),(1421,'Nova Lacerda',13),(1422,'Nova Marilândia',13),(1423,'Nova Maringá',13),(1424,'Nova Monte verde',13),(1425,'Nova Mutum',13),(1426,'Nova Olímpia',13),(1427,'Nova Santa Helena',13),(1428,'Nova Ubiratã',13),(1429,'Nova Xavantina',13),(1430,'Novo Horizonte do Norte',13),(1431,'Novo Mundo',13),(1432,'Novo Santo Antônio',13),(1433,'Novo São Joaquim',13),(1434,'Paranaíta',13),(1435,'Paranatinga',13),(1436,'Pedra Preta',13),(1437,'Peixoto de Azevedo',13),(1438,'Planalto da Serra',13),(1439,'Poconé',13),(1440,'Pontal do Araguaia',13),(1441,'Ponte Branca',13),(1442,'Pontes e Lacerda',13),(1443,'Porto Alegre do Norte',13),(1444,'Porto dos Gaúchos',13),(1445,'Porto Esperidião',13),(1446,'Porto Estrela',13),(1447,'Poxoréo',13),(1448,'Primavera do Leste',13),(1449,'Querência',13),(1450,'Reserva do Cabaçal',13),(1451,'Ribeirão Cascalheira',13),(1452,'Ribeirãozinho',13),(1453,'Rio Branco',13),(1454,'Rondolândia',13),(1455,'Rondonópolis',13),(1456,'Rosário Oeste',13),(1457,'Salto do Céu',13),(1458,'Santa Carmem',13),(1459,'Santa Cruz do Xingu',13),(1460,'Santa Rita do Trivelato',13),(1461,'Santa Terezinha',13),(1462,'Santo Afonso',13),(1463,'Santo Antônio do Leste',13),(1464,'Santo Antônio do Leverger',13),(1465,'São Félix do Araguaia',13),(1466,'São José do Povo',13),(1467,'São José do Rio Claro',13),(1468,'São José do Xingu',13),(1469,'São José dos Quatro Marcos',13),(1470,'São Pedro da Cipa',13),(1471,'Sapezal',13),(1472,'Serra Nova Dourada',13),(1473,'Sinop',13),(1474,'Sorriso',13),(1475,'Tabaporã',13),(1476,'Tangará da Serra',13),(1477,'Tapurah',13),(1478,'Terra Nova do Norte',13),(1479,'Tesouro',13),(1480,'Torixoréu',13),(1481,'União do Sul',13),(1482,'Vale de São Domingos',13),(1483,'Várzea Grande',13),(1484,'Vera',13),(1485,'Vila Bela da Santíssima Trindade',13),(1486,'Vila Rica',13),(1487,'Água Clara',12),(1488,'Alcinópolis',12),(1489,'Amambaí',12),(1490,'Anastácio',12),(1491,'Anaurilândia',12),(1492,'Angélica',12),(1493,'Antônio João',12),(1494,'Aparecida do Taboado',12),(1495,'Aquidauana',12),(1496,'Aral Moreira',12),(1497,'Bandeirantes',12),(1498,'Bataguassu',12),(1499,'Bataiporã',12),(1500,'Bela Vista',12),(1501,'Bodoquena',12),(1502,'Bonito',12),(1503,'Brasilândia',12),(1504,'Caarapó',12),(1505,'Camapuã',12),(1506,'Campo Grande',12),(1507,'Caracol',12),(1508,'Cassilândia',12),(1509,'Chapadão do Sul',12),(1510,'Corguinho',12),(1511,'Coronel Sapucaia',12),(1512,'Corumbá',12),(1513,'Costa Rica',12),(1514,'Coxim',12),(1515,'Deodápolis',12),(1516,'Dois Irmãos do Buriti',12),(1517,'Douradina',12),(1518,'Dourados',12),(1519,'Eldorado',12),(1520,'Fátima do Sul',12),(1521,'Figueirão',12),(1522,'Glória de Dourados',12),(1523,'Guia Lopes da Laguna',12),(1524,'Iguatemi',12),(1525,'Inocência',12),(1526,'Itaporã',12),(1527,'Itaquiraí',12),(1528,'Ivinhema',12),(1529,'Japorã',12),(1530,'Jaraguari',12),(1531,'Jardim',12),(1532,'Jateí',12),(1533,'Juti',12),(1534,'Ladário',12),(1535,'Laguna Carapã',12),(1536,'Maracaju',12),(1537,'Miranda',12),(1538,'Mundo Novo',12),(1539,'Naviraí',12),(1540,'Nioaque',12),(1541,'Nova Alvorada do Sul',12),(1542,'Nova Andradina',12),(1543,'Novo Horizonte do Sul',12),(1544,'Paranaíba',12),(1545,'Paranhos',12),(1546,'Pedro Gomes',12),(1547,'Ponta Porã',12),(1548,'Porto Murtinho',12),(1549,'Ribas do Rio Pardo',12),(1550,'Rio Brilhante',12),(1551,'Rio Negro',12),(1552,'Rio Verde de Mato Grosso',12),(1553,'Rochedo',12),(1554,'Santa Rita do Pardo',12),(1555,'São Gabriel do Oeste',12),(1556,'Selvíria',12),(1557,'Sete Quedas',12),(1558,'Sidrolândia',12),(1559,'Sonora',12),(1560,'Tacuru',12),(1561,'Taquarussu',12),(1562,'Terenos',12),(1563,'Três Lagoas',12),(1564,'Vicentina',12),(1565,'Abadia dos Dourados',11),(1566,'Abaeté',11),(1567,'Abre Campo',11),(1568,'Acaiaca',11),(1569,'Açucena',11),(1570,'Água Boa',11),(1571,'Água Comprida',11),(1572,'Aguanil',11),(1573,'Águas Formosas',11),(1574,'Águas Vermelhas',11),(1575,'Aimorés',11),(1576,'Aiuruoca',11),(1577,'Alagoa',11),(1578,'Albertina',11),(1579,'Além Paraíba',11),(1580,'Alfenas',11),(1581,'Alfredo Vasconcelos',11),(1582,'Almenara',11),(1583,'Alpercata',11),(1584,'Alpinópolis',11),(1585,'Alterosa',11),(1586,'Alto Caparaó',11),(1587,'Alto Jequitibá',11),(1588,'Alto Rio Doce',11),(1589,'Alvarenga',11),(1590,'Alvinópolis',11),(1591,'Alvorada de Minas',11),(1592,'Amparo do Serra',11),(1593,'Andradas',11),(1594,'Andrelândia',11),(1595,'Angelândia',11),(1596,'Antônio Carlos',11),(1597,'Antônio Dias',11),(1598,'Antônio Prado de Minas',11),(1599,'Araçaí',11),(1600,'Aracitaba',11),(1601,'Araçuaí',11),(1602,'Araguari',11),(1603,'Arantina',11),(1604,'Araponga',11),(1605,'Araporã',11),(1606,'Arapuá',11),(1607,'Araújos',11),(1608,'Araxá',11),(1609,'Arceburgo',11),(1610,'Arcos',11),(1611,'Areado',11),(1612,'Argirita',11),(1613,'Aricanduva',11),(1614,'Arinos',11),(1615,'Astolfo Dutra',11),(1616,'Ataléia',11),(1617,'Augusto de Lima',11),(1618,'Baependi',11),(1619,'Baldim',11),(1620,'Bambuí',11),(1621,'Bandeira',11),(1622,'Bandeira do Sul',11),(1623,'Barão de Cocais',11),(1624,'Barão de Monte Alto',11),(1625,'Barbacena',11),(1626,'Barra Longa',11),(1627,'Barroso',11),(1628,'Bela Vista de Minas',11),(1629,'Belmiro Braga',11),(1630,'Belo Horizonte',11),(1631,'Belo Oriente',11),(1632,'Belo Vale',11),(1633,'Berilo',11),(1634,'Berizal',11),(1635,'Bertópolis',11),(1636,'Betim',11),(1637,'Bias Fortes',11),(1638,'Bicas',11),(1639,'Biquinhas',11),(1640,'Boa Esperança',11),(1641,'Bocaina de Minas',11),(1642,'Bocaiúva',11),(1643,'Bom Despacho',11),(1644,'Bom Jardim de Minas',11),(1645,'Bom Jesus da Penha',11),(1646,'Bom Jesus do Amparo',11),(1647,'Bom Jesus do Galho',11),(1648,'Bom Repouso',11),(1649,'Bom Sucesso',11),(1650,'Bonfim',11),(1651,'Bonfinópolis de Minas',11),(1652,'Bonito de Minas',11),(1653,'Borda da Mata',11),(1654,'Botelhos',11),(1655,'Botumirim',11),(1656,'Brás Pires',11),(1657,'Brasilândia de Minas',11),(1658,'Brasília de Minas',11),(1659,'Brasópolis',11),(1660,'Braúnas',11),(1661,'Brumadinho',11),(1662,'Bueno Brandão',11),(1663,'Buenópolis',11),(1664,'Bugre',11),(1665,'Buritis',11),(1666,'Buritizeiro',11),(1667,'Cabeceira Grande',11),(1668,'Cabo Verde',11),(1669,'Cachoeira da Prata',11),(1670,'Cachoeira de Minas',11),(1671,'Cachoeira de Pajeú',11),(1672,'Cachoeira Dourada',11),(1673,'Caetanópolis',11),(1674,'Caeté',11),(1675,'Caiana',11),(1676,'Cajuri',11),(1677,'Caldas',11),(1678,'Camacho',11),(1679,'Camanducaia',11),(1680,'Cambuí',11),(1681,'Cambuquira',11),(1682,'Campanário',11),(1683,'Campanha',11),(1684,'Campestre',11),(1685,'Campina Verde',11),(1686,'Campo Azul',11),(1687,'Campo Belo',11),(1688,'Campo do Meio',11),(1689,'Campo Florido',11),(1690,'Campos Altos',11),(1691,'Campos Gerais',11),(1692,'Cana Verde',11),(1693,'Canaã',11),(1694,'Canápolis',11),(1695,'Candeias',11),(1696,'Cantagalo',11),(1697,'Caparaó',11),(1698,'Capela Nova',11),(1699,'Capelinha',11),(1700,'Capetinga',11),(1701,'Capim Branco',11),(1702,'Capinópolis',11),(1703,'Capitão Andrade',11),(1704,'Capitão Enéas',11),(1705,'Capitólio',11),(1706,'Caputira',11),(1707,'Caraí',11),(1708,'Caranaíba',11),(1709,'Carandaí',11),(1710,'Carangola',11),(1711,'Caratinga',11),(1712,'Carbonita',11),(1713,'Careaçu',11),(1714,'Carlos Chagas',11),(1715,'Carmésia',11),(1716,'Carmo da Cachoeira',11),(1717,'Carmo da Mata',11),(1718,'Carmo de Minas',11),(1719,'Carmo do Cajuru',11),(1720,'Carmo do Paranaíba',11),(1721,'Carmo do Rio Claro',11),(1722,'Carmópolis de Minas',11),(1723,'Carneirinho',11),(1724,'Carrancas',11),(1725,'Carvalhópolis',11),(1726,'Carvalhos',11),(1727,'Casa Grande',11),(1728,'Cascalho Rico',11),(1729,'Cássia',11),(1730,'Cataguases',11),(1731,'Catas Altas',11),(1732,'Catas Altas da Noruega',11),(1733,'Catuji',11),(1734,'Catuti',11),(1735,'Caxambu',11),(1736,'Cedro do Abaeté',11),(1737,'Central de Minas',11),(1738,'Centralina',11),(1739,'Chácara',11),(1740,'Chalé',11),(1741,'Chapada do Norte',11),(1742,'Chapada Gaúcha',11),(1743,'Chiador',11),(1744,'Cipotânea',11),(1745,'Claraval',11),(1746,'Claro dos Poções',11),(1747,'Cláudio',11),(1748,'Coimbra',11),(1749,'Coluna',11),(1750,'Comendador Gomes',11),(1751,'Comercinho',11),(1752,'Conceição da Aparecida',11),(1753,'Conceição da Barra de Minas',11),(1754,'Conceição das Alagoas',11),(1755,'Conceição das Pedras',11),(1756,'Conceição de Ipanema',11),(1757,'Conceição do Mato Dentro',11),(1758,'Conceição do Pará',11),(1759,'Conceição do Rio Verde',11),(1760,'Conceição dos Ouros',11),(1761,'Cônego Marinho',11),(1762,'Confins',11),(1763,'Congonhal',11),(1764,'Congonhas',11),(1765,'Congonhas do Norte',11),(1766,'Conquista',11),(1767,'Conselheiro Lafaiete',11),(1768,'Conselheiro Pena',11),(1769,'Consolação',11),(1770,'Contagem',11),(1771,'Coqueiral',11),(1772,'Coração de Jesus',11),(1773,'Cordisburgo',11),(1774,'Cordislândia',11),(1775,'Corinto',11),(1776,'Coroaci',11),(1777,'Coromandel',11),(1778,'Coronel Fabriciano',11),(1779,'Coronel Murta',11),(1780,'Coronel Pacheco',11),(1781,'Coronel Xavier Chaves',11),(1782,'Córrego Danta',11),(1783,'Córrego do Bom Jesus',11),(1784,'Córrego Fundo',11),(1785,'Córrego Novo',11),(1786,'Couto de Magalhães de Minas',11),(1787,'Crisólita',11),(1788,'Cristais',11),(1789,'Cristália',11),(1790,'Cristiano Otoni',11),(1791,'Cristina',11),(1792,'Crucilândia',11),(1793,'Cruzeiro da Fortaleza',11),(1794,'Cruzília',11),(1795,'Cuparaque',11),(1796,'Curral de Dentro',11),(1797,'Curvelo',11),(1798,'Datas',11),(1799,'Delfim Moreira',11),(1800,'Delfinópolis',11),(1801,'Delta',11),(1802,'Descoberto',11),(1803,'Desterro de Entre Rios',11),(1804,'Desterro do Melo',11),(1805,'Diamantina',11),(1806,'Diogo de Vasconcelos',11),(1807,'Dionísio',11),(1808,'Divinésia',11),(1809,'Divino',11),(1810,'Divino das Laranjeiras',11),(1811,'Divinolândia de Minas',11),(1812,'Divinópolis',11),(1813,'Divisa Alegre',11),(1814,'Divisa Nova',11),(1815,'Divisópolis',11),(1816,'Dom Bosco',11),(1817,'Dom Cavati',11),(1818,'Dom Joaquim',11),(1819,'Dom Silvério',11),(1820,'Dom Viçoso',11),(1821,'Dona Eusébia',11),(1822,'Dores de Campos',11),(1823,'Dores de Guanhães',11),(1824,'Dores do Indaiá',11),(1825,'Dores do Turvo',11),(1826,'Doresópolis',11),(1827,'Douradoquara',11),(1828,'Durandé',11),(1829,'Elói Mendes',11),(1830,'Engenheiro Caldas',11),(1831,'Engenheiro Navarro',11),(1832,'Entre Folhas',11),(1833,'Entre Rios de Minas',11),(1834,'Ervália',11),(1835,'Esmeraldas',11),(1836,'Espera Feliz',11),(1837,'Espinosa',11),(1838,'Espírito Santo do Dourado',11),(1839,'Estiva',11),(1840,'Estrela Dalva',11),(1841,'Estrela do Indaiá',11),(1842,'Estrela do Sul',11),(1843,'Eugenópolis',11),(1844,'Ewbank da Câmara',11),(1845,'Extrema',11),(1846,'Fama',11),(1847,'Faria Lemos',11),(1848,'Felício dos Santos',11),(1849,'Felisburgo',11),(1850,'Felixlândia',11),(1851,'Fernandes Tourinho',11),(1852,'Ferros',11),(1853,'Fervedouro',11),(1854,'Florestal',11),(1855,'Formiga',11),(1856,'Formoso',11),(1857,'Fortaleza de Minas',11),(1858,'Fortuna de Minas',11),(1859,'Francisco Badaró',11),(1860,'Francisco Dumont',11),(1861,'Francisco Sá',11),(1862,'Franciscópolis',11),(1863,'Frei Gaspar',11),(1864,'Frei Inocêncio',11),(1865,'Frei Lagonegro',11),(1866,'Fronteira',11),(1867,'Fronteira dos Vales',11),(1868,'Fruta de Leite',11),(1869,'Frutal',11),(1870,'Funilândia',11),(1871,'Galiléia',11),(1872,'Gameleiras',11),(1873,'Glaucilândia',11),(1874,'Goiabeira',11),(1875,'Goianá',11),(1876,'Gonçalves',11),(1877,'Gonzaga',11),(1878,'Gouveia',11),(1879,'Governador Valadares',11),(1880,'Grão Mogol',11),(1881,'Grupiara',11),(1882,'Guanhães',11),(1883,'Guapé',11),(1884,'Guaraciaba',11),(1885,'Guaraciama',11),(1886,'Guaranésia',11),(1887,'Guarani',11),(1888,'Guarará',11),(1889,'Guarda-Mor',11),(1890,'Guaxupé',11),(1891,'Guidoval',11),(1892,'Guimarânia',11),(1893,'Guiricema',11),(1894,'Gurinhatã',11),(1895,'Heliodora',11),(1896,'Iapu',11),(1897,'Ibertioga',11),(1898,'Ibiá',11),(1899,'Ibiaí',11),(1900,'Ibiracatu',11),(1901,'Ibiraci',11),(1902,'Ibirité',11),(1903,'Ibitiúra de Minas',11),(1904,'Ibituruna',11),(1905,'Icaraí de Minas',11),(1906,'Igarapé',11),(1907,'Igaratinga',11),(1908,'Iguatama',11),(1909,'Ijaci',11),(1910,'Ilicínea',11),(1911,'Imbé de Minas',11),(1912,'Inconfidentes',11),(1913,'Indaiabira',11),(1914,'Indianópolis',11),(1915,'Ingaí',11),(1916,'Inhapim',11),(1917,'Inhaúma',11),(1918,'Inimutaba',11),(1919,'Ipaba',11),(1920,'Ipanema',11),(1921,'Ipatinga',11),(1922,'Ipiaçu',11),(1923,'Ipuiúna',11),(1924,'Iraí de Minas',11),(1925,'Itabira',11),(1926,'Itabirinha de Mantena',11),(1927,'Itabirito',11),(1928,'Itacambira',11),(1929,'Itacarambi',11),(1930,'Itaguara',11),(1931,'Itaipé',11),(1932,'Itajubá',11),(1933,'Itamarandiba',11),(1934,'Itamarati de Minas',11),(1935,'Itambacuri',11),(1936,'Itambé do Mato Dentro',11),(1937,'Itamogi',11),(1938,'Itamonte',11),(1939,'Itanhandu',11),(1940,'Itanhomi',11),(1941,'Itaobim',11),(1942,'Itapagipe',11),(1943,'Itapecerica',11),(1944,'Itapeva',11),(1945,'Itatiaiuçu',11),(1946,'Itaú de Minas',11),(1947,'Itaúna',11),(1948,'Itaverava',11),(1949,'Itinga',11),(1950,'Itueta',11),(1951,'Ituiutaba',11),(1952,'Itumirim',11),(1953,'Iturama',11),(1954,'Itutinga',11),(1955,'Jaboticatubas',11),(1956,'Jacinto',11),(1957,'Jacuí',11),(1958,'Jacutinga',11),(1959,'Jaguaraçu',11),(1960,'Jaíba',11),(1961,'Jampruca',11),(1962,'Janaúba',11),(1963,'Januária',11),(1964,'Japaraíba',11),(1965,'Japonvar',11),(1966,'Jeceaba',11),(1967,'Jenipapo de Minas',11),(1968,'Jequeri',11),(1969,'Jequitaí',11),(1970,'Jequitibá',11),(1971,'Jequitinhonha',11),(1972,'Jesuânia',11),(1973,'Joaíma',11),(1974,'Joanésia',11),(1975,'João Monlevade',11),(1976,'João Pinheiro',11),(1977,'Joaquim Felício',11),(1978,'Jordânia',11),(1979,'José Gonçalves de Minas',11),(1980,'José Raydan',11),(1981,'Josenópolis',11),(1982,'Juatuba',11),(1983,'Juiz de Fora',11),(1984,'Juramento',11),(1985,'Juruaia',11),(1986,'Juvenília',11),(1987,'Ladainha',11),(1988,'Lagamar',11),(1989,'Lagoa da Prata',11),(1990,'Lagoa dos Patos',11),(1991,'Lagoa Dourada',11),(1992,'Lagoa Formosa',11),(1993,'Lagoa Grande',11),(1994,'Lagoa Santa',11),(1995,'Lajinha',11),(1996,'Lambari',11),(1997,'Lamim',11),(1998,'Laranjal',11),(1999,'Lassance',11),(2000,'Lavras',11),(2001,'Leandro Ferreira',11),(2002,'Leme do Prado',11),(2003,'Leopoldina',11),(2004,'Liberdade',11),(2005,'Lima Duarte',11),(2006,'Limeira do Oeste',11),(2007,'Lontra',11),(2008,'Luisburgo',11),(2009,'Luislândia',11),(2010,'Luminárias',11),(2011,'Luz',11),(2012,'Machacalis',11),(2013,'Machado',11),(2014,'Madre de Deus de Minas',11),(2015,'Malacacheta',11),(2016,'Mamonas',11),(2017,'Manga',11),(2018,'Manhuaçu',11),(2019,'Manhumirim',11),(2020,'Mantena',11),(2021,'Mar de Espanha',11),(2022,'Maravilhas',11),(2023,'Maria da Fé',11),(2024,'Mariana',11),(2025,'Marilac',11),(2026,'Mário Campos',11),(2027,'Maripá de Minas',11),(2028,'Marliéria',11),(2029,'Marmelópolis',11),(2030,'Martinho Campos',11),(2031,'Martins Soares',11),(2032,'Mata Verde',11),(2033,'Materlândia',11),(2034,'Mateus Leme',11),(2035,'Mathias Lobato',11),(2036,'Matias Barbosa',11),(2037,'Matias Cardoso',11),(2038,'Matipó',11),(2039,'Mato Verde',11),(2040,'Matozinhos',11),(2041,'Matutina',11),(2042,'Medeiros',11),(2043,'Medina',11),(2044,'Mendes Pimentel',11),(2045,'Mercês',11),(2046,'Mesquita',11),(2047,'Minas Novas',11),(2048,'Minduri',11),(2049,'Mirabela',11),(2050,'Miradouro',11),(2051,'Miraí',11),(2052,'Miravânia',11),(2053,'Moeda',11),(2054,'Moema',11),(2055,'Monjolos',11),(2056,'Monsenhor Paulo',11),(2057,'Montalvânia',11),(2058,'Monte Alegre de Minas',11),(2059,'Monte Azul',11),(2060,'Monte Belo',11),(2061,'Monte Carmelo',11),(2062,'Monte Formoso',11),(2063,'Monte Santo de Minas',11),(2064,'Monte Sião',11),(2065,'Montes Claros',11),(2066,'Montezuma',11),(2067,'Morada Nova de Minas',11);
/*!40000 ALTER TABLE `municipio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissao`
--

DROP TABLE IF EXISTS `permissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissao` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  `nome` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kgx7ng07rgem07n056nqc7i1q` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissao`
--

LOCK TABLES `permissao` WRITE;
/*!40000 ALTER TABLE `permissao` DISABLE KEYS */;
INSERT INTO `permissao` VALUES (1,'Administradores do Sistema','ADMINISTRADOR'),(2,'Usuários do Sistema, aqueles que são restaurantes','USUARIO'),(3,'Clientes do Sistema, aqueles que fazem pedidos para os Restaurantes','CLIENTE');
/*!40000 ALTER TABLE `permissao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `valor` decimal(19,2) NOT NULL,
  `restaurante_id` bigint DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hdot1xprktyi4sf2onvllkmkd` (`nome`),
  KEY `FKb9jhjyghjcn25guim7q4pt8qx` (`restaurante_id`),
  CONSTRAINT `FKb9jhjyghjcn25guim7q4pt8qx` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,_binary '','Porco com molho agridoce',78.90,1,'Deliciosa carne suína ao molho especial'),(2,_binary '','Camarão tailandês',110.00,1,'16 camarões grandes ao molho picante'),(3,_binary '','Salada picante com carne grelhada',87.20,2,'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha'),(4,_binary '','Garlic Naan',21.00,3,'Pão tradicional indiano com cobertura de alho'),(5,_binary '','Murg Curry',43.00,3,'Cubos de frango preparados com molho curry e especiarias'),(6,_binary '','Bife Ancho',79.00,4,'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé'),(7,_binary '','T-Bone',89.00,4,'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon'),(8,_binary '','Sanduíche X-Tudo',19.00,5,'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese'),(9,_binary '','Espetinho de Cupim',8.00,6,'Acompanha farinha, mandioca e vinagrete');
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurante`
--

DROP TABLE IF EXISTS `restaurante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurante` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `endereco_bairro` varchar(255) DEFAULT NULL,
  `endereco_cep` int DEFAULT NULL,
  `endereco_complemento` varchar(255) DEFAULT NULL,
  `endereco_logradouro` varchar(255) DEFAULT NULL,
  `endereco_numero` varchar(255) DEFAULT NULL,
  `nome` varchar(60) NOT NULL,
  `taxa_entrega` decimal(19,2) NOT NULL,
  `cozinha_id` bigint NOT NULL,
  `municipio_id` int DEFAULT NULL,
  `data_cadastro` datetime DEFAULT NULL,
  `data_ultima_atualizacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK76grk4roudh659skcgbnanthi` (`cozinha_id`),
  KEY `FK469uc8uun9vl9tg6swiifh0l2` (`municipio_id`),
  CONSTRAINT `FK469uc8uun9vl9tg6swiifh0l2` FOREIGN KEY (`municipio_id`) REFERENCES `municipio` (`id`),
  CONSTRAINT `FK76grk4roudh659skcgbnanthi` FOREIGN KEY (`cozinha_id`) REFERENCES `cozinha` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurante`
--

LOCK TABLES `restaurante` WRITE;
/*!40000 ALTER TABLE `restaurante` DISABLE KEYS */;
INSERT INTO `restaurante` VALUES (1,'Campinas',88101365,'102A','Rua Dom Pedro II','841','Thai Gourmet',0.00,1,189,'2022-05-31 21:02:16','2022-05-31 21:02:16'),(2,'Campinas',88101365,'102A','Rua Dom Pedro II','841','Thai Delivery',9.50,1,1312,'2022-05-31 21:02:16','2022-05-31 21:02:16'),(3,'Campinas',88101365,'102A','Rua Dom Pedro II','841','Tuk Tuk Comida Indiana',15.00,2,1469,'2022-05-31 21:02:16','2022-05-31 21:02:16'),(4,'Campinas',88101365,'102A','Rua Dom Pedro II','841','Comida Brasileira',100.00,3,45,'2022-05-31 21:02:16','2022-05-31 21:02:16'),(5,'Campinas',88101365,'102A','Rua Dom Pedro II','841','Comida Portuguesa',9.50,4,1201,'2022-05-31 21:02:16','2022-05-31 21:02:16'),(6,'Campinas',88101365,'102A','Rua Dom Pedro II','841','Comida  Italiana',0.00,5,2048,'2022-05-31 21:02:16','2022-05-31 21:02:16'),(7,'Campinas',88101365,'102A','Rua Dom Pedro II','841','Comida Alemã',10.00,6,1001,'2022-05-31 21:02:16','2022-05-31 21:02:16'),(8,'Campinas',88101365,'102A','Rua Dom Pedro II','841','Comida Francesa',9.50,7,575,'2022-05-31 21:02:16','2022-05-31 21:02:16'),(12,'Campinas',88101365,'102A','Rua Dom Pedro II','841','Comida da Vovó',0.00,8,12,'2022-05-31 18:03:09','2022-05-31 18:03:09'),(20,'Campinas',88101365,'102A','Rua Dom Pedro II','841','Restaurante Cozinha Francesa',11.99,5,67,'2022-05-31 21:02:16','2022-05-31 21:02:16'),(21,'Serraria',88102365,'501A','Rua Paulino Pedro Hermes','172','Restaurante Cozinha da Geni',7.99,5,44,'2022-05-31 21:02:16','2022-05-31 21:02:16'),(22,'Serraria',88102365,'501A','Rua Paulino Pedro Hermes','172','Restaurante vô Banga',7.99,5,2001,'2022-05-31 18:33:01','2022-05-31 18:50:02'),(23,NULL,NULL,NULL,NULL,NULL,'Restaurante vô Banga',7.99,5,2000,'2022-05-31 18:50:34','2022-05-31 18:55:47'),(24,NULL,NULL,NULL,NULL,NULL,'Restaurante vô Banga',7.99,5,2000,'2022-05-31 18:56:42','2022-05-31 19:03:42');
/*!40000 ALTER TABLE `restaurante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurante_tipo_pagamento`
--

DROP TABLE IF EXISTS `restaurante_tipo_pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurante_tipo_pagamento` (
  `restaurante_id` bigint NOT NULL,
  `tipo_pagamento_id` bigint NOT NULL,
  KEY `FKhtoijs4bjuw9fo6wksmi4pyu2` (`tipo_pagamento_id`),
  KEY `FK5ewi04k9cbll90g3new30dia1` (`restaurante_id`),
  CONSTRAINT `FK5ewi04k9cbll90g3new30dia1` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`),
  CONSTRAINT `FKhtoijs4bjuw9fo6wksmi4pyu2` FOREIGN KEY (`tipo_pagamento_id`) REFERENCES `tipo_pagamento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurante_tipo_pagamento`
--

LOCK TABLES `restaurante_tipo_pagamento` WRITE;
/*!40000 ALTER TABLE `restaurante_tipo_pagamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `restaurante_tipo_pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_pagamento`
--

DROP TABLE IF EXISTS `tipo_pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_pagamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_pagamento`
--

LOCK TABLES `tipo_pagamento` WRITE;
/*!40000 ALTER TABLE `tipo_pagamento` DISABLE KEYS */;
INSERT INTO `tipo_pagamento` VALUES (1,'DINHEIRO'),(2,'CARTÃO CRÉDITO'),(3,'CARTÃO DÉBITO'),(4,'PIX'),(5,'BOLETO'),(6,'VOUCHER'),(7,'CUPOM DESCONTO');
/*!40000 ALTER TABLE `tipo_pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_cadastro` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_grupos`
--

DROP TABLE IF EXISTS `usuario_grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_grupos` (
  `usuario_id` bigint NOT NULL,
  `grupo_id` bigint NOT NULL,
  PRIMARY KEY (`usuario_id`,`grupo_id`),
  KEY `FKn1t1y2qk5hn6vdh0d4s4wsu67` (`grupo_id`),
  CONSTRAINT `FK158r9y55ufwykh675ddt8kb43` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKn1t1y2qk5hn6vdh0d4s4wsu67` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_grupos`
--

LOCK TABLES `usuario_grupos` WRITE;
/*!40000 ALTER TABLE `usuario_grupos` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_grupos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-01 16:14:05