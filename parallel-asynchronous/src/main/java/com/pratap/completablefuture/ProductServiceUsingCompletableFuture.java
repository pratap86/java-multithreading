package com.pratap.completablefuture;

import static com.pratap.util.CommonUtil.stopWatch;
import static com.pratap.util.LoggerUtil.log;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.pratap.domain.Inventory;
import com.pratap.domain.Product;
import com.pratap.domain.ProductInfo;
import com.pratap.domain.ProductOption;
import com.pratap.domain.Review;
import com.pratap.service.InventoryService;
import com.pratap.service.ProductInfoService;
import com.pratap.service.ReviewService;
/**
 * 
 * @author Pratap Narayan
 *
 */
public class ProductServiceUsingCompletableFuture {
	
    private ProductInfoService productInfoService;
    private ReviewService reviewService;
    private InventoryService inventoryService;

    public ProductServiceUsingCompletableFuture(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }
    
    public ProductServiceUsingCompletableFuture(ProductInfoService productInfoService, ReviewService reviewService,
			InventoryService inventoryService) {
		this.productInfoService = productInfoService;
		this.reviewService = reviewService;
		this.inventoryService = inventoryService;
	}



	public Product retrieveProductDetails(String productId) {
        stopWatch.start();

        CompletableFuture<ProductInfo> cfProductInfo = CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId));
        CompletableFuture<Review> cfReview = CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));
        
        Product product = cfProductInfo.thenCombine(cfReview, (productInfo, review) -> new Product(productId, productInfo, review)).join();

        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        return product;
    }
    
    public CompletableFuture<Product> retrieveProductDetailsWithStandardApproach(String productId) {
        CompletableFuture<ProductInfo> cfProductInfo = CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId));
        CompletableFuture<Review> cfReview = CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));
        
        return cfProductInfo
        		.thenCombine(cfReview, (productInfo, review) -> new Product(productId, productInfo, review));
    }
    
    public Product retrieveProductDetailsWithInventoryService(String productId) {
        stopWatch.start();

        CompletableFuture<ProductInfo> cfProductInfo = CompletableFuture
        		.supplyAsync(() -> productInfoService.retrieveProductInfo(productId))
        		.thenApply(productInfo -> {
        			productInfo.setProductOptions(updateInventory(productInfo));
        			return productInfo;
        		});
        CompletableFuture<Review> cfReview = CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));
        Product product = cfProductInfo.thenCombine(cfReview, (productInfo, review) -> new Product(productId, productInfo, review)).join();
        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        return product;
    }

	private List<ProductOption> updateInventory(ProductInfo productInfo) {
		return productInfo.getProductOptions().stream().map(productOption -> {
			Inventory inventory = inventoryService.retrieveInventory(productOption);
			productOption.setInventory(inventory);
			return productOption;
		}).collect(Collectors.toList());
	}
	
	public Product retrieveProductDetailsWithInventoryServiceWithCompletableFuture(String productId) {
        stopWatch.start();

        CompletableFuture<ProductInfo> cfProductInfo = CompletableFuture
        		.supplyAsync(() -> productInfoService.retrieveProductInfo(productId))
        		.thenApply(productInfo -> {
        			productInfo.setProductOptions(updateInventoryWithCompletableFuture(productInfo));
        			return productInfo;
        		});
        CompletableFuture<Review> cfReview = CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));
        Product product = cfProductInfo.thenCombine(cfReview, (productInfo, review) -> new Product(productId, productInfo, review)).join();
        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        return product;
    }
	
	private List<ProductOption> updateInventoryWithCompletableFuture(ProductInfo productInfo) {
		
		List<CompletableFuture<ProductOption>> productOptionList = productInfo.getProductOptions().stream().map(productOption -> {
			
			return CompletableFuture.supplyAsync(() -> inventoryService.retrieveInventory(productOption))
					.thenApply(inventory -> {
						productOption.setInventory(inventory);
						return productOption;
					});
		}).collect(Collectors.toList());
		
		return productOptionList.stream().map(CompletableFuture::join).collect(Collectors.toList());
	}

}
