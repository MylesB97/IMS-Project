package com.qa.ims.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAO implements Dao<Item>{
	
	//Attributes
	public static final Logger LOGGER = LogManager.getLogger();
	private static DBUtils DBInstance;
	
	//Methods
	
	public Item readLatest() {
		String query = "SELECT * FROM item ORDER BY id DESC LIMIT 1;";
		ResultSet rs = DBInstance.executeQuery(query);
		try {	
			rs.next();
			return modelFromResultSet(rs);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 *  Reads all the items in the database
	 *  
	 *  @return ArrayList - list the item id, name and price 
	 */

	@Override
	public List<Item> readAll() throws SQLException {
		String query = "SELECT * FROM item;";
			ResultSet rs = DBInstance.executeQuery(query);
			List<Item> items = new ArrayList<>();
			while(rs.next()) {
				items.add(modelFromResultSet(rs));
			}
			return items;
	}
	
	/**
	 *  Creates an item in the database
	 * 
	 * @param item - takes in an item object, the name and price attributes will be used
	 * 					to create the item in the database
	 * 
	 * @return
	 */
	@Override
	public Item create(Item item) {
		String query = String.format("INSERT INTO item(name, price) VALUES('%s', '%d');", item.getName(), item.getPrice());
			DBInstance.executeUpdate(query);
			return readLatest();
	}
	
	public Item readItem(Long id) throws SQLException {
		String query = String.format("SELECT * from item WHERE item_id= '%d%;", id);
		ResultSet rs = DBInstance.executeQuery(query);
			rs.next();
			return modelFromResultSet(rs);
	}
	
	/**
	 * Updates a customer in the database
	 * 
	 * @param item - takes in a item object, the id field will be used to
	 *                 update that item in the database
	 * @return
	 */
	@Override
	public Item update(Item item) {
		String query = String.format("UPDATE ITEM SET name='%s', price='%d' where item_id='%d;", item.getName(), item.getPrice(), item.getId());
			DBInstance.executeUpdate(query);
			try {
				return readItem(item.getId());
			} catch (SQLException e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
			return null;
	}
	
	/**
	 * Deletes a item in the database
	 * 
	 * @param id - id of the item
	 */
	@Override
	public int delete(long id) {
		String query = String.format("DELETE FROM ITEM WHERE item_id = '%d';", id);
		DBInstance.executeUpdate(query);
		return 0;
	}

	@Override
	public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("item_id");
		String name = resultSet.getString("name");
		Long price = resultSet.getLong("price");
		return new Item(id, name, price);
	}

	

}
