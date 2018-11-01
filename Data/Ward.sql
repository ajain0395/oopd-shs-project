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
-- Table structure for table `Ward`
--

CREATE TABLE `Ward` (
  `WardId` int(20) NOT NULL,
  `Capacity` int(11) NOT NULL,
  `Type` varchar(20) NOT NULL,
  `Cost_Per_Day` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Ward`
--

INSERT INTO `Ward` (`WardId`, `Capacity`, `Type`, `Cost_Per_Day`) VALUES
(1, 20, 'Critical', 5000),
(2, 30, 'Non Critical', 4000),
(3, 1, 'Critical', 15000),
(4, 50, 'Non Critical', 5000),
(5, 10, 'Critical', 7000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Ward`
--
ALTER TABLE `Ward`
  ADD PRIMARY KEY (`WardId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Ward`
--
ALTER TABLE `Ward`
  MODIFY `WardId` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
