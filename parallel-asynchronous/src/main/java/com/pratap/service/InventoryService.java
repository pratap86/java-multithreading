package com.pratap.service;

import com.pratap.domain.Inventory;
import com.pratap.domain.ProductOption;
import lombok.extern.slf4j.Slf4j;

import static com.pratap.util.CommonUtil.delay;

@Slf4j
public class InventoryService {

	public Inventory retrieveInventory(ProductOption productOption) {

		log.info("Executing retrieveInventory() with productOption={}", productOption);
		delay(500);
		return new Inventory(2);
	}
}
