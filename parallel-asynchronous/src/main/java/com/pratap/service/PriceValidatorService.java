package com.pratap.service;

import com.pratap.domain.checkout.CartItem;
import lombok.extern.slf4j.Slf4j;

import static com.pratap.util.CommonUtil.delay;

@Slf4j
public class PriceValidatorService {

	public boolean isCartItemInvalid(CartItem cartItem){

        log.info("Executing isCartItemInvalid() with cartItem={}", cartItem);
        int cartId = cartItem.getItemId();
        delay(500);
        return cartId == 7 || cartId == 9 || cartId == 11;
    }
}
