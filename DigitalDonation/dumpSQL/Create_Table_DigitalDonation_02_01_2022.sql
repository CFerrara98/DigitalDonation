CREATE DATABASE  IF NOT EXISTS `digitaldonation` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `digitaldonation`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: digitaldonation
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `donatore`
--

DROP TABLE IF EXISTS `donatore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donatore` (
  `data_di_nascita` datetime(6) DEFAULT NULL,
  `luogo_di_nascita` varchar(255) DEFAULT NULL,
  `residenza` varchar(255) DEFAULT NULL,
  `codice_fiscale_utente` varchar(255) NOT NULL,
  PRIMARY KEY (`codice_fiscale_utente`),
  CONSTRAINT `FK8k8dbpwjlwoq8bgcxifolut62` FOREIGN KEY (`codice_fiscale_utente`) REFERENCES `utente` (`codice_fiscale_utente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `donazione`
--

DROP TABLE IF EXISTS `donazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donazione` (
  `id_donazione` bigint NOT NULL,
  `data_donazione` datetime(6) DEFAULT NULL,
  `tipo_donazione` varchar(255) DEFAULT NULL,
  `cf_tessera` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_donazione`),
  KEY `FKgmnflltpl1gfx3kefrdlat0ms` (`cf_tessera`),
  CONSTRAINT `FKgmnflltpl1gfx3kefrdlat0ms` FOREIGN KEY (`cf_tessera`) REFERENCES `tesserino` (`codice_fiscale_donatore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `guest`
--

DROP TABLE IF EXISTS `guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guest` (
  `codice_fiscale_guest` varchar(255) NOT NULL,
  `cognome` varchar(255) DEFAULT NULL,
  `gruppo_sanguigno` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `patologie` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codice_fiscale_guest`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `indisponibilita`
--

DROP TABLE IF EXISTS `indisponibilita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `indisponibilita` (
  `id_indisponibilita` bigint NOT NULL,
  `codice_fiscale_donatore` varchar(255) DEFAULT NULL,
  `data_prossima_disponibilita` datetime(6) DEFAULT NULL,
  `motivazioni` varchar(255) DEFAULT NULL,
  `nome_medico` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_indisponibilita`),
  KEY `FKap1s57tlbruhmts92d2p64apr` (`codice_fiscale_donatore`),
  CONSTRAINT `FKap1s57tlbruhmts92d2p64apr` FOREIGN KEY (`codice_fiscale_donatore`) REFERENCES `donatore` (`codice_fiscale_utente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `operatore`
--

DROP TABLE IF EXISTS `operatore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operatore` (
  `codice_fiscale_utente` varchar(255) NOT NULL,
  `sede_locale_codice_identificativo` bigint DEFAULT NULL,
  PRIMARY KEY (`codice_fiscale_utente`),
  KEY `FKn7bx56kkmp0sptrvjq99pps2a` (`sede_locale_codice_identificativo`),
  CONSTRAINT `FKas4rpftceqxk4cwdmuo33mju2` FOREIGN KEY (`codice_fiscale_utente`) REFERENCES `utente` (`codice_fiscale_utente`),
  CONSTRAINT `FKn7bx56kkmp0sptrvjq99pps2a` FOREIGN KEY (`sede_locale_codice_identificativo`) REFERENCES `sede_locale` (`id_sede`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sede_locale`
--

DROP TABLE IF EXISTS `sede_locale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sede_locale` (
  `id_sede` bigint NOT NULL,
  `via` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_sede`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `seduta`
--

DROP TABLE IF EXISTS `seduta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seduta` (
  `id_seduta` bigint NOT NULL,
  `data_fine_prenotazione` datetime(6) DEFAULT NULL,
  `data_inizio_prenotazione` datetime(6) DEFAULT NULL,
  `data_seduta` datetime(6) DEFAULT NULL,
  `luogo` varchar(255) DEFAULT NULL,
  `numero_partecipanti` int DEFAULT NULL,
  `ora_fine` time DEFAULT NULL,
  `ora_inizio` time DEFAULT NULL,
  `id_sede_locale` bigint DEFAULT NULL,
  PRIMARY KEY (`id_seduta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `seduta_donatore`
--

DROP TABLE IF EXISTS `seduta_donatore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seduta_donatore` (
  `id_seduta` bigint NOT NULL,
  `codice_fiscale_donatore` varchar(255) NOT NULL,
  KEY `FKqpr5pmry0q5mqjh3pwko21n7i` (`codice_fiscale_donatore`),
  KEY `FKgxv0a8ljdbufxh2322hpiuqkm` (`id_seduta`),
  CONSTRAINT `FKgxv0a8ljdbufxh2322hpiuqkm` FOREIGN KEY (`id_seduta`) REFERENCES `seduta` (`id_seduta`),
  CONSTRAINT `FKqpr5pmry0q5mqjh3pwko21n7i` FOREIGN KEY (`codice_fiscale_donatore`) REFERENCES `donatore` (`codice_fiscale_utente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `seduta_guest`
--

DROP TABLE IF EXISTS `seduta_guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seduta_guest` (
  `id_seduta` bigint NOT NULL,
  `codice_fiscale_guest` varchar(255) NOT NULL,
  KEY `FK3cbpo6lvsi85pkr2ec9txkbn6` (`codice_fiscale_guest`),
  KEY `FKhno016rtvn84hf0p0m8u7vrdg` (`id_seduta`),
  CONSTRAINT `FK3cbpo6lvsi85pkr2ec9txkbn6` FOREIGN KEY (`codice_fiscale_guest`) REFERENCES `guest` (`codice_fiscale_guest`),
  CONSTRAINT `FKhno016rtvn84hf0p0m8u7vrdg` FOREIGN KEY (`id_seduta`) REFERENCES `seduta` (`id_seduta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tesserino`
--

DROP TABLE IF EXISTS `tesserino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tesserino` (
  `codice_fiscale_donatore` varchar(255) NOT NULL,
  `data_rilascio` datetime(6) DEFAULT NULL,
  `gruppo_sanguigno` varchar(255) DEFAULT NULL,
  `id_tessera` bigint DEFAULT NULL,
  `img_source` varchar(255) DEFAULT NULL,
  `numero_matricola` int DEFAULT NULL,
  `rh` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codice_fiscale_donatore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `codice_fiscale_utente` varchar(255) NOT NULL,
  `cognome` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codice_fiscale_utente`),
  UNIQUE KEY `UK_gxvq4mjswnupehxnp35vawmo2` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-18 18:11:19
