CREATE SCHEMA `fitnessdb` ;

CREATE TABLE `fitnessdb`.`workouts` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `trainer` VARCHAR(45) NOT NULL,
  `description` VARCHAR(3000) NULL,
  PRIMARY KEY (`id`));