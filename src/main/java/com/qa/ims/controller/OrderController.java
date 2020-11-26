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
	private ItemDAO itemDAO;
		
	public OrderController(OrderDAO orderDAO, Utils utils, ItemDAO itemDAO) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
		this.itemDAO = itemDAO;
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
			LOGGER.info("Order created\n\n");
			return order;
	}
		
		

	@Override
	public Order update() {
		Order order;
		Long id = null;
		Long itemID;
		boolean flag = true;
		while(flag) {
			LOGGER.info("Would you like to:\n(1) Change Order Owner \n(2) Add item to order \n(3) Return to CRUD Menu");
			String choice = utils.getString();
			switch(choice) {
			case "1":
				LOGGER.info("Please enter the ID of the order you would like to update");
				id = utils.getLong();
				LOGGER.info("Please enter the new Customer ID");
				Long customerID = utils.getLong();
				orderDAO.update(new Order(id, customerID));
				LOGGER.info("Item Updated");
				break;
			case "2":
				itemDAO.readAll();
				LOGGER.info("Please enter the Order ID");
				id = utils.getLong();
				LOGGER.info("Please enter the Item ID");
				itemID = utils.getLong();
				orderDAO.createLine(id, itemID);
				break;
			case "3":
				LOGGER.info("Returning to CRUD \n\n");
				flag = false;
				order = orderDAO.readOrder(id);
				return order;
			}
		}
		return null;
	}

	@Override
	public int delete() {
		Integer removeItem = null;
		Integer deleteOrder = null;
		boolean flag = true;
		while (flag) {
			LOGGER.info("Would you like to:\n(1) Remove an item from an order \n(2) Delete an Entire Order \n(3) Return to CRUD Menu");
			String choice = utils.getString();
			switch(choice) {
			case "1":
				LOGGER.info("Please enter the ID of the order you would Change");
				Long id = utils.getLong();
				LOGGER.info(orderDAO.readLines(id));
				LOGGER.info("Please enter the id of the item you would like to delete");
				Long itemID = utils.getLong();
				removeItem = orderDAO.removeItem(id, itemID);
				break;
			case "2":
				LOGGER.info("Please enter the ID of the order you would like to delete");
				Long orderID = utils.getLong();
				deleteOrder = orderDAO.delete(orderID);
				break;
			case "3":
				LOGGER.info("Returning to CRUD \n\n");
				flag = false;
				if(removeItem != null && deleteOrder == null) {
					return removeItem;
				} else if (deleteOrder != null && removeItem == null) {
					return deleteOrder;
				} else {
					return removeItem + deleteOrder;
				}			
			}
		}
		return 0;
	}

}
