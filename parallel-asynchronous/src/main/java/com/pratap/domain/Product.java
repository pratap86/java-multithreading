package com.pratap.domain;

import lombok.NonNull;

public class Product {

    @NonNull
    private String productId;
    @NonNull
    private ProductInfo productInfo;
    @NonNull
    private Review review;
    
    public Product() {
    	
    }

	public Product(@NonNull String productId, @NonNull ProductInfo productInfo, @NonNull Review review) {
		this.productId = productId;
		this.productInfo = productInfo;
		this.review = review;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return String.format("Product [productId=%s, productInfo=%s, review=%s]", productId, productInfo, review);
	}
    
}
