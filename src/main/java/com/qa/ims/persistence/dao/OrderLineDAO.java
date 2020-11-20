package com.qa.ims.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.OrderLine;
import com.qa.ims.utils.DBUtils;

public class OrderLineDAO implements Dao<OrderLine> {
	
	//Attributes
	public static final Logger LOGGER = LogManager.getLogger();
	private static DBUtils DBInstance;
	
	public OrderLine readLatest() {
		String query = "SELECT * FROM order_line ORDER BY id DESC LIMIT 1;";
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
	public List<OrderLine> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public OrderLine readOrderLine(Long id) throws SQLException {
		String query = String.format("SELECT * from items WHERE id= '%d';", id);
		ResultSet rs = DBInstance.executeQuery(query);
		rs.next();
		return modelFromResultSet(rs);
	}
	
	public List<OrderLine> readOrderContents(long id) {
		String query = "SELECT * FROM order_line WHERE order_id= " + id + ";";
		ResultSet rs = DBInstance.executeQuery(query);
		List<OrderLine> orderLine = new ArrayList<>();
		try {
			while(rs.next()) {
				orderLine.add(modelFromResultSet(rs));
			}
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return orderLine;
	}
	@Override
	public OrderLine create(OrderLine orderLine){
		String query = String.format("INSERT INTO order_line(order_id, item_id) VALUES('%d', '%d');", orderLine.getOrderID(), orderLine.getItemID());
		DBInstance.executeUpdate(query);
		return readLatest();
	}

	@Override
	public OrderLine update(OrderLine orderLine) {
		String query = String.format("UPDATE order_line SET item_id = '%d', quantity = '%d' WHERE (order_id ='%d' and item_id = '%d');", orderLine.getItemID(), orderLine.getQuantity(), orderLine.getID(), orderLine.getOldItemID());
		DBInstance.executeUpdate(query);
		try {
			return readOrderLine(orderLine.getID());
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 *  deletes a single item from order
	 */
	@Override
	public int delete(long id) {
		String query = String.format("DELETE FROM order_line WHERE id='%d';", id);
		DBInstance.executeQuery(query);
		return 0;
	}
	
	/**
	 * Deletes every item in the order
	 * @param id - order_id to be deleted
	 * @return
	 */
	public int deleteOrder(long id) {
		String query = String.format("DELETE FROM order_line WHERE order_id='%d';", id);
		DBInstance.executeQuery(query);
		return 0;
	}

	@Override
	public OrderLine modelFromResultSet(ResultSet resultSet) throws SQLException {
		long id = resultSet.getLong("id");
		long itemID = resultSet.getLong("item_id");
		long orderID = resultSet.getLong("order_id");
		int quantity = resultSet.getInt("quantity");
		return new OrderLine(id, itemID, orderID, quantity);
	}

}
