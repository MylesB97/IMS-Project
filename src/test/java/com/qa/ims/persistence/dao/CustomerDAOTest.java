package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DBUtils;

public class CustomerDAOTest {

	private final CustomerDAO DAO = new CustomerDAO();

	@BeforeClass
	public static void init() {
<<<<<<< HEAD
		DBUtils.connect("root", "pass");
=======
		DBUtils.connect("root", "root", "testims");
>>>>>>> 81d2584424cbeb328bae08b88e51a30db0313198
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
<<<<<<< HEAD
		final Customer created = new Customer(2L, "chris", "perrins");
=======
		final Customer created = new Customer(7L, "chris", "perrins");
>>>>>>> 81d2584424cbeb328bae08b88e51a30db0313198
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Customer> expected = new ArrayList<>();
		expected.add(new Customer(1L, "jordan", "harrison"));
<<<<<<< HEAD
=======
		expected.add(new Customer(2L, "john", "doe"));
		expected.add(new Customer(3L, "jane", "doe"));
		expected.add(new Customer(4L, "peter", "parker"));
		expected.add(new Customer(5L, "mary", "jane"));
		expected.add(new Customer(6L, "miles", "morales"));
>>>>>>> 81d2584424cbeb328bae08b88e51a30db0313198
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
<<<<<<< HEAD
		assertEquals(new Customer(1L, "jordan", "harrison"), DAO.readLatest());
=======
		assertEquals(new Customer(6L, "miles", "morales"), DAO.readLatest());
>>>>>>> 81d2584424cbeb328bae08b88e51a30db0313198
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Customer(ID, "jordan", "harrison"), DAO.readCustomer(ID));
	}

	@Test
	public void testUpdate() {
<<<<<<< HEAD
		final Customer updated = new Customer(1L, "chris", "perrins");
=======
		final Customer updated = new Customer(2L, "chris", "perrins");
>>>>>>> 81d2584424cbeb328bae08b88e51a30db0313198
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
<<<<<<< HEAD
		assertEquals(1, DAO.delete(1));
=======
		assertEquals(1, DAO.delete(6));
>>>>>>> 81d2584424cbeb328bae08b88e51a30db0313198
	}
}
