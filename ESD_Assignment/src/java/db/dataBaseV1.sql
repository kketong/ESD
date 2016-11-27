CREATE DATABASE IF NOT EXISTS `myDB` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
-- Create db "MyUse" if it doesn't already exist, with latin1 character set (set of symbols & encodings) & latin1_swedish_ci collation (set of rules to compare characters/encodings)

USE `myDB`;                                                                    -- Specify db to apply the following actions to (useful for multiple dbs/schemata)



CREATE TABLE IF NOT EXISTS `account` (                                            -- Create table "users" if it doesn't already exist
    `username` VARCHAR(20) DEFAULT NULL, -- This will be the first inital + last name                                         -- Column name, data_type(size), inserts default value of null into each cell
    `password` VARCHAR(6) DEFAULT NULL, -- This will be the DOB
    `firstName` VARCHAR(20) DEFAULT NULL,
    `lastName` VARCHAR(20) DEFAULT NULL,
    `dateOfBirth` VARCHAR(6) DEFAULT NULL, -- DOB will be representent as DDMMYY
    `dateOfCreation` DATE, -- When the account is registered
    `membershipStatus` VARCHAR(20) DEFAULT NULL, -- This is the status membership
    `claimBalance` DECIMAL(6,2) DEFAULT NULL, -- How much the users has left to claim
    `accountBalance` DECIMAL(6,2) DEFAULT NULL, -- How much is to pay for membership
    `claimCount`  INT(1) DEFAULT NULL -- this will count the amount of claims on that account (never more than 2)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;                                         -- Defines InnoDB as the default storage engine (core service for storing/processing/securing data) & the characterset of the table

-- List of hard coded MEMBERS
INSERT INTO `account`   (`username`, `password` ,`firstName`, `lastName`, `dateOfBirth`,
                        `dateOfCreation`, `membershipStatus`,`claimBalance`,`accountBalance`,`claimCount`) 
VALUES                  ('gsmith', '250394','George','Smith','250395',
                        '2016-01-13', 'ACTIVE', 500, 0, 1);       -- Inserts into "username" & "password" columns of "users" table the specified values
INSERT INTO `account`   (`username`, `password` ,`firstName`, `lastName`, `dateOfBirth`,
                        `dateOfCreation`, `membershipStatus`,`claimBalance`,`accountBalance`,`claimCount`) 
VALUES                  ('jmoore', '260795','Jack', 'Moore','260795',
                        '2015-01-01', 'SUSPENDED', 0, 10, 2);

-- EXAMPLE:
-- INSERT INTO 
                                        -- Defines InnoDB as the default storage engine (core service for storing/processing/securing data) & the characterset of the table

-- List of hard coded ADMINS
INSERT INTO `account`   (`username`, `password` ,`firstName`, `lastName`, `dateOfBirth`,
                        `dateOfCreation`, `membershipStatus`,`claimBalance`,`accountBalance`,`claimCount`) 
VALUES                  ('admin1', '201068', 'A', 'Dmin1', '201068',
                        '2014-01-01', 'ADMIN',0,0,0);  



CREATE TABLE IF NOT EXISTS `claims` (    
    `username` VARCHAR(20) DEFAULT NULL,                                        -- Create table "users" if it doesn't already exist
    `amount` INT DEFAULT NULL,                                          -- Column name, data_type(size), inserts default value of null into each cell
    `date` VARCHAR(6) DEFAULT NULL,
    `description` VARCHAR(140) DEFAULT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=latin1;           
                                                                      -- Defines InnoDB as the default storage engine (core service for storing/processing/securing data) & the characterset of the table
-- Hard coded CLAIMS
INSERT INTO `claims`    (`username`, `amount`,`date`,`description`)  
VALUES                  ('gsmith', 70, 171116, 'Cracked phone screen');   
                                                                
CREATE TABLE IF NOT EXISTS `payments` (                                            -- Create table "users" if it doesn't already exist
    `amount` int DEFAULT NULL,                                          -- Column name, data_type(size), inserts default value of null into each cell
    `date` varchar(20) DEFAULT NULL,
    `username` VARCHAR(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;                                         -- Defines InnoDB as the default storage engine (core service for storing/processing/securing data) & the characterset of the table