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
-- Table structure for table `record`
--

CREATE TABLE `record` (
  `RecId` int(20) NOT NULL,
  `Pid` int(25) NOT NULL,
  `Admit_Date` date NOT NULL,
  `Discharge_Date` date DEFAULT NULL,
  `Patient_Desc` varchar(200) NOT NULL,
  `Disease_Identified` varchar(50) DEFAULT NULL,
  `Location` varchar(10) NOT NULL DEFAULT 'OPD',
  `Did` int(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `record`
--

INSERT INTO `record` (`RecId`, `Pid`, `Admit_Date`, `Discharge_Date`, `Patient_Desc`, `Disease_Identified`, `Location`, `Did`) VALUES
(1, 1, '2018-10-31', NULL, 'cold and cough', NULL, 'OPD', 1),
(2, 2, '2018-10-28', NULL, 'headache', NULL, 'OPD', 2),
(3, 3, '2018-09-18', '2018-10-01', 'leg fracture', NULL, 'LOCAL', 3),
(4, 4, '2018-10-11', NULL, 'Chest pain', NULL, 'OPD', 4),
(5, 5, '2018-05-31', NULL, 'Fever', NULL, 'OPD', 1),
(6, 6, '2018-11-12', NULL, 'Hand sprain', NULL, 'OPD', 3),
(7, 1, '2018-01-31', NULL, 'Heart attack', NULL, 'OPD', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `record`
--
ALTER TABLE `record`
  ADD PRIMARY KEY (`RecId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `record`
--
ALTER TABLE `record`
  MODIFY `RecId` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
