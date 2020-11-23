drop schema ims;
CREATE SCHEMA IF NOT EXISTS `ims`;
USE `ims` ;
CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` smallint(5) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NULL DEFAULT NULL,
    `last_name` VARCHAR(40) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items`(
	`id` smallint(5) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(40) NULL DEFAULT NULL,
    `price` decimal(15,2) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
    );
    
 CREATE TABLE IF NOT EXISTS `ims`.`orders` (
	`id` SMALLINT(5) NOT NULL AUTO_INCREMENT,
    `customer_id` SMALLINT NOT NULL,
    PRIMARY KEY(`id`),
	CONSTRAINT `fk_customer_id` FOREIGN KEY(`customer_id`) REFERENCES `ims`.`customers`(`id`)
    );   
    
CREATE TABLE IF NOT EXISTS `ims`.`order_line` (
	`id` smallint(5) NOT NULL AUTO_INCREMENT,
    `item_id` smallint NOT NULL,
    `order_id` smallint NOT NULL,
    PRIMARY KEY(`id`),
    CONSTRAINT `fk_items_id` FOREIGN KEY(`item_id`) REFERENCES `ims`.`items`(`id`),
    CONSTRAINT `fk_order_id` FOREIGN KEY(`order_id`) REFERENCES `ims`.`orders`(`id`)
    );

