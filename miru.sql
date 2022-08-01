DROP DATABASE IF EXISTS miru;

CREATE DATABASE miru;

USE miru;

CREATE TABLE `user` (
  `id` varchar(30) NOT NULL,
  `password` char(64) NOT NULL,
  `email` varchar(50) NOT NULL,
  `recommendFlag` tinyint(1) NOT NULL,
  `salt` char(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `picture` (
  `pictureIdx` int NOT NULL AUTO_INCREMENT,
  `filepath` varchar(100) NOT NULL,
  `tag` varchar(50) NOT NULL,
  `publicFlag` tinyint(1) DEFAULT '1',
  `id` varchar(30) DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pictureIdx`),
  KEY `id` (`id`),
  CONSTRAINT `picture_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `favoriteuser` (
  `id` varchar(30) NOT NULL,
  `followId` varchar(30) NOT NULL,
  PRIMARY KEY (`id`,`followId`),
  KEY `followId` (`followId`),
  CONSTRAINT `favoriteuser_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `favoriteuser_ibfk_2` FOREIGN KEY (`followId`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `favoritepicture` (
  `id` varchar(30) NOT NULL,
  `pictureIdx` int NOT NULL,
  `pictureOrder` int NOT NULL,
  PRIMARY KEY (`id`,`pictureIdx`),
  KEY `pictureIdx` (`pictureIdx`),
  CONSTRAINT `favoritepicture_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `favoritepicture_ibfk_2` FOREIGN KEY (`pictureIdx`) REFERENCES `picture` (`pictureIdx`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci