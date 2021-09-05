package com.pratap.service;

import java.util.List;
import java.util.stream.Collectors;

import com.pratap.domain.checkout.Cart;
import com.pratap.domain.checkout.CartItem;
import com.pratap.domain.checkout.CheckoutResponse;
import com.pratap.domain.checkout.CheckoutStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.pratap.util.CommonUtil.startTimer;
import static com.pratap.util.CommonUtil.timeTaken;

@Slf4j
@AllArgsConstructor
public class CheckoutService {

	private PriceValidatorService priceValidatorService;

	/**
	 * validate each cart item wrt price validator service
	 * 
	 * @param cart
	 * @return CheckoutResponse
	 */
	public CheckoutResponse checkout(Cart cart) {

		log.info("Executing checkout() with cart={}", cart);
		startTimer();
		List<CartItem> priceValidationList = cart.getCartItemList().parallelStream().map(cartItem -> {
			boolean isCartItemInvalid = priceValidatorService.isCartItemInvalid(cartItem);
			cartItem.setExpired(isCartItemInvalid);
			return cartItem;
		}).filter(CartItem::isExpired).collect(Collectors.toList());

		if (priceValidationList.size() > 0) {
			return new CheckoutResponse(CheckoutStatus.FAILURE, priceValidationList);
		}

		Double finalPrice = calculateFinalPriceWithReduce(cart);
		log.info("checkout completed & finalPrice={}", finalPrice);
		timeTaken();
		return new CheckoutResponse(CheckoutStatus.SUCCESS, finalPrice);
	}

	private Double calculateFinalPrice(Cart cart) {

		log.info("Executing calculateFinalPrice() with cart={}", cart);
		return cart.getCartItemList()
				.parallelStream()
				.map(cartItem -> cartItem.getQuantity() * cartItem.getRate())
//				.collect(summingDouble(Double::doubleValue));
				.mapToDouble(Double::doubleValue)
				.sum();
	}
	
	private Double calculateFinalPriceWithReduce(Cart cart) {

		log.info("Executing calculateFinalPriceWithReduce() with cart={}", cart);
		return cart.getCartItemList()
				.parallelStream()
				.map(cartItem -> cartItem.getQuantity() * cartItem.getRate())
				.reduce(0.0, Double::sum);
	}
}
