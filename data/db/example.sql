-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: example
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `example`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `example` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `example`;

--
-- Table structure for table `m_authority`
--

DROP TABLE IF EXISTS `m_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_authority` (
  `authorityid` varchar(10) NOT NULL,
  `authorityname` varchar(40) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`authorityid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_authority`
--

LOCK TABLES `m_authority` WRITE;
/*!40000 ALTER TABLE `m_authority` DISABLE KEYS */;
INSERT INTO `m_authority` VALUES ('0001','SHOW_USER_PAGE','view public pages'),('0002','SHOW_ADMIN_PAGE','view admin pages'),('0003','SHOW_COMMON_PAGE','view common pages');
/*!40000 ALTER TABLE `m_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_role`
--

DROP TABLE IF EXISTS `m_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_role` (
  `roleid` varchar(10) NOT NULL,
  `rollname` varchar(40) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_role`
--

LOCK TABLES `m_role` WRITE;
/*!40000 ALTER TABLE `m_role` DISABLE KEYS */;
INSERT INTO `m_role` VALUES ('0001','USER','public'),('0002','ADMIN','admin');
/*!40000 ALTER TABLE `m_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_role_authority`
--

DROP TABLE IF EXISTS `m_role_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_role_authority` (
  `roleid` varchar(10) NOT NULL,
  `authorityid` varchar(10) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`roleid`,`authorityid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_role_authority`
--

LOCK TABLES `m_role_authority` WRITE;
/*!40000 ALTER TABLE `m_role_authority` DISABLE KEYS */;
INSERT INTO `m_role_authority` VALUES ('0001','0001',NULL),('0001','0003',NULL),('0002','0001',NULL),('0002','0002',NULL);
/*!40000 ALTER TABLE `m_role_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_user`
--

DROP TABLE IF EXISTS `m_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_user` (
  `userid` varchar(10) NOT NULL,
  `username` varchar(40) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_user`
--

LOCK TABLES `m_user` WRITE;
/*!40000 ALTER TABLE `m_user` DISABLE KEYS */;
INSERT INTO `m_user` VALUES ('admin','Admin User','$2a$10$rp9AuUrhFpJoKdMtQN3LfODQI8ffwbNRn97KZcPN7FPTFSJpUVyPK',_binary '',''),('user','Public User','$2a$10$Py0Q1kE//gPVSbB5GIIAIeooyPSsQh84wg3anuq71xjr4hgDIHmNC',_binary '','');
/*!40000 ALTER TABLE `m_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_user_role`
--

DROP TABLE IF EXISTS `m_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_user_role` (
  `userid` varchar(10) NOT NULL,
  `roleid` varchar(10) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userid`,`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_user_role`
--

LOCK TABLES `m_user_role` WRITE;
/*!40000 ALTER TABLE `m_user_role` DISABLE KEYS */;
INSERT INTO `m_user_role` VALUES ('admin','0002',NULL),('user','0001',NULL);
/*!40000 ALTER TABLE `m_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_scheduledtask_history`
--

DROP TABLE IF EXISTS `t_scheduledtask_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_scheduledtask_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `method` varchar(30) DEFAULT NULL,
  `message` varchar(100) DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_scheduledtask_history`
--

LOCK TABLES `t_scheduledtask_history` WRITE;
/*!40000 ALTER TABLE `t_scheduledtask_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_scheduledtask_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_task`
--

DROP TABLE IF EXISTS `t_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_task` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `scheduledate` date DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `description` text,
  `userid` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_task`
--

LOCK TABLES `t_task` WRITE;
/*!40000 ALTER TABLE `t_task` DISABLE KEYS */;
INSERT INTO `t_task` VALUES (1,'Task1','2017-03-22',_binary '','Comment1','admin'),(2,'Task2','2017-03-22',_binary '\0','Comment2','admin'),(3,'Task3','2017-03-22',_binary '','Comment3','admin'),(4,'Task4','2017-03-22',_binary '\0','Comment4','admin'),(5,'Task5','2017-03-22',_binary '','Comment5','admin'),(6,'Task6','2017-03-22',_binary '\0','Comment6','admin'),(7,'Task7','2017-03-22',_binary '','Comment7','admin'),(8,'Task8','2017-03-22',_binary '\0','Comment8','admin'),(9,'Task9','2017-03-22',_binary '','Comment9','admin'),(10,'Task10','2017-03-22',_binary '\0','Comment10','admin'),(11,'Task11','2017-03-22',_binary '','Comment11','admin'),(12,'Task12','2017-03-22',_binary '\0','Comment12','admin'),(13,'Task13','2017-03-22',_binary '','Comment13','admin'),(14,'Task14','2017-03-22',_binary '\0','Comment14','admin'),(15,'Task15','2017-03-22',_binary '','Comment15','admin'),(16,'Task16','2017-03-22',_binary '\0','Comment16','admin'),(17,'Task17','2017-03-22',_binary '','Comment17','admin'),(18,'Task18','2017-03-22',_binary '\0','Comment18','admin'),(19,'Task19','2017-03-22',_binary '','Comment19','admin'),(20,'Task20','2017-03-22',_binary '\0','Comment20','admin'),(21,'Task21','2017-03-22',_binary '','Comment21','admin'),(22,'Task22','2017-03-22',_binary '\0','Comment22','admin'),(23,'Task23','2017-03-22',_binary '','Comment23','admin'),(24,'Task24','2017-03-22',_binary '\0','Comment24','admin'),(25,'Task25','2017-03-22',_binary '','Comment25','admin'),(26,'Task26','2017-03-22',_binary '\0','Comment26','admin'),(27,'Task27','2017-03-22',_binary '','Comment27','admin'),(28,'Task28','2017-03-22',_binary '\0','Comment28','admin'),(29,'Task29','2017-03-22',_binary '','Comment29','admin'),(30,'Task30','2017-03-22',_binary '\0','Comment30','admin'),(31,'Task31','2017-03-22',_binary '','Comment31','admin'),(32,'Task32','2017-03-22',_binary '\0','Comment32','admin'),(33,'Task33','2017-03-22',_binary '','Comment33','admin'),(34,'Task34','2017-03-22',_binary '\0','Comment34','admin'),(35,'Task35','2017-03-22',_binary '','Comment35','admin'),(36,'Task36','2017-03-22',_binary '\0','Comment36','admin'),(37,'Task37','2017-03-22',_binary '','Comment37','admin'),(38,'Task38','2017-03-22',_binary '\0','Comment38','admin'),(39,'Task39','2017-03-22',_binary '','Comment39','admin'),(40,'Task40','2017-03-22',_binary '\0','Comment40','admin'),(41,'Task41','2017-03-22',_binary '','Comment41','admin'),(42,'Task42','2017-03-22',_binary '\0','Comment42','admin'),(43,'Task43','2017-03-22',_binary '','Comment43','admin'),(44,'Task44','2017-03-22',_binary '\0','Comment44','admin'),(45,'Task45','2017-03-22',_binary '','Comment45','admin'),(46,'Task46','2017-03-22',_binary '\0','Comment46','admin'),(47,'Task47','2017-03-22',_binary '','Comment47','admin'),(48,'Task48','2017-03-22',_binary '\0','Comment48','admin'),(49,'Task49','2017-03-22',_binary '','Comment49','admin'),(50,'Task50','2017-03-22',_binary '\0','Comment50','admin'),(51,'Task51','2017-03-22',_binary '','Comment51','admin'),(52,'Task52','2017-03-22',_binary '\0','Comment52','admin'),(53,'Task53','2017-03-22',_binary '','Comment53','admin'),(54,'Task54','2017-03-22',_binary '\0','Comment54','admin'),(55,'Task55','2017-03-22',_binary '','Comment55','admin'),(56,'Task56','2017-03-22',_binary '\0','Comment56','admin'),(57,'Task57','2017-03-22',_binary '','Comment57','admin'),(58,'Task58','2017-03-22',_binary '\0','Comment58','admin'),(59,'Task59','2017-03-22',_binary '','Comment59','admin'),(60,'Task60','2017-03-22',_binary '\0','Comment60','admin'),(61,'Task61','2017-03-22',_binary '','Comment61','admin'),(62,'Task62','2017-03-22',_binary '\0','Comment62','admin'),(63,'Task63','2017-03-22',_binary '','Comment63','admin'),(64,'Task64','2017-03-22',_binary '\0','Comment64','admin'),(65,'Task65','2017-03-22',_binary '','Comment65','admin'),(66,'Task66','2017-03-22',_binary '\0','Comment66','admin'),(67,'Task67','2017-03-22',_binary '','Comment67','admin'),(68,'Task68','2017-03-22',_binary '\0','Comment68','admin'),(69,'Task69','2017-03-22',_binary '','Comment69','admin'),(70,'Task70','2017-03-22',_binary '\0','Comment70','admin'),(71,'Task71','2017-03-22',_binary '','Comment71','admin'),(72,'Task72','2017-03-22',_binary '\0','Comment72','admin'),(73,'Task73','2017-03-22',_binary '','Comment73','admin'),(74,'Task74','2017-03-22',_binary '\0','Comment74','admin'),(75,'Task75','2017-03-22',_binary '','Comment75','admin'),(76,'Task76','2017-03-22',_binary '\0','Comment76','admin'),(77,'Task77','2017-03-22',_binary '','Comment77','admin'),(78,'Task78','2017-03-22',_binary '\0','Comment78','admin'),(79,'Task79','2017-03-22',_binary '','Comment79','admin'),(80,'Task80','2017-03-22',_binary '\0','Comment80','admin'),(81,'Task81','2017-03-22',_binary '','Comment81','admin'),(82,'Task82','2017-03-22',_binary '\0','Comment82','admin'),(83,'Task83','2017-03-22',_binary '','Comment83','admin'),(84,'Task84','2017-03-22',_binary '\0','Comment84','admin'),(85,'Task85','2017-03-22',_binary '','Comment85','admin'),(86,'Task86','2017-03-22',_binary '\0','Comment86','admin'),(87,'Task87','2017-03-22',_binary '','Comment87','admin'),(88,'Task88','2017-03-22',_binary '\0','Comment88','admin'),(89,'Task89','2017-03-22',_binary '','Comment89','admin'),(90,'Task90','2017-03-22',_binary '\0','Comment90','admin'),(91,'Task91','2017-03-22',_binary '','Comment91','admin'),(92,'Task92','2017-03-22',_binary '\0','Comment92','admin'),(93,'Task93','2017-03-22',_binary '','Comment93','admin'),(94,'Task94','2017-03-22',_binary '\0','Comment94','admin'),(95,'Task95','2017-03-22',_binary '','Comment95','admin'),(96,'Task96','2017-03-22',_binary '\0','Comment96','admin'),(97,'Task97','2017-03-22',_binary '','Comment97','admin'),(98,'Task98','2017-03-22',_binary '\0','Comment98','admin'),(99,'Task99','2017-03-22',_binary '','Comment99','admin'),(100,'Task100','2017-03-22',_binary '\0','Comment100','admin');
/*!40000 ALTER TABLE `t_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `v_user_authority`
--

DROP TABLE IF EXISTS `v_user_authority`;
/*!50001 DROP VIEW IF EXISTS `v_user_authority`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `v_user_authority` AS SELECT 
 1 AS `id`,
 1 AS `userid`,
 1 AS `authorityname`*/;
SET character_set_client = @saved_cs_client;

--
-- Current Database: `example`
--

USE `example`;

--
-- Final view structure for view `v_user_authority`
--

/*!50001 DROP VIEW IF EXISTS `v_user_authority`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp932 */;
/*!50001 SET character_set_results     = cp932 */;
/*!50001 SET collation_connection      = cp932_japanese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_user_authority` AS select concat(`u`.`userid`,'-',`a`.`authorityid`) AS `id`,`u`.`userid` AS `userid`,`a`.`authorityname` AS `authorityname` from ((((`m_user` `u` join `m_user_role` `ur`) join `m_role_authority` `ra`) join `m_role` `r`) join `m_authority` `a`) where ((`u`.`userid` = `ur`.`userid`) and (`ur`.`roleid` = `r`.`roleid`) and (`ur`.`roleid` = `ra`.`roleid`) and (`ra`.`authorityid` = `a`.`authorityid`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-17 19:38:23
