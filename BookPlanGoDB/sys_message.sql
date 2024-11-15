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
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `from_id` int DEFAULT NULL,
  `to_id` int DEFAULT NULL,
  `from_name` varchar(100) DEFAULT NULL,
  `to_name` varchar(100) DEFAULT NULL,
  `message` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,NULL,NULL,'sdfs','hjhjfgh'),(1234321,NULL,NULL,'ZARIF','sfsdfsdf'),(1234321,NULL,NULL,'ZARIF','dsgxbcvjggjghjgh'),(1234321,NULL,NULL,'ZARIF','fghjfguyrtyerwerwrwerwerwe'),(1234321,NULL,NULL,'ZARIF','Hello '),(1234321,NULL,NULL,'ZARIF','HI'),(NULL,1234321,'ZARIF',NULL,'asfjdkjfshfgjksdfjhsdgfjsdhgfjksdbnbvxcmbncxnvbxmvbxcmvbxmcvbxmbxcmnbxcvmnxcbvmnxvbxncbxnvbmxcnbvmxcnbvmxncvbxcmvbxmcn'),(1234321,NULL,NULL,'Zarif','asdgajsgjdcnzxczxcnzbcnzjhajkdhasj'),(1234321,NULL,NULL,'Zarif','asdgajsgjdcnzxczxcnzbcnzjhajkdhasjdsfdssfsdfs'),(1234321,NULL,NULL,'','dkshfsd\ndsjkfhkjsdf\nsjdfkjhsdkjfsd'),(NULL,1234321,'ZARIF',NULL,'asfjdkjfshfgjksdfjhsdgfjsdhgfjksd\nbnbvxcmbncxnvbxmvbxcmvbxmcvbxmbxcmnbxcvmnxcbvmn\nxvbxncbxnvbmxcnbvmxcnbvmxncvbxcmvbxmcn'),(1234321,NULL,NULL,'ZARIF','asfsadfsdsd'),(1234321,NULL,NULL,'',''),(1234321,NULL,NULL,'ZARIF','sdfsdfd'),(NULL,1234321,'Armenia',NULL,'iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiimmmmmmmmmmmmmmmmmmmmmmmmmmasd\nadssssssssss'),(NULL,1234321,'ZARIF',NULL,'I am zarif\nand you are?\n'),(1234321,NULL,NULL,'ZARIF','I am nobody\nha ha ha'),(NULL,1234231,'Admin','1234231','I am Admin'),(NULL,1234231,'Admin','1234231','I am ADMIN 2'),(NULL,NULL,'Admin','1234321','Hello,\nI am ADMIN'),(NULL,1234321,'Admin','1234321','Hello,\nI am ADMIN'),(1234321,NULL,NULL,'Admin','Hello \nI am User'),(1234321,NULL,NULL,'Admin','I am Traveler\nYour system is  nice'),(1234321,NULL,NULL,'Admin','I am IUT'),(NULL,NULL,'Admin','ZARIF','sadasdasdas'),(NULL,NULL,'Admin','ZARIF','sfadsfsfsd'),(NULL,NULL,'Admin','ZARIF','sadasdas'),(NULL,NULL,'Admin','ZARIF','sadasdas'),(NULL,NULL,'Admin','ZARIF','dsfsdf'),(NULL,NULL,'Admin','ZARIF','ererwerwer'),(NULL,NULL,'Admin','ZARIF','1111111111112\n2222222222222\n3333333333'),(NULL,NULL,'ZARIF','Admin','111111111111111\n32222222\n322222222222'),(1234321,NULL,NULL,'1234321','HElloo'),(2191,NULL,NULL,'ZARIF','asfmnf,mfn'),(NULL,NULL,'Admin','ZARIF','HOTEL'),(NULL,NULL,'Hotel_armania','Admin','I am New'),(NULL,NULL,'iui','Admin','HEllo I iiiiiiiiiiiiii\ndsjafd\ndsafsdf'),(NULL,1234321,'iui',NULL,'wrerwe\nwrewr\nwerew'),(1234321,NULL,NULL,'Admin','Ijhsdjh\nsajdasj\njhkasjd');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-02 14:13:56
