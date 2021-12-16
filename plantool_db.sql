-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: plantool
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `assignment` (
  `projectid` int DEFAULT NULL,
  `subprojectid` int DEFAULT NULL,
  `taskid` int DEFAULT NULL,
  `subtaskid` int DEFAULT NULL,
  `userid` int DEFAULT NULL,
  KEY `projectid_ass` (`projectid`),
  KEY `subprojectid_ass` (`subprojectid`),
  KEY `taskid_ass` (`taskid`),
  KEY `userid_ass` (`userid`),
  KEY `fksubtaskid_idx` (`subtaskid`),
  CONSTRAINT `fksubtaskid` FOREIGN KEY (`subtaskid`) REFERENCES `subtask` (`subtaskid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `projectid_ass` FOREIGN KEY (`projectid`) REFERENCES `project` (`projectid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `subprojectid` FOREIGN KEY (`subprojectid`) REFERENCES `subproject` (`subprojectid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `taskid` FOREIGN KEY (`taskid`) REFERENCES `task` (`taskid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userid_ass` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment`
--

LOCK TABLES `assignment` WRITE;
/*!40000 ALTER TABLE `assignment` DISABLE KEYS */;
INSERT INTO `assignment` VALUES (33,NULL,NULL,NULL,1),(33,NULL,NULL,NULL,2),(33,NULL,NULL,NULL,10);
/*!40000 ALTER TABLE `assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `projectid` int NOT NULL AUTO_INCREMENT,
  `projectname` varchar(100) DEFAULT NULL,
  `projectstartdate` date DEFAULT NULL,
  `projectenddate` date DEFAULT NULL,
  `projectdeadline` date DEFAULT NULL,
  `projecthoursallo` int DEFAULT NULL,
  `projecthoursused` int DEFAULT NULL,
  `projectleader` int DEFAULT NULL,
  `projectdescrip` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`projectid`),
  UNIQUE KEY `projectname_UNIQUE` (`projectname`),
  KEY `projectleader_idx` (`projectleader`),
  CONSTRAINT `projectleader` FOREIGN KEY (`projectleader`) REFERENCES `user` (`userid`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (33,'Dødsdruk','2021-12-17','2021-12-19','2021-12-19',12,0,3,'Helt i hegnet');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projectskills`
--

DROP TABLE IF EXISTS `projectskills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `projectskills` (
  `projectid` int DEFAULT NULL,
  `skillid` int DEFAULT NULL,
  KEY `projectid_idx` (`projectid`),
  KEY `projectskillid_idx` (`skillid`),
  CONSTRAINT `projectid` FOREIGN KEY (`projectid`) REFERENCES `project` (`projectid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `projectskillid` FOREIGN KEY (`skillid`) REFERENCES `skills` (`skillid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projectskills`
--

LOCK TABLES `projectskills` WRITE;
/*!40000 ALTER TABLE `projectskills` DISABLE KEYS */;
INSERT INTO `projectskills` VALUES (33,4),(33,9);
/*!40000 ALTER TABLE `projectskills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `skills` (
  `skillid` int NOT NULL AUTO_INCREMENT,
  `skillname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`skillid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skills`
--

LOCK TABLES `skills` WRITE;
/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
INSERT INTO `skills` VALUES (1,'Java'),(2,'Python'),(3,'C++'),(4,'MySQL'),(5,'Ruby'),(6,'JavaScript'),(7,'HTML'),(8,'CSS'),(9,'Ølbowling');
/*!40000 ALTER TABLE `skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subproject`
--

DROP TABLE IF EXISTS `subproject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `subproject` (
  `subprojectid` int NOT NULL AUTO_INCREMENT,
  `projectid` int DEFAULT NULL,
  `subprojectname` varchar(100) DEFAULT NULL,
  `subprojectstartdate` date DEFAULT NULL,
  `subprojectenddate` date DEFAULT NULL,
  `subprojectdeadline` date DEFAULT NULL,
  `subprojecthoursallo` int DEFAULT NULL,
  `subprojecthoursused` int DEFAULT NULL,
  `subprojectdescription` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`subprojectid`),
  UNIQUE KEY `subprojectname_UNIQUE` (`subprojectname`),
  KEY `fkprojectid_idx` (`projectid`),
  CONSTRAINT `fkprojectid` FOREIGN KEY (`projectid`) REFERENCES `project` (`projectid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subproject`
--

LOCK TABLES `subproject` WRITE;
/*!40000 ALTER TABLE `subproject` DISABLE KEYS */;
/*!40000 ALTER TABLE `subproject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subtask`
--

DROP TABLE IF EXISTS `subtask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `subtask` (
  `subtaskid` int NOT NULL AUTO_INCREMENT,
  `taskid` int DEFAULT NULL,
  `subtaskname` varchar(100) DEFAULT NULL,
  `subtaskstartdate` date DEFAULT NULL,
  `subtaskenddate` date DEFAULT NULL,
  `subtaskdeadline` date DEFAULT NULL,
  `subtaskhoursallo` int DEFAULT NULL,
  `subtaskhoursused` int DEFAULT NULL,
  `subtaskdescription` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`subtaskid`),
  UNIQUE KEY `subtaskname_UNIQUE` (`subtaskname`),
  KEY `fktaskid_idx` (`taskid`),
  CONSTRAINT `fktaskid` FOREIGN KEY (`taskid`) REFERENCES `task` (`taskid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subtask`
--

LOCK TABLES `subtask` WRITE;
/*!40000 ALTER TABLE `subtask` DISABLE KEYS */;
/*!40000 ALTER TABLE `subtask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `taskid` int NOT NULL AUTO_INCREMENT,
  `subprojectid` int DEFAULT NULL,
  `taskename` varchar(100) DEFAULT NULL,
  `taskstartdate` date DEFAULT NULL,
  `taskenddate` date DEFAULT NULL,
  `taskdeadline` date DEFAULT NULL,
  `taskhoursallo` int DEFAULT NULL,
  `taskhoursused` int DEFAULT NULL,
  `taskdescription` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`taskid`),
  UNIQUE KEY `taskename_UNIQUE` (`taskename`),
  KEY `fksubprojectid_idx` (`subprojectid`),
  CONSTRAINT `fksubprojectid` FOREIGN KEY (`subprojectid`) REFERENCES `subproject` (`subprojectid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `projectleader` tinyint(1) DEFAULT NULL,
  `projectid` int DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fkprojectid_idx` (`projectid`),
  CONSTRAINT `fkuserprojectid` FOREIGN KEY (`projectid`) REFERENCES `project` (`projectid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Kevin','kevin@mail.com','123',NULL,NULL),(2,'Michael','mic@mail.com','1235',NULL,NULL),(3,'Jonas','jonas@mail.com','123',1,NULL),(4,'Lars','lars@mail.com','11',NULL,NULL),(9,'werwer','nmail@.com','1249191',NULL,NULL),(10,'Lone','lone@mail.com','123',1,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userskills`
--

DROP TABLE IF EXISTS `userskills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `userskills` (
  `userid` int DEFAULT NULL,
  `skillid` int DEFAULT NULL,
  KEY `userskillid_idx` (`skillid`),
  KEY `userid` (`userid`),
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userskillid` FOREIGN KEY (`skillid`) REFERENCES `skills` (`skillid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userskills`
--

LOCK TABLES `userskills` WRITE;
/*!40000 ALTER TABLE `userskills` DISABLE KEYS */;
/*!40000 ALTER TABLE `userskills` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-13 23:45:39
