
INSERT INTO `testims`.`customers` (`first_name`, `last_name`) VALUES ('jordan', 'harrison');
INSERT INTO `testims`.`customers` (`first_name`, `last_name`) VALUES ('john', 'doe');
INSERT INTO `testims`.`customers` (`first_name`, `last_name`) VALUES ('jane', 'doe');
INSERT INTO `testims`.`customers` (`first_name`, `last_name`) VALUES ('peter', 'parker');
INSERT INTO `testims`.`customers` (`first_name`, `last_name`) VALUES ('mary', 'jane');
INSERT INTO `testims`.`customers` (`first_name`, `last_name`) VALUES ('miles', 'morales');

INSERT INTO `testims`.`items` (`name`, `price`) VALUES ('Spider-Man: Miles Morales', 40);
INSERT INTO `testims`.`items` (`name`, `price`) VALUES ('Call of Duty: Black Ops', 50);
INSERT INTO `testims`.`items` (`name`, `price`) VALUES ('PlayStation 5', 459);

INSERT INTO `testims`.`orders`(`customer_id`) VALUES(1);

INSERT INTO `testims`.`order_line`(`order_id`, `item_id`) VALUES(1,2);

