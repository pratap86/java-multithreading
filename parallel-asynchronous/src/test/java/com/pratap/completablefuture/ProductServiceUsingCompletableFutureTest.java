package com.pratap.completablefuture;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;

import com.pratap.domain.Product;
import com.pratap.service.InventoryService;
import com.pratap.service.ProductInfoService;
import com.pratap.service.ReviewService;
import static com.pratap.util.CommonUtil.stopWatchReset;

class ProductServiceUsingCompletableFutureTest {

	private ProductInfoService productInfoService = new ProductInfoService();

	private ReviewService reviewService = new ReviewService();
	
	private InventoryService inventoryService = new InventoryService();

	ProductServiceUsingCompletableFuture productServiceUsingCompletableFuture = new ProductServiceUsingCompletableFuture(productInfoService,
			reviewService, inventoryService);

	@Test
	void testRetrieveProductDetails() {
		stopWatchReset();
		String productId = "ABC123";

		Product retrieveProductDetails = productServiceUsingCompletableFuture.retrieveProductDetails(productId);

		assertAll(() -> assertNotNull(retrieveProductDetails),
				() -> assertTrue(retrieveProductDetails.getProductInfo().getProductOptions().size() > 0),
				() -> assertNotNull(retrieveProductDetails.getReview()));
	}

	@Test
	void testRetrieveProductDetailsWithStandardApproach() {

		String productId = "ABC123";
		CompletableFuture<Product> completableFuture = productServiceUsingCompletableFuture.retrieveProductDetailsWithStandardApproach(productId);
		completableFuture.thenAccept(result -> {
			assertAll(() -> assertNotNull(result),
					() -> assertTrue(result.getProductInfo().getProductOptions().size() > 0),
					() -> assertNotNull(result.getReview()));
		});
	}
	
	@Test
	void testRetrieveProductDetailsWithInventoryService() {
		stopWatchReset();
		String productId = "ABC123";
		Product product = productServiceUsingCompletableFuture.retrieveProductDetailsWithInventoryService(productId);

		assertAll(() -> assertNotNull(product),
				() -> assertTrue(product.getProductInfo().getProductOptions().size() > 0),
				() -> product.getProductInfo().getProductOptions().forEach(productOption -> {
					assertNotNull(productOption.getInventory());
				}),
				() -> assertNotNull(product.getReview()));
	}
	
	@Test
	void retrieveProductDetailsWithInventoryServiceWithCompletableFuture() {
		stopWatchReset();
		String productId = "ABC123";
		Product product = productServiceUsingCompletableFuture.retrieveProductDetailsWithInventoryServiceWithCompletableFuture(productId);

		assertAll(() -> assertNotNull(product),
				() -> assertTrue(product.getProductInfo().getProductOptions().size() > 0),
				() -> product.getProductInfo().getProductOptions().forEach(productOption -> {
					assertNotNull(productOption.getInventory());
				}),
				() -> assertNotNull(product.getReview()));
	}

}
