-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 01, 2018 at 06:27 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE `prescription` (
  `HistId` int(25) NOT NULL,
  `Did` int(25) NOT NULL,
  `RecId` int(25) NOT NULL,
  `Test_Adviced` tinytext NOT NULL,
  `Test_Report` varchar(100) NOT NULL,
  `Medicine_Prescribed` tinytext NOT NULL,
  `Date` date NOT NULL,
  `Patient_Status` varchar(20) NOT NULL,
  `Fees_Per_Day` int(10) DEFAULT NULL,
  `WardId` int(25) DEFAULT NULL,
  `Location` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prescription`
--

INSERT INTO `prescription` (`HistId`, `Did`, `RecId`, `Test_Adviced`, `Test_Report`, `Medicine_Prescribed`, `Date`, `Patient_Status`, `Fees_Per_Day`, `WardId`, `Location`) VALUES
(1, 1, 1, 'Ultra Sound', 'positive', 'Paracetamol', '2018-10-30', 'Not Critical', NULL, 1, NULL),
(2, 3, 3, 'CT Scan', 'positive', 'Neumoclide', '2018-10-31', 'Not Critical', NULL, 2, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`HistId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `HistId` int(25) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
