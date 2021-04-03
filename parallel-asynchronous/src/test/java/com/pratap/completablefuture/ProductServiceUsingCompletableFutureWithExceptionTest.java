package com.pratap.completablefuture;

import static com.pratap.util.CommonUtil.stopWatchReset;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pratap.domain.Product;
import com.pratap.service.InventoryService;
import com.pratap.service.ProductInfoService;
import com.pratap.service.ReviewService;

@ExtendWith(MockitoExtension.class)
class ProductServiceUsingCompletableFutureWithExceptionTest {

	@Mock
	private ProductInfoService productInfoService;
	
	@Mock
    private ReviewService reviewService;
	
	@Mock
    private InventoryService inventoryService;
    
	@InjectMocks
	private ProductServiceUsingCompletableFuture productService;
    
	@Test
	void testRetrieveProductDetailsWithInventoryServiceWithCompletableFuture_When_ReviewService_Throw_Exception() {
		
		stopWatchReset();
		String productId = "ABCD123";
		
		when(productInfoService.retrieveProductInfo(any())).thenCallRealMethod();
		when(reviewService.retrieveReviews(any())).thenThrow(new RuntimeException("exception occurred"));
		when(inventoryService.retrieveInventory(any())).thenCallRealMethod();
		
		Product product = productService.retrieveProductDetailsWithInventoryServiceWithCompletableFuture(productId);
		
		assertNotNull(product);
		assertTrue(product.getProductInfo().getProductOptions().size() > 0);
		product.getProductInfo().getProductOptions().forEach(productOption -> {
			assertNotNull(productOption.getInventory());
		});
		assertNotNull(product.getReview());
		assertEquals(0, product.getReview().getNoOfReviews());
		assertEquals(0.0, product.getReview().getOverallRating());
		
	}
	
	@Test
	void testRetrieveProductDetailsWithInventoryServiceWithCompletableFuture_When_ProductInfoService_Throw_Exception() {
		
		stopWatchReset();
		
		String productId = "ABCD123";
		
		when(productInfoService.retrieveProductInfo(any())).thenThrow(new RuntimeException("exception occurred"));
		Assertions.assertThrows(RuntimeException.class, () -> productService.retrieveProductDetailsWithInventoryServiceWithCompletableFuture(productId));
	}
	
	@Test
	void testRetrieveProductDetailsWithInventoryServiceWithCompletableFuture_When_InventoryService_Throw_Exception() {
		
		stopWatchReset();
		
		String productId = "ABCD123";
		
		when(productInfoService.retrieveProductInfo(any())).thenCallRealMethod();
		when(reviewService.retrieveReviews(any())).thenCallRealMethod();
		when(inventoryService.retrieveInventory(any())).thenThrow(new RuntimeException("exception occurred"));
		
		Product product = productService.retrieveProductDetailsWithInventoryServiceWithCompletableFuture(productId);
		
		assertNotNull(product);
		assertTrue(product.getProductInfo().getProductOptions().size() > 0);
		product.getProductInfo().getProductOptions().forEach(productOption -> {
			assertNotNull(productOption.getInventory());
			assertEquals(1, productOption.getInventory().getCount());
		});
		assertNotNull(product.getReview());
		assertEquals(200, product.getReview().getNoOfReviews());
		assertEquals(4.5, product.getReview().getOverallRating());
	}

}
