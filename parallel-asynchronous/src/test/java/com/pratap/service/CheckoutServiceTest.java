package com.pratap.service;
import static com.pratap.util.LoggerUtil.log;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.pratap.domain.checkout.CheckoutResponse;
import com.pratap.domain.checkout.CheckoutStatus;
import com.pratap.util.DataSet;
import static com.pratap.util.CommonUtil.stopWatchReset;

class CheckoutServiceTest {
	
	private PriceValidatorService priceValidatorService = new PriceValidatorService();
	private CheckoutService checkoutService = new CheckoutService(priceValidatorService);

	@Test
	void testNumberOfCores() {
		log("Number Of cores : "+Runtime.getRuntime().availableProcessors());
	}
	
//	@Test
	void testCheckoutWith4Items() {

		CheckoutResponse checkoutResponse = checkoutService.checkout(DataSet.createCart(4));
		assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
	}
	
	@Test
	void testCheckoutWith7Items() {

		stopWatchReset();
		CheckoutResponse checkoutResponse = checkoutService.checkout(DataSet.createCart(7));
		assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
	}

}
