CREATE DATABASE IF NOT EXISTS `myDB` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
-- Create db "MyUse" if it doesn't already exist, with latin1 character set (set of symbols & encodings) & latin1_swedish_ci collation (set of rules to compare characters/encodings)

USE `myDB`;                                                                    -- Specify db to apply the following actions to (useful for multiple dbs/schemata)

CREATE TABLE IF NOT EXISTS `users` (                                            -- Create table "users" if it doesn't already exist
  `username` varchar(20) DEFAULT NULL,                                          -- Column name, data_type(size), inserts default value of null into each cell
  `password` varchar(20) DEFAULT NULL,
  `membership` varchar(20) DEFAULT NULL,
  `balance` int DEFAULT NULL,
  `claimId`  int[] DEFAULT NULL,
  `paymentId` int[] DEFAULT NULL  
) ENGINE=InnoDB DEFAULT CHARSET=latin1;                                         -- Defines InnoDB as the default storage engine (core service for storing/processing/securing data) & the characterset of the table

INSERT INTO `users` (`username`, `password`) VALUES('meaydin', '201068');       -- Inserts into "username" & "password" columns of "users" table the specified values
INSERT INTO `users` (`username`, `password`) VALUES('aydinme', '108752');
INSERT INTO `users` (`username`, `password`) VALUES('gulbir', '010568');

CREATE TABLE IF NOT EXISTS `admin` (                                            -- Create table "users" if it doesn't already exist
  `username` varchar(20) DEFAULT NULL,                                          -- Column name, data_type(size), inserts default value of null into each cell
  `password` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;                                         -- Defines InnoDB as the default storage engine (core service for storing/processing/securing data) & the characterset of the table

INSERT INTO `admin` (`username`, `password`) VALUES('admin1', '201068');  

CREATE TABLE IF NOT EXISTS `claims` (                                            -- Create table "users" if it doesn't already exist
  `amount` int DEFAULT NULL,                                          -- Column name, data_type(size), inserts default value of null into each cell
  `date` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;                                         -- Defines InnoDB as the default storage engine (core service for storing/processing/securing data) & the characterset of the table

CREATE TABLE IF NOT EXISTS `payments` (                                            -- Create table "users" if it doesn't already exist
  `amount` int DEFAULT NULL,                                          -- Column name, data_type(size), inserts default value of null into each cell
  `date` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;                                         -- Defines InnoDB as the default storage engine (core service for storing/processing/securing data) & the characterset of the table

