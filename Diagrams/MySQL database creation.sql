-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema carportDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `carportDB` ;

-- -----------------------------------------------------
-- Schema carportDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `carportDB` DEFAULT CHARACTER SET utf8 ;
USE `carportDB` ;

-- -----------------------------------------------------
-- Table `carportDB`.`Roof`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carportDB`.`Roof` ;

CREATE TABLE IF NOT EXISTS `carportDB`.`Roof` (
  `Roof_id` INT NOT NULL AUTO_INCREMENT,
  `Type` VARCHAR(45) NOT NULL,
  `Slope` INT UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`Roof_id`),
  UNIQUE INDEX `Roof_id_UNIQUE` (`Roof_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carportDB`.`Shed`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carportDB`.`Shed` ;

CREATE TABLE IF NOT EXISTS `carportDB`.`Shed` (
  `Shed_id` INT NOT NULL AUTO_INCREMENT,
  `Cover` VARCHAR(45) NOT NULL,
  `Width` INT UNSIGNED NOT NULL,
  `Length` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`Shed_id`),
  UNIQUE INDEX `Shed_id_UNIQUE` (`Shed_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carportDB`.`Carport`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carportDB`.`Carport` ;

CREATE TABLE IF NOT EXISTS `carportDB`.`Carport` (
  `Carport_id` INT NOT NULL AUTO_INCREMENT,
  `Width` INT UNSIGNED NOT NULL,
  `Length` INT UNSIGNED NOT NULL,
  `Shed_id` INT NULL,
  `Roof_id` INT NOT NULL,
  PRIMARY KEY (`Carport_id`),
  UNIQUE INDEX `Carport_id_UNIQUE` (`Carport_id` ASC) VISIBLE,
  INDEX `Roof_id_idx` (`Roof_id` ASC) VISIBLE,
  INDEX `Shed_id_idx` (`Shed_id` ASC) VISIBLE,
  CONSTRAINT `Roof_id_fk`
    FOREIGN KEY (`Roof_id`)
    REFERENCES `carportDB`.`Roof` (`Roof_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `Shed_id_fk`
    FOREIGN KEY (`Shed_id`)
    REFERENCES `carportDB`.`Shed` (`Shed_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carportDB`.`Customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carportDB`.`Customer` ;

CREATE TABLE IF NOT EXISTS `carportDB`.`Customer` (
  `Customer_id` INT NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `Address` VARCHAR(45) NOT NULL,
  `Zipcode` INT(4) UNSIGNED NOT NULL,
  `City` VARCHAR(45) NOT NULL,
  `Phone` INT(8) UNSIGNED NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `Customer_id_UNIQUE` (`Customer_id` ASC) VISIBLE,
  PRIMARY KEY (`Customer_id`),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carportDB`.`Request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carportDB`.`Request` ;

CREATE TABLE IF NOT EXISTS `carportDB`.`Request` (
  `Request_id` INT NOT NULL AUTO_INCREMENT,
  `Carport_id` INT NOT NULL,
  `Customer_id` INT NOT NULL,
  `Date` DATE NOT NULL,
  `Comments` TEXT NULL,
  PRIMARY KEY (`Request_id`),
  UNIQUE INDEX `Request_id_UNIQUE` (`Request_id` ASC) VISIBLE,
  INDEX `Customer_id_idx` (`Customer_id` ASC) VISIBLE,
  INDEX `Carport_id_idx` (`Carport_id` ASC) VISIBLE,
  CONSTRAINT `Customer_id_fk`
    FOREIGN KEY (`Customer_id`)
    REFERENCES `carportDB`.`Customer` (`Customer_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `Carport_id_fk`
    FOREIGN KEY (`Carport_id`)
    REFERENCES `carportDB`.`Carport` (`Carport_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
