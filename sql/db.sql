-- MySQL Script generated by MySQL Workbench
-- Fri Nov  3 12:19:25 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ssafyenjoy
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ssafyenjoy` ;

-- -----------------------------------------------------
-- Schema ssafyenjoy
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafyenjoy` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ssafyenjoy` ;

-- -----------------------------------------------------
-- Table `ssafyenjoy`.`member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyenjoy`.`member` ;

CREATE TABLE IF NOT EXISTS `ssafyenjoy`.`member` (
  `userId` VARCHAR(45) NOT NULL,
  `userName` VARCHAR(45) NOT NULL,
  `userPwd` VARCHAR(45) NOT NULL,
  `emailId` VARCHAR(45) NOT NULL,
  `emailDomain` VARCHAR(45) NOT NULL,
  `joinDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `token` varchar(1000) null default null,
  PRIMARY KEY (`userId`))
ENGINE = InnoDB;

insert into member (userId, userName, userPwd, emailId, emailDomain) values ("ssafy", "ssafy", "ssafy", "ssafy", "google.com");
select * from member;


-- -----------------------------------------------------
-- Table `ssafyenjoy`.`board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyenjoy`.`board` ;

CREATE TABLE IF NOT EXISTS `ssafyenjoy`.`board` (
  `articleNo` INT NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(45) NOT NULL,
  `subject` VARCHAR(45) NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  `hit` INT DEFAULT 0,
  `registerTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`articleNo`),
  INDEX `userId_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `userId`
    FOREIGN KEY (`userId`)
    REFERENCES `ssafyenjoy`.`member` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

insert into board (articleNo, userId, subject, content) values (1, "ssafy", "hi", "bye");
select * from board;

-- -----------------------------------------------------
-- Table `ssafyenjoy`.`Sido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyenjoy`.`sido` ;

CREATE TABLE IF NOT EXISTS `ssafyenjoy`.`sido` (
  `sidoCode` INT NOT NULL,
  `sidoName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`sidoCode`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafyenjoy`.`Gugun`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyenjoy`.`gugun` ;

CREATE TABLE IF NOT EXISTS `ssafyenjoy`.`gugun` (
  `sidoCode` INT NOT NULL,
  `gugunCode` INT NOT NULL,
  `gugunName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`gugunCode`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafyenjoy`.`Marker`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyenjoy`.`attraction_info` ;

CREATE TABLE IF NOT EXISTS `ssafyenjoy`.`attraction_info` (
  `contentTypeId` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `addr1` VARCHAR(45) NOT NULL,
  `addr2` VARCHAR(45) NULL,
  `firstImage` VARCHAR(45) NULL,
  `readCount` INT NOT NULL,
  `latitude` DOUBLE NOT NULL,
  `longitude` DOUBLE NOT NULL,
  `sidoCode` INT NOT NULL,
  `gugunCode` INT NOT NULL
  )
ENGINE = InnoDB;

insert into sido (sidoCode, sidoName) values (1, "서울");

select * from sido;


insert into gugun (sidoCode, gugunCode, gugunName) values (1, 1, 'tlqk');

delete from gugun;

select * from gugun;

insert into attraction_info (contentTypeId, title, addr1, addr2, firstImage, readCount, latitude, longitude, sidoCode, gugunCode) values (1, "Ehfkdl", "또라이", "13반", "근성맨", 2, 3.088, 1.008, 1, 1);

select * from attraction_info;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIattraction_infoQUE_CHECKS=@OLD_UNIQUE_CHECKS;