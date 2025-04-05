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
  `book_end_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `h_roomdetails`
--

LOCK TABLES `h_roomdetails` WRITE;
/*!40000 ALTER TABLE `h_roomdetails` DISABLE KEYS */;
INSERT INTO `h_roomdetails` VALUES (1234,'111','Double','Booked','AC',1,4000,1711111,'2025-03-07','2025-03-10'),(1234,'123','Single','Available','Non AC',1,1111,NULL,NULL,NULL),(1234,'222','Single','Available','AC',2,2222,NULL,NULL,NULL),(123456,'233','Single','Available','AC',2,3333,NULL,NULL,NULL),(1234,'333','Single','Available','Non AC',3,3333,NULL,NULL,NULL),(1235,'601','Double','Available','AC',6,1100,NULL,NULL,NULL);
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

-- Dump completed on 2025-03-21 22:24:47
