package com.pratap.service;

import com.pratap.domain.Review;
import lombok.extern.slf4j.Slf4j;

import static com.pratap.util.CommonUtil.delay;

@Slf4j
public class ReviewService {

    public Review retrieveReviews(String productId) {

        log.info("Executing retrieveReviews() with productId={}", productId);
        delay(1000);
        return new Review(200, 4.5);
    }
}
