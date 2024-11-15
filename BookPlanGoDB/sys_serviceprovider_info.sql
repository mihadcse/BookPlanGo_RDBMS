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
INSERT INTO `serviceprovider_info` VALUES (1,'H-1','6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918',12,'','',NULL,NULL),(3,'bool','6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918',10101,'Car','',NULL,NULL),(6,'C1','6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918',12,'','',NULL,NULL),(11,'11','4fc82b26aecb47d2868c4efbe3581732a3e7cbcc6c2efb32062c08170a05eeb8',11,'','',NULL,NULL),(39,'39','0b918943df0962bc7a1824c0555a389347b4febdc7cf9d1254406d80ce44e3f9',39,'','',NULL,NULL),(100,'null','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',1,'Car','Rangpur1',0,NULL),(201,'gdjghjdsgfhgsdhgfsdhgfsdfgdhfgsdhfgjhfgsdjfhgsdjfhgnbvcxncvnxbvbxcnv','43974ed74066b207c30ffd0fed5146762e6c60745ac977004bc14507c7c42b50',201,'Hotel','Sylhet',NULL,NULL),(210,'null','d4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35',210,'Car','Sylhet',0,NULL),(222,'www\"','222',222,'\"+\"Hotel','dhaka',NULL,NULL),(435,'Hotel_Armania','5f2703a5211db19a9020f7443f6a440fbc95cda90b7c2d53912f5ce47d050056',435,'Hotel','Khulna',4,NULL),(444,'null','3538a1ef2e113da64249eea7bd068b585ec7ce5df73b2d1e319d8c9bf47eb314',443,'Car','Rangpur',0,NULL),(1000,'null','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',1000,'Car','Mymensingh',0,NULL),(1001,'ZARIF','fe675fe7aaee830b6fed09b64e034f84dcbdaeb429d9cccd4ebb90e15af8dd71',324234234,'Hotel','Dhaka',NULL,'D:\\IUT SEMESTER 4\\CSE 4402\\BookPlanGo\\src\\main\\resources\\org\\example\\bookplango/ZARIF.jpg'),(1011,'Armenia','3dd9c0995d54c0abd51a90f1d57b1ce77bc885fc8a7cea52dcad3c2540dda5ee',1011,'Hotel','Sylhet',4,NULL),(1119,'Hotel dhaka','b280279a0ef279d0b9f0bdc4162591dbbc6312abac67120527b20d65c7de5dbf',1119,'Hotel','',NULL,NULL),(1777,'LakeView','3d80025e7a475d44dde1e42dff074c1b20338642e295bbd9160cfb03918223c0',19991,'Hotel','',NULL,NULL),(1872,'wqjqw','3f613c55d57f95fe15534edae86f6863d67d2df186eb6721cd70a037b03cf7fa',1892,'Hotel','',NULL,NULL),(1888,'hallroom','9988ad0a66f28fe8611962a06a593865c1c8103f87258e3920986fc2a4ba2d0e',1888,'Hotel','',NULL,NULL),(2002,'hotel-344','3fdba35f04dc8c462986c992bcf875546257113072a909c162f7e470e581e278',83372832,'','',NULL,NULL),(2020,'rangamati','73a2af8864fc500fa49048bf3003776c19938f360e56bd03663866fb3087884a',20202,'Hotel','',NULL,NULL),(2222,'name1','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',2222,'Hotel','Mymensingh',4,NULL),(2922,'bighotel','5d072f5b8512844dad19ed32b1dc199532ed39d55df6c081742a2affae583525',2929,'Hotel','',NULL,NULL),(5000,'Car_1','0f8eb4b72b6e0c9e88b388eb967b49e067ef1004bf07bffc22c3acb13b43580a',5000,'','',NULL,NULL),(5003,'BestCar','5ca452a03e3d5c2d4ecfc44a5d2cdec61f313cab4520be85b785fd83c74eda04',23211,'Car','',NULL,NULL),(9292,'new_gazipur','86853ece4e2ce6de7f3516048b854f9c3779cbaf5bf17a99a2f2a825b53b4d62',9292,'Hotel','',NULL,NULL),(9999,'iui','19581e27de7ced00ff1ce50b2047e7a567c76b1cbaebabe5ef03f7c3017bb5b7',9999,'Hotel','Dhaka',5,NULL),(30302,'jamuna','c63196444cddd50abd3892732f7de196a4b55aa677ca771b73fb09cf0e1ecb92',30303,'Hotel','',NULL,NULL),(54632,'IUT east Hall','43089cfbaf05c3aba231895fd9930aee8f309315667c75a3c4a01b4a95c5ab4c',1995752895,'','',NULL,NULL);
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

-- Dump completed on 2024-06-02 14:13:55
