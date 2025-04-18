-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: bookplango
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `serviceprovider_info`
--

DROP TABLE IF EXISTS `serviceprovider_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `serviceprovider_info` (
  `service_id` int NOT NULL,
  `service_name` varchar(100) NOT NULL,
  `service_password` varchar(64) DEFAULT NULL,
  `service_phone_no` int NOT NULL,
  `service_type` varchar(45) NOT NULL,
  `service_location` varchar(45) NOT NULL,
  `floor` int DEFAULT NULL,
  `service_approval` varchar(50) DEFAULT NULL,
  `service_image` varchar(450) DEFAULT NULL,
  PRIMARY KEY (`service_id`),
  UNIQUE KEY `service_id_UNIQUE` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serviceprovider_info`
--

LOCK TABLES `serviceprovider_info` WRITE;
/*!40000 ALTER TABLE `serviceprovider_info` DISABLE KEYS */;
INSERT INTO `serviceprovider_info` VALUES (1234,'H_123','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4',1777777700,'Hotel','Sylhet',3,'Approved',NULL),(1235,'ZarifHotel','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4',1851472695,'Hotel','Chattogram',11,'Approved',NULL),(123456,'ZARIF_Hotel','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1888888881,'Hotel','Chattogram',4,'Approved',NULL);
/*!40000 ALTER TABLE `serviceprovider_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-21 22:24:46
