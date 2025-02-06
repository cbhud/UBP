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
-- Table structure for table `karta`
--

DROP TABLE IF EXISTS `karta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `karta` (
  `br_karte` int(11) NOT NULL AUTO_INCREMENT,
  `datum_prodaje` date NOT NULL,
  `ime` varchar(16) NOT NULL,
  `prezime` varchar(16) NOT NULL,
  `tip_mjesta` enum('VIP','Obicno') NOT NULL,
  `red_voznje_id` int(11) NOT NULL,
  PRIMARY KEY (`br_karte`),
  UNIQUE KEY `br_karte` (`br_karte`),
  KEY `red_voznje_id` (`red_voznje_id`),
  CONSTRAINT `karta_ibfk_1` FOREIGN KEY (`red_voznje_id`) REFERENCES `red_voznje` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `karta`
--

LOCK TABLES `karta` WRITE;
/*!40000 ALTER TABLE `karta` DISABLE KEYS */;
INSERT INTO `karta` VALUES (1,'2025-02-01','Marko','Petrovic','Obicno',1),(2,'2025-02-01','Jovan','Nikolic','VIP',1),(3,'2025-02-02','Ana','Jovanovic','Obicno',1),(4,'2025-02-02','Milos','Stojanovic','VIP',1),(5,'2025-02-02','Tamara','Ilic','Obicno',1),(6,'2025-02-03','Nikola','Vasic','Obicno',1),(7,'2025-02-03','Maja','Todorovic','VIP',1),(8,'2025-02-03','Ivan','Pavlovic','Obicno',1),(9,'2025-02-04','Sara','Kovacevic','VIP',1),(10,'2025-02-04','Stefan','Jankovic','Obicno',1),(11,'2025-02-05','Luka','Milic','VIP',1),(12,'2025-02-05','Ivana','Ristic','Obicno',1),(13,'2025-02-05','Milan','Simic','Obicno',1),(14,'2025-02-06','Dunja','Radovic','VIP',1),(15,'2025-02-06','Petar','Mladenovic','Obicno',1),(16,'2025-02-02','Jelena','Petrovic','VIP',2),(17,'2025-02-02','Nikola','Ilic','Obicno',2),(18,'2025-02-03','Ana','Vukovic','VIP',2),(19,'2025-02-03','Stefan','Milic','Obicno',2),(20,'2025-02-03','Ivana','Simic','VIP',2),(21,'2025-02-04','Luka','Pavlovic','Obicno',2),(22,'2025-02-04','Sara','Kovacevic','VIP',2),(23,'2025-02-05','Tamara','Jankovic','Obicno',2),(24,'2025-02-05','Jovan','Radovic','VIP',2),(25,'2025-02-06','Dunja','Mladenovic','Obicno',2),(26,'2025-02-03','Milos','Ilic','Obicno',3),(27,'2025-02-03','Nikola','Todorovic','VIP',3),(28,'2025-02-04','Maja','Vasic','Obicno',3),(29,'2025-02-04','Jelena','Stojanovic','VIP',3),(30,'2025-02-05','Marko','Jovanovic','Obicno',3),(31,'2025-02-05','Tamara','Kovacevic','VIP',3),(32,'2025-02-05','Ana','Radovic','Obicno',3),(33,'2025-02-06','Luka','Mladenovic','VIP',3),(34,'2025-02-06','Ivana','Pavlovic','Obicno',3),(35,'2025-02-06','Jovan','Simic','Obicno',3),(36,'2025-02-07','Dunja','Milic','VIP',3),(37,'2025-02-07','Petar','Jankovic','Obicno',3),(38,'2025-02-04','Stefan','Jovanovic','VIP',4),(39,'2025-02-04','Milos','Todorovic','Obicno',4),(40,'2025-02-05','Nikola','Pavlovic','VIP',4),(41,'2025-02-05','Maja','Kovacevic','Obicno',4),(42,'2025-02-06','Tamara','Radovic','VIP',4),(43,'2025-02-06','Luka','Milic','Obicno',4),(44,'2025-02-07','Ivana','Simic','VIP',4),(45,'2025-02-07','Jovan','Jankovic','Obicno',4),(46,'2025-02-08','Petar','Nikolic','VIP',5),(47,'2025-02-08','Milica','Jovanovic','Obicno',5),(48,'2025-02-09','Stefan','Stojanovic','Obicno',5),(49,'2025-02-10','Luka','Radovic','VIP',6),(50,'2025-02-10','Ivana','Petrovic','Obicno',6),(51,'2025-02-11','Tamara','Milic','Obicno',6),(52,'2025-02-11','Milos','Kovacevic','VIP',7),(53,'2025-02-11','Sara','Pavlovic','Obicno',7),(54,'2025-02-12','Nikola','Simic','Obicno',7),(55,'2025-02-12','Marko','Jankovic','VIP',8),(56,'2025-02-12','Jovana','Ilic','Obicno',8),(57,'2025-02-13','Dunja','Todorovic','Obicno',8),(58,'2025-02-13','Ana','Jovanovic','VIP',9),(59,'2025-02-13','Luka','Nikolic','Obicno',9),(60,'2025-02-14','Tamara','Petrovic','Obicno',9),(61,'2025-02-14','Nikola','Stojanovic','VIP',10),(62,'2025-02-14','Stefan','Pavlovic','Obicno',10),(63,'2025-02-15','Jelena','Milic','Obicno',10),(64,'2025-02-15','Petar','Jankovic','VIP',11),(65,'2025-02-15','Ivana','Radovic','Obicno',11),(66,'2025-02-16','Tamara','Milic','Obicno',11),(67,'2025-02-16','Milos','Kovacevic','VIP',12),(68,'2025-02-16','Sara','Pavlovic','Obicno',12),(69,'2025-02-17','Nikola','Simic','Obicno',12),(70,'2025-02-17','Marko','Jankovic','VIP',13),(71,'2025-02-17','Jovana','Ilic','Obicno',13),(72,'2025-02-18','Dunja','Todorovic','Obicno',13),(73,'2025-02-18','Ana','Jovanovic','VIP',14),(74,'2025-02-18','Luka','Nikolic','Obicno',14),(75,'2025-02-19','Tamara','Petrovic','Obicno',14),(76,'2025-02-19','Nikola','Stojanovic','VIP',15),(77,'2025-02-19','Stefan','Pavlovic','Obicno',15),(78,'2025-02-20','Jelena','Milic','Obicno',15),(79,'2025-01-30','Peki','Rosa','Obicno',16),(80,'2025-01-30','Marko','Markovic','VIP',2);
/*!40000 ALTER TABLE `karta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-06 18:08:28
