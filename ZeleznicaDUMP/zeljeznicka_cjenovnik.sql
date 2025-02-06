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
-- Table structure for table `cjenovnik`
--

DROP TABLE IF EXISTS `cjenovnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cjenovnik` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cijena` decimal(10,2) NOT NULL,
  `vip_cijena` decimal(10,2) NOT NULL,
  `red_voznje_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `red_voznje_id` (`red_voznje_id`),
  CONSTRAINT `cjenovnik_ibfk_1` FOREIGN KEY (`red_voznje_id`) REFERENCES `red_voznje` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cjenovnik`
--

LOCK TABLES `cjenovnik` WRITE;
/*!40000 ALTER TABLE `cjenovnik` DISABLE KEYS */;
INSERT INTO `cjenovnik` VALUES (1,20.00,35.00,1),(2,18.50,32.00,2),(3,25.00,40.00,3),(4,22.00,38.00,4),(5,19.00,34.00,5),(6,21.50,36.50,6),(7,23.00,39.00,7),(8,24.00,42.00,8),(9,17.00,30.00,9),(10,26.00,45.00,10),(11,20.50,37.00,11),(12,22.50,41.00,12),(13,19.50,35.50,13),(14,21.00,38.50,14),(15,18.00,33.00,15),(16,4.00,8.00,16);
/*!40000 ALTER TABLE `cjenovnik` ENABLE KEYS */;
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
