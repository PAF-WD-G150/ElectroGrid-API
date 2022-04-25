-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2022 at 03:51 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecpay`
--

-- --------------------------------------------------------

--
-- Table structure for table `ecpay`
--

CREATE TABLE `ecpay` (
  `payID` int(255) NOT NULL,
  `payCardType` varchar(30) CHARACTER SET latin1 NOT NULL,
  `payCardNO` int(50) NOT NULL,
  `payExpiryDate` date NOT NULL,
  `payCVV` int(10) NOT NULL,
  `payDate` date NOT NULL,
  `payTotalAmount` decimal(10,2) NOT NULL,
  `payAmount` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ecpay`
--

INSERT INTO `ecpay` (`payID`, `payCardType`, `payCardNO`, `payExpiryDate`, `payCVV`, `payDate`, `payTotalAmount`, `payAmount`) VALUES
(6, 'Master Card', 45234544, '2023-12-20', 786, '2021-07-20', '9550.00', '7500.00'),
(8, 'Master Card', 10555548, '2024-12-30', 564, '2021-05-23', '6550.00', '6500.00'),
(11, 'Master Card', 10234548, '2025-11-20', 864, '2020-06-23', '8550.00', '6500.00'),
(12, 'Master Card', 95234544, '2027-10-20', 796, '2020-07-20', '10550.00', '7500.00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ecpay`
--
ALTER TABLE `ecpay`
  ADD PRIMARY KEY (`payID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ecpay`
--
ALTER TABLE `ecpay`
  MODIFY `payID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
