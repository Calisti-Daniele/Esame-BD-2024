-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Gen 02, 2024 alle 18:26
-- Versione del server: 10.4.28-MariaDB
-- Versione PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `esamebd`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `acquisti`
--

CREATE TABLE `acquisti` (
  `idAcquisto` int(11) NOT NULL,
  `ksCliente` varchar(16) DEFAULT NULL,
  `ksFarmaco` varchar(6) DEFAULT NULL,
  `quantita` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `acquisti`
--

INSERT INTO `acquisti` (`idAcquisto`, `ksCliente`, `ksFarmaco`, `quantita`) VALUES
(1, 'CLSDNL03M23E472O', '012745', 5),
(2, 'CLSDNL03M23E472O', '035500', 3),
(3, 'CLSDNL03M23E472U', '035500', 7);

-- --------------------------------------------------------

--
-- Struttura della tabella `case_produttrici`
--

CREATE TABLE `case_produttrici` (
  `partita_iva` varchar(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `indirizzo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `case_produttrici`
--

INSERT INTO `case_produttrici` (`partita_iva`, `nome`, `indirizzo`) VALUES
('00212840235', 'GLAXOSMITHKLINE S.P.A.', 'VIALE DELL AGRICOLTURA 7 - 37135 - VERONA (VR)'),
('00867200156', 'HALEON ITALY S.R.L.', 'VIA MONTE ROSA 91 - 20149 - MILANO (MI)'),
('16251911000', 'ANGELINI PHARMA VENTURES S.P.A.', 'VIALE AMELIA 70 - 00181 - ROMA (RM)');

-- --------------------------------------------------------

--
-- Struttura della tabella `clienti`
--

CREATE TABLE `clienti` (
  `codiceFiscale` varchar(16) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `clienti`
--

INSERT INTO `clienti` (`codiceFiscale`, `nome`, `cognome`, `email`, `pwd`) VALUES
('CLSDNL03M23E472O', 'Daniele', 'Calisti', 'dcalisti03@gmail.com', 'passwordDani'),
('CLSDNL03M23E472U', 'Danilo', 'Callisto', 'dcallisto03@gmail.com', 'PwDDanielo!');

-- --------------------------------------------------------

--
-- Struttura della tabella `elencoTelefonico`
--

CREATE TABLE `elencoTelefonico` (
  `telefono` varchar(8) NOT NULL,
  `ksCasaProduttrice` varchar(11) DEFAULT NULL,
    PRIMARY KEY (telefono)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `elencoTelefonico`
--

INSERT INTO `elencoTelefonico` (`idElencoTelefonico`, `telefono`, `ksCasaProduttrice`) VALUES
(1, '06780531', '16251911000');

-- --------------------------------------------------------

--
-- Struttura della tabella `farmaci`
--

CREATE TABLE `farmaci` (
  `numeroAIC` varchar(6) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `quantita` int(11) NOT NULL,
  `data_scadenza` date NOT NULL,
  `prezzoVendita` float NOT NULL,
  `tipo` varchar(255) NOT NULL COMMENT 'Può essere: Compressa, Bustina o Capsula',
  `ksCasaProduttrice` varchar(11) DEFAULT NULL,
  `formulazione` varchar(255) DEFAULT NULL COMMENT 'Informazione di bustina',
  `divisibile` tinyint(1) DEFAULT NULL COMMENT 'Informazione di compressa',
  `involucro` varchar(255) DEFAULT NULL COMMENT 'Informazione di capsula'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `farmaci`
--

INSERT INTO `farmaci` (`numeroAIC`, `nome`, `quantita`, `data_scadenza`, `prezzoVendita`, `tipo`, `ksCasaProduttrice`, `formulazione`, `divisibile`, `involucro`) VALUES
('012745', 'Tachipirina', 5, '2024-04-25', 15, 'Bustina', '16251911000', 'principio attivo: paracetamolo 125 mg. eccipienti con effetti noti: aspartame, maltitolo, 3,07 mmoli di sodio per bustina TACHIPIRINA Neonati 62,5 mg supposte', NULL, NULL),
('026089', 'Augmentin', 3, '2024-04-12', 15, 'bustina', '00212840235', 'amoxicillina triidrato corrispondente a 875 mg di amoxicillina e potassio clavulanato corrispondente a 125 mg di acido clavulanico', NULL, NULL),
('035500', 'Voltadvance', 4, '2024-06-13', 30, 'Compressa', '00867200156', NULL, 0, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `ordini`
--

CREATE TABLE `ordini` (
  `idOrdine` int(11) NOT NULL,
  `dataOrdine` date NOT NULL,
  `iva` float NOT NULL,
  `importoNetto` float NOT NULL,
  `importoLordo` float GENERATED ALWAYS AS (`importoNetto` + `importoNetto` * `iva` / 100) STORED
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `ordini`
--

INSERT INTO `ordini` (`idOrdine`, `dataOrdine`, `iva`, `importoNetto`) VALUES
(1, '2023-12-28', 22, 120);

-- --------------------------------------------------------

--
-- Struttura della tabella `ordini_effettuati`
--

CREATE TABLE `ordini_effettuati` (
  `idOrdineEffettuato` int(11) NOT NULL,
  `quantita` int(11) NOT NULL,
  `prezzo` float NOT NULL,
  `ksOrdine` int(11) DEFAULT NULL,
  `ksFarmaco` varchar(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `ordini_effettuati`
--

INSERT INTO `ordini_effettuati` (`idOrdineEffettuato`, `quantita`, `prezzo`, `ksOrdine`, `ksFarmaco`) VALUES
(1, 5, 10, 1, '012745'),
(2, 7, 10, 1, '035500');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `acquisti`
--
ALTER TABLE `acquisti`
  ADD PRIMARY KEY (`idAcquisto`),
  ADD KEY `ksCliente` (`ksCliente`),
  ADD KEY `ksFarmaco` (`ksFarmaco`);

--
-- Indici per le tabelle `case_produttrici`
--
ALTER TABLE `case_produttrici`
  ADD PRIMARY KEY (`partita_iva`);

--
-- Indici per le tabelle `clienti`
--
ALTER TABLE `clienti`
  ADD PRIMARY KEY (`codiceFiscale`);

--
-- Indici per le tabelle `elencoTelefonico`
--
ALTER TABLE `elencoTelefonico`
  ADD PRIMARY KEY (`idElencoTelefonico`),
  ADD KEY `ksCasaProduttrice` (`ksCasaProduttrice`);

--
-- Indici per le tabelle `farmaci`
--
ALTER TABLE `farmaci`
  ADD PRIMARY KEY (`numeroAIC`),
  ADD KEY `ksCasaProduttrice` (`ksCasaProduttrice`);

--
-- Indici per le tabelle `ordini`
--
ALTER TABLE `ordini`
  ADD PRIMARY KEY (`idOrdine`);

--
-- Indici per le tabelle `ordini_effettuati`
--
ALTER TABLE `ordini_effettuati`
  ADD PRIMARY KEY (`idOrdineEffettuato`),
  ADD KEY `ksFarmaco` (`ksFarmaco`),
  ADD KEY `ksOrdine` (`ksOrdine`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `acquisti`
--
ALTER TABLE `acquisti`
  MODIFY `idAcquisto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `elencoTelefonico`
--
ALTER TABLE `elencoTelefonico`
  MODIFY `idElencoTelefonico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `ordini`
--
ALTER TABLE `ordini`
  MODIFY `idOrdine` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `ordini_effettuati`
--
ALTER TABLE `ordini_effettuati`
  MODIFY `idOrdineEffettuato` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `acquisti`
--
ALTER TABLE `acquisti`
  ADD CONSTRAINT `acquisti_ibfk_1` FOREIGN KEY (`ksCliente`) REFERENCES `clienti` (`codiceFiscale`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `acquisti_ibfk_2` FOREIGN KEY (`ksFarmaco`) REFERENCES `farmaci` (`numeroAIC`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Limiti per la tabella `elencoTelefonico`
--
ALTER TABLE `elencoTelefonico`
  ADD CONSTRAINT `elencotelefonico_ibfk_1` FOREIGN KEY (`ksCasaProduttrice`) REFERENCES `case_produttrici` (`partita_iva`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Limiti per la tabella `farmaci`
--
ALTER TABLE `farmaci`
  ADD CONSTRAINT `farmaci_ibfk_1` FOREIGN KEY (`ksCasaProduttrice`) REFERENCES `case_produttrici` (`partita_iva`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `ordini_effettuati`
--
ALTER TABLE `ordini_effettuati`
  ADD CONSTRAINT `ordini_effettuati_ibfk_1` FOREIGN KEY (`ksOrdine`) REFERENCES `ordini` (`idOrdine`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `ordini_effettuati_ibfk_2` FOREIGN KEY (`ksFarmaco`) REFERENCES `farmaci` (`numeroAIC`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `ordini_effettuati_ibfk_3` FOREIGN KEY (`ksOrdine`) REFERENCES `ordini` (`idOrdine`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
