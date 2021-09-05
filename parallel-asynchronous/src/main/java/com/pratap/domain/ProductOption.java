package com.pratap.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOption {

    private Integer productionOptionId;
    private String size;
    private String  color;
    private double  price;
    private Inventory inventory;
}
