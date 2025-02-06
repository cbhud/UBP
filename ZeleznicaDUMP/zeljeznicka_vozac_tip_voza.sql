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
-- Table structure for table `vozac_tip_voza`
--

DROP TABLE IF EXISTS `vozac_tip_voza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vozac_tip_voza` (
  `vozac_id` int(11) NOT NULL,
  `tip_voza_id` int(11) NOT NULL,
  PRIMARY KEY (`vozac_id`,`tip_voza_id`),
  KEY `tip_voza_id` (`tip_voza_id`),
  CONSTRAINT `vozac_tip_voza_ibfk_1` FOREIGN KEY (`vozac_id`) REFERENCES `vozac` (`id`),
  CONSTRAINT `vozac_tip_voza_ibfk_2` FOREIGN KEY (`tip_voza_id`) REFERENCES `tip_voza` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vozac_tip_voza`
--

LOCK TABLES `vozac_tip_voza` WRITE;
/*!40000 ALTER TABLE `vozac_tip_voza` DISABLE KEYS */;
INSERT INTO `vozac_tip_voza` VALUES (1,1),(1,3),(2,2),(2,5),(3,1),(3,4),(4,3),(4,4),(4,5),(5,2),(5,3),(6,1),(6,2),(7,3),(7,4),(8,4),(8,5),(9,1),(9,2),(9,3),(10,1),(10,5),(11,4),(11,5);
/*!40000 ALTER TABLE `vozac_tip_voza` ENABLE KEYS */;
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
