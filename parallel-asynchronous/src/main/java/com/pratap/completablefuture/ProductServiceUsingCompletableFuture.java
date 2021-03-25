package com.pratap.completablefuture;

import static com.pratap.util.CommonUtil.stopWatch;
import static com.pratap.util.LoggerUtil.log;

import java.util.concurrent.CompletableFuture;

import com.pratap.domain.Product;
import com.pratap.domain.ProductInfo;
import com.pratap.domain.Review;
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

    public ProductServiceUsingCompletableFuture(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
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

}
