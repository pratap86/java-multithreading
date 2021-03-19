package com.pratap.thread;

import static com.pratap.util.CommonUtil.stopWatch;
import static com.pratap.util.LoggerUtil.log;

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
public class ProductServiceUsingThread {
	
    private ProductInfoService productInfoService;
    private ReviewService reviewService;

    public ProductServiceUsingThread(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }

    public Product retrieveProductDetails(String productId) {
        stopWatch.start();

        ProductInfo productInfo = productInfoService.retrieveProductInfo(productId); // blocking call
        Review review = reviewService.retrieveReviews(productId); // blocking call

        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        return new Product(productId, productInfo, review);
    }

    public static void main(String[] args) {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductServiceUsingThread productService = new ProductServiceUsingThread(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is " + product);

    }
}
