package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;


/*
 * Takes in item details for CRUD functionality
 * 
 */
public class ItemController implements CrudController<Item> {
	
	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private Utils utils;
	
	
	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}
	
	/*
	 * Reads all items to the logger
	 */
	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		for(Item item : items) {
			LOGGER.info(item.toString());
		}
		return items;
	}
	
	
	/**
	 * Creates an item using user input
	 * 
	 * @return - returns new item 
	 */
	@Override
	public Item create() {
		LOGGER.info("Please enter item name");
		String itemName = utils.getString();
		LOGGER.info("Please enter item price");
		long itemPrice = utils.getLong();
		Item item = itemDAO.create(new Item(itemName, itemPrice));
		LOGGER.info("Item Created");
		return item;	
	}
	
	/**
	 * Updates an item using user input
	 * 
	 * @return - returns the updated item
	 */
	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter the item name");
		String itemName = utils.getString();
		LOGGER.info("Please enter item price");
		Long itemPrice = utils.getLong();
		Item item = itemDAO.update(new Item(id, itemName, itemPrice));
		LOGGER.info("Item Updated");
		return item;
	}
	
	
	/**
	 * Deletes an item using input from user
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = utils.getLong();
		return itemDAO.delete(id);
	}

}
