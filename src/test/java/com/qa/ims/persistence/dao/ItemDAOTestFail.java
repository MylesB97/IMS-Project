package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTestFail {
	private final ItemDAO itemDAO = new ItemDAO();
	
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
	Item created = new Item("Test Item", 29l);
	Item expected = new Item(4L, "Test Item", 29l);
	
	assertEquals(expected, itemDAO.create(created));
	}
	
	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1l,"Spider-Man: Miles Morales", 40l));
		expected.add(new Item(2l, "Call of Duty: Black Ops", 50l));
		expected.add(new Item(3l, "PlayStation 5", 459l));
		
		assertEquals(expected, itemDAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		Item expected = new Item(3l, "PlayStation 5", 459l);
		
		assertEquals(expected, itemDAO.readLatest());
	}
	
	@Test
	public void testUpdate() {
		Item expected = new Item(1l, "Test", 20l);
		
		assertEquals(expected, itemDAO.update(expected));
	}
	
	@Test
	public void testDelete() {
		assertEquals(1, itemDAO.delete(1));
	}
}
