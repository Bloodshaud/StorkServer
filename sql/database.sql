--
-- Created on 27-11-2017
-- By Johannes Ernstsen
--

-- DDL
DROP DATABASE IF EXISTS Stork;
CREATE DATABASE Stork;
USE Stork;

DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
  id       INT(11) PRIMARY KEY AUTO_INCREMENT,
  name     VARCHAR(255) NOT NULL,
  picture  VARCHAR(255),
  mail     VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  location VARCHAR(255)
);