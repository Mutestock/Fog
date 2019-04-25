-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Roof`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Roof` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Roof` (
  `Roof_id` INT NOT NULL AUTO_INCREMENT,
  `Type` VARCHAR(45) NOT NULL,
  `Slope` INT UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`Roof_id`),
  UNIQUE INDEX `Roof_id_UNIQUE` (`Roof_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Shed`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Shed` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Shed` (
  `Shed_id` INT NOT NULL AUTO_INCREMENT,
  `Cover` VARCHAR(45) NOT NULL,
  `Width` INT UNSIGNED NOT NULL,
  `Length` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`Shed_id`),
  UNIQUE INDEX `Shed_id_UNIQUE` (`Shed_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Carport`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Carport` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Carport` (
  `Carport_id` INT NOT NULL AUTO_INCREMENT,
  `Width` INT UNSIGNED NOT NULL,
  `Length` INT UNSIGNED NOT NULL,
  `Shed_id` INT NOT NULL,
  `Roof_id` INT NOT NULL,
  PRIMARY KEY (`Carport_id`),
  UNIQUE INDEX `Carport_id_UNIQUE` (`Carport_id` ASC) VISIBLE,
  INDEX `Roof_id_idx` (`Roof_id` ASC) VISIBLE,
  INDEX `Shed_id_idx` (`Shed_id` ASC) VISIBLE,
  CONSTRAINT `Roof_id_fk`
    FOREIGN KEY (`Roof_id`)
    REFERENCES `mydb`.`Roof` (`Roof_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `Shed_id_fk`
    FOREIGN KEY (`Shed_id`)
    REFERENCES `mydb`.`Shed` (`Shed_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Customer` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Customer` (
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
-- Table `mydb`.`Request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Request` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Request` (
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
    REFERENCES `mydb`.`Customer` (`Customer_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `Carport_id_fk`
    FOREIGN KEY (`Carport_id`)
    REFERENCES `mydb`.`Carport` (`Carport_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
