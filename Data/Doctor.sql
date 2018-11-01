-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 01, 2018 at 10:50 AM
-- Server version: 5.7.24-0ubuntu0.18.04.1
-- PHP Version: 7.2.10-0ubuntu0.18.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `SHSDB`
--

-- --------------------------------------------------------

--
-- Table structure for table `Doctor`
--

CREATE TABLE `Doctor` (
  `Did` int(50) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `DOB` date NOT NULL,
  `Gender` char(1) NOT NULL,
  `Address` varchar(50) NOT NULL,
  `ContactNo` varchar(10) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `DeptId` int(25) NOT NULL,
  `Rank` varchar(25) NOT NULL,
  `Surgeon` varchar(15) NOT NULL DEFAULT 'NO',
  `OpdFees` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Doctor`
--

INSERT INTO `Doctor` (`Did`, `Name`, `DOB`, `Gender`, `Address`, `ContactNo`, `Password`, `DeptId`, `Rank`, `Surgeon`, `OpdFees`) VALUES
(1, 'A', '1989-12-16', 'M', '27/64 Delhi', '7896541234', '123', 1, 'senior specialist', 'NO', 1500),
(2, 'B', '1990-11-18', 'M', '26/46 Faridabad', '4562318768', '123', 2, 'senior specialist', 'NO', 1600),
(3, 'C', '1990-12-18', 'M', '20/12 Dehradun', '4562318769', '123', 3, 'senior specialist', 'NO', 1400),
(4, 'D', '1990-09-18', 'F', '10/41 Bangalore', '4562318760', '123', 4, 'senior specialist', 'NO', 1650),
(5, 'E', '1990-10-18', 'M', '17/13 Chandigarh', '4562318764', '123', 5, 'senior specialist', 'NO', 1600),
(6, 'F', '1985-12-31', 'F', '56/48 Hyderabad', '9874563215', '123', 6, 'senior specialist', 'No', 900);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Doctor`
--
ALTER TABLE `Doctor`
  ADD PRIMARY KEY (`Did`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Doctor`
--
ALTER TABLE `Doctor`
  MODIFY `Did` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
