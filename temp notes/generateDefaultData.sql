-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: carportDB
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `AvailableOptions`
--

LOCK TABLES `AvailableOptions` WRITE;
/*!40000 ALTER TABLE `AvailableOptions` DISABLE KEYS */;
INSERT INTO `AvailableOptions` VALUES ('Eternitplader','shedCovering'),('Fibercement','shedCovering'),('Latexattrap','shedCovering'),('length','240'),('length','270'),('length','300'),('length','330'),('length','360'),('length','390'),('length','420'),('length','450'),('length','480'),('length','510'),('length','540'),('length','570'),('length','600'),('length','630'),('length','660'),('length','690'),('length','720'),('length','750'),('length','780'),('roofFlat','Plasttrapezplader'),('roofRaised','Betontagsten - Brun'),('roofRaised','Betontagsten - Rød'),('roofRaised','Betontagsten - Sort'),('roofRaised','Betontagsten - Teglrød'),('roofRaised','Eternittag B6 - Grå'),('roofRaised','Eternittag B6 - Mokkabrun'),('roofRaised','Eternittag B6 - Rødbrun'),('roofRaised','Eternittag B6 - Sort'),('roofRaised','Eternittag B6 - Teglrød'),('roofRaised','Eternittag B7 - Grå'),('roofRaised','Eternittag B7 - Mokkabrun'),('roofRaised','Eternittag B7 - Rødbrun'),('roofRaised','Eternittag B7 - Rødflammet'),('roofRaised','Eternittag B7 - Sort'),('roofRaised','Eternittag B7 - Teglrød'),('roofSlope','15'),('roofSlope','20'),('roofSlope','25'),('roofSlope','30'),('roofSlope','35'),('roofSlope','40'),('roofSlope','45'),('shedCovering','Eternitplader'),('shedCovering','Fibercement'),('shedCovering','Skumpaneler'),('shedCovering','Trykimprægneret træ'),('shedLength','150'),('shedLength','180'),('shedLength','210'),('shedLength','240'),('shedLength','270'),('shedLength','300'),('shedLength','330'),('shedLength','360'),('shedLength','390'),('shedLength','420'),('shedLength','450'),('shedLength','480'),('shedLength','510'),('shedLength','540'),('shedLength','570'),('shedLength','600'),('shedLength','630'),('shedLength','660'),('shedLength','690'),('shedWidth','210'),('shedWidth','240'),('shedWidth','270'),('shedWidth','300'),('shedWidth','330'),('shedWidth','360'),('shedWidth','390'),('shedWidth','420'),('shedWidth','450'),('shedWidth','480'),('shedWidth','510'),('shedWidth','540'),('shedWidth','570'),('shedWidth','600'),('shedWidth','630'),('shedWidth','660'),('shedWidth','690'),('shedWidth','720'),('Skumpaneler','shedCovering'),('Trykimprægneret træ','shedCovering'),('width','240'),('width','270'),('width','300'),('width','330'),('width','360'),('width','390'),('width','420'),('width','450'),('width','480'),('width','510'),('width','540'),('width','570'),('width','600'),('width','630'),('width','660'),('width','690'),('width','720'),('width','750');
/*!40000 ALTER TABLE `AvailableOptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES ('admin','admin');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-16 11:10:05
