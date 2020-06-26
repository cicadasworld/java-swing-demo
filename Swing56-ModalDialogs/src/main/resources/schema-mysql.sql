CREATE DATABASE IF NOT EXISTS `swingtest`;
USE `swingtest`;
--
-- Table structure for table `people`
--
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `age` ENUM('child', 'adult', 'senior') NOT NULL,
  `employment_status` VARCHAR(45) NOT NULL,
  `us_citizen` TINYINT NOT NULL,
  `tax_id` VARCHAR(45) NULL,
  `gender` ENUM('male', 'female') NOT NULL,
  `occupation` VARCHAR(45) NULL,
  PRIMARY KEY (`id`)
  ) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
