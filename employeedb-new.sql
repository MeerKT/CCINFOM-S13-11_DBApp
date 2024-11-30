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
DROP TABLE IF EXISTS `employee_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;

CREATE TABLE IF NOT EXISTS `employeeDB`.`Employee_Records` (
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
  `performance_reviews` ENUM('Needs Improvement', 'Good', 'Satisfactory', 'Excellent') NOT NULL,
  `termination_date` DATE NULL,
  `hire_date` DATE NOT NULL,
  PRIMARY KEY (`employee_ID`),
  UNIQUE INDEX `employee_ID_UNIQUE` (`employee_ID` ASC) VISIBLE)
ENGINE = InnoDB;
/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO `employeeDB`.`Employee_Records`
(`employee_ID`, `first_name`, `last_name`, `gender`, `department_name`, `position_type`, `years_of_experience`, `education`, `check_in_time`, `check_out_time`, `performance_reviews`, `termination_date`, `hire_date`) 
VALUES
(1, 'John', 'Doe', 'Male', 'IT', 'Software Engineer', 5, 'Bachelor of Computer Science', '08:00:00', '17:00:00', 'Excellent', NULL, '2018-06-15'),
(2, 'Jane', 'Smith', 'Female', 'HR', 'HR Manager', 8, 'Master of Business Administration', '09:00:00', '18:00:00', 'Satisfactory', NULL, '2015-03-10'),
(3, 'Michael', 'Brown', 'Male', 'Finance', 'Financial Analyst', 3, 'Bachelor of Accounting', '08:30:00', '17:30:00', 'Needs Improvement', NULL, '2020-01-05'),
(4, 'Sarah', 'Taylor', 'Female', 'Marketing', 'Marketing Specialist', 2, 'Bachelor of Marketing', '10:00:00', '19:00:00', 'Good', '2024-10-30', '2021-11-15'),
(5, 'Chris', 'Johnson', 'Male', 'Operations', 'Operations Manager', 10, 'Bachelor of Management', '07:45:00', '16:45:00', 'Excellent', NULL, '2012-09-20');


-- -----------------------------------------------------
-- Table `employeeDB`.`Employee_Status_Change`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `Employee_Status_Change`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;

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
/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO `employeeDB`.`Employee_Status_Change`
(`status_changeID`, `employee_ID`, `status_type`, `status_change_date`) 
VALUES
(1, 1, 'Active', '2020-05-07'),
(2, 2, 'Active', '2016-06-12'),
(3, 3, 'Promoted', '2020-12-04'),
(4, 4, 'Terminated', '2022-01-05'),
(5, 5, 'Active', '2022-05-02');

-- -----------------------------------------------------
-- Table `employeeDB`.`Performance_Review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Performance_Review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO `employeeDB`.`Performance_Review` (`employee_ID`, `reviewID`, `review_date`, `review_score`, `comments`) VALUES
(1, 101, '2023-01-15', 98, 'Consistently meets goals. Demonstrates leadership potential.'),
(2, 102, '2023-06-20', 75, 'Excellent work ethic. Needs improvement in time management and teamwork skills.'),  
(3, 103, '2022-12-10', 31, 'Needs to enhance technical skills and client interactions.'), 
(4, 104, '2023-03-25', 66, 'Adequate performance but requires additional training.'), 
(5, 105, '2023-09-12', 96, 'Outstanding performance and highly proactive.');

-- -----------------------------------------------------
-- Table `employeeDB`.`Hiring_Termination`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Hiring_Termination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `employeeDB`.`Hiring_Termination` (
  `employee_ID` INT NOT NULL,
  `event_type` ENUM('Hired', 'Terminated', 'Transferred') NOT NULL,
  `department_from` ENUM('IT', 'HR', 'Finance', 'Marketing', 'Operations') NULL,
  `department_to` ENUM('IT', 'HR', 'Finance', 'Marketing', 'Operations') NULL,
  `event_date` DATE NOT NULL,
  PRIMARY KEY (`employee_ID`),
  UNIQUE INDEX `employee_ID_UNIQUE` (`employee_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Hiring_Termination_Employee_Records1`
    FOREIGN KEY (`employee_ID`)
    REFERENCES `employeeDB`.`Employee_Records` (`employee_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO `employeeDB`.`Hiring_Termination` (`employee_ID`, `event_type`, `department_from`, `department_to`, `event_date`) VALUES
(1, 'Hired', NULL, 'IT', '2021-06-01'),
(2, 'Transferred', 'Operations', 'HR', '2022-04-15'),
(3, 'Hired', NULL, 'Finance', '2022-09-10'),
(4, 'Terminated', 'Marketing', NULL, '2023-11-01'),
(5, 'Transferred', 'HR', 'Operations', '2023-08-20');


-- -----------------------------------------------------
-- Table `employeeDB`.`Projects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `employeeDB`.`Projects` (
  `project_type` ENUM('Software Developemnt', 'Recruitment Process Improvement', 'Cost Reduction Initiative', 'Public Relations', 'Process Optimization') NOT NULL,
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
/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO  `employeeDB`.`Projects` 
(`employee_ID`, `project_type`, `project_ID`, `start_date`, `end_date`, `project_deadline`, `project_description`, `project_status`, `client`)
 VALUES
(1, 'Software Developemnt', 101, '2023-01-01', '2023-03-30', '2023-04-01', 'Developing a CRM system for client management.', 'Completed', 'TechCorp Inc.'),
(2, 'Recruitment Process Improvement', 102, '2023-05-15', NULL, '2023-12-31', 'Enhance recruitment process to attract talent.', 'Pending', 'Global Networks'),
(3, 'Cost Reduction Initiative', 103, '2022-10-01', '2023-02-15', '2023-03-01', 'Identify areas for cost reduction within the organization.', 'Completed', 'SecureSystems Ltd.'),
(4, 'Public Relations', 104, '2023-07-01', NULL, '2024-01-15', 'Enhance public relations efforts to build a positive brand image.', 'Pending', 'Cloud Solutions Co.'),
(5, 'Process Optimization', 105, '2022-06-01', '2023-05-31', '2023-06-15', 'Evaluate and streamline existing processes to increase efficiency.', 'Completed', 'Data Insight Inc.');





-- -----------------------------------------------------
-- Table `employeeDB`.`Employee_Salary`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Employee_Salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO `employeeDB`.`Employee_Salary`
(`employee_ID`, `salary_ID`, `basic_salary`, `bank_account`, `overtime_record`, `taxes`, `benefits`, `raises`, `net_total_salary`) 
VALUES
(1, 101, 50000.00, '1234567890', 10, 6000.00, 'Health Insurance, Retirement Plan', 2000.00, 46000.00),
(2, 102, 40000.00, '9876543210', 5, 4000.00, 'Dental Plan, Travel Allowance', 1500.00, 37500.00),
(3, 103, 60000.00, '1122334455', 8, 8000.00, 'Housing Allowance, Medical Coverage', 3000.00, 55000.00),
(4, 104, 35000.00, '5566778899', 12, 3000.00, 'Free Lunch, Commute Allowance', 1000.00, 33000.00),
(5, 105, 45000.00, '2233445566', 0, 5000.00, 'Bonus Incentives, Fitness Allowance', 2500.00, 42500.00);


-- -----------------------------------------------------
-- Table `employeeDB`.`employee_documents`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `employee_documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `employeeDB`.`employee_documents` (
  `employee_ID` INT NOT NULL,
  `document_ID` INT NOT NULL,

  `document_type` ENUM('Government ID', 'Employee Contract', 'NDA', 'Certificates', 'Health', 'Insurance') NOT NULL,
  `document_status` ENUM('Active', 'Expired') NOT NULL,
  `submission_date` DATE NOT NULL,
  `expiry_date` DATE NOT NULL,
  INDEX `fk_table1_Employee_Records1_idx` (`employee_ID` ASC) VISIBLE,
  PRIMARY KEY (`document_ID`),
  UNIQUE INDEX `document_ID_UNIQUE` (`document_ID` ASC) VISIBLE,
  CONSTRAINT `fk_table1_Employee_Records1`
    FOREIGN KEY (`employee_ID`)
    REFERENCES `employeeDB`.`Employee_Records` (`employee_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO `employeeDB`.`employee_documents` 
  (`employee_ID`, `document_ID`, `document_type`, `document_status`, `submission_date`, `expiry_date`) 
VALUES
  (1, 101, 'Government ID', 'Active', '2022-01-15', '2027-01-14'),
  (2, 102, 'Employee Contract', 'Active', '2023-03-01', '2025-03-01'),
  (3, 103, 'Certificates', 'Expired', '2019-06-20', '2021-06-20'),
  (4, 104, 'Insurance', 'Active', '2021-08-10', '2024-08-10'),
  (5, 105, 'NDA', 'Expired', '2020-05-05', '2022-05-05');


-- -----------------------------------------------------
-- Table `employeeDB`.`Salary_Disbursement`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `Salary_Disbursement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;

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
INSERT INTO `employeeDB`.`Salary_Disbursement` 
  (`salary_ID`, `disbursment_date`, `disbursementID`) 
VALUES
  (101, '2024-01-15', 1),
  (101, '2024-02-15', 2),
  (102, '2024-01-20', 3),
  (102, '2024-02-20', 4),
  (103, '2024-01-25', 5),
  (103, '2024-02-25', 6),
  (104, '2024-01-30', 7),
  (104, '2024-02-28', 8),
  (105, '2024-01-10', 9),
  (105, '2024-02-10', 10);




SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
