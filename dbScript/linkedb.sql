-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: linkedb
-- ------------------------------------------------------
-- Server version	5.7.23-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chatmessages`
--

DROP TABLE IF EXISTS `chatmessages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chatmessages` (
  `idchatmessages` int(11) NOT NULL AUTO_INCREMENT,
  `chatid` int(11) NOT NULL,
  `senderid` int(11) NOT NULL,
  `message` longtext,
  PRIMARY KEY (`idchatmessages`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chatmessages`
--

LOCK TABLES `chatmessages` WRITE;
/*!40000 ALTER TABLE `chatmessages` DISABLE KEYS */;
INSERT INTO `chatmessages` VALUES (1,1,6,'Hello Henry'),(2,1,7,'Hi Ted ');
/*!40000 ALTER TABLE `chatmessages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chats`
--

DROP TABLE IF EXISTS `chats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chats` (
  `idchats` int(11) NOT NULL AUTO_INCREMENT,
  `firstuserid` int(11) NOT NULL,
  `seconduserid` int(11) NOT NULL,
  PRIMARY KEY (`idchats`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chats`
--

LOCK TABLES `chats` WRITE;
/*!40000 ALTER TABLE `chats` DISABLE KEYS */;
INSERT INTO `chats` VALUES (1,6,7);
/*!40000 ALTER TABLE `chats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobap`
--

DROP TABLE IF EXISTS `jobap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobap` (
  `idjobap` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `jobname` varchar(45) DEFAULT NULL,
  `jobposition` varchar(45) DEFAULT NULL,
  `jobcompany` varchar(45) DEFAULT NULL,
  `jobincome` int(11) DEFAULT NULL,
  `jobdescription` mediumtext,
  PRIMARY KEY (`idjobap`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobap`
--

LOCK TABLES `jobap` WRITE;
/*!40000 ALTER TABLE `jobap` DISABLE KEYS */;
INSERT INTO `jobap` VALUES (1,5,'Assistant IT Manager Needed','Assistant IT Manager','Network Corp',1500,'We are in need of an IT assistant for our newly composed IT departement.'),(2,6,'We are hirering for Interns','Intern Architect','Mosbious Designs',1200,'Applicants must have a Bachelor in Architecture and be ready to work hard. Previous work experience not needed. ');
/*!40000 ALTER TABLE `jobap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobapplication`
--

DROP TABLE IF EXISTS `jobapplication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobapplication` (
  `idjobapplication` int(11) NOT NULL AUTO_INCREMENT,
  `jobapid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idjobapplication`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobapplication`
--

LOCK TABLES `jobapplication` WRITE;
/*!40000 ALTER TABLE `jobapplication` DISABLE KEYS */;
INSERT INTO `jobapplication` VALUES (2,1,6);
/*!40000 ALTER TABLE `jobapplication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pinfo`
--

DROP TABLE IF EXISTS `pinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pinfo` (
  `idpinfo` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `school` varchar(45) DEFAULT NULL,
  `bachelor` varchar(45) DEFAULT NULL,
  `master` varchar(45) DEFAULT NULL,
  `doctorate` varchar(45) DEFAULT NULL,
  `workspace` varchar(45) DEFAULT NULL,
  `position` varchar(45) DEFAULT NULL,
  `misc` mediumtext,
  PRIMARY KEY (`idpinfo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pinfo`
--

LOCK TABLES `pinfo` WRITE;
/*!40000 ALTER TABLE `pinfo` DISABLE KEYS */;
INSERT INTO `pinfo` VALUES (1,5,'2nd Westminster HighScool','Computer Science','No Master','No Ph.D','Reynholm Industries','IT','Programming languages: C, C++, C#, Java. Interested in web development and computer maintenance.'),(2,6,'24th Ohaio HighSchool','University of New Your Architecture','Modern Designs','No Ph.D','Mosbious Designs','C.E.O.','Highly interested in modern buildings,skyscrapers and big business office designs. '),(3,7,'New Castle High School','Computer Science Imperial College London','Cognitive Science Imperial College London','No P.hD','Unemployed','','Research interests: Parallel Systems, Cognitive Science  IT expert.');
/*!40000 ALTER TABLE `pinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postcomment`
--

DROP TABLE IF EXISTS `postcomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `postcomment` (
  `idpostcomment` int(11) NOT NULL AUTO_INCREMENT,
  `postid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `comment` mediumtext,
  PRIMARY KEY (`idpostcomment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postcomment`
--

LOCK TABLES `postcomment` WRITE;
/*!40000 ALTER TABLE `postcomment` DISABLE KEYS */;
/*!40000 ALTER TABLE `postcomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postinterest`
--

DROP TABLE IF EXISTS `postinterest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `postinterest` (
  `idpostinterest` int(11) NOT NULL AUTO_INCREMENT,
  `postid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpostinterest`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postinterest`
--

LOCK TABLES `postinterest` WRITE;
/*!40000 ALTER TABLE `postinterest` DISABLE KEYS */;
INSERT INTO `postinterest` VALUES (3,3,6);
/*!40000 ALTER TABLE `postinterest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posts` (
  `idposts` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `text` longtext,
  `pmedia` longblob,
  `postdate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idposts`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (3,5,'Hello Everybody',NULL,'2018-09-30 04:26:28');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relations`
--

DROP TABLE IF EXISTS `relations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relations` (
  `idrelations` int(11) NOT NULL AUTO_INCREMENT,
  `firstuserid` int(11) DEFAULT NULL,
  `seconduserid` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idrelations`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relations`
--

LOCK TABLES `relations` WRITE;
/*!40000 ALTER TABLE `relations` DISABLE KEYS */;
INSERT INTO `relations` VALUES (1,7,6,1),(2,5,6,1);
/*!40000 ALTER TABLE `relations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `idusers` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `telnumber` bigint(10) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`idusers`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admingk@gmail.com','12345','Vassilis','Gkatsis','Admin',123456789,NULL),(4,'adminsevas@gmail.com','12345','Giannis','Sevaslidis','Admin',123456789,NULL),(5,'trenneman@gmail.com','123','Roy','Treneman','user',123456789,'/home/vassilis/eclipse-workspace/LinkedIn/WebContent/images/null'),(6,'tedy@gmail.com','123','Ted','Mosby','user',123456789,'/home/vassilis/eclipse-workspace/LinkedIn/WebContent/images/null'),(7,'henryIII@gmail.com','123','Henry ','Third','user',123456789,'/home/vassilis/eclipse-workspace/LinkedIn/WebContent/images/null');
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

-- Dump completed on 2018-09-30  5:51:22
