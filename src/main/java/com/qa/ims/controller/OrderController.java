package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	private OrderDAO orderDAO;
	private Utils utils;
	private final ItemDAO itemDAO;
		
	public OrderController(OrderDAO orderDAO, Utils utils, ItemDAO itemDAO) {
		super();
		this.orderDAO = orderDAO;
		this.itemDAO = itemDAO;
		this.utils = utils;
	}

	@Override
	public List<Order> readAll(){
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}
	
	@Override
	public Order create() {
			LOGGER.info("Please enter the Customer ID");
			Long customerID = utils.getLong();
			Order order = orderDAO.create(new Order(customerID));
			LOGGER.info("Order created");
			return order;
	}
		
		

	@Override
	public Order update() {
		LOGGER.info("Would you like to (1) Change Order Owner or (2) Add item to order");
		String choice = utils.getString();
		switch(choice) {
		case "1":
			LOGGER.info("Please enter the ID of the order you would like to update");
			Long id = utils.getLong();
			LOGGER.info("Please enter the new Customer ID");
			Long customerID = utils.getLong();
			Order order = orderDAO.update(new Order(id, customerID));
			LOGGER.info("Item Updated");
			return order;
		case "2":
			itemDAO.readAll();
			LOGGER.info("Please enter the Order ID");
			Long orderID = utils.getLong();
			LOGGER.info("Please enter the Item ID");
			Long itemID = utils.getLong();
			Order itemOrder = orderDAO.createLine(orderID, itemID);
			return itemOrder;
		}
		return null;
	}

	@Override
	public int delete() {
		LOGGER.info("Would you like to (1) Remove an item from an order or (2) Delete an Entire Order");
		String choice = utils.getString();
		switch(choice) {
		case "1":
			LOGGER.info("Please enter the ID of the order you would Change");
			Long id = utils.getLong();
			LOGGER.info(orderDAO.readLines(id));
			LOGGER.info("Please enter the id of the item you would like to delete");
			Long itemID = utils.getLong();
			return orderDAO.removeItem(id, itemID);
		case "2":
			LOGGER.info("Please enter the ID of the order you would like to delete");
			Long orderID = utils.getLong();
			return orderDAO.delete(orderID);
		}
		return 0;
	}

}
