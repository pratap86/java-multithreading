package com.pratap.service;

import com.pratap.domain.Product;
import com.pratap.domain.ProductInfo;
import com.pratap.domain.Review;
import lombok.extern.slf4j.Slf4j;

import static com.pratap.util.CommonUtil.stopWatch;
import static com.pratap.util.LoggerUtil.log;
/**
 * 
 * @author Pratap Narayan
 * <p></>Client invokes the ProductService(return Product) by passing the productId & ProductService going to intract
 * two different services ie ProductInfoService & ReviewService</p>
 *
 */
@Slf4j
public class ProductService {
	
    private ProductInfoService productInfoService;
    private ReviewService reviewService;

    public ProductService(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }

    public Product retrieveProductDetails(String productId) {
        log.info("Executing retrieveProductDetails() with productId={}", productId);
        stopWatch.start();
        ProductInfo productInfo = productInfoService.retrieveProductInfo(productId); // blocking call
        Review review = reviewService.retrieveReviews(productId); // blocking call

        stopWatch.stop();
        log.info("Total Time Taken : {} and Thread={}", stopWatch.getTime()+" ms", Thread.currentThread().getName());
        return new Product(productId, productInfo, review);
    }

    public static void main(String[] args) {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductService productService = new ProductService(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is " + product);

    }
}
