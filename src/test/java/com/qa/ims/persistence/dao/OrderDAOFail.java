package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOFail {
	private final ItemDAO iDAO = new ItemDAO();
	private final OrderDAO DAO = new OrderDAO(iDAO);

	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "fail", "testims");
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final Order order = new Order(1L);
		final Order expected = new Order(2l, 1l);
		assertEquals(expected, DAO.create(order));
	}
	
	
	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(2l ,"Call of Duty: Black Ops", 50l));
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1l, 1l, items));
		expected.add(new Order(2l, 1l));
		
		DAO.create(new Order(2l, 1l));
		assertEquals(expected, DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(2l ,"Call of Duty: Black Ops", 50l));
		Order expected = new Order(1l, 1l, items);
		assertEquals(expected, DAO.readLatest());
	}
	
	@Test
	public void testUpdate() {
		Order update = new Order(1l, 3l);
		List<Item> items = new ArrayList<>();
		items.add(new Item(2l ,"Call of Duty: Black Ops", 50l));
		Order expected = new Order(1l, 3l, items);
		assertEquals(expected, DAO.update(update));
	}
	
	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
	
	@Test
	public void testRemoveItem() {
		assertEquals(0, DAO.removeItem(1, 1));
	}
	
	@Test
	public void testCreateLine() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(2l, "Call of Duty: Black Ops", 50l));
		items.add(new Item(3l, "PlayStation 5", 459l));	
		Order expected = new Order(1l, 1l, items);
		
		assertEquals(expected, DAO.createLine(expected.getId(), 3l));
	}
}
