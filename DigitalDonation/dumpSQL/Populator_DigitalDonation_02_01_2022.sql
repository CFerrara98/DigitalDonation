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

-- Dump dei dati della tabella digitaldonation.donatore: ~0 rows (circa)
/*!40000 ALTER TABLE `donatore` DISABLE KEYS */;
INSERT INTO `donatore` (`data_di_nascita`, `luogo_di_nascita`, `residenza`, `codice_fiscale_utente`) VALUES
	('2022-01-02 17:07:26.000000', 'rabello', 'vietri sulla terra', 'ABRBAK53R70D887T'),
	('1950-04-29 00:00:00.000000', 'Casanova Lerrone', 'San Giovanni in Galdo', 'ABRBAK53R70D887V'),
	('1934-10-09 00:00:00.000000', 'Breia', 'Buttigliera d\'Asti', 'ACNCZY45X38I793C'),
	('1908-01-13 00:00:00.000000', 'San Paolo Solbrito', 'Capriglio', 'AFCLCW22Z02F362J'),
	('1956-12-06 00:00:00.000000', 'Monchio delle Corti', 'Santi Cosma e Damiano', 'AFKIWW94S77T163B'),
	('2009-01-23 00:00:00.000000', 'La Maddalena', 'Grezzana', 'AIEVGN44I74X492I'),
	('1929-09-17 00:00:00.000000', 'Peccioli', 'Cerami', 'AJQJLS56M07J753M');
/*!40000 ALTER TABLE `donatore` ENABLE KEYS */;

-- Dump dei dati della tabella digitaldonation.donazione: ~0 rows (circa)
/*!40000 ALTER TABLE `donazione` DISABLE KEYS */;
INSERT INTO `donazione` (`id_donazione`, `cf_tessera`, `data_donazione`, `tipo_donazione`) VALUES
	(721, 'ABRBAK53R70D887T', '2021-12-31 17:58:32.213000', 'NHPAYWPZ'),
	(722, 'ABRBAK53R70D887T', '2021-12-31 17:58:32.245000', 'YCKJNXERS'),
	(723, 'ABRBAK53R70D887V', '2021-12-31 17:58:32.286000', 'OVBRDRS'),
	(724, 'ACNCZY45X38I793C', '2021-12-31 17:58:32.325000', 'KGHIL'),
	(725, 'ACNCZY45X38I793C', '2021-12-31 17:58:32.353000', 'HPNRAMGBD'),
	(726, 'AFCLCW22Z02F362J', '2021-12-31 17:58:32.391000', 'IRRPW'),
	(727, 'AFCLCW22Z02F362J', '2021-12-31 17:58:32.429000', 'DQIYK'),
	(728, 'AFKIWW94S77T163B', '2021-12-31 17:58:32.466000', 'NNYJKJZ'),
	(729, 'AFKIWW94S77T163B', '2021-12-31 17:58:32.663000', 'FTEOH'),
	(730, 'AFKIWW94S77T163B', '2021-12-31 17:58:32.768000', 'XAMPM'),
	(731, 'AIEVGN44I74X492I', '2021-12-31 17:58:32.834000', 'ULTROJT'),
	(732, 'AIEVGN44I74X492I', '2021-12-31 17:58:32.901000', 'XNXSSER'),
	(733, 'AIEVGN44I74X492I', '2021-12-31 17:58:32.928000', 'RDLIOLGGF'),
	(734, 'AIEVGN44I74X492I', '2021-12-31 17:58:32.964000', 'IIISTSKJP');
/*!40000 ALTER TABLE `donazione` ENABLE KEYS */;

-- Dump dei dati della tabella digitaldonation.guest: ~0 rows (circa)
/*!40000 ALTER TABLE `guest` DISABLE KEYS */;
INSERT INTO `guest` (`codice_fiscale_guest`, `cognome`, `gruppo_sanguigno`, `nome`, `patologie`, `telefono`) VALUES
	('AABBHQ79C38L971N', 'LLTPWPURC', 'A+', 'YBDTAXL', 'MALATTIA DIVERTICOLARE', '5643396367'),
	('AGEVAK54X80D358Q', 'XVYBZASER', 'A-', 'CGICCX', '//', '+25 5975579781'),
	('AHVLJG07Y77O594Y', 'NVIUR', 'AB-', 'WBUHJNHKA', 'DISFUNZIONE ERETTILE', '2523926899'),
	('AKLSFW35M87M057T', 'FCBVY', 'AB-', 'RKLMFDL', 'MALATTIA DI ALZHEIMER', '2417243690'),
	('ALRPCW05I76A575R', 'VBMGFBR', '0-', 'DDPIY', 'MALATTIA DIVERTICOLARE', '2444326477'),
	('AMMZTV18B15Q282A', 'GGIGU', '0+', 'LOTIEXAZ', 'MALATTIA DI ALZHEIMER', '1047176553'),
	('AOQRDO18O33R446N', 'YFNLCVZGH', 'A+', 'YMJQIK', 'MALATTIA DI PARKINSON', '7095163942'),
	('ASMMCA44V89A539E', 'FEKISPTRR', 'AB-', 'IZGQSDXS', '//', '6034535493'),
	('ATKSXZ88F40E674T', 'KCYSUHHW', '0+', 'MYSVRWTN', '//', '+71 9417154971'),
	('AUOZWO78F84W141Z', 'XTQHLMGMQ', 'A+', 'ABFBLDVVY', 'DISFUNZIONE ERETTILE', '+63 5482681624'),
	('AXUQYA24X88Y477Y', 'IXALDE', '0-', 'UAQZPUBA', 'OSTEOPOROSI', '5307156441'),
	('AYEVFX07N27I662Q', 'BGQEL', '0+', 'IMJXDMYNP', 'MENINGITE', '8132705782'),
	('AZBFKZ44J98E162G', 'LAOEX', '0+', 'BCKJWZX', '//', '1592396690'),
	('AZUMJB66U65X072J', 'VUIRXN', 'A+', 'NRRTJY', '//', '+41 2661557707'),
	('BDMIVX29F10J509S', 'QNGIRJLX', 'A-', 'HWGLMUIZ', '//', '+31 9344099395'),
	('BDVDPL16R67J793U', 'LRBRPMM', '0-', 'VHSNFHPR', '//', '2745567778'),
	('BGKFFV58I04H816E', 'HVVNLBX', 'AB-', 'AWLXOW', 'SCHIZOFRENIA', '8572819452'),
	('BGWTSL98L41A247B', 'PGTJHEI', 'A+', 'OCBCAK', 'INCONTINENZA URINARIA', '2993574346'),
	('BHCCSC72T01M089Q', 'QNXAMLRH', 'AB+', 'FRTBX', '//', '+45 8311878674'),
	('BJANRY99A30M669Y', 'TBRLBDHBM', 'AB+', 'DFWLEGHI', '//', '6786382023'),
	('BKRZUH27V06I189X', 'RKRXIU', 'AB-', 'KYPVSHUC', 'IL DOLORE', '+88 2192158467'),
	('BLDTSV22J31Z271L', 'QCYAZ', 'AB+', 'BJVBKJHS', 'DERMATITI', '+56 9012926135'),
	('BNABMC97U47Z300J', 'QJIKB', 'A+', 'CTOJCO', '//', '+72 6244627621'),
	('BNFEFC42X88Q898X', 'NBJWH', '0-', 'EEPUGD', 'SINUSITI', '+39 8081962714'),
	('BQUROP34R86M094W', 'OHYFAKJP', '0+', 'TCSRIEWFZ', '//', '7771033978'),
	('BRUJAP73E99U556P', 'NAAJAA', 'A-', 'TPPBX', 'SINUSITI', '+26 9192047521'),
	('BSMDVR40H45D561D', 'VJXZOZL', 'AB+', 'XDZYOBOZG', 'IL DOLORE', '+73 2207061819'),
	('BUXKQD62S48D171L', 'MOKHJUL', 'A-', 'RFIIZWWGG', 'SINUSITI', '2032012338'),
	('BXPDTZ35Q70N390H', 'AVMTLNO', '0-', 'KIKWI', '//', '+35 9472999546'),
	('BZZROT90Z88X258L', 'NTPGRQQC', '0+', 'UQBGFL', '//', '+10 1396022503'),
	('CAKFOX58P77L944L', 'SQVKD', 'A-', 'LIWQRYRT', 'MALATTIA DI ALZHEIMER', '6923487202'),
	('CDMZIP27X74Q410L', 'EKAUOMY', 'A-', 'BEUQAQ', 'ARTRITI INFETTIVE', '7751575163'),
	('CDQSCB82N71B308C', 'NHFWH', '0+', 'NIEXNF', '//', '+62 1094299487'),
	('CFDZLP88L04S369F', 'RJPKDH', 'AB+', 'AKXYXYIRM', '//', '5012026029'),
	('CGXXST64E46R693X', 'RLAOOIC', '0-', 'VDUYNA', '//', '6311885692');
/*!40000 ALTER TABLE `guest` ENABLE KEYS */;

-- Dump dei dati della tabella digitaldonation.hibernate_sequence: ~0 rows (circa)
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(3),
	(3);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dump dei dati della tabella digitaldonation.indisponibilita: ~0 rows (circa)
/*!40000 ALTER TABLE `indisponibilita` DISABLE KEYS */;
INSERT INTO `indisponibilita` (`id_indisponibilita`, `data_prossima_disponibilita`, `motivazioni`, `nome_medico`, `codice_fiscale_donatore`) VALUES
	(1, '2022-01-02 23:44:47.000000', 'sono caduto', 'Autodichiarazione', 'AFCLCW22Z02F362J'),
	(2, '2022-02-12 23:44:47.000000', 'non ho motivazioni', 'Autodichiarazione', 'AFCLCW22Z02F362J'),
	(3, '2022-01-22 23:44:47.000000', 'Ha paura', 'Dottor. Enzo Ferrari', 'AFCLCW22Z02F362J'),
	(4, '2022-01-13 23:44:47.000000', 'ho il covid-33', 'Dottor. Madman', 'ACNCZY45X38I793C'),
	(5, '2022-02-02 23:44:47.000000', 'ho fumato', 'Autodichiarazione', 'ABRBAK53R70D887T'),
	(6, '2022-03-02 23:44:47.000000', 'è venuto ubriaco', 'Dottor. Lil Peep', 'AFCLCW22Z02F362J'),
	(7, '2022-01-02 23:44:47.000000', 'sono alcolizzato', 'Autodichiarazione', 'AIEVGN44I74X492I');
/*!40000 ALTER TABLE `indisponibilita` ENABLE KEYS */;

-- Dump dei dati della tabella digitaldonation.operatore: ~0 rows (circa)
/*!40000 ALTER TABLE `operatore` DISABLE KEYS */;
INSERT INTO `operatore` (`codice_fiscale_utente`, `sede_locale_codice_identificativo`) VALUES
	('AJQJLS56M07J753M', 1),
	('BSDIGA33S44F574I', 1),
	('CJRWOS59E43S947J', 678),
	('CNGJWF51U60J369V', 678),
	('AFCLCW22Z02F362J', 679),
	('CPQHVR51M18L075S', 679);
/*!40000 ALTER TABLE `operatore` ENABLE KEYS */;

-- Dump dei dati della tabella digitaldonation.sede_locale: ~0 rows (circa)
/*!40000 ALTER TABLE `sede_locale` DISABLE KEYS */;
INSERT INTO `sede_locale` (`id_sede`, `via`) VALUES
	(1, 'Via castel Nuvola'),
	(676, 'Pella Via txqblq 72'),
	(677, 'Brunate Via estyg 74'),
	(678, 'Serra de\' Conti Via ksxwckrs 76'),
	(679, 'Vallata Via goywnv 17'),
	(680, 'Marrubiu Via wtyrm 12');
/*!40000 ALTER TABLE `sede_locale` ENABLE KEYS */;

-- Dump dei dati della tabella digitaldonation.seduta: ~0 rows (circa)
/*!40000 ALTER TABLE `seduta` DISABLE KEYS */;
INSERT INTO `seduta` (`id_seduta`, `data_fine_prenotazione`, `data_inizio_prenotazione`, `data_seduta`, `luogo`, `numero_partecipanti`, `ora_fine`, `ora_inizio`, `id_sede_locale`) VALUES
	(1, '2022-01-18 01:00:00.000000', '2022-01-12 01:00:00.000000', '2022-02-12 01:00:00.000000', 'Via Giuseppeverdi 2&Salerno&84010&SA', 52, '18:22:00', '12:22:00', 1),
	(2, '2022-01-18 01:00:00.000000', '2022-01-12 01:00:00.000000', '2022-02-12 01:00:00.000000', 'Via Giuseppeverdi 2&Salerno&84010&SA', 52, '18:22:00', '12:22:00', 1),
	(100, '2022-05-02 14:26:17.000000', '2022-03-02 14:26:21.000000', '2022-07-02 14:26:28.000000', 'via casa pisani 2', 100, '18:26:47', '14:26:49', 1),
	(200, '2022-04-02 14:26:17.000000', '2022-02-22 14:26:21.000000', '2022-06-12 14:26:28.000000', 'via casa pisani 2', 88, '16:26:47', '01:36:49', 1),
	(681, '2022-06-14 00:00:00.000000', '2022-05-19 00:00:00.000000', '2021-12-31 17:58:30.331000', 'Appignano Via nprtcaif 12', 40, '21:58:00', '03:34:00', 678),
	(682, '2022-09-24 00:00:00.000000', '2022-08-11 00:00:00.000000', '2021-12-31 17:58:30.398000', 'Galzignano Terme Via pjhzbx 92', 67, '04:48:00', '18:26:00', 676),
	(683, '2022-06-03 00:00:00.000000', '2022-04-06 00:00:00.000000', '2021-12-31 17:58:30.443000', 'Dolegna del Collio Via finxgahf 98', 46, '17:28:00', '11:45:00', 676),
	(684, '2022-08-03 00:00:00.000000', '2022-06-18 00:00:00.000000', '2021-12-31 17:58:30.544000', 'San Lazzaro di Savena Via ikndlj 95', 61, '04:31:00', '23:33:00', 679),
	(685, '2022-10-09 00:00:00.000000', '2022-08-15 00:00:00.000000', '2021-12-31 17:58:30.609000', 'Tessennano Via qvldiadpz 93', 75, '14:09:00', '09:37:00', 679),
	(686, '2022-10-01 00:00:00.000000', '2022-08-22 00:00:00.000000', '2021-12-31 17:58:30.648000', 'Madesimo Via wwxwohcoc 44', 29, '13:48:00', '08:22:00', 677),
	(687, '2022-08-03 00:00:00.000000', '2022-07-13 00:00:00.000000', '2021-12-31 17:58:30.685000', 'Incisa in Val d\'Arno Via rpoglw 67', 19, '04:32:00', '05:20:00', 679),
	(688, '2023-02-04 00:00:00.000000', '2022-12-06 00:00:00.000000', '2021-12-31 17:58:30.720000', 'Chambave Via aqwdvcw 49', 63, '15:29:00', '16:46:00', 678),
	(689, '2022-07-04 00:00:00.000000', '2022-05-24 00:00:00.000000', '2021-12-31 17:58:30.764000', 'Tremestieri Etneo Via bgasrkoh 64', 21, '07:13:00', '13:11:00', 677),
	(690, '2022-03-28 01:00:00.000000', '2022-03-06 00:00:00.000000', '2021-12-31 17:58:30.803000', 'Gallicano Via ewztxl 6', 79, '08:05:00', '02:33:00', 678),
	(691, '2022-03-14 00:00:00.000000', '2022-02-05 00:00:00.000000', '2021-12-31 17:58:30.841000', 'Bordighera Via gnsydzjhk 18', 62, '19:26:00', '18:32:00', 677),
	(692, '2023-01-16 00:00:00.000000', '2022-12-01 00:00:00.000000', '2021-12-31 17:58:30.881000', 'Malo Via bgqhcwg 74', 20, '09:34:00', '09:08:00', 677),
	(693, '2022-12-01 23:00:00.000000', '2022-10-16 00:00:00.000000', '2021-12-31 17:58:30.913000', 'Lama dei Peligni Via esftqcqt 97', 57, '17:09:00', '11:04:00', 678),
	(694, '2022-04-18 01:00:00.000000', '2022-03-09 00:00:00.000000', '2021-12-31 17:58:30.960000', 'Chiusi Via woqef 67', 47, '21:44:00', '10:17:00', 680),
	(695, '2022-05-07 01:00:00.000000', '2022-03-17 00:00:00.000000', '2021-12-31 17:58:31.004000', 'Camogli Via myajy 87', 49, '13:28:00', '11:30:00', 677),
	(696, '2022-11-18 23:00:00.000000', '2022-10-22 00:00:00.000000', '2021-12-31 17:58:31.048000', 'San Canzian d\'Isonzo Via bltrh 6', 40, '10:33:00', '06:22:00', 677);
/*!40000 ALTER TABLE `seduta` ENABLE KEYS */;

-- Dump dei dati della tabella digitaldonation.seduta_donatore: ~0 rows (circa)
/*!40000 ALTER TABLE `seduta_donatore` DISABLE KEYS */;
INSERT INTO `seduta_donatore` (`id_seduta`, `codice_fiscale_donatore`) VALUES
	(2, 'ACNCZY45X38I793C'),
	(693, 'AIEVGN44I74X492I'),
	(687, 'AFKIWW94S77T163B'),
	(681, 'ACNCZY45X38I793C'),
	(681, 'ACNCZY45X38I793C'),
	(686, 'ACNCZY45X38I793C'),
	(689, 'AFCLCW22Z02F362J'),
	(681, 'ABRBAK53R70D887V'),
	(681, 'AJQJLS56M07J753M'),
	(688, 'ABRBAK53R70D887T'),
	(694, 'AFCLCW22Z02F362J');
/*!40000 ALTER TABLE `seduta_donatore` ENABLE KEYS */;

-- Dump dei dati della tabella digitaldonation.seduta_guest: ~0 rows (circa)
/*!40000 ALTER TABLE `seduta_guest` DISABLE KEYS */;
INSERT INTO `seduta_guest` (`id_seduta`, `codice_fiscale_guest`) VALUES
	(681, 'AABBHQ79C38L971N'),
	(681, 'AXUQYA24X88Y477Y'),
	(683, 'AXUQYA24X88Y477Y'),
	(683, 'CDQSCB82N71B308C'),
	(681, 'AYEVFX07N27I662Q'),
	(687, 'CGXXST64E46R693X'),
	(686, 'BGWTSL98L41A247B'),
	(692, 'BJANRY99A30M669Y'),
	(684, 'CGXXST64E46R693X'),
	(684, 'BSMDVR40H45D561D'),
	(685, 'BGWTSL98L41A247B'),
	(683, 'BGWTSL98L41A247B');
/*!40000 ALTER TABLE `seduta_guest` ENABLE KEYS */;

-- Dump dei dati della tabella digitaldonation.tesserino: ~0 rows (circa)
/*!40000 ALTER TABLE `tesserino` DISABLE KEYS */;
INSERT INTO `tesserino` (`codice_fiscale_donatore`, `numero_matricola`, `data_rilascio`, `gruppo_sanguigno`, `rh`, `img_source`) VALUES
	('ABRBAK53R70D887T', 1990, '1975-02-24', 'AB+', 'POS', 'YJGRYV.png'),
	('ABRBAK53R70D887V', 2391, '1976-04-14', 'A-', 'NEG', 'IZEZCJ.png'),
	('ACNCZY45X38I793C', 2746, '1979-09-23', 'AB-', 'POS', 'SDWSPJJEG.png'),
	('AFCLCW22Z02F362J', 3518, '2003-11-07', '0+', 'NEG', 'LYRTUR.png'),
	('AFKIWW94S77T163B', 3113, '1938-10-02', 'A+', 'POS', 'JNQAYIEJ.png'),
	('AIEVGN44I74X492I', 5458, '1925-05-10', 'A-', 'NEG', 'JRGFCM.png'),
	('AJQJLS56M07J753M', 6997, '1924-06-03', 'A+', 'NEG', 'OSVTIBLXQ.png');
/*!40000 ALTER TABLE `tesserino` ENABLE KEYS */;

-- Dump dei dati della tabella digitaldonation.utente: ~0 rows (circa)
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` (`codice_fiscale_utente`, `cognome`, `email`, `nome`, `password`) VALUES
	('ABRBAK53R70D887T', 'KDOQU', 'operatoredd@gmail.com', 'YFGOKDPE', 'e1adc3949ba59abbe56e057f2f883e'),
	('ABRBAK53R70D887V', 'KDOQU', 'donatoredd@gmail.com', 'YFGOKDPE', 'e1adc3949ba59abbe56e057f2f883e'),
	('ACNCZY45X38I793C', 'YNHCG', '9KPCCFZN76@gmail.com', 'CMSUNVE', '387cdc3ee61fe8faa7f9f1d9e670522c'),
	('AFCLCW22Z02F362J', 'TWYYGZKIG', '8PL25FCOAN@gmail.com', 'XMAWXLF', 'df2d62e17936f16a5a34f652eeaa8'),
	('AFKIWW94S77T163B', 'CMWPY', 'P2C4SD30YP@gmail.com', 'EMDPQWRK', '1db9461a9e32b51e7d522a6458bf79'),
	('AIEVGN44I74X492I', 'NJWVVYM', 'BOCOJB20Z1@gmail.com', 'AYCSN', '695d1de52f3815dec7ad3dd9e6e479ca'),
	('AJQJLS56M07J753M', 'NCQRTFUXV', '6JQR7J3Z6S@gmail.com', 'NLCIOZATV', '7330d02740363978ed3ffcee16ffcf34'),
	('AMTBQC45M52C400Q', 'ZXPMK', 'CFS4T6O523@gmail.com', 'QPRHB', 'f7ccfc3fe6d3135161cec6a0153752a9'),
	('AMWZEY46X20K485B', 'FAYMO', '6O4A3GVPC4@gmail.com', 'XSAHIWY', '3c771d18d7bd4dcdf3d5c1bcf4ea45ea'),
	('ANRYMC87A14O149N', 'CBCFYDK', '8V7G2PAVMJ@gmail.com', 'QALIAG', '5559fa66c13355378a222d4c76b7173'),
	('AOZDOS40J61Z599K', 'XFBAUQILI', 'ZQWJCIN3D6@gmail.com', 'WTWGMGM', 'e18b7572e658633ee44fe6db53744f70'),
	('AQZOCR18G00I387Q', 'IPPGCNJ', 'DHA6DBGGXY@gmail.com', 'SITSUOJN', 'b17916d62dd340878676a4444ad51eca'),
	('ASVTJJ76K75T780N', 'DNXOYEJS', 'YMI55XYX3G@gmail.com', 'XKJMKSU', 'f0aaa33c85f7dc117ba367a7e225535'),
	('AUMNCM18X04O772I', 'ETBBWRXU', 'U4FYSSGT71@gmail.com', 'PWYLZD', '6aafda747b1d20e85446d270d5612e'),
	('AUPTZX75E21Y698F', 'CGIDNOQC', 'SFBG7JFQ2C@gmail.com', 'MEGLXYE', '9c1a2d18bb9113bf731982c9991e89e'),
	('BADTSS96B80Y712H', 'UWFDXN', 'QE99EYGCTB@gmail.com', 'YZCDBLNFN', '8d9af8334775a39e66e16a4344dd7a87'),
	('BSDIGA33S44F574I', 'YHRPW', 'Z41DAYYU3F@gmail.com', 'WPEYJDIT', 'fae3466da34b57f12e294d08b80a1a'),
	('BSRZSP35I62U346C', 'QCNMCLFW', 'JAJPOHA3C2@gmail.com', 'XCWQRRGY', 'fa506f7c19cdbcd81e32bb556dbf7518'),
	('BXLDEV64R23F850I', 'KAOIMURIN', '7H15DJQOEO@gmail.com', 'LLNKB', '45b563ef05c48efebd6c5b61b56440'),
	('BXUMQW54E66K020J', 'EIOQC', 'A58TDWYMC6@gmail.com', 'HVCNG', 'ef5df2fcaff55e363baf7de0945d13c3'),
	('CCVQVK33G19V200E', 'GXHZMPI', 'IPNME4QH8P@gmail.com', 'CUREOH', '315fc41a44e73dc3b3aa48984c20a379'),
	('CHGDAO06U95U786O', 'VNTZD', 'BN1IYD1W05@gmail.com', 'XAFGZ', '697f9aa25477e9535a5f7fb59b8bf1'),
	('CJRWOS59E43S947J', 'ZZTUROSBX', '7RI1EGUBN4@gmail.com', 'ZVULTYU', '63830178c51bb40743aca3663d3f8'),
	('CNGJWF51U60J369V', 'SHPTZR', '4MZKJI0ARE@gmail.com', 'IGGCHARG', 'cf501ce7ec7f0ebddf7a25b8cb3920'),
	('CPQHVR51M18L075S', 'LPBZYCVI', 'CEEH0J7FCU@gmail.com', 'ROLPLVA', '46e73a765b5f501661625109abe135'),
	('CPZGOX92W55R634A', 'SAINNKJN', '5A9YNXKI9G@gmail.com', 'JJOBUXY', '96f23dcbb870b4531e5435be12cd553a'),
	('CQBRSZ33B55J432D', 'HFKOQI', 'FBUGC0AP46@gmail.com', 'BEJXOUB', 'd0bcb3ccf2d7f214d83f4e1d9a62abf'),
	('CRTELM03I78U973V', 'CKJSWYXT', 'OBHFYP8Q45@gmail.com', 'PWWGOUNV', '5a835d39ce3fdc888c6718e4339796');
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
