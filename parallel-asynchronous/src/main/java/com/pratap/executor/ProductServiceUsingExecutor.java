package com.pratap.executor;

import static com.pratap.util.CommonUtil.stopWatch;
import static com.pratap.util.LoggerUtil.log;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
public class ProductServiceUsingExecutor {
	
	private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	
    private ProductInfoService productInfoService;
    private ReviewService reviewService;

    public ProductServiceUsingExecutor(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }

    public Product retrieveProductDetails(String productId) throws InterruptedException, ExecutionException {
        stopWatch.start();

        Future<ProductInfo> productInfoFuture = executorService.submit(() -> productInfoService.retrieveProductInfo(productId));
        Future<Review> reviewFuture = executorService.submit(() -> reviewService.retrieveReviews(productId));

        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        return new Product(productId, productInfoFuture.get(), reviewFuture.get());
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductServiceUsingExecutor productService = new ProductServiceUsingExecutor(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is " + product);
        executorService.shutdown();

    }
}
