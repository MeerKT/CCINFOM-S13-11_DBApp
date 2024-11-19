-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema employeeDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema employeeDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `employeeDB` DEFAULT CHARACTER SET utf8 ;
USE `employeeDB` ;

-- -----------------------------------------------------
-- Table `employeeDB`.`Employee_Records`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employeeDB`.`Employee_Records` (
  `check_in_time` VARCHAR(45) NOT NULL,
  `employee_ID` INT NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `gender` ENUM('male', 'female') NOT NULL,
  `department_name` VARCHAR(45) NOT NULL,
  `position_type` VARCHAR(45) NOT NULL,
  `years_of_experience` INT NOT NULL,
  `education` VARCHAR(45) NULL,
  `check_in_time` TIME NULL,
  `check_out_time` TIME NULL,
  `performance_reviews` ENUM('Good', 'Satisfactory', 'Excellent') NOT NULL,
  `termination_date` DATE NULL,
  `hire_date` DATE NOT NULL,
  PRIMARY KEY (`employee_ID`),
  UNIQUE INDEX `employee_ID_UNIQUE` (`employee_ID` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `employeeDB`.`Employee_Status_Change`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employeeDB`.`Employee_Status_Change` (
  `employee_ID` INT NOT NULL,
  `status_changeID` INT NOT NULL,
  `status_type` ENUM('Active', 'Terminated', 'Promoted') NOT NULL,
  `status_change_date` DATE NOT NULL,
  INDEX `fk_Employee_Status_Change_Employee_Records_idx` (`employee_ID` ASC) VISIBLE,
  PRIMARY KEY (`status_changeID`),
  UNIQUE INDEX `employee_ID_UNIQUE` (`employee_ID` ASC) VISIBLE,
  UNIQUE INDEX `status_changeID_UNIQUE` (`status_changeID` ASC) VISIBLE,
  CONSTRAINT `fk_Employee_Status_Change_Employee_Records`
    FOREIGN KEY (`employee_ID`)
    REFERENCES `employeeDB`.`Employee_Records` (`employee_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `employeeDB`.`Performance_Review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employeeDB`.`Performance_Review` (
  `employee_ID` INT NOT NULL,
  `reviewID` INT NOT NULL,
  `review_date` DATE NOT NULL,
  `review_score` INT NOT NULL,
  `comments` VARCHAR(100) NULL,
  INDEX `fk_Performance_Review_Employee_Records1_idx` (`employee_ID` ASC) VISIBLE,
  PRIMARY KEY (`reviewID`),
  UNIQUE INDEX `employee_ID_UNIQUE` (`employee_ID` ASC) VISIBLE,
  UNIQUE INDEX `reviewID_UNIQUE` (`reviewID` ASC) VISIBLE,
  CONSTRAINT `fk_Performance_Review_Employee_Records1`
    FOREIGN KEY (`employee_ID`)
    REFERENCES `employeeDB`.`Employee_Records` (`employee_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `employeeDB`.`Hiring_Termination`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employeeDB`.`Hiring_Termination` (
  `employee_ID` INT NOT NULL,
  `event_type` ENUM('Hired', 'Terminated', 'Transferred') NOT NULL,
  `department_from` ENUM('Financial', 'Logistics', 'RnD', 'Marketing', 'Internal Relations', 'External Relations') NULL,
  `department_to` ENUM('Financial', 'Logistics', 'RnD', 'Marketing', 'Internal Relations', 'External Relations') NULL,
  `event_date` DATE NOT NULL,
  PRIMARY KEY (`employee_ID`),
  UNIQUE INDEX `employee_ID_UNIQUE` (`employee_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Hiring_Termination_Employee_Records1`
    FOREIGN KEY (`employee_ID`)
    REFERENCES `employeeDB`.`Employee_Records` (`employee_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `employeeDB`.`Projects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employeeDB`.`Projects` (
  `project_type` ENUM('Software Developemnt', 'Infrastruture Improvements', 'Cyber Security', 'Cloud', 'Data Managemnet & Analytics', 'ENP', 'Digital transformation', 'Leagacy Systems') NOT NULL,
  `employee_ID` INT NOT NULL,
  `project_ID` INT NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NULL,
  `project_deadline` DATE NOT NULL,
  `project_description` VARCHAR(225) NOT NULL,
  `project_status` ENUM('Pending', 'Completed', 'Overdue') NOT NULL,
  `client` VARCHAR(50) NOT NULL,
  INDEX `fk_Projects_Employee_Records1_idx` (`employee_ID` ASC) VISIBLE,
  PRIMARY KEY (`project_ID`),
  UNIQUE INDEX `employee_ID_UNIQUE` (`employee_ID` ASC) VISIBLE,
  UNIQUE INDEX `project_ID_UNIQUE` (`project_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Projects_Employee_Records1`
    FOREIGN KEY (`employee_ID`)
    REFERENCES `employeeDB`.`Employee_Records` (`employee_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `employeeDB`.`Employee_Salary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employeeDB`.`Employee_Salary` (
  `employee_ID` INT NOT NULL,
  `salary_ID` INT NOT NULL,
  `basic_salary` DECIMAL(10,2) NOT NULL,
  `bank_account` VARCHAR(20) NOT NULL,
  `overtime_record` INT NOT NULL,
  `taxes` DECIMAL(10,2) NOT NULL,
  `benefits` VARCHAR(100) NULL,
  `raises` DECIMAL(10,2) NULL,
  `net_total_salary` DECIMAL(10,2) NOT NULL,
  INDEX `fk_Employee_Salary_Employee_Records1_idx` (`employee_ID` ASC) VISIBLE,
  PRIMARY KEY (`salary_ID`),
  UNIQUE INDEX `employee_ID_UNIQUE` (`employee_ID` ASC) VISIBLE,
  UNIQUE INDEX `salary_ID_UNIQUE` (`salary_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Employee_Salary_Employee_Records1`
    FOREIGN KEY (`employee_ID`)
    REFERENCES `employeeDB`.`Employee_Records` (`employee_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `employeeDB`.`employee_documents`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employeeDB`.`employee_documents` (
  `Employee_Records_employee_ID` INT NOT NULL,
  `document_ID` INT NOT NULL,
  `doc_type` VARCHAR(45) NULL,
  `employee_documentscol` VARCHAR(45) NULL,
  `document_type` ENUM('Government ID', 'Employee Contract', 'NDA', 'Certificates', 'Health', 'Insurance') NOT NULL,
  `document_status` ENUM('Active', 'Expired') NOT NULL,
  `submission_date` DATE NOT NULL,
  `expiry_date` DATE NOT NULL,
  INDEX `fk_table1_Employee_Records1_idx` (`Employee_Records_employee_ID` ASC) VISIBLE,
  PRIMARY KEY (`document_ID`),
  UNIQUE INDEX `document_ID_UNIQUE` (`document_ID` ASC) VISIBLE,
  CONSTRAINT `fk_table1_Employee_Records1`
    FOREIGN KEY (`Employee_Records_employee_ID`)
    REFERENCES `employeeDB`.`Employee_Records` (`employee_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `employeeDB`.`Salary_Disbursement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employeedb`.`Salary_Disbursement` (
    `salary_ID` INT NOT NULL,
    `disbursment_date` DATE NOT NULL,
    `disbursementID` INT NOT NULL,
    PRIMARY KEY (`disbursementID`),
    UNIQUE INDEX `disbursementID_UNIQUE` (`disbursementID` ASC),
    CONSTRAINT `fk_table1_Employee_Salary1`
        FOREIGN KEY (`salary_ID`)
        REFERENCES `employeedb`.`Employee_Salary` (`salary_ID`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE = InnoDB;




SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
