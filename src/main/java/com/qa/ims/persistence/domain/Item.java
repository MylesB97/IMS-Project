package com.qa.ims.persistence.domain;

public class Item {
	//Attributes
	
	private long id;
	private String name;
	private long quantity;
	private long price;
	
	//Constructors
	
	
	public Item(String name, long price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Item(long id, long quantity) {
		super();
		this.id = id;
		this.quantity = quantity;
	}

	public Item(long id, String name, long price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public Item(long id, String name, long quantity, long price) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	//Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(Long itemID) {
		this.id = itemID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Item - ID: " + id + "| Name: " + name + "| Price: £" + price + "|";
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price != other.price)
			return false;
		return true;
	}
		
	
}
