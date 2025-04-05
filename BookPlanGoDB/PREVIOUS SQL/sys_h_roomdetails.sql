-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: sys
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `h_roomdetails`
--

DROP TABLE IF EXISTS `h_roomdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `h_roomdetails` (
  `Hotel_ID` int NOT NULL,
  `room_num` varchar(45) NOT NULL,
  `bedding` varchar(45) DEFAULT NULL,
  `room_status` varchar(45) DEFAULT NULL,
  `room_ac` varchar(45) DEFAULT NULL,
  `room_floor` int DEFAULT NULL,
  `room_price` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `book_start_date` date DEFAULT NULL,
  `book_end_date` date DEFAULT NULL,
  PRIMARY KEY (`room_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `h_roomdetails`
--

LOCK TABLES `h_roomdetails` WRITE;
/*!40000 ALTER TABLE `h_roomdetails` DISABLE KEYS */;
INSERT INTO `h_roomdetails` VALUES (1001,'111','double','Booked','yes',1,5000,1234321,'2024-06-01','2024-06-06'),(1001,'121','single','Booked','yes',1,4000,1234321,'2024-06-02','2024-06-05'),(1011,'201','triple','Booked','AC',2,7500,1234321,'2024-06-02','2024-06-05'),(1011,'222','Single','Booked','AC',2,2000,1234321,'2024-06-01','2024-06-06'),(435,'234','Single','Available','AC',2,2500,NULL,NULL,NULL),(2222,'301','triple','Booked','AC',3,5500,1234321,'2024-06-13','2024-06-20'),(9999,'310','Double','Booked','AC',3,4500,1234321,'2024-06-03','2024-06-12'),(1011,'333','Single','Booked','AC',3,23423,1234321,'2024-06-02','2024-06-05'),(2000,'404','single','Available','no',4,2500,NULL,NULL,NULL);
/*!40000 ALTER TABLE `h_roomdetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-02 14:13:54
