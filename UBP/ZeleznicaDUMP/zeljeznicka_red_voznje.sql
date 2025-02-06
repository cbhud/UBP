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
-- Table structure for table `red_voznje`
--

DROP TABLE IF EXISTS `red_voznje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `red_voznje` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datum_kreiranja` date NOT NULL,
  `destinacija` varchar(24) NOT NULL,
  `datum_vrijeme_polaska` datetime NOT NULL,
  `br_mjesta` int(11) NOT NULL,
  `br_vip_mjesta` int(11) NOT NULL,
  `zaposleni_id` int(11) NOT NULL,
  `vozac_id` int(11) NOT NULL,
  `voz_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `zaposleni_id` (`zaposleni_id`),
  KEY `vozac_id` (`vozac_id`),
  KEY `voz_id` (`voz_id`),
  CONSTRAINT `red_voznje_ibfk_1` FOREIGN KEY (`zaposleni_id`) REFERENCES `zaposleni` (`id`),
  CONSTRAINT `red_voznje_ibfk_2` FOREIGN KEY (`vozac_id`) REFERENCES `vozac` (`id`),
  CONSTRAINT `red_voznje_ibfk_3` FOREIGN KEY (`voz_id`) REFERENCES `voz` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `red_voznje`
--

LOCK TABLES `red_voznje` WRITE;
/*!40000 ALTER TABLE `red_voznje` DISABLE KEYS */;
INSERT INTO `red_voznje` VALUES (1,'2025-02-01','Beograd','2025-02-05 08:30:00',120,20,1,1,1),(2,'2025-02-02','Novi Sad','2025-02-06 14:15:00',100,15,2,2,2),(3,'2025-02-03','Sarajevo','2025-02-07 09:45:00',90,10,3,3,3),(4,'2025-02-04','Skoplje','2025-02-08 12:00:00',110,18,4,4,4),(5,'2025-02-05','Podgorica','2025-02-09 16:30:00',130,25,5,5,5),(6,'2025-02-06','Zagreb','2025-02-10 07:00:00',115,22,6,6,6),(7,'2025-02-07','Ljubljana','2025-02-11 10:45:00',125,30,7,7,7),(8,'2025-02-08','Banja Luka','2025-02-12 15:20:00',140,35,8,8,8),(9,'2025-02-09','Nis','2025-02-13 06:50:00',95,14,9,9,9),(10,'2025-02-10','Tuzla','2025-02-14 17:40:00',105,17,10,9,10),(11,'2025-02-11','Beograd','2025-02-15 09:00:00',120,22,1,2,3),(12,'2025-02-12','Sarajevo','2025-02-16 14:30:00',110,20,3,5,7),(13,'2025-02-13','Zagreb','2025-02-17 11:10:00',130,25,6,8,9),(14,'2025-02-14','Skoplje','2025-02-18 19:20:00',140,30,4,9,2),(15,'2025-02-15','Podgorica','2025-02-19 07:45:00',100,15,5,9,4),(16,'2025-01-30','Bar','2025-02-13 20:30:00',64,24,5,11,16);
/*!40000 ALTER TABLE `red_voznje` ENABLE KEYS */;
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
