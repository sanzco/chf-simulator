-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 25, 2022 at 11:18 PM
-- Server version: 5.7.26
-- PHP Version: 7.4.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: thirdapp
--
CREATE DATABASE IF NOT EXISTS chfdb CHARACTER SET utf8;
USE chfdb;

-- --------------------------------------------------------

--
-- Table structure for table counter
--

CREATE TABLE IF NOT EXISTS counter (
  counter_id int(11) NOT NULL,
  current_status varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  name varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (counter_id)
) ENGINE=ndbcluster DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Dumping data for table counter
--

INSERT INTO counter (counter_id, current_status, `name`) VALUES
(1, '12Mbps', 'D5G1'),
(2, '12Mbps', 'D5G1');

-- --------------------------------------------------------

--
-- Table structure for table counter_pending_counters
--

CREATE TABLE IF NOT EXISTS counter_pending_counters (
  counter_counter_id int(11) NOT NULL,
  pending_counters_counter_id int(11) NOT NULL,
  UNIQUE KEY UK_isrep9csswhadilul8pjqi3uj (pending_counters_counter_id),
  KEY FKqsarry2d22bqw87or7w86dn3h (counter_counter_id)
) ENGINE=ndbcluster DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Dumping data for table counter_pending_counters
--

INSERT INTO counter_pending_counters (counter_counter_id, pending_counters_counter_id) VALUES
(1, 3);

-- --------------------------------------------------------

--
-- Table structure for table hibernate_sequence
--

CREATE TABLE IF NOT EXISTS hibernate_sequence (
  next_val bigint(20) DEFAULT NULL
) ENGINE=ndbcluster DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Dumping data for table hibernate_sequence
--

INSERT INTO hibernate_sequence (next_val) VALUES
(5);

-- --------------------------------------------------------

--
-- Table structure for table pending_counter
--

CREATE TABLE IF NOT EXISTS pending_counter (
  counter_id int(255) NOT NULL,
  activation_time datetime DEFAULT NULL,
  counter_status varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (counter_id)
) ENGINE=ndbcluster DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Dumping data for table pending_counter
--

INSERT INTO pending_counter (counter_id, activation_time, counter_status) VALUES
(3, '2022-11-11 04:00:00', '42Mbps');

-- --------------------------------------------------------

--
-- Table structure for table subscription
--

CREATE TABLE IF NOT EXISTS subscription (
  subscription_id varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  expiry timestamp NULL DEFAULT NULL,
  gpsi varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  notif_uri varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  supi varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  supported_features varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (subscription_id)
) ENGINE=ndbcluster DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Dumping data for table subscription
--

INSERT INTO subscription (subscription_id, expiry, gpsi, notif_uri, supi, supported_features) VALUES
('0d822745-ae6f-4af5-b4f0-9fc53422fcbe', '2022-11-11 05:00:00', 'imsi-99999', 'http://someserver.com/notify', 'imsi-22999', 'F'),
('1449de08-12d9-4295-95ee-bba09c317d1d', NULL, 'imsi-99999', 'http://someserver.com/notify', 'imsi-22999', 'F'),
('9f155733-66dc-4ff2-a4c1-9200c081836d', NULL, 'imsi-99999', 'http://someserver.com/notify', 'imsi-22999', 'F');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  user_id int(11) NOT NULL,
  supi varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  gpsi varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (user_id)
) ENGINE=ndbcluster DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (user_id, supi, gpsi) VALUES
(1, 'imsi-22999', NULL);

-- --------------------------------------------------------

--
-- Table structure for table user_counters
--

CREATE TABLE IF NOT EXISTS user_counters (
  user_user_id int(11) NOT NULL,
  counters_counter_id int(11) NOT NULL,
  UNIQUE KEY UK_2oua36gmj5ggue06g2qvrx8kk (counters_counter_id),
  KEY FKpcyi0yhl6sgjbphpoilb8gtpe (user_user_id)
) ENGINE=ndbcluster DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Dumping data for table user_counters
--

INSERT INTO user_counters (user_user_id, counters_counter_id) VALUES
(1, 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table counter_pending_counters
--
ALTER TABLE counter_pending_counters
  ADD CONSTRAINT FK79oklejmqrf9f1b0d6eq8qswm FOREIGN KEY (pending_counters_counter_id) REFERENCES pending_counter (counter_id),
  ADD CONSTRAINT FKqsarry2d22bqw87or7w86dn3h FOREIGN KEY (counter_counter_id) REFERENCES counter (counter_id);

--
-- Constraints for table user_counters
--
ALTER TABLE user_counters
  ADD CONSTRAINT FKb4pd8u620ludlgfyjx80gqqoa FOREIGN KEY (counters_counter_id) REFERENCES counter (counter_id),
  ADD CONSTRAINT FKpcyi0yhl6sgjbphpoilb8gtpe FOREIGN KEY (user_user_id) REFERENCES `user` (user_id);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
