ALTER TABLE `delivery`.`usuario`
CHANGE COLUMN `email` `email` VARCHAR(255) NOT NULL ,
ADD UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE;
;
