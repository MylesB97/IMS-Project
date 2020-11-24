drop schema testims;
CREATE SCHEMA IF NOT EXISTS `testims`;
USE `testims` ;
CREATE TABLE IF NOT EXISTS `testims`.`customers` (
    `id` smallint(5) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NULL DEFAULT NULL,
    `last_name` VARCHAR(40) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `testims`.`items`(
	`id` smallint(5) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(40) NULL DEFAULT NULL,
    `price` decimal(15,2) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
    );
    
 CREATE TABLE IF NOT EXISTS `testims`.`orders` (
	`id` SMALLINT(5) NOT NULL AUTO_INCREMENT,
    `customer_id` SMALLINT NOT NULL,
    `price` decimal(15,2) NULL DEFAULT NULL,
    PRIMARY KEY(`id`),
	CONSTRAINT `fk_customer_id` FOREIGN KEY(`customer_id`) REFERENCES `testims`.`customers`(`id`)
    );   
    
CREATE TABLE IF NOT EXISTS `testims`.`order_line` (
	`id` smallint(5) NOT NULL AUTO_INCREMENT,
    `item_id` smallint NOT NULL,
    `order_id` smallint NOT NULL,
    PRIMARY KEY(`id`),
    CONSTRAINT `fk_items_id` FOREIGN KEY(`item_id`) REFERENCES `testims`.`items`(`id`),
    CONSTRAINT `fk_order_id` FOREIGN KEY(`order_id`) REFERENCES `testims`.`orders`(`id`)
    );

