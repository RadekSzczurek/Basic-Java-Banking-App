-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Paź 31, 2024 at 12:02 AM
-- Wersja serwera: 10.4.32-MariaDB
-- Wersja PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bankjava`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `accounts`
--

CREATE TABLE `accounts` (
  `id` int(11) NOT NULL,
  `imie` text NOT NULL,
  `nazwisko` text NOT NULL,
  `saldo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`id`, `imie`, `nazwisko`, `saldo`) VALUES
(1, 'Aleksandra', 'Sieprawska', 3600),
(2, 'Mirosława', 'Bartnikowska', 7450),
(3, 'Helena', 'Wąsala', 400),
(4, 'Sylwia', 'Kałuzińska', 10000),
(5, 'Jędrzej', 'Michalczyk', 1740),
(6, 'Konrad', 'Kidoń', 400),
(7, 'Nataliia', 'Kulak', 500000),
(8, 'Ignacy', 'Matecki', 9000),
(9, 'Leopold', 'Szczepkowski', 5435),
(10, 'Radek', 'Kowalski', 5100),
(11, 'Kasia', 'Pudełko', 250),
(12, 'Kacper', 'Szlezak', 140);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `transactions`
--

CREATE TABLE `transactions` (
  `id_transactions` int(11) NOT NULL,
  `account_id` int(11) DEFAULT NULL,
  `ilosc` int(11) DEFAULT NULL,
  `transakcja_data` timestamp NOT NULL DEFAULT current_timestamp(),
  `transakcja_typ` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`id_transactions`, `account_id`, `ilosc`, `transakcja_data`, `transakcja_typ`) VALUES
(1, 10, 1500, '2024-10-29 21:13:59', 'Depozyt'),
(2, 10, 10, '2024-10-29 21:16:17', 'Depozyt'),
(3, 10, 50, '2024-10-29 21:16:51', 'Depozyt'),
(4, 10, 10, '2024-10-29 21:22:51', 'Depozyt'),
(5, 10, 500, '2024-10-29 21:23:37', 'Depozyt'),
(6, 3, 300, '2024-10-29 21:26:59', 'Depozyt'),
(7, 6, -350, '2024-10-29 21:51:09', 'Wyplata'),
(8, 1, 100, '2024-10-29 23:07:01', 'Depozyt'),
(9, 1, -100, '2024-10-29 23:07:15', 'Wyplata'),
(10, 1, -100, '2024-10-29 23:08:34', 'Wyplata'),
(11, 11, 100, '2024-10-29 23:13:14', 'Depozyt'),
(12, 11, -100, '2024-10-29 23:13:50', 'Wyplata'),
(13, 12, 100, '2024-10-30 22:47:19', 'Depozyt'),
(14, 12, -10, '2024-10-30 22:47:29', 'Wypłata');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `transfers`
--

CREATE TABLE `transfers` (
  `id_transfer` int(11) NOT NULL,
  `from_account_id` int(11) DEFAULT NULL,
  `to_account_id` int(11) DEFAULT NULL,
  `ilosc` int(11) DEFAULT NULL,
  `transfer_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `transfer_type` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transfers`
--

INSERT INTO `transfers` (`id_transfer`, `from_account_id`, `to_account_id`, `ilosc`, `transfer_date`, `transfer_type`) VALUES
(1, 10, 6, 2000, '2024-10-30 22:30:59', 'Przelew'),
(2, 6, 10, 2000, '2024-10-30 22:33:31', 'Przelew'),
(3, 12, 10, 50, '2024-10-30 22:47:40', 'Przelew');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id_transactions`),
  ADD KEY `account_id` (`account_id`);

--
-- Indeksy dla tabeli `transfers`
--
ALTER TABLE `transfers`
  ADD PRIMARY KEY (`id_transfer`),
  ADD KEY `from_account_id` (`from_account_id`),
  ADD KEY `to_account_id` (`to_account_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts`
--
ALTER TABLE `accounts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id_transactions` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `transfers`
--
ALTER TABLE `transfers`
  MODIFY `id_transfer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`);

--
-- Constraints for table `transfers`
--
ALTER TABLE `transfers`
  ADD CONSTRAINT `transfers_ibfk_1` FOREIGN KEY (`from_account_id`) REFERENCES `accounts` (`id`),
  ADD CONSTRAINT `transfers_ibfk_2` FOREIGN KEY (`to_account_id`) REFERENCES `accounts` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
