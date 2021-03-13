package com.pratap.service;

import com.pratap.domain.Review;
import static com.pratap.util.CommonUtil.delay;

public class ReviewService {

    public Review retrieveReviews(String productId) {
        delay(1000);
        return new Review(200, 4.5);
    }
}
