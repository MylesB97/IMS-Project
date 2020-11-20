package com.qa.ims.persistence.domain;

public class OrderLine {
	//Attributes
	private long ID;
	private long orderID;
	private long itemID;
	private long quantity;
	private int oldItemID;
	
	//Constructor
	public OrderLine(long iD, long orderID, long itemID, long quantity) {
		super();
		ID = iD;
		this.orderID = orderID;
		this.itemID = itemID;
		this.quantity = quantity;
	}

	public OrderLine(long orderID, long itemID, long quantity) {
		super();
		this.orderID = orderID;
		this.itemID = itemID;
		this.quantity = quantity;
	}



	public OrderLine(long orderID, long itemID, long quantity, int oldItemID){
		super();
		this.orderID = orderID;
		this.itemID = itemID;
		this.quantity = quantity;
		this.oldItemID = oldItemID;
	}

	//Getters and Setters
	public long getID() {
		return ID;
	}


	public void setID(long iD) {
		ID = iD;
	}


	public long getItemID() {
		return itemID;
	}


	public void setItemID(long itemID) {
		this.itemID = itemID;
	}


	public long getOrderID() {
		return orderID;
	}


	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}


	public long getQuantity() {
		return quantity;
	}


	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	

	public int getOldItemID() {
		return oldItemID;
	}

	public void setOldItemID(int oldItemID) {
		this.oldItemID = oldItemID;
	}

	//Checking equality
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderLine other = (OrderLine) obj;
		if (ID != other.ID)
			return false;
		if (itemID != other.itemID)
			return false;
		if (orderID != other.orderID)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "OrderLine ID=" + ID + "| orderID=" + orderID + "| itemID=" + itemID + "| quantity=" + quantity + "|";
	}
	
	
	
	
}
