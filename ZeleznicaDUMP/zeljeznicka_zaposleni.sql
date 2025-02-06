-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: zeljeznicka
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `zaposleni`
--

DROP TABLE IF EXISTS `zaposleni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zaposleni` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jmbg` varchar(13) NOT NULL,
  `ime` varchar(16) NOT NULL,
  `prezime` varchar(16) NOT NULL,
  `radni_staz` int(11) NOT NULL,
  `datum_zaposlenja` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `jmbg` (`jmbg`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zaposleni`
--

LOCK TABLES `zaposleni` WRITE;
/*!40000 ALTER TABLE `zaposleni` DISABLE KEYS */;
INSERT INTO `zaposleni` VALUES (1,'9999999999999','Ivo','Zadro',3,'2022-01-12'),(2,'1212342397124','Milan','Mikic',1,'2024-01-25'),(3,'4687250143213','Ana','Marija',5,'2020-01-20'),(4,'5412891199102','Izabela','Hama',2,'2023-01-01'),(5,'1901842735191','Veljko','Kunic',10,'2015-01-18'),(6,'1000000000008','Sara','Todorovic',9,'2016-01-29'),(7,'1000000000010','Tamara','Ristic',6,'2019-01-29'),(8,'1000000000012','Dunja','Radovic',2,'2023-01-29'),(9,'1000000000014','Milica','Lazarevic',11,'2014-01-29'),(10,'1000000000002','Ivana','Milic',8,'2017-01-29'),(11,'1000000000004','Bato','Kovacevic',12,'2013-01-29'),(12,'1000000000006','Mitar','Nikolic',15,'2010-01-29'),(13,'1000000000005','Marko','Stojanovic',7,'2018-01-29'),(14,'1000000000007','Nikola','Ilic',10,'2015-01-29'),(15,'1000000000009','Stefan','Vasic',4,'2021-01-29'),(16,'1000000000011','Milan','Pavlovic',13,'2012-01-29'),(17,'1000000000013','Petar','Simovic',14,'2011-01-29'),(18,'1000000000015','Vuk','Mladenovic',1,'2024-01-29'),(19,'1000000000001','Milos','Jankovic',5,'2020-01-29'),(20,'1000000000003','Luka','Petrovic',3,'2022-01-29'),(21,'0407004456321','Amer','Hot',10,'2024-01-03');
/*!40000 ALTER TABLE `zaposleni` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-06 18:08:29
