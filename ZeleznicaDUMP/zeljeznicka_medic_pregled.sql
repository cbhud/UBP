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
-- Table structure for table `medic_pregled`
--

DROP TABLE IF EXISTS `medic_pregled`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medic_pregled` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datum_pregleda` date NOT NULL,
  `nalaz` varchar(250) NOT NULL,
  `vozac_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `vozac_id` (`vozac_id`),
  CONSTRAINT `medic_pregled_ibfk_1` FOREIGN KEY (`vozac_id`) REFERENCES `vozac` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medic_pregled`
--

LOCK TABLES `medic_pregled` WRITE;
/*!40000 ALTER TABLE `medic_pregled` DISABLE KEYS */;
INSERT INTO `medic_pregled` VALUES (1,'2025-01-15','Zdrav',1),(2,'2024-01-10','Zdrav',1),(3,'2024-01-08','Zdrav',2),(4,'2024-01-12','Zdrav',3),(5,'2024-01-05','Zdrav',4),(6,'2024-01-02','Zdrav',5),(7,'2023-12-28','Zdrav',6),(8,'2023-06-15','Umor primijećen, preporučena pauza',6),(9,'2023-12-20','Zdrav',7),(10,'2023-06-10','Visok pritisak, preporučena terapija',7),(11,'2024-01-15','Neuracunjiv',8),(12,'2024-01-09','Bolestan',9),(13,'2024-01-07','Zdrav',10),(14,'2025-01-28','Krvni pritisak normalan, vozac je sposoban za upravljanje vozom',11);
/*!40000 ALTER TABLE `medic_pregled` ENABLE KEYS */;
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
