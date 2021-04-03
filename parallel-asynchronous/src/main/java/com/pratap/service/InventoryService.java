package com.pratap.service;

import com.pratap.domain.Inventory;
import com.pratap.domain.ProductOption;
import static com.pratap.util.CommonUtil.delay;

public class InventoryService {

	public Inventory retrieveInventory(ProductOption productOption) {
		delay(500);
		return new Inventory(2);
	}
}
