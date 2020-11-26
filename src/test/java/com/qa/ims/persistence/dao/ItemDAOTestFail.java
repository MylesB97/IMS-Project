package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

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
	
	assertEquals(null, itemDAO.create(created));
	}
	
	@Test
	public void testReadAll() {
		assertEquals(new ArrayList<>(), itemDAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(null, itemDAO.readLatest());
	}
	
	@Test
	public void testUpdate() {
		Item expected = new Item(1l, "Test", 20l);
		
		assertEquals(null, itemDAO.update(expected));
	}
	
	@Test
	public void testDelete() {
		assertEquals(0, itemDAO.delete(1));
	}
}
