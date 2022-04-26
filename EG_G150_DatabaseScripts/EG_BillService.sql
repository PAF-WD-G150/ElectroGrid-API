-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2022 at 03:49 PM
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
-- Database: `electricity_bill`
--

-- --------------------------------------------------------

--
-- Table structure for table `e_bill`
--

CREATE TABLE `e_bill` (
  `bill_id` int(10) NOT NULL,
  `elec_acc_no` int(10) NOT NULL,
  `month` varchar(25) NOT NULL,
  `current_meter_reading` int(15) NOT NULL,
  `previous_meter_reading` int(15) NOT NULL,
  `consumed_units` int(15) NOT NULL,
  `bill_amount` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `e_bill`
--

INSERT INTO `e_bill` (`bill_id`, `elec_acc_no`, `month`, `current_meter_reading`, `previous_meter_reading`, `consumed_units`, `bill_amount`) VALUES
(20, 2, 'Jan', 280, 120, 160, '2460.00'),
(21, 3, 'Jan', 480, 300, 180, '3060.00'),
(22, 4, 'Jan', 567, 455, 112, '1140.00'),
(23, 5, 'Jan', 767, 482, 285, '7785.00');

-- --------------------------------------------------------

--
-- Table structure for table `e_units`
--

CREATE TABLE `e_units` (
  `unit_id` int(10) NOT NULL,
  `unit_desc` varchar(100) NOT NULL,
  `unit_charge` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `e_units`
--

INSERT INTO `e_units` (`unit_id`, `unit_desc`, `unit_charge`) VALUES
(1, '0 to 60 units', '6.00'),
(2, '60 to 120 units', '15.00'),
(3, '120 to 180 units', '30.00'),
(4, 'above 180 units', '45.00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `e_bill`
--
ALTER TABLE `e_bill`
  ADD PRIMARY KEY (`bill_id`);

--
-- Indexes for table `e_units`
--
ALTER TABLE `e_units`
  ADD PRIMARY KEY (`unit_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `e_bill`
--
ALTER TABLE `e_bill`
  MODIFY `bill_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `e_units`
--
ALTER TABLE `e_units`
  MODIFY `unit_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
