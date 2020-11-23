package com.qa.ims.persistence.domain;

import java.util.List;

public class Order {

	//Attributes
	private long id;
	private long customerID;
	private List<Item> items;
	
	
//	private long orderLinesID;
//	private long itemID;
//	private long quantity;
//	private int oldItemID;
//	private long price;
//	
	//Constructors
	public Order(long customerID) {
		this.customerID = customerID;
	}


	public Order(long id, long customerID) {
		super();
		this.id = id;
		this.customerID = customerID;
	}

	public Order(long id, long customerID, List<Item> items) {
		super();
		this.id = id;
		this.customerID = customerID;
		this.items = items;
	}


	//Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}



	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customerID != other.customerID)
			return false;
		if (id != other.id)
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}


	@Override
	public String toString() {
		String nl = "\n";
		double totalPrice = 0.0;
		for(Item item: items) {
			totalPrice += item.getPrice();
			nl += item.toString() + "\n";
		}
		
		return "Order ID:" + id + "| Customer ID: " + customerID + "| Items: " + nl + "| Total Pricce £" + totalPrice + " |";
	}
	
	
	
}
