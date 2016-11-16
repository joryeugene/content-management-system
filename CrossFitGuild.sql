-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 15, 2016 at 10:32 PM
-- Server version: 5.7.16-0ubuntu0.16.04.1
-- PHP Version: 7.0.8-0ubuntu0.16.04.3
DROP TABLE IF EXISTS `User`;
DROP TABLE IF EXISTS `Post`;
DROP TABLE IF EXISTS `ArchivedPost`;
DROP TABLE IF EXISTS `Category`;
DROP TABLE IF EXISTS `Hashtag`;
DROP TABLE IF EXISTS `PostHashtag`;
DROP TABLE IF EXISTS `Page`;
DROP TABLE IF EXISTS `Nav`;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `CrossFitGuild`
--

-- --------------------------------------------------------

--
-- Table structure for table `ArchivedPost`
--

CREATE TABLE `ArchivedPost` (
  `PostId` int(16) NOT NULL,
  `UserId` int(4) NOT NULL,
  `Title` varchar(100) NOT NULL,
  `Content` varchar(65536) NOT NULL,
  `NumOfViews` int(12) NOT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  `CategoryId` int(4) DEFAULT NULL,
  `Queued` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `User` (
  `UserId` int(4) NOT NULL AUTO_INCREMENT,
  `Email` varchar(255),
  `DisplayName` varchar(128),
  `Authority` varchar(16),
  `AvatarUrl` varchar(2048),
  `Password` varchar(80),
  PRIMARY KEY(`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------

--
-- Table structure for table `Category`
--

CREATE TABLE `Category` (
  `CategoryId` int(4) NOT NULL,
  `CategoryName` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Hashtag`
--

CREATE TABLE `Hashtag` (
  `HashtagId` int(8) NOT NULL,
  `Hashtag` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Nav`
--

CREATE TABLE `Nav` (
  `NavId` int(4) NOT NULL,
  `PageId` int(8) NOT NULL,
  `Position` int(4) NOT NULL,
  `MenuName` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Page`
--

CREATE TABLE `Page` (
  `PageId` int(8) NOT NULL,
  `Title` varchar(80) NOT NULL,
  `Content` varchar(65536) NOT NULL,
  `UserId` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Post`
--

CREATE TABLE `Post` (
  `PostId` int(16) NOT NULL,
  `UserId` int(4) NOT NULL,
  `Title` varchar(100) NOT NULL,
  `Content` varchar(65536) NOT NULL,
  `NumOfViews` int(12) NOT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  `CategoryId` int(4) DEFAULT NULL,
  `Queued` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `PostHashtag`
--

CREATE TABLE `PostHashtag` (
  `HashtagId` int(8) NOT NULL,
  `PostId` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ArchivedPost`
--
ALTER TABLE `ArchivedPost`
  ADD PRIMARY KEY (`PostId`),
  ADD KEY `PostId` (`PostId`);

--
-- Indexes for table `Category`
--
ALTER TABLE `Category`
  ADD PRIMARY KEY (`CategoryId`),
  ADD KEY `CategoryId` (`CategoryId`);

--
-- Indexes for table `Hashtag`
--
ALTER TABLE `Hashtag`
  ADD PRIMARY KEY (`HashtagId`),
  ADD KEY `HashtagId` (`HashtagId`);

--
-- Indexes for table `Nav`
--
ALTER TABLE `Nav`
  ADD PRIMARY KEY (`NavId`);

--
-- Indexes for table `Page`
--
ALTER TABLE `Page`
  ADD PRIMARY KEY (`PageId`);

--
-- Indexes for table `Post`
--
ALTER TABLE `Post`
  ADD PRIMARY KEY (`PostId`),
  ADD KEY `PostId` (`PostId`);

--
-- Indexes for table `PostHashtag`
--
ALTER TABLE `PostHashtag`
  ADD PRIMARY KEY (`HashtagId`,`PostId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Category`
--
ALTER TABLE `Category`
  MODIFY `CategoryId` int(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Hashtag`
--
ALTER TABLE `Hashtag`
  MODIFY `HashtagId` int(8) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Nav`
--
ALTER TABLE `Nav`
  MODIFY `NavId` int(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Page`
--
ALTER TABLE `Page`
  MODIFY `PageId` int(8) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Post`
--
ALTER TABLE `Post`
  MODIFY `PostId` int(16) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
