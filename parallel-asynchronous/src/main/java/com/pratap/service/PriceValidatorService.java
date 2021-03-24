package com.pratap.service;

import com.pratap.domain.checkout.CartItem;
import static com.pratap.util.CommonUtil.delay;

public class PriceValidatorService {

	public boolean isCartItemInvalid(CartItem cartItem){
        int cartId = cartItem.getItemId();
        delay(500);
        if (cartId == 7 || cartId == 9 || cartId == 11) {
            return true;
        }
        return false;
    }
}
