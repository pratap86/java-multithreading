package com.pratap.service;

import java.util.List;

import com.pratap.domain.ProductInfo;
import com.pratap.domain.ProductOption;
import static com.pratap.util.CommonUtil.delay;

public class ProductInfoService {

    public ProductInfo retrieveProductInfo(String productId) {
        delay(1000);
        List<ProductOption> productOptions = List.of(new ProductOption(1, "64GB", "Black", 699.99),
        		new ProductOption(2, "64GB", "Black", 699.99),
        		new ProductOption(3, "64GB", "Black", 699.99),
                new ProductOption(4, "128GB", "Black", 749.99));
        return new ProductInfo(productId, productOptions);
    }
}
