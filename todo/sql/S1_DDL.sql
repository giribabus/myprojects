drop table usersdb.user_dept;
drop table usersdb.user_role;
drop table usersdb.task_activity;
drop table usersdb.schedule;
drop table usersdb.tasklist_assignment;
drop table usersdb.task;
drop table usersdb.tasklist;
drop table usersdb.department;
drop table usersdb.user_group;
drop table usersdb.groups;
drop table usersdb.user;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema usersdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema usersdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `usersdb` DEFAULT CHARACTER SET utf8 ;
USE `usersdb` ;

-- -----------------------------------------------------
-- Table `usersdb`.`department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usersdb`.`department` (
  `dept_id` INT(11) NOT NULL AUTO_INCREMENT,
  `dept_nm` CHAR(100) NOT NULL,
  `created_by` CHAR(100) NULL DEFAULT NULL,
  `created_dttm` DATETIME NULL DEFAULT NULL,
  `updated_by` CHAR(100) NULL DEFAULT NULL,
  `updated_dttm` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`dept_id`),
  UNIQUE INDEX `dept_id` (`dept_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `usersdb`.`groups`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usersdb`.`groups` (
  `group_id` INT(11) NOT NULL AUTO_INCREMENT,
  `group_nm` CHAR(100) NULL DEFAULT NULL,
  `created_by` CHAR(100) NULL DEFAULT NULL,
  `created_dttm` DATETIME NULL DEFAULT NULL,
  `updated_by` CHAR(100) NULL DEFAULT NULL,
  `updated_dttm` DATETIME NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`group_id`),
  UNIQUE INDEX `group_id` (`group_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `usersdb`.`tasklist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usersdb`.`tasklist` (
  `ts_id` INT(11) NOT NULL AUTO_INCREMENT,
  `tasklist_nm` CHAR(100) NOT NULL,
  `tasklist_content` TEXT NULL DEFAULT NULL,
  `dept_id` INT(11) NOT NULL,
  `created_by` CHAR(100) NULL DEFAULT NULL,
  `created_dttm` DATETIME NULL DEFAULT NULL,
  `updated_by` CHAR(100) NULL DEFAULT NULL,
  `updated_dttm` DATETIME NULL DEFAULT NULL,
  `locked_user` CHAR(100) NULL DEFAULT NULL,
  `status` CHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`ts_id`),
  UNIQUE INDEX `ts_id` (`ts_id` ASC),
  UNIQUE INDEX `dept_id` (`dept_id` ASC),
  CONSTRAINT `tasklist_ibfk_1`
    FOREIGN KEY (`dept_id`)
    REFERENCES `usersdb`.`department` (`dept_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `usersdb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usersdb`.`user` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_name` CHAR(100) NOT NULL,
  `password` CHAR(100) NOT NULL,
  `first_nm` CHAR(100) NOT NULL,
  `last_nm` CHAR(100) NOT NULL,
  `email` CHAR(200) NOT NULL,
  `mobile` CHAR(15) NOT NULL,
  `created_by` CHAR(100) NULL DEFAULT NULL,
  `created_dttm` DATETIME NULL DEFAULT NULL,
  `updated_by` CHAR(100) NULL DEFAULT NULL,
  `updated_dttm` DATETIME NULL DEFAULT NULL,
  `status` CHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id` (`user_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `usersdb`.`tasklist_assignment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usersdb`.`tasklist_assignment` (
  `assignment_id` INT(11) NOT NULL AUTO_INCREMENT,
  `group_id` INT(11) NULL DEFAULT NULL,
  `user_id` INT(11) NULL DEFAULT NULL,
  `ts_id` INT(11) NOT NULL,
  `occurrence` CHAR(50) NOT NULL COMMENT 'Once\nDaily\nWeekly\nMonthly\nQuaterly\nYearly\n',
  `created_by` CHAR(100) NULL DEFAULT NULL,
  `created_dttm` DATETIME NULL DEFAULT NULL,
  `updated_by` CHAR(100) NULL DEFAULT NULL,
  `updated_dttm` DATETIME NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`assignment_id`),
  UNIQUE INDEX `assignment_id` (`assignment_id` ASC),
  UNIQUE INDEX `ts_id` (`ts_id` ASC),
  INDEX `group_id` (`group_id` ASC),
  INDEX `user_id` (`user_id` ASC),
  CONSTRAINT `tasklist_assignment_ibfk_1`
    FOREIGN KEY (`group_id`)
    REFERENCES `usersdb`.`groups` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `tasklist_assignment_ibfk_2`
    FOREIGN KEY (`ts_id`)
    REFERENCES `usersdb`.`tasklist` (`ts_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `tasklist_assignment_ibfk_3`
    FOREIGN KEY (`user_id`)
    REFERENCES `usersdb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `usersdb`.`schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usersdb`.`schedule` (
  `schedule_id` INT(11) NOT NULL AUTO_INCREMENT,
  `assignment_id` INT(11) NOT NULL,
  `start_dttm` DATETIME NOT NULL,
  `end_dttm` DATETIME NOT NULL,
  `created_by` CHAR(100) NULL DEFAULT NULL,
  `created_dttm` DATETIME NULL DEFAULT NULL,
  `updated_by` CHAR(100) NULL DEFAULT NULL,
  `updated_dttm` DATETIME NULL DEFAULT NULL,
  `occurrence` CHAR(100) NULL DEFAULT NULL,
  `status` CHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`schedule_id`),
  UNIQUE INDEX `schedule_id` (`schedule_id` ASC),
  UNIQUE INDEX `assignment_id` (`assignment_id` ASC),
  CONSTRAINT `schedule_ibfk_1`
    FOREIGN KEY (`assignment_id`)
    REFERENCES `usersdb`.`tasklist_assignment` (`assignment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `usersdb`.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usersdb`.`task` (
  `task_id` INT(11) NOT NULL AUTO_INCREMENT,
  `ts_id` INT(11) NOT NULL,
  `task_nm` TEXT NOT NULL,
  `task_shortnm` CHAR(250) NULL DEFAULT NULL,
  `created_by` CHAR(100) NULL DEFAULT NULL,
  `created_dttm` DATETIME NULL DEFAULT NULL,
  `updated_by` CHAR(100) NULL DEFAULT NULL,
  `updated_dttm` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  UNIQUE INDEX `task_id` (`task_id` ASC),
  UNIQUE INDEX `ts_id` (`ts_id` ASC),
  CONSTRAINT `task_ibfk_1`
    FOREIGN KEY (`ts_id`)
    REFERENCES `usersdb`.`tasklist` (`ts_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `usersdb`.`task_activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usersdb`.`task_activity` (
  `activity_id` INT(11) NOT NULL AUTO_INCREMENT,
  `ts_id` INT(11) NOT NULL,
  `task_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `schedule_id` INT(11) NOT NULL,
  `task_result` CHAR(100) NULL DEFAULT NULL,
  `task_comment` CHAR(250) NULL DEFAULT NULL,
  `task_file` CHAR(100) NULL DEFAULT NULL,
  `task_audio` CHAR(100) NULL DEFAULT NULL,
  `task_vedio` CHAR(100) NULL DEFAULT NULL,
  `created_by` CHAR(100) NULL DEFAULT NULL,
  `created_dttm` DATETIME NULL DEFAULT NULL,
  `updated_by` CHAR(100) NULL DEFAULT NULL,
  `updated_dttm` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`activity_id`),
  UNIQUE INDEX `activity_id` (`activity_id` ASC),
  UNIQUE INDEX `ts_id` (`ts_id` ASC),
  UNIQUE INDEX `task_id` (`task_id` ASC),
  UNIQUE INDEX `user_id` (`user_id` ASC),
  UNIQUE INDEX `schedule_id` (`schedule_id` ASC),
  CONSTRAINT `task_activity_ibfk_1`
    FOREIGN KEY (`schedule_id`)
    REFERENCES `usersdb`.`schedule` (`schedule_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `task_activity_ibfk_2`
    FOREIGN KEY (`task_id`)
    REFERENCES `usersdb`.`task` (`task_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `task_activity_ibfk_3`
    FOREIGN KEY (`ts_id`)
    REFERENCES `usersdb`.`tasklist` (`ts_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `task_activity_ibfk_4`
    FOREIGN KEY (`user_id`)
    REFERENCES `usersdb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `usersdb`.`user_dept`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usersdb`.`user_dept` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `dept_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `created_by` CHAR(100) NULL DEFAULT NULL,
  `created_dttm` DATETIME NULL DEFAULT NULL,
  `updated_by` CHAR(100) NULL DEFAULT NULL,
  `updated_dttm` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id` ASC),
  UNIQUE INDEX `user_id` (`user_id` ASC),
  INDEX `user_dept_ibfk_1` (`dept_id` ASC),
  CONSTRAINT `user_dept_ibfk_1`
    FOREIGN KEY (`dept_id`)
    REFERENCES `usersdb`.`department` (`dept_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_dept_ibfk_2`
    FOREIGN KEY (`user_id`)
    REFERENCES `usersdb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `usersdb`.`user_group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usersdb`.`user_group` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `group_id` INT(11) NOT NULL,
  `created_by` CHAR(100) NULL DEFAULT NULL,
  `created_dttm` DATETIME NULL DEFAULT NULL,
  `updated_by` CHAR(100) NULL DEFAULT NULL,
  `updated_dttm` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id` ASC),
  UNIQUE INDEX `user_group` (`user_id` ASC, `group_id` ASC),
  INDEX `group_id` (`group_id` ASC),
  INDEX `user_id` (`user_id` ASC),
  CONSTRAINT `user_group_ibfk_1`
    FOREIGN KEY (`group_id`)
    REFERENCES `usersdb`.`groups` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_group_ibfk_2`
    FOREIGN KEY (`user_id`)
    REFERENCES `usersdb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `usersdb`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usersdb`.`user_role` (
  `role_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `role_type` CHAR(100) NOT NULL,
  `created_by` CHAR(100) NULL DEFAULT NULL,
  `created_dttm` DATETIME NULL DEFAULT NULL,
  `updated_by` CHAR(100) NULL DEFAULT NULL,
  `updated_dttm` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `user_id` (`user_id` ASC),
  CONSTRAINT `user_role_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `usersdb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO `usersdb`.`department` (`dept_id`, `dept_nm`, `created_by`, `created_dttm`) VALUES ('1', 'HR', 'Raja', '2016-06-11');
INSERT INTO `usersdb`.`department` (`dept_id`, `dept_nm`, `created_by`, `created_dttm`) VALUES ('2', 'Admin', 'Raja', '2016-06-11');
INSERT INTO `usersdb`.`department` (`dept_id`, `dept_nm`, `created_by`, `created_dttm`) VALUES ('3', 'Dev', 'Raja', '2016-06-11');
INSERT INTO `usersdb`.`department` (`dept_id`, `dept_nm`, `created_by`, `created_dttm`) VALUES ('4', 'Testing', 'Raja', '2016-06-11');
