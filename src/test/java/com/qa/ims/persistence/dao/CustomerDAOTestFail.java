package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DBUtils;

public class CustomerDAOTestFail {
	private final CustomerDAO DAO = new CustomerDAO();
	
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
		final Customer created = new Customer(7L, "chris", "perrins");
		assertEquals(null, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Customer> expected = new ArrayList<>();
		expected.add(new Customer(1L, "jordan", "harrison"));
		expected.add(new Customer(2L, "john", "doe"));
		expected.add(new Customer(3L, "jane", "doe"));
		expected.add(new Customer(4L, "peter", "parker"));
		expected.add(new Customer(5L, "mary", "jane"));
		expected.add(new Customer(6L, "miles", "morales"));
		assertEquals(new ArrayList<>(), DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(null, DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(null, DAO.readCustomer(ID));
	}

	@Test
	public void testUpdate() {
		final Customer updated = new Customer(2L, "chris", "perrins");
		assertEquals(null, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(6));
	}
}

