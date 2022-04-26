-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2022 at 06:31 PM
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
-- Database: `ecustomerdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `electricityaccount`
--

CREATE TABLE `electricityaccount` (
  `ElectricityAcNo` int(11) NOT NULL,
  `CustomerName` varchar(50) CHARACTER SET latin1 NOT NULL,
  `NIC` varchar(12) CHARACTER SET latin1 NOT NULL,
  `Address` varchar(70) CHARACTER SET latin1 NOT NULL,
  `PhoneNumber` varchar(10) CHARACTER SET latin1 NOT NULL,
  `Email` varchar(70) CHARACTER SET latin1 NOT NULL,
  `Province` varchar(20) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `electricityaccount`
--

INSERT INTO `electricityaccount` (`ElectricityAcNo`, `CustomerName`, `NIC`, `Address`, `PhoneNumber`, `Email`, `Province`) VALUES
(1, 'Ruby Collins', '9956234521v', '9/5, Aralya Rd, Kesbewa.', '0715648965', 'ruby@gmail.com', 'Western'),
(2, 'Ben Collins', '9956234445v', '9/5, Aralya Rd, Piliyandala.', '0715648978', 'ben@gmail.com', 'Western'),
(3, 'Sunil Rathnayaka', '8956478956v', '4, Kandy Rd, Yakkala.', '0758648923', 'sunil@gmail.com', 'Western'),
(4, 'Jimmy Leo', '9896542322v', '9/2, Lotus Street, Moratuwa.', '0758889547', 'jimmy@gmail.com', 'Western');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `electricityaccount`
--
ALTER TABLE `electricityaccount`
  ADD PRIMARY KEY (`ElectricityAcNo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `electricityaccount`
--
ALTER TABLE `electricityaccount`
  MODIFY `ElectricityAcNo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
