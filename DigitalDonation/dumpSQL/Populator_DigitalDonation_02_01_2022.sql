CREATE DATABASE  IF NOT EXISTS `digitaldonation` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `digitaldonation`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: digitaldonation
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
-- Dumping data for table `donatore`
--

LOCK TABLES `donatore` WRITE;
/*!40000 ALTER TABLE `donatore` DISABLE KEYS */;
INSERT INTO `donatore` VALUES ('2000-05-12 02:00:00.000000','Salerno','Via casa del maestro Mazza 22','ABRBAK53R70D887V'),('1997-12-29 01:00:00.000000','Antessano','Via diaz, 10, Pollenostrocchio','AIENAN44I74X492I'),('1994-06-12 02:00:00.000000','Cerrocupo','Via Santa Monica, 42/b, Salerno SA','PPEPPK00A53H703Y'),('2000-09-11 02:00:00.000000','Avellino','Corso Ettore Padovano, 44, Pagani SA','SPGMRK00P51A509L');
/*!40000 ALTER TABLE `donatore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `donazione`
--

LOCK TABLES `donazione` WRITE;
/*!40000 ALTER TABLE `donazione` DISABLE KEYS */;
INSERT INTO `donazione` VALUES (3,'2022-01-11 01:00:00.000000','cito','PPEPPK00A53H703Y'),(4,'2022-01-01 01:00:00.000000','sangue','AIENAN44I74X492I'),(6,'2021-12-31 01:00:00.000000','cito','AIENAN44I74X492I'),(721,'2021-12-31 17:58:32.213000','sangue','ABRBAK53R70D887T'),(722,'2021-12-31 17:58:32.245000','cito','ABRBAK53R70D887T'),(723,'2021-12-31 17:58:32.286000','sangue','PPEPPK00A53H703Y'),(724,'2021-12-31 17:58:32.325000','cito','ACNCZY45X38I793C'),(725,'2021-12-31 17:58:32.353000','sangue','PPEPPK00A53H703Y'),(726,'2021-12-31 17:58:32.391000','cito','AFCLCW22Z02F362J'),(727,'2021-12-31 17:58:32.429000','sangue','PPEPPK00A53H703Y'),(728,'2021-12-31 17:58:32.466000','cito','PPEPPK00A53H703Y'),(729,'2021-12-31 17:58:32.663000','sangue','AFKIWW94S77T163B'),(730,'2021-12-31 17:58:32.768000','cito','AFKIWW94S77T163B'),(731,'2021-12-31 17:58:32.834000','sangue','PPEPPK00A53H703Y'),(732,'2021-12-31 17:58:32.901000','cito','PPEPPK00A53H703Y'),(733,'2021-12-31 17:58:32.928000','cito','PPEPPK00A53H703Y'),(734,'2021-12-31 17:58:32.964000','sangue','PPEPPK00A53H703Y');
/*!40000 ALTER TABLE `donazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `guest`
--

LOCK TABLES `guest` WRITE;
/*!40000 ALTER TABLE `guest` DISABLE KEYS */;
INSERT INTO `guest` VALUES ('AABBHQ79C38L971N','Cito','A+','Emiliano','MALATTIA DIVERTICOLARE','5643396367'),('AHVLJG07Y77O594Y','Zito','AB-','Daniela','DISFUNZIONE ERETTILE','2523926899'),('ALRPCW05I76A575R','Spagna','0-','Marco','MALATTIA DIVERTICOLARE','2444326477'),('AXUQYA24X88Y477Y','Mazza','A-','Emiliano','//','+25 5975579781'),('AYEVFX07N27I662Q','Siepe','AB-','Enrico','MALATTIA DI ALZHEIMER','2417243690'),('BGWTSL98L41A247B','Pacifico','0+','Daniela','MALATTIA DI ALZHEIMER','1047176553'),('BJANRY99A30M669Y','Sapere','A-','Emiliano','ARTRITI INFETTIVE','7751575163'),('BSMDVR40H45D561D','De Martino','A+','Loredana','MALATTIA DI PARKINSON','7095163942'),('CDQSCB82N71B308C','Basile','0+','Loredana','//','+62 1094299487'),('CFDZLP88L04S369F','Ferrara','AB+','Marco','//','5012026029'),('CGXXST64E46R693X','Abate','0-','Ornella','//','6311885692'),('OOEMRK00A53H703Y','Fiorella','AB+','Ornella','Ochhiali','3409588624'),('SOLMRK00P51A509L','Da Solo','0+','Utente','Da Solo','1254799865'),('SPAARK00P51A509L','Delle Donne','0+','Francesco','Nulla','3407066985');
/*!40000 ALTER TABLE `guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (7),(7);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `indisponibilita`
--

LOCK TABLES `indisponibilita` WRITE;
/*!40000 ALTER TABLE `indisponibilita` DISABLE KEYS */;
/*!40000 ALTER TABLE `indisponibilita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `operatore`
--

LOCK TABLES `operatore` WRITE;
/*!40000 ALTER TABLE `operatore` DISABLE KEYS */;
INSERT INTO `operatore` VALUES ('ABRBAK53R70D887T',1),('AAABAK53R70D887T',678);
/*!40000 ALTER TABLE `operatore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sede_locale`
--

LOCK TABLES `sede_locale` WRITE;
/*!40000 ALTER TABLE `sede_locale` DISABLE KEYS */;
INSERT INTO `sede_locale` VALUES (1,'Via castel Nuvola'),(676,'Pella Via txqblq 72'),(677,'Brunate Via estyg 74'),(678,'Serra de\' Conti Via ksxwckrs 76'),(679,'Vallata Via goywnv 17'),(680,'Marrubiu Via wtyrm 12');
/*!40000 ALTER TABLE `sede_locale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `seduta`
--

LOCK TABLES `seduta` WRITE;
/*!40000 ALTER TABLE `seduta` DISABLE KEYS */;
INSERT INTO `seduta` VALUES (2,'2022-01-18 01:00:00.000000','2022-01-12 01:00:00.000000','2022-02-12 01:00:00.000000','Via Giuseppeverdi Salerno 84010 SA',55,'18:22:00','12:22:00',1),(5,'2022-01-29 01:00:00.000000','2022-01-24 01:00:00.000000','2022-02-28 01:00:00.000000','Via Scafati, 7 Avellino 84100 SA',1,'12:00:00','08:00:00',1),(681,'2022-06-14 00:00:00.000000','2022-05-19 00:00:00.000000','2021-12-31 17:58:30.331000','Appignano Via nprtcaif 12',40,'21:58:00','03:34:00',678),(683,'2022-06-03 00:00:00.000000','2022-04-06 00:00:00.000000','2021-12-31 17:58:30.443000','Dolegna del Collio Via isegna 98',47,'17:28:00','11:45:00',676),(684,'2022-08-03 00:00:00.000000','2022-06-18 00:00:00.000000','2021-12-31 17:58:30.544000','San Lazzaro di Savena Via Casa 95',62,'04:31:00','23:33:00',679),(685,'2022-10-09 00:00:00.000000','2022-08-15 00:00:00.000000','2021-12-31 17:58:30.609000','Tessennano Via Ornella 93',75,'14:09:00','09:37:00',679),(686,'2022-10-01 00:00:00.000000','2022-08-22 00:00:00.000000','2021-12-31 17:58:30.648000','Madesimo Via Madesimo 44',29,'13:48:00','08:22:00',677),(687,'2022-08-03 00:00:00.000000','2022-07-13 00:00:00.000000','2021-12-31 17:58:30.685000','Incisa in Val d\'Arno Via rullo 67',20,'04:32:00','05:20:00',679),(688,'2023-02-04 00:00:00.000000','2022-12-06 00:00:00.000000','2021-12-31 17:58:30.720000','Chambave Via Pollo 49',63,'15:29:00','16:46:00',678),(689,'2022-07-04 00:00:00.000000','2022-05-24 00:00:00.000000','2021-12-31 17:58:30.764000','Tremestieri Etneo Via bangladesh 64',21,'07:13:00','13:11:00',677),(692,'2023-01-16 00:00:00.000000','2022-12-01 00:00:00.000000','2021-12-31 17:58:30.881000','Malo Via Mai 74',20,'09:34:00','09:08:00',677),(693,'2022-12-01 23:00:00.000000','2022-10-16 00:00:00.000000','2021-12-31 17:58:30.913000','Lama dei Peligni Via Estonia 97',58,'17:09:00','11:04:00',678);
/*!40000 ALTER TABLE `seduta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `seduta_donatore`
--

LOCK TABLES `seduta_donatore` WRITE;
/*!40000 ALTER TABLE `seduta_donatore` DISABLE KEYS */;
INSERT INTO `seduta_donatore` VALUES (693,'AIEVGN44I74X492I'),(681,'ACNCZY45X38I793C'),(681,'ACNCZY45X38I793C'),(686,'ACNCZY45X38I793C'),(689,'AFCLCW22Z02F362J'),(681,'ABRBAK53R70D887V'),(681,'AJQJLS56M07J753M'),(688,'ABRBAK53R70D887T'),(694,'AFCLCW22Z02F362J'),(2,'ACNCZY45X38I793C'),(2,'SPGMRK00P51A509L'),(683,'SPGMRK00P51A509L'),(687,'AFKIWW94S77T163B'),(687,'SPGMRK00P51A509L'),(684,'PPEPPK00A53H703Y'),(693,'PPEPPK00A53H703Y');
/*!40000 ALTER TABLE `seduta_donatore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `seduta_guest`
--

LOCK TABLES `seduta_guest` WRITE;
/*!40000 ALTER TABLE `seduta_guest` DISABLE KEYS */;
INSERT INTO `seduta_guest` VALUES (681,'AABBHQ79C38L971N'),(681,'AXUQYA24X88Y477Y'),(683,'AXUQYA24X88Y477Y'),(683,'CDQSCB82N71B308C'),(681,'AYEVFX07N27I662Q'),(687,'CGXXST64E46R693X'),(686,'BGWTSL98L41A247B'),(692,'BJANRY99A30M669Y'),(684,'CGXXST64E46R693X'),(684,'BSMDVR40H45D561D'),(685,'BGWTSL98L41A247B'),(683,'BGWTSL98L41A247B'),(2,'SPAARK00P51A509L'),(2,'OOEMRK00A53H703Y'),(5,'SOLMRK00P51A509L');
/*!40000 ALTER TABLE `seduta_guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tesserino`
--

LOCK TABLES `tesserino` WRITE;
/*!40000 ALTER TABLE `tesserino` DISABLE KEYS */;
INSERT INTO `tesserino` VALUES ('ABRBAK53R70D887V','2022-01-18 01:00:00.000000','A',4,'https://drive.google.com/uc?export=view&id=1H6sl6dWBLFS8NCFQPw71Fxdhul3X9HEM',22222,'POS'),('AIENAN44I74X492I','2022-01-18 01:00:00.000000','B',1,'https://drive.google.com/uc?export=view&id=1fIWLDzXcMwzNu9buZ500P84PIW5ruEbx',12598,'POS'),('PPEPPK00A53H703Y','2022-01-18 01:00:00.000000','B',2,'https://drive.google.com/uc?export=view&id=1EoFja3X-C59jjSde7hhMtsQnWiwi_OBC',12547,'POS'),('SPGMRK00P51A509L','2022-01-18 01:00:00.000000','A',3,'https://drive.google.com/uc?export=view&id=1H6sl6dWBLFS8NCFQPw71Fxdhul3X9HEM',66667,'POS');
/*!40000 ALTER TABLE `tesserino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES ('AAABAK53R70D887T','Abate','emailgenrica87@gmail.com','Carmine','e1adc3949ba59abbe56e057f2f883e'),('ABRBAK53R70D887T','Ferrara','operatoredd@gmail.com','Francesco','e1adc3949ba59abbe56e057f2f883e'),('ABRBAK53R70D887V','Peppe','donatoredd@gmail.com','Scoppola','e1adc3949ba59abbe56e057f2f883e'),('AIENAN44I74X492I','Leriex','o.lysytsya@studenti.unisa.it','Penelope','f71c434bbbfdd80d8aa4dda8bc867f6'),('PPEPPK00A53H703Y','Scalli','forzaroma.10@hotmail.it','Ryan','d3fab33f73e2997b24f43df935d2f1d'),('SPGMRK00P51A509L','Spaccia Zito','marikaspagnazito.hp@gmail.com','Marika','12339941846b9adb53c607d807cd841');
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-18 18:19:43
