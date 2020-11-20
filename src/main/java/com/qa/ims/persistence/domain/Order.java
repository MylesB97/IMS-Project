package com.qa.ims.persistence.domain;

public class Order {

	//Attributes
	private long id;
	private long customerID;
	private long price;
	
	//Constructors
	public Order(long customerID) {
		this.customerID = customerID;
	}
	
	
	public Order(long id, long customerID) {
		super();
		this.id = id;
		this.customerID = customerID;
	}


	public Order(long id, long customerID, long price) {
		super();
		this.id = id;
		this.customerID = customerID;
		this.price = price;
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

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
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
		if (price != other.price)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order ID:" + id + "| Customer ID: " + customerID + "| Price: " + price + "|";
	}
	
	
	
}
