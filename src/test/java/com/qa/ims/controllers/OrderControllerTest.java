package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;
	
	@Mock
	private ItemDAO iDAO;

	@InjectMocks
	private OrderController controller;
	
	@Test
	public void testCreate() {
		final long ID = 1l;
		Order expected = new Order(1l);
		
		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.create(expected)).thenReturn(expected);
		
		assertEquals(expected, controller.create());
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(expected);
	}
	
	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<>();
		List<Item> items = new ArrayList<>();
		items.add(new Item(1l, "Added Item", 60l));
		orders.add(new Order(1l, 1l, items));
		
		Mockito.when(dao.readAll()).thenReturn(orders);
		
		assertEquals(orders, controller.readAll());
		
		Mockito.verify(dao, Mockito.times(1)).readAll();
		}
	
	/* 
	 * This method is testing the first choice where the user can change
	 * which customer is the owner of an order
	 */
	@Test
	public void testUpdateOne() {
		final String choiceOne = "1", choiceTwo = "2", choiceThree = "3";
		final Long id = 1l;
		
		List<Item> orderItems = new ArrayList<>();
		orderItems.add(new Item(1l, "Added Item", 60l));
		
		
		List<Item> listOfItems = new ArrayList<>();
		listOfItems.add(new Item(1l, "Added Item", 60l));
		listOfItems.add(new Item(2l, "Second Item", 65l));
		
		
		Order expected = new Order(1l, 1l);
		Order expectedTwo = new Order(1l, 1l, orderItems);
		
		Mockito.when(utils.getString()).thenReturn(choiceOne, choiceTwo, choiceThree);
		Mockito.when(utils.getLong()).thenReturn(expected.getId(), expected.getCustomerID(), id, id);
		Mockito.when(dao.update(expected)).thenReturn(expected);
		Mockito.when(iDAO.readAll()).thenReturn(listOfItems);
		Mockito.when(dao.createLine(id, id)).thenReturn(expectedTwo);
		Mockito.when(dao.readOrder(id)).thenReturn(expected);
		
		assertEquals(expected, controller.update());
		
		
		Mockito.verify(utils, Mockito.times(3)).getString();
		Mockito.verify(utils, Mockito.times(4)).getLong();
		Mockito.verify(dao, Mockito.times(1)).update(expected);
		Mockito.verify(iDAO, Mockito.times(1)).readAll();
		Mockito.verify(dao, Mockito.times(1)).createLine(id, id);
		Mockito.verify(dao, Mockito.times(1)).readOrder(id);
	}
	
	/*
	 * This is testing the Second Choice for the update method where 
	 * the user can add an item to an order
	 */
	@Test
	public void testUpdateTwo() {
		final String choiceOne = "1", choiceTwo = "2", choiceThree = "3";
		final Long id = 1l;
		
		List<Item> orderItems = new ArrayList<>();
		orderItems.add(new Item(1l, "Added Item", 60l));
		
		
		List<Item> listOfItems = new ArrayList<>();
		listOfItems.add(new Item(1l, "Added Item", 60l));
		listOfItems.add(new Item(2l, "Second Item", 65l));
		
		
		Order expected = new Order(1l, 1l);
		Order expectedTwo = new Order(1l, 1l, orderItems);
		
		Mockito.when(utils.getString()).thenReturn(choiceOne, choiceTwo, choiceThree);
		Mockito.when(utils.getLong()).thenReturn(expected.getId(), expected.getCustomerID(), id, id);
		Mockito.when(dao.update(expected)).thenReturn(expected);
		Mockito.when(iDAO.readAll()).thenReturn(listOfItems);
		Mockito.when(dao.createLine(id, id)).thenReturn(expectedTwo);
		Mockito.when(dao.readOrder(id)).thenReturn(expectedTwo);
		
		assertEquals(expectedTwo, controller.update());
		
		
		Mockito.verify(utils, Mockito.times(3)).getString();
		Mockito.verify(utils, Mockito.times(4)).getLong();
		Mockito.verify(dao, Mockito.times(1)).update(expected);
		Mockito.verify(iDAO, Mockito.times(1)).readAll();
		Mockito.verify(dao, Mockito.times(1)).createLine(id, id);
		Mockito.verify(dao, Mockito.times(1)).readOrder(id);
	}
	
	
	@Test
	public void testDeleteOne() {
		final String choiceOne = "1", choiceThree = "3";
		final Long id = 1l;
		
		Mockito.when(utils.getString()).thenReturn(choiceOne, choiceThree);
		Mockito.when(utils.getLong()).thenReturn(id, id);
		Mockito.when(dao.removeItem(id, id)).thenReturn(1);
		
		assertEquals(1, controller.delete());
		
		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(dao, Mockito.times(1)).removeItem(id, id);
	}
	
	@Test
	public void testDeleteTwo() {
		final String choiceTwo = "2", choiceThree = "3";
		final Long id = 1l;
		
		Mockito.when(utils.getString()).thenReturn(choiceTwo, choiceThree);
		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(dao.delete(id)).thenReturn(1);
		
		assertEquals(1, controller.delete());
		
		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(id);
	}
	
	
	/*
	 * This final test is testing the return if the user is to
	 * delete an item from an order and then remove an order entirely
	 * 
	 */
	@Test
	public void testDeleteThree() {
		final String  choiceOne = "1", choiceTwo = "2", choiceThree = "3";
		final Long id = 1l;
		
		
		Mockito.when(utils.getString()).thenReturn(choiceOne, choiceTwo, choiceThree);
		Mockito.when(utils.getLong()).thenReturn(id, id, id);
		Mockito.when(dao.removeItem(id, id)).thenReturn(1);
		Mockito.when(dao.delete(id)).thenReturn(1);
		
		assertEquals(2, controller.delete());
		
		Mockito.verify(utils, Mockito.times(3)).getString();
		Mockito.verify(utils, Mockito.times(3)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(id);
		Mockito.verify(dao, Mockito.times(1)).removeItem(id, id);
	}
	
}