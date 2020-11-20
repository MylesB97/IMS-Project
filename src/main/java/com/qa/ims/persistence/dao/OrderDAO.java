package com.qa.ims.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderLine;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {
	
	//Attributes
	public static final Logger LOGGER = LogManager.getLogger();
	private static DBUtils DBInstance;

	/*
	 * Read the latest order entry into the database
	 */
	public Order readLatest() {
		String query = "SELECT * FROM orders ORDER BY id DESC LIMIT 1;";
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
	
	@Override
	public List<Order> readAll() {
		String query = "SELECT * FROM orders;";
		ResultSet rs = DBInstance.executeQuery(query);
		List<Order> orders = new ArrayList<>();
		try {
			while(rs.next()) {
				orders.add(modelFromResultSet(rs));
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return orders;
	}
	
	public Order readOrder(Long id) throws SQLException {
		String query = String.format("SELECT * from order where id = '%d';", id);
		ResultSet rs = DBInstance.executeQuery(query);
		rs.next();
		return modelFromResultSet(rs);
	}
	
	/**
	 * Creates an order in the database
	 * 
	 * @param - takes in an order object. id will be ignored
	 */
	@Override
	public Order create(Order order) {
		String query = String.format("INSERT INTO orders(customer_id) VALUES('%d');", order.getCustomerID());
		DBInstance.executeUpdate(query);
		return readLatest();
	}
	
	@Override
	public Order update(Order order) {
		String query = String.format("UPDATE orders SET customer_id ='%d' where id='%d';", order.getCustomerID(), order.getId());
		DBInstance.executeUpdate(query);
		try {
			return readOrder(order.getId());
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Deletes order from order table and all occurrences of order ID from Order Line table
	 */
	@Override
	public int delete(long id) {
		String query = String.format("DELETE FROM order where id='%d';", id);
		DBInstance.executeUpdate(query);
		String queryTwo = String.format("DELETE FROM order_line WHERE order_id='%d';", id);
		DBInstance.executeQuery(queryTwo);
		return 0;
	}

	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long customerID = resultSet.getLong("cutomer_id");
		Long price = resultSet.getLong("price");
		return new Order(id, customerID , price);
	}

}
