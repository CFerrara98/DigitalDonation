-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versione server:              8.0.27 - MySQL Community Server - GPL
-- S.O. server:                  Win64
-- HeidiSQL Versione:            11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dump della struttura del database digitaldonation
DROP DATABASE IF EXISTS `digitaldonation`;
CREATE DATABASE IF NOT EXISTS `digitaldonation` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `digitaldonation`;

-- Dump della struttura di tabella digitaldonation.donatore
DROP TABLE IF EXISTS `donatore`;
CREATE TABLE IF NOT EXISTS `donatore` (
  `data_di_nascita` datetime(6) DEFAULT NULL,
  `luogo_di_nascita` varchar(255) DEFAULT NULL,
  `residenza` varchar(255) DEFAULT NULL,
  `codice_fiscale_utente` varchar(255) NOT NULL,
  PRIMARY KEY (`codice_fiscale_utente`),
  CONSTRAINT `FK8k8dbpwjlwoq8bgcxifolut62` FOREIGN KEY (`codice_fiscale_utente`) REFERENCES `utente` (`codice_fiscale_utente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella digitaldonation.donazione
DROP TABLE IF EXISTS `donazione`;
CREATE TABLE IF NOT EXISTS `donazione` (
  `id_donazione` bigint NOT NULL,
  `data_donazione` datetime(6) DEFAULT NULL,
  `tipo_donazione` varchar(255) DEFAULT NULL,
  `id_tessera` bigint DEFAULT NULL,
  PRIMARY KEY (`id_donazione`),
  KEY `FK1ubqg88yg76r02tv0chigiw0g` (`id_tessera`),
  CONSTRAINT `FK1ubqg88yg76r02tv0chigiw0g` FOREIGN KEY (`id_tessera`) REFERENCES `tesserino` (`id_tessera`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella digitaldonation.guest
DROP TABLE IF EXISTS `guest`;
CREATE TABLE IF NOT EXISTS `guest` (
  `codice_fiscale_guest` varchar(255) NOT NULL,
  `cognome` varchar(255) DEFAULT NULL,
  `gruppo_sanguigno` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `patologie` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codice_fiscale_guest`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella digitaldonation.hibernate_sequence
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella digitaldonation.indisponibilita
DROP TABLE IF EXISTS `indisponibilita`;
CREATE TABLE IF NOT EXISTS `indisponibilita` (
  `id_indisponibilita` bigint NOT NULL,
  `data_prossima_disponibilita` datetime(6) DEFAULT NULL,
  `motivazioni` varchar(255) DEFAULT NULL,
  `nome_medico` varchar(255) DEFAULT NULL,
  `codice_fiscale_donatore` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_indisponibilita`),
  KEY `FKap1s57tlbruhmts92d2p64apr` (`codice_fiscale_donatore`),
  CONSTRAINT `FKap1s57tlbruhmts92d2p64apr` FOREIGN KEY (`codice_fiscale_donatore`) REFERENCES `donatore` (`codice_fiscale_utente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella digitaldonation.operatore
DROP TABLE IF EXISTS `operatore`;
CREATE TABLE IF NOT EXISTS `operatore` (
  `codice_fiscale_utente` varchar(255) NOT NULL,
  `sede_locale_codice_identificativo` bigint DEFAULT NULL,
  PRIMARY KEY (`codice_fiscale_utente`),
  KEY `FKn7bx56kkmp0sptrvjq99pps2a` (`sede_locale_codice_identificativo`),
  CONSTRAINT `FKas4rpftceqxk4cwdmuo33mju2` FOREIGN KEY (`codice_fiscale_utente`) REFERENCES `utente` (`codice_fiscale_utente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKn7bx56kkmp0sptrvjq99pps2a` FOREIGN KEY (`sede_locale_codice_identificativo`) REFERENCES `sede_locale` (`id_sede`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella digitaldonation.sede_locale
DROP TABLE IF EXISTS `sede_locale`;
CREATE TABLE IF NOT EXISTS `sede_locale` (
  `id_sede` bigint NOT NULL,
  `via` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_sede`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella digitaldonation.seduta
DROP TABLE IF EXISTS `seduta`;
CREATE TABLE IF NOT EXISTS `seduta` (
  `id_seduta` bigint NOT NULL,
  `data_fine_prenotazione` datetime(6) DEFAULT NULL,
  `data_inizio_prenotazione` datetime(6) DEFAULT NULL,
  `data_seduta` datetime(6) DEFAULT NULL,
  `luogo` varchar(255) DEFAULT NULL,
  `numero_partecipanti` int NOT NULL,
  `ora_fine` time DEFAULT NULL,
  `ora_inizio` time DEFAULT NULL,
  `id_sede_locale` bigint DEFAULT NULL,
  PRIMARY KEY (`id_seduta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella digitaldonation.seduta_donatore
DROP TABLE IF EXISTS `seduta_donatore`;
CREATE TABLE IF NOT EXISTS `seduta_donatore` (
  `id_seduta` bigint NOT NULL,
  `codice_fiscale_donatore` varchar(255) NOT NULL,
  KEY `FKgxv0a8ljdbufxh2322hpiuqkm` (`id_seduta`),
  KEY `FKqpr5pmry0q5mqjh3pwko21n7i` (`codice_fiscale_donatore`),
  CONSTRAINT `FKgxv0a8ljdbufxh2322hpiuqkm` FOREIGN KEY (`id_seduta`) REFERENCES `seduta` (`id_seduta`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKqpr5pmry0q5mqjh3pwko21n7i` FOREIGN KEY (`codice_fiscale_donatore`) REFERENCES `donatore` (`codice_fiscale_utente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella digitaldonation.seduta_guest
DROP TABLE IF EXISTS `seduta_guest`;
CREATE TABLE IF NOT EXISTS `seduta_guest` (
  `id_seduta` bigint NOT NULL,
  `codice_fiscale_guest` varchar(255) NOT NULL,
  KEY `FKhno016rtvn84hf0p0m8u7vrdg` (`id_seduta`),
  KEY `FK3cbpo6lvsi85pkr2ec9txkbn6` (`codice_fiscale_guest`),
  CONSTRAINT `FK3cbpo6lvsi85pkr2ec9txkbn6` FOREIGN KEY (`codice_fiscale_guest`) REFERENCES `guest` (`codice_fiscale_guest`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKhno016rtvn84hf0p0m8u7vrdg` FOREIGN KEY (`id_seduta`) REFERENCES `seduta` (`id_seduta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella digitaldonation.tesserino
DROP TABLE IF EXISTS `tesserino`;
CREATE TABLE IF NOT EXISTS `tesserino` (
  `id_tessera` bigint NOT NULL,
  `data_rilascio` datetime(6) DEFAULT NULL,
  `codice_fiscale_donatore` varchar(255) DEFAULT NULL,
  `gruppo_sanguigno` varchar(255) DEFAULT NULL,
  `img_source` varchar(255) DEFAULT NULL,
  `numero_matricola` int DEFAULT NULL,
  `rh` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_tessera`),
  UNIQUE KEY `UK_in4w6e2quoskt6ev6sqvxceio` (`codice_fiscale_donatore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella digitaldonation.utente
DROP TABLE IF EXISTS `utente`;
CREATE TABLE IF NOT EXISTS `utente` (
  `codice_fiscale_utente` varchar(255) NOT NULL,
  `cognome` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codice_fiscale_utente`),
  UNIQUE KEY `UK_gxvq4mjswnupehxnp35vawmo2` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- L’esportazione dei dati non era selezionata.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
