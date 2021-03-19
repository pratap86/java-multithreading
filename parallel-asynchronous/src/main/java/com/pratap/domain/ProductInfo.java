package com.pratap.domain;

import java.util.List;


public class ProductInfo {
	
    private String productId;
    private List<ProductOption> productOptions;
    
    public ProductInfo() {}

	public ProductInfo(String productId, List<ProductOption> productOptions) {
		this.productId = productId;
		this.productOptions = productOptions;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public List<ProductOption> getProductOptions() {
		return productOptions;
	}

	public void setProductOptions(List<ProductOption> productOptions) {
		this.productOptions = productOptions;
	}

	@Override
	public String toString() {
		return String.format("ProductInfo [productId=%s, productOptions=%s]", productId, productOptions);
	}
    
}
