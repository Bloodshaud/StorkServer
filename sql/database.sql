--
-- Created on 27-11-2017
-- By Johannes Ernstsen
--

-- DDL
DROP DATABASE IF EXISTS stork;
CREATE DATABASE stork;
USE stork;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id        INT(11) PRIMARY KEY AUTO_INCREMENT,
  name      VARCHAR(255) NOT NULL,
  picture   VARCHAR(255),
  mail      VARCHAR(255) NOT NULL,
  password  VARCHAR(255) NOT NULL,
  location  VARCHAR(255),
  friends   VARCHAR(255),
  sessionId VARCHAR(255)
);

DROP TABLE IF EXISTS groups;
CREATE TABLE groups (
  id   INT(11) PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255)
);

DROP TABLE IF EXISTS users_groups;
CREATE TABLE users_groups (
  userId  INT(11),
  groupId INT(11)
);