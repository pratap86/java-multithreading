package com.pratap.domain;

public class ProductOption {
    private Integer productionOptionId;
    private String size;
    private String  color;
    private double  price;
    private Inventory inventory;

    public ProductOption() {
    	
    }
    public ProductOption(Integer productionOptionId, String size, String color, double price) {
        this.productionOptionId = productionOptionId;
        this.size = size;
        this.color = color;
        this.price = price;
    }
	public Integer getProductionOptionId() {
		return productionOptionId;
	}
	public void setProductionOptionId(Integer productionOptionId) {
		this.productionOptionId = productionOptionId;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	@Override
	public String toString() {
		return String.format("ProductOption [productionOptionId=%s, size=%s, color=%s, price=%s, inventory=%s]",
				productionOptionId, size, color, price, inventory);
	}
    
}
