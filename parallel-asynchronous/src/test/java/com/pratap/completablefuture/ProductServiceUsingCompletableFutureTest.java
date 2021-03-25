package com.pratap.completablefuture;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;

import com.pratap.domain.Product;
import com.pratap.service.ProductInfoService;
import com.pratap.service.ReviewService;

class ProductServiceUsingCompletableFutureTest {

	private ProductInfoService productInfoService = new ProductInfoService();

	private ReviewService reviewService = new ReviewService();

	ProductServiceUsingCompletableFuture psucf = new ProductServiceUsingCompletableFuture(productInfoService,
			reviewService);

	@Test
	void testRetrieveProductDetails() {
		String productId = "ABC123";

		Product retrieveProductDetails = psucf.retrieveProductDetails(productId);

		assertAll(() -> assertNotNull(retrieveProductDetails),
				() -> assertTrue(retrieveProductDetails.getProductInfo().getProductOptions().size() > 0),
				() -> assertNotNull(retrieveProductDetails.getReview()));
	}

	@Test
	void testRetrieveProductDetailsWithStandardApproach() {

		String productId = "ABC123";
		CompletableFuture<Product> completableFuture = psucf.retrieveProductDetailsWithStandardApproach(productId);
		completableFuture.thenAccept(result -> {
			assertAll(() -> assertNotNull(result),
					() -> assertTrue(result.getProductInfo().getProductOptions().size() > 0),
					() -> assertNotNull(result.getReview()));
		});
	}

}
