package com.pratap.domain.checkout;

import java.util.ArrayList;
import java.util.List;

public class CheckoutResponse {

	CheckoutStatus checkoutStatus;
    List<CartItem> errorList = new ArrayList<>();
    
    public CheckoutStatus getCheckoutStatus() {
		return checkoutStatus;
	}

	double finalRate;

    public CheckoutResponse(CheckoutStatus checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }

    public CheckoutResponse(CheckoutStatus checkoutStatus, List<CartItem> errorList) {
        this.checkoutStatus = checkoutStatus;
        this.errorList = errorList;
    }

    public CheckoutResponse(CheckoutStatus checkoutStatus, double finalRate) {
        this.checkoutStatus = checkoutStatus;
        this.finalRate = finalRate;
    }
}
