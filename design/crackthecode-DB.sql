-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema CrackTheCodeDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CrackTheCodeDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CrackTheCodeDB` DEFAULT CHARACTER SET utf8 ;
USE `CrackTheCodeDB` ;

-- -----------------------------------------------------
-- Table `CrackTheCodeDB`.`Round`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CrackTheCodeDB`.`Round` (
  `roundId` INT NOT NULL AUTO_INCREMENT,
  `roundNumber` INT NOT NULL,
  `guessTime` VARCHAR(45) NOT NULL,
  `partial` INT NOT NULL,
  `exact` INT NOT NULL,
  `Game_gameId` INT NOT NULL,
  PRIMARY KEY (`roundId`),
  INDEX `fk_Round_Game_idx` (`Game_gameId` ASC) VISIBLE,
  CONSTRAINT `fk_Round_Game`
    FOREIGN KEY (`Game_gameId`)
    REFERENCES `CrackTheCodeDB`.`Game` (`gameId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `CrackTheCodeDB`.`game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CrackTheCodeDB`.`game` (
  `gameId` INT NULL,
  `guess` VARCHAR(4),
  `answer` VARCHAR(4),
  `status` ENUM('in-Progress', 'finished') NOT NULL,
  PRIMARY KEY (`gameId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
