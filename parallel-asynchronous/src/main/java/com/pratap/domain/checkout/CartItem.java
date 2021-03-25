package com.pratap.domain.checkout;

public class CartItem {

	private Integer itemId;
	private String itemName;
	private double rate;
	private Integer quantity;
	private boolean isExpired;

	public CartItem() {}
	
	public CartItem(Integer itemId, String itemName, double rate, Integer quantity, boolean isExpired) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.rate = rate;
		this.quantity = quantity;
		this.isExpired = isExpired;
	}



	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

}
