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

    public Product retrieveProductDetails(String productId) throws InterruptedException {
        stopWatch.start();

        ProductInfoRunnbale productInfoRunnable = new ProductInfoRunnbale(productId);
        Thread productThread = new Thread(productInfoRunnable);
        
        ReviewRunnbale reviewRunnable = new ReviewRunnbale(productId);
        Thread reviewThread = new Thread(reviewRunnable);
        
        productThread.start();
        reviewThread.start();
        
        productThread.join();
        reviewThread.join();

        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        return new Product(productId, productInfoRunnable.getProductInfo(), reviewRunnable.getReview());
    }

    public static void main(String[] args) throws InterruptedException {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductServiceUsingThread productService = new ProductServiceUsingThread(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is " + product);

    }
    
    private class ProductInfoRunnbale implements Runnable {

    	private ProductInfo productInfo;
    	private String productId;
    	
    	public ProductInfoRunnbale(String productId) {
    		this.productId = productId;
    	}
		@Override
		public void run() {

			productInfo = productInfoService.retrieveProductInfo(productId);
		}
		public ProductInfo getProductInfo() {
			return productInfo;
		}
	}
    
    private class ReviewRunnbale implements Runnable {

    	private String productId;
    	private Review review;
    	
		public ReviewRunnbale(String productId) {
			this.productId = productId;
		}

		@Override
		public void run() {
			review = reviewService.retrieveReviews(productId);
		}

		public Review getReview() {
			return review;
		}
	}
}
