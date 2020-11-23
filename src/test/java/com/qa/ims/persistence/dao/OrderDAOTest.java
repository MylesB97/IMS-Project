package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {
	private final OrderDAO DAO = new OrderDAO();

	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "root", "testims");
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
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1l));
		expected.add(new Order(2l));
		expected.add(new Order(3l));
		expected.add(new Order(4l));
		expected.add(new Order(5l));
		expected.add(new Order(6l));
		assertEquals(expected, DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		Order expected = new Order(1l, 1l);
		assertEquals(expected, DAO.readLatest());
	}
	
	@Test
	public void testUpdate() {
		Order expected = new Order(1l, 6l);
		assertEquals(expected, DAO.update(expected));
	}
	
	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
}