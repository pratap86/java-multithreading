package com.pratap.service;

import java.util.List;
import java.util.stream.Collectors;

import com.pratap.domain.checkout.Cart;
import com.pratap.domain.checkout.CartItem;
import com.pratap.domain.checkout.CheckoutResponse;
import com.pratap.domain.checkout.CheckoutStatus;

import static com.pratap.util.CommonUtil.startTimer;
import static com.pratap.util.CommonUtil.timeTaken;

public class CheckoutService {

	private PriceValidatorService priceValidatorService;

	public CheckoutService(PriceValidatorService priceValidatorService) {
		this.priceValidatorService = priceValidatorService;
	}

	/**
	 * validate each cart item wrt price validator service
	 * 
	 * @param cart
	 * @return CheckoutResponse
	 */
	public CheckoutResponse checkout(Cart cart) {

		startTimer();
		List<CartItem> priceValidationList = cart.getCartItemList().parallelStream().map(cartItem -> {
			boolean isCartItemInvalid = priceValidatorService.isCartItemInvalid(cartItem);
			cartItem.setExpired(isCartItemInvalid);
			return cartItem;
		}).filter(CartItem::isExpired).collect(Collectors.toList());

		if (priceValidationList.size() > 0) {
			return new CheckoutResponse(CheckoutStatus.FAILURE, priceValidationList);
		}

		timeTaken();
		return new CheckoutResponse(CheckoutStatus.SUCCESS);
	}
}
