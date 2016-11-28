-- phpMyAdmin SQL Dump
-- version 4.5.0.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 07, 2016 at 03:50 PM
-- Server version: 10.0.17-MariaDB
-- PHP Version: 5.5.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `XYZ_Assoc`
--
CREATE DATABASE IF NOT EXISTS `XYZ_Assoc` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `XYZ_Assoc`;

-- --------------------------------------------------------

--
-- Table structure for table `Claims`
--

DROP TABLE IF EXISTS `Claims`;
CREATE TABLE IF NOT EXISTS `Claims` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mem_id` text NOT NULL,
  `date` date NOT NULL,
  `rationale` text NOT NULL,
  `status` varchar(10) NOT NULL,
  `amount` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `Claims`:
--

--
-- Truncate table before insert `Claims`
--

TRUNCATE TABLE `Claims`;
-- --------------------------------------------------------

--
-- Table structure for table `Members`
--

DROP TABLE IF EXISTS `Members`;
CREATE TABLE IF NOT EXISTS `Members` (
  `id` text CHARACTER SET ascii NOT NULL,
  `name` text CHARACTER SET ascii,
  `address` text CHARACTER SET ascii,
  `dob` date DEFAULT NULL,
  `dor` date DEFAULT NULL,
  `status` text NOT NULL,
  `balance` float NOT NULL,
  PRIMARY KEY (`id`(10))
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `Members`:
--

--
-- Truncate table before insert `Members`
--

TRUNCATE TABLE `Members`;
--
-- Dumping data for table `Members`
--

INSERT INTO `Members` (`id`, `name`, `address`, `dob`, `dor`, `status`, `balance`) VALUES
('me-aydin', 'Mehmet Aydin', '148 Station Rd, London, N3 2SG', '1968-10-20', '2015-01-26', 'APPLIED', 0);

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
CREATE TABLE IF NOT EXISTS `payments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mem_id` text NOT NULL,
  `type_of_payment` char(10) NOT NULL,
  `amount` float NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `payments`:
--   `mem_id`
--       `Members` -> `id`
--

--
-- Truncate table before insert `payments`
--

TRUNCATE TABLE `payments`;
-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` text CHARACTER SET ascii NOT NULL,
  `password` text NOT NULL,
  `status` text NOT NULL,
  PRIMARY KEY (`id`(10))
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `users`:
--   `id`
--       `Members` -> `id`
--

--
-- Truncate table before insert `users`
--

TRUNCATE TABLE `users`;
--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `password`, `status`) VALUES
('me-aydin', '201068', 'APPLIED');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
