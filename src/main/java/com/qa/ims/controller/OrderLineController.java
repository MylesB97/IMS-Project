package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderLineDAO;
import com.qa.ims.persistence.domain.OrderLine;
import com.qa.ims.utils.Utils;

public class OrderLineController implements CrudController<OrderLine>{

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderLineDAO olDAO;
	private Utils utils;
	
	
	
	public OrderLineController(OrderLineDAO olDAO, Utils utils) {
		super();
		this.olDAO = olDAO;
		this.utils = utils;
	}
		
		
	@Override
	public List<OrderLine> readAll() {
		List<OrderLine> orderLines = olDAO.readAll();
		for(OrderLine orderLine : orderLines) {
			LOGGER.info(orderLine.toString());
		}
		return orderLines;
	}

	@Override
	public OrderLine create() {
		LOGGER.info("Please enter Order ID");
		Long orderID = utils.getLong();
		LOGGER.info("Please enter Item ID");
		Long itemID = utils.getLong();
		LOGGER.info("How many would you like to order?");
		Long quantity = utils.getLong();
		OrderLine orderLine = olDAO.create(new OrderLine(orderID, itemID, quantity));
		LOGGER.info("Item Added");
		return orderLine;
	}

	@Override
	public OrderLine update() {
			LOGGER.info("Please enter Order ID");
			Long orderID = utils.getLong();
			LOGGER.info("Please enter Old Item ID");
			Integer oldItemID = Integer.parseInt(utils.getString());
			LOGGER.info("Please enter New Item ID");
			Long newItemID = utils.getLong();
			LOGGER.info("How many would you like to order?");
			Long quantity = utils.getLong();
			OrderLine orderLine = olDAO.update(new OrderLine(orderID, newItemID, quantity, oldItemID));
			return orderLine;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter Order ID");
		Long orderID = utils.getLong();
		
	}

}
