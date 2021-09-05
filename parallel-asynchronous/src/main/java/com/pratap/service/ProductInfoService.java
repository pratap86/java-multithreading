package com.pratap.service;

import java.util.List;

import com.pratap.domain.Inventory;
import com.pratap.domain.ProductInfo;
import com.pratap.domain.ProductOption;
import lombok.extern.slf4j.Slf4j;

import static com.pratap.util.CommonUtil.delay;
@Slf4j
public class ProductInfoService {

    public ProductInfo retrieveProductInfo(String productId) {

        log.info("Executing retrieveProductInfo() with productId={}", productId);
        delay(1000);
        List<ProductOption> productOptions = List.of(new ProductOption(1, "64GB", "Black", 699.99, new Inventory(11)),
        		new ProductOption(2, "64GB", "Black", 799.99, new Inventory(12)),
        		new ProductOption(3, "64GB", "Black", 645.99, new Inventory(8)),
                new ProductOption(4, "128GB", "Black", 749.99, new Inventory(14)));
        return new ProductInfo(productId, productOptions);
    }
}
