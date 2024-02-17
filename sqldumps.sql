-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: attendance
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.22.04.1

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
-- Table structure for table `attendance_logs`
--

DROP TABLE IF EXISTS `attendance_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance_logs` (
  `id` bigint NOT NULL,
  `attendance_details` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance_logs`
--

LOCK TABLES `attendance_logs` WRITE;
/*!40000 ALTER TABLE `attendance_logs` DISABLE KEYS */;
INSERT INTO `attendance_logs` VALUES (139,'Attendance for Immaculate Kimani has been added at 12:50'),(143,'Attendance for Taylor Swift has been added at 13:19'),(148,'Attendance for Immaculate Kimani has been added at 10:57'),(151,'Attendance for Taylor Swift has been updated at 11:03'),(154,'Attendance for maculate Kimani has been updated at 22:27'),(159,'Attendance for Awere Some has been added at 22:31'),(161,'Attendance for Taylor Banks has been added at 06:59'),(163,'Attendance for Awere Some has been added at 06:59'),(213,'Attendance for Beyonce Bitvh has been added at 15:09'),(258,'Attendance for Awere Some has been updated at 16:34'),(260,'Attendance for Cute Swift has been added at 16:34'),(264,'Attendance for Taylor Bankasa has been updated at 17:57'),(283,'Attendance for Immaculate Kimani has been added at 18:04'),(284,'Attendance for Immaculate Kimani has been updated at 18:05'),(285,'Attendance for Cute Swift has been updated at 18:05'),(291,'Attendance for Mehki Dear has been added at 18:06'),(294,'Attendance for Mehki Dearle has been updated at 18:06'),(301,'Attendance for Check OUr has been added at 18:13'),(302,'Attendance for Check OUr has been updated at 18:14'),(304,'Attendance for Beyonce Quinn has been added at 10:13'),(306,'Attendance for Immaculate Kimani has been added at 10:14'),(308,'Attendance for Mehki Dearle has been added at 10:15'),(310,'Attendance for Check OUr has been added at 10:17'),(311,'Attendance for Immaculate Kimani has been updated at 10:31'),(318,'Attendance for Taylor Script has been added at 11:03'),(333,'Attendance for Java Swift9 has been added at 14:40'),(345,'Attendance for Taylor Swift9 has been added at 15:43'),(360,'Attendance for Chacha Check has been added at 16:01'),(362,'Attendance for Immaculate Kimani has been added at 16:01'),(364,'Attendance for Beyonce Dares has been added at 16:01'),(365,'Attendance for Immaculate Kimani has been updated at 16:01'),(367,'Attendance for Mstahiki Meya has been added at 16:02'),(368,'Attendance for Taylor Swift9 has been updated at 19:14'),(369,'Attendance for Mstahiki Meya has been updated at 19:14'),(373,'Attendance for Priyanka  Chopra has been added at 19:18'),(375,'Attendance for Priyanka  Chopra has been added at 19:23'),(376,'Attendance for Priyanka  Chopra has been updated at 19:23'),(379,'Attendance for Priyanka  Chopra has been added at 19:39'),(380,'Attendance for Priyanka  Chopra has been updated at 19:40'),(384,'Attendance for Priyanka  Chopra has been added at 20:02'),(385,'Attendance for Priyanka  Chopra has been updated at 20:02'),(389,'Attendance for Priyanka  Chopra has been added at 20:10'),(390,'Attendance for Priyanka  Chopra has been updated at 20:10'),(392,'Attendance for Priyanka  Chopra has been added at 20:12'),(393,'Attendance for Priyanka  Chopra has been updated at 20:12'),(396,'Attendance for Priyanka  Chopra has been added at 20:15'),(397,'Attendance for Priyanka  Chopra has been updated at 20:15'),(399,'Attendance for Priyanka  Chopra has been added at 20:17'),(400,'Attendance for Priyanka  Chopra has been updated at 20:20'),(403,'Attendance for Priyanka  Chopra has been added at 20:24'),(404,'Attendance for Priyanka  Chopra has been updated at 20:25'),(407,'Attendance for Priyanka  Chopra has been added at 20:34'),(408,'Attendance for Priyanka  Chopra has been updated at 20:34'),(411,'Attendance for Priyanka  Chopra has been added at 20:49'),(412,'Attendance for Priyanka  Chopra has been updated at 20:49'),(413,'Attendance for Priyanka  Chopra has been updated at 20:49'),(416,'Attendance for Priyanka  Chopra has been added at 20:57'),(417,'Attendance for Priyanka  Chopra has been updated at 20:57'),(423,'Attendance for Priyanka Chopra has been added at 21:05'),(424,'Attendance for Priyanka Chopra has been updated at 21:05'),(426,'Attendance for Taylor Swift9 has been added at 14:07'),(428,'Attendance for Taylor Swift9 has been added at 10:49'),(431,'Attendance for Beyonce Dares has been added at 10:52'),(433,'Attendance for Mstahiki Meya has been added at 10:52'),(434,'Attendance for Beyonce Dares has been updated at 10:52'),(451,'Attendance for Priyanka Chopra has been added at 12:37'),(455,'Attendance for Supa Strikaa has been added at 12:42'),(469,'Attendance for Supa Strikaa has been updated at 17:59'),(471,'Attendance for Morgan Tasha has been added at 18:01'),(474,'Attendance for Beyonce Jules has been added at 18:09'),(475,'Attendance for Beyonce Jules has been updated at 18:10'),(481,'Attendance for Hey Bestie has been added at 20:16'),(483,'Attendance for Taylor Swift has been added at 20:16'),(485,'Attendance for Jacob Banks has been added at 20:16'),(486,'Attendance for Hey Bestie has been updated at 20:17'),(487,'Attendance for Taylor Swift has been updated at 20:17'),(494,'Attendance for Tea Marr has been added at 20:29'),(496,'Attendance for Supa Strikaa has been added at 11:40'),(498,'Attendance for Beyonce Jules has been added at 11:40'),(500,'Attendance for Hey Bestie has been added at 11:40'),(502,'Attendance for Taylor Swift has been added at 11:40'),(504,'Attendance for Jacob Banks has been added at 11:40'),(506,'Attendance for Morgan Tasha has been added at 11:40'),(508,'Attendance for Tea Marr has been added at 11:40'),(509,'Attendance for Hey Bestie has been updated at 11:40'),(510,'Attendance for Beyonce Jules has been updated at 16:08'),(514,'Attendance for Justin Beiber has been added at 16:12'),(520,'Attendance for Supa Shakes has been updated at 17:21'),(521,'Attendance for Morgan Tasha has been updated at 17:25'),(522,'Attendance for Tea Marr has been updated at 17:26'),(530,'Attendance for Supa Shakes has been added at 08:48'),(532,'Attendance for Beyonce Knowles has been added at 08:48'),(534,'Attendance for Hey Bestie has been added at 08:48'),(536,'Attendance for Taylor Swift has been added at 08:48'),(538,'Attendance for Jacob Banks has been added at 08:48'),(540,'Attendance for Morgan Tasha has been added at 08:48'),(542,'Attendance for Tea Marr has been added at 08:49'),(544,'Attendance for Justin Beiber has been added at 08:49'),(546,'Attendance for Supa Shakes has been updated at 11:39'),(547,'Attendance for Jacob Banks has been updated at 11:39'),(553,'Attendance for Lena Lifts has been added at 13:03'),(554,'Attendance for Lena Lifts has been updated at 13:03'),(555,'Attendance for Hey Bestie has been updated at 15:17'),(556,'Attendance for Hey Bestie has been updated at 15:18'),(560,'Attendance for Jack Harlow has been added at 15:48'),(564,'Attendance for Smart Shakes has been added at 08:29'),(566,'Attendance for Justin Beiber has been added at 08:53'),(569,'Attendance for Taylor Swift has been added at 10:35'),(571,'Attendance for Lena Lifts has been added at 10:35'),(573,'Attendance for Morgan Tasha has been added at 10:35'),(575,'Attendance for Jacob Banks has been added at 10:35'),(576,'Attendance for Lena Lifts has been updated at 10:50'),(577,'Attendance for Smart Shakes has been updated at 11:19'),(579,'Attendance for Beyonce Knowles has been added at 11:20'),(581,'Attendance for Tea Marr has been added at 13:42'),(582,'Attendance for Beyonce Knowles has been updated at 13:48'),(585,'Attendance for Lena Lifts has been added at 12:21'),(588,'Attendance for Smart Shakes has been added at 13:14'),(590,'Attendance for Beyonce Knowles has been added at 13:17'),(592,'Attendance for Hey Besties has been added at 13:21'),(594,'Attendance for Jacob Banks has been added at 13:41'),(596,'Attendance for Morgan Tasha has been added at 13:45'),(597,'Attendance for Morgan Tasha has been updated at 13:48'),(599,'Attendance for Taylor Swift has been added at 14:54'),(600,'Attendance for Taylor Swift has been updated at 15:06'),(602,'Attendance for Justin Beiber has been added at 15:19'),(603,'Attendance for Jacob Banks has been updated at 15:24'),(604,'Attendance for Justin Beiber has been updated at 15:26'),(606,'Attendance for Lena Lifts has been added at 15:28'),(608,'Attendance for Jack Harlow has been added at 15:31'),(609,'Attendance for Jack Harlow has been updated at 15:32'),(610,'Attendance for Lena Lifts has been updated at 16:43'),(612,'Attendance for Lena Lifts has been added at 15:19'),(613,'Attendance for Lena Lifts has been updated at 19:42'),(615,'Attendance for Beyonce Knowles has been added at 16:29'),(617,'Attendance for Beyonce Knowles has been added at 11:53'),(619,'Attendance for Hey Besties has been added at 11:59'),(621,'Attendance for Jacob Banks has been added at 12:03'),(623,'Attendance for Tea Marr has been added at 12:08'),(625,'Attendance for Taylor Swift has been added at 12:43'),(626,'Attendance for Taylor Swift has been updated at 12:44'),(628,'Attendance for Morgan Tasha has been added at 12:47'),(630,'Attendance for Justin Beiber has been added at 12:47'),(632,'Attendance for Jack Harlow has been added at 12:51'),(634,'Attendance for Smart Shakes has been added at 12:53'),(636,'Attendance for Lena Lifts has been added at 12:54');
/*!40000 ALTER TABLE `attendance_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendances`
--

DROP TABLE IF EXISTS `attendances`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendances` (
  `id` bigint NOT NULL,
  `attendance_date` date DEFAULT NULL,
  `attendance_status` varchar(255) DEFAULT NULL,
  `attendance_time` time DEFAULT NULL,
  `display_id` varchar(255) DEFAULT NULL,
  `employeeImage` varchar(255) DEFAULT NULL,
  `employee_name` varchar(255) DEFAULT NULL,
  `joining_status` varchar(255) DEFAULT NULL,
  `time_in` time DEFAULT NULL,
  `time_out` time DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2mia0pcnmy2nddwedvfrt0w08` (`employee_id`),
  CONSTRAINT `FK2mia0pcnmy2nddwedvfrt0w08` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendances`
--

LOCK TABLES `attendances` WRITE;
/*!40000 ALTER TABLE `attendances` DISABLE KEYS */;
INSERT INTO `attendances` VALUES (454,'2023-12-10','FullDay','07:42:00','E34562','shakes.png','Supa Shakes','In-Time','07:42:00','17:59:00',452),(470,'2023-12-16','Absent','18:01:00','E46242','fluffygirl.png','Morgan Tasha',NULL,NULL,NULL,467),(473,'2023-12-16','FullDay','18:09:00','E46292','ladybun.png','Beyonce Knowles','Late','18:09:00','18:10:00',458),(478,'2023-12-11','FullDay','06:18:00','E64737','afroman.png','Jacob  Banks','In-Time','06:18:00','18:20:00',464),(479,'2023-12-12','FullDay','06:18:00','E64737','afroman.png','Jacob  Banks','In-Time','06:18:00','18:20:00',464),(480,'2023-12-16','FullDay','20:16:00','E47283','indian.png','Hey Besties','Late','20:16:00','20:17:00',460),(482,'2023-12-16','FullDay','20:16:00','E64735','asian.png','Taylor Swift','Late','20:16:00','20:17:00',462),(484,'2023-12-16','Absent','20:16:00','E64737','afroman.png','Jacob Banks',NULL,NULL,NULL,464),(493,'2023-12-16','Absent','20:29:00','E462920','afrowoman.png','Tea Marr',NULL,NULL,NULL,491),(495,'2023-12-17','FullDay','11:40:00','E34562','shakes.png','Smart Shakes','Late','11:40:00','17:21:00',452),(497,'2023-12-17','HalfDay','11:40:00','E46292','ladybun.png','Beyonce Knowles','Late','11:40:00','16:08:00',458),(499,'2023-12-17','HalfDay','11:40:00','E47283','indian.png','Hey Besties','Late','11:40:00','11:40:00',460),(501,'2023-12-17','Absent','11:40:00','E64735','asian.png','Taylor Swift',NULL,NULL,NULL,462),(503,'2023-12-17','Absent','11:40:00','E64737','afroman.png','Jacob Banks',NULL,NULL,NULL,464),(505,'2023-12-17','FullDay','11:40:00','E46242','fluffygirl.png','Morgan Tasha','Late','11:40:00','17:25:00',467),(507,'2023-12-17','FullDay','11:40:00','E462920','afrowoman.png','Tea Marr','Late','11:40:00','17:26:00',491),(513,'2023-12-17','Absent','16:12:00','E32716','rastabun.png','Justin Beiber',NULL,NULL,NULL,511),(529,'2023-12-18','HalfDay','08:48:00','E34562','shakes.png','Smart Shakes','Late','08:48:00','11:39:00',452),(531,'2023-12-18','Absent','08:48:00','E46292','ladybun.png','Beyonce Knowles',NULL,NULL,NULL,458),(533,'2023-12-18','HalfDay','08:48:00','E47283','indian.png','Hey Besties','Late','08:48:00','15:18:00',460),(535,'2023-12-18','Absent','08:48:00','E64735','asian.png','Taylor Swift',NULL,NULL,NULL,462),(537,'2023-12-18','HalfDay','08:48:00','E64737','afroman.png','Jacob Banks','Late','08:48:00','11:39:00',464),(539,'2023-12-18','Late','08:48:00','E46242','fluffygirl.png','Morgan Tasha','Late','08:48:00',NULL,467),(541,'2023-12-18','Late','08:49:00','E462920','afrowoman.png','Tea Marr','Late','08:49:00',NULL,491),(543,'2023-12-18','Absent','08:49:00','E32716','rastabun.png','Justin Beiber',NULL,NULL,NULL,511),(552,'2023-12-18','HalfDay','13:03:00','E23487','asian.png','Lena Lifts','Late','13:03:00','13:03:00',550),(559,'2023-12-18','Absent','15:48:00','E23517','fade.png','Jack Harlow',NULL,NULL,NULL,557),(563,'2023-12-19','HalfDay','08:29:00','E34562','shakes.png','Smart Shakes','Late','08:29:00','11:19:00',452),(565,'2023-12-19','Absent','08:53:00','E32716','rastabun.png','Justin Beiber',NULL,NULL,NULL,511),(568,'2023-12-19','Absent','10:35:00','E64735','asian.png','Taylor Swift',NULL,NULL,NULL,462),(570,'2023-12-19','HalfDay','10:35:00','E23487','asian.png','Lena Lifts','Late','10:35:00','10:50:00',550),(572,'2023-12-19','Late','10:35:00','E46242','fluffygirl.png','Morgan Tasha','Late','10:35:00',NULL,467),(574,'2023-12-19','Late','10:35:00','E64737','afroman.png','Jacob Banks','Late','10:35:00',NULL,464),(578,'2023-12-19','Late','11:20:00','E46292','ladybun.png','Beyonce Knowles','Late','11:20:00',NULL,458),(580,'2023-12-19','Late','13:42:00','E462920','afrowoman.png','Tea Marr','Late','13:42:00',NULL,491),(584,'2024-01-24','Late','12:21:00','E23487','asian.png','Lena Lifts','Late','12:21:00',NULL,550),(587,'2024-01-25','Absent','13:14:00','E34562','shakes.png','Smart Shakes',NULL,NULL,NULL,452),(589,'2024-01-25','Absent','13:17:00','E46292','ladybun.png','Beyonce Knowles','Not Attended',NULL,NULL,458),(591,'2024-01-25','Absent','13:21:00','E47283','indian.png','Hey Besties','Not Attended',NULL,NULL,460),(593,'2024-01-25','HalfDay','13:41:00','E64737','afroman.png','Jacob Banks','Late','13:41:00','15:24:00',464),(595,'2024-01-25','HalfDay','13:45:00','E46242','fluffygirl.png','Morgan Tasha','Late','13:45:00','13:48:00',467),(598,'2024-01-25','HalfDay','14:54:00','E64735','asian.png','Taylor Swift','Late','14:54:00','15:06:00',462),(601,'2024-01-25','HalfDay','15:19:00','E32716','rastabun.png','Justin Beiber','Late','15:19:00','15:26:00',511),(605,'2024-01-25','HalfDay','15:28:00','E23487','asian.png','Lena Lifts','Late','15:28:00','16:43:00',550),(607,'2024-01-25','HalfDay','15:31:00','E23517','fade.png','Jack Harlow','Late','15:31:00','15:32:00',557),(611,'2024-01-31','FullDay','15:19:00','E23487','asian.png','Lena Lifts','Late','15:19:00','19:42:00',550),(614,'2024-02-12','Late','16:29:00','E46292','ladybun.png','Beyonce Knowles','Late','16:29:00',NULL,458),(616,'2024-02-16','Late','11:53:00','E46292','ladybun.png','Beyonce Knowles','Late','11:53:00',NULL,458),(618,'2024-02-16','Absent','11:59:00','E47283','indian.png','Hey Besties','Not Attended',NULL,NULL,460),(620,'2024-02-16','Absent','12:03:00','E64737','afroman.png','Jacob Banks',NULL,NULL,NULL,464),(622,'2024-02-16','Absent','12:08:00','E462920','afrowoman.png','Tea Marr','Not Attended',NULL,NULL,491),(624,'2024-02-16','HalfDay','12:43:00','E64735','asian.png','Taylor Swift','Late','12:43:00','12:44:00',462),(627,'2024-02-16','Late','12:47:00','E46242','fluffygirl.png','Morgan Tasha','Late','12:47:00','12:47:03',467),(629,'2024-02-16','Absent','12:47:00','E32716','rastabun.png','Justin Beiber','Not Attended','12:47:27','12:47:27',511),(631,'2024-02-16','Absent','12:51:00','E23517','fade.png','Jack Harlow','Not Attended','12:51:42','00:00:00',557),(633,'2024-02-16','Late','12:53:00','E34562','shakes.png','Smart Shakes','Late','12:53:00','00:00:00',452),(635,'2024-02-16','Absent','12:54:00','E23487','asian.png','Lena Lifts','Not Attended','00:00:00','00:00:00',550);
/*!40000 ALTER TABLE `attendances` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_logs`
--

DROP TABLE IF EXISTS `employee_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_logs` (
  `id` bigint NOT NULL,
  `employee_details` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_logs`
--

LOCK TABLES `employee_logs` WRITE;
/*!40000 ALTER TABLE `employee_logs` DISABLE KEYS */;
INSERT INTO `employee_logs` VALUES (137,'Successfully added an employee at 12:46'),(141,'Successfully added an employee at 13:19'),(144,'Successfully deleted E64738 at 13:19'),(146,'Successfully deleted E216828 at 10:56'),(155,'Successfully deleted E34562 at 22:28'),(157,'Successfully added an employee at 22:30'),(195,'Successfully added an employee at 14:25'),(196,'Successfully deleted E47287 at 14:26'),(200,'Successfully added an employee at 14:35'),(206,'Successfully added an employee at 14:55'),(207,'Successfully deleted E64737h at 14:55'),(211,'Successfully added an employee at 15:09'),(214,'Details for Cute have been updated at 15:11'),(215,'Details for Cute have been updated at 15:12'),(216,'Details for Taylor have been updated at 15:22'),(217,'Details for Taylor have been updated at 15:22'),(219,'Successfully added an employee at 15:25'),(221,'Successfully added an employee at 15:25'),(223,'Successfully added an employee at 15:26'),(225,'Successfully added an employee at 15:26'),(226,'Successfully deleted null at 15:26'),(227,'Successfully deleted null at 15:26'),(228,'Successfully deleted null at 15:26'),(229,'Successfully deleted null at 15:26'),(230,'Successfully deleted null at 15:26'),(231,'Successfully deleted null at 15:26'),(232,'Successfully deleted null at 15:26'),(233,'Successfully deleted null at 15:26'),(234,'Successfully deleted null at 15:26'),(235,'Successfully deleted null at 15:26'),(236,'Successfully deleted null at 15:26'),(237,'Successfully deleted null at 15:26'),(238,'Successfully deleted null at 15:26'),(239,'Successfully deleted null at 15:26'),(240,'Successfully deleted null at 15:27'),(241,'Successfully deleted null at 15:27'),(242,'Successfully deleted null at 15:27'),(243,'Successfully deleted null at 15:27'),(244,'Successfully deleted null at 15:27'),(245,'Successfully deleted null at 15:27'),(246,'Successfully deleted null at 15:27'),(247,'Successfully deleted null at 15:27'),(248,'Successfully deleted null at 15:27'),(249,'Successfully deleted null at 15:27'),(250,'Successfully deleted null at 15:27'),(251,'Successfully deleted null at 15:27'),(252,'Successfully deleted null at 15:27'),(253,'Details for Beyonce have been updated at 15:28'),(254,'Details for Beyonce have been updated at 15:29'),(255,'Details for Taylor have been updated at 15:48'),(256,'Details for Cute have been updated at 15:50'),(257,'Details for Cute have been updated at 15:50'),(261,'Successfully deleted null at 17:56'),(262,'Successfully deleted null at 17:56'),(263,'Successfully deleted null at 17:56'),(265,'Successfully deleted null at 17:58'),(266,'Successfully deleted null at 17:58'),(267,'Successfully deleted null at 17:58'),(268,'Successfully deleted null at 17:58'),(270,'Successfully added an employee at 17:58'),(271,'Successfully deleted null at 17:58'),(272,'Successfully deleted null at 17:58'),(273,'Successfully deleted null at 17:58'),(274,'Successfully deleted null at 17:58'),(275,'Successfully deleted null at 17:58'),(276,'Successfully deleted null at 17:58'),(277,'Successfully deleted null at 17:58'),(279,'Successfully added an employee at 17:59'),(280,'Successfully deleted null at 17:59'),(281,'Successfully deleted null at 18:03'),(286,'Successfully deleted null at 18:05'),(287,'Successfully deleted null at 18:05'),(289,'Successfully added an employee at 18:06'),(292,'Details for Mehki have been updated at 18:06'),(293,'Details for Mehki have been updated at 18:06'),(295,'Successfully deleted an employee at 18:10'),(296,'Details for Immaculate have been updated at 18:10'),(297,'Details for Mehki have been updated at 18:10'),(299,'Successfully added an employee at 18:13'),(313,'Successfully added an employee at 11:02'),(314,'Successfully deleted an employee at 11:02'),(316,'Successfully added an employee at 11:03'),(319,'Successfully deleted an employee at 11:03'),(321,'Successfully added an employee at 11:07'),(323,'Successfully added an employee at 11:08'),(324,'Successfully deleted an employee at 11:08'),(326,'Successfully added an employee at 11:08'),(327,'Details for heiw have been updated at 11:08'),(328,'Successfully deleted an employee at 11:09'),(330,'Details for Java have been updated at 14:32'),(331,'Details for Java have been updated at 14:32'),(334,'Successfully deleted an employee at 14:42'),(336,'Successfully added an employee at 14:42'),(337,'Details for Taylor have been updated at 14:42'),(339,'Successfully added an employee at 14:43'),(340,'Successfully deleted an employee at 14:43'),(341,'Details for Taylor have been updated at 14:54'),(343,'Details for Taylor have been updated at 15:15'),(347,'Successfully added an employee at 15:52'),(348,'Details for Chacha have been updated at 15:53'),(349,'Details for Chacha have been updated at 15:55'),(350,'Details for Chacha have been updated at 15:56'),(352,'Successfully added an employee at 15:57'),(353,'Details for Immaculate have been updated at 15:58'),(355,'Successfully added an employee at 15:59'),(357,'Successfully added an employee at 16:00'),(358,'Details for Mstahiki have been updated at 16:01'),(371,'Successfully added an employee at 19:18'),(418,'Details for Priyankas have been updated at 20:58'),(419,'Details for Priyanka have been updated at 20:58'),(429,'Details for Taylor have been updated at 10:52'),(435,'Successfully deleted an employee at 12:01'),(436,'Successfully deleted an employee at 12:01'),(437,'Successfully deleted an employee at 12:07'),(438,'Successfully deleted an employee at 12:08'),(439,'Successfully deleted an employee at 12:08'),(440,'Successfully deleted an employee at 12:08'),(441,'Successfully deleted an employee at 12:10'),(442,'Successfully deleted an employee at 12:10'),(443,'Successfully deleted an employee at 12:10'),(444,'Successfully deleted an employee at 12:10'),(445,'Successfully deleted an employee at  number is E3456212:14'),(446,'Successfully deleted an employee at  number is E4629212:22'),(447,'Successfully deleted an employee at  number is E6473512:23'),(448,'Successfully deleted an employee at  number is E6473512:23'),(449,'Successfully deleted an employee at  number is E6473512:23'),(453,'Successfully added an employee at 12:42'),(457,'Successfully added an employee at 16:21'),(459,'Successfully added an employee at 16:21'),(461,'Successfully added an employee at 16:22'),(463,'Successfully added an employee at 16:22'),(465,'Successfully added an employee at 16:27'),(466,'Successfully deleted an employee at 17:46'),(468,'Successfully added an employee at 17:47'),(472,'Details for Morgan have been updated at 18:03'),(489,'Successfully added an employee at 20:27'),(490,'Successfully deleted an employee at 20:27'),(492,'Successfully added an employee at 20:27'),(512,'Successfully added an employee at 16:12'),(515,'Details for Morgan have been updated at 16:15'),(516,'Details for Morgan have been updated at 16:15'),(517,'Details for Supa have been updated at 16:23'),(528,'Details for Beyonce have been updated at 22:30'),(545,'Details for Taylor have been updated at 09:31'),(548,'Details for Taylor have been updated at 11:47'),(549,'Details for Tea have been updated at 11:48'),(551,'Successfully added an employee at 13:02'),(558,'Successfully added an employee at 15:47'),(561,'Details for Jack have been updated at 15:51'),(562,'Details for Smart have been updated at 15:59'),(567,'Details for Hey have been updated at 09:02');
/*!40000 ALTER TABLE `employee_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `id` bigint NOT NULL,
  `employee_id` varchar(255) NOT NULL,
  `employeeImage` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ovvvp79dq21byf7svnuekb6iw` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (452,'E34562','shakes.png','Smart','Shakes','DEVELOPER'),(458,'E46292','ladybun.png','Beyonce','Knowles','DEVOPS'),(460,'E47283','indian.png','Hey','Besties','QA'),(462,'E64735','asian.png','Taylor','Swift','DEVOPS'),(464,'E64737','afroman.png','Jacob','Banks','QA'),(467,'E46242','fluffygirl.png','Morgan','Tasha','DEVELOPER'),(491,'E462920','afrowoman.png','Tea','Marr','DEVELOPER'),(511,'E32716','rastabun.png','Justin','Beiber','QA'),(550,'E23487','asian.png','Lena','Lifts','DEVELOPER'),(557,'E23517','fade.png','Jack','Harlow','QA');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('default',636);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL,
  `confirmPassword` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (523,'Nefertari4*','�n/\0��c�A�y*lو�&�v�E���C�Ԉ�','Tatu'),(525,'Nefertari4*','�n/\0��c�A�y*lو�&�v�E���C�Ԉ�','Mike'),(526,'Nefertari4*','�n/\0��c�A�y*lو�&�v�E���C�Ԉ�','Cute'),(527,'123456Red*','�f���\r��\Zp\'<�etZJ�fyy>��P�*=�\'','Doc'),(583,'Nefertari4*','�n/\0��c�A�y*lو�&�v�E���C�Ԉ�','Capri'),(586,'Systech123*','˷B�5��*BkS���H1�GKY\r�^?S+�','Aqua');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-17 16:21:27
