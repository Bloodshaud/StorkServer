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
  id          INT(11) PRIMARY KEY AUTO_INCREMENT,
  name        VARCHAR(255) NOT NULL,
  mail        VARCHAR(255) NOT NULL,
  password    VARCHAR(255) NOT NULL,
  location    VARCHAR(255),
  sessionId   VARCHAR(255),
  ownedGroups VARCHAR(255)
);

DROP TABLE IF EXISTS friends;
CREATE TABLE friends (
  userId   INT(11),
  friendId INT(11)
);
DROP TABLE IF EXISTS users_active_groups;
CREATE TABLE users_active_groups (
  userId  INT(11),
  groupId INT(11)
);

DROP TABLE IF EXISTS groups;
CREATE TABLE groups (
  id    INT(11) PRIMARY KEY AUTO_INCREMENT,
  name  VARCHAR(255),
  owner VARCHAR(255)
);

DROP TABLE IF EXISTS users_groups;
CREATE TABLE users_groups (
  userId  INT(11),
  groupId INT(11)
);

DROP TABLE IF EXISTS group_owners;
CREATE TABLE group_owners (
  groupId INT(11),
  userId  INT(11)
);