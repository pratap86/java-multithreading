package com.pratap.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.pratap.domain.checkout.Cart;
import com.pratap.domain.checkout.CartItem;

public class DataSet {

	public static List<String> nameList(){
		return List.of("Pratap", "Sankalp", "Narayan", "Deepika");
	}
	
	public static Cart createCart(int noOfItemsInCart) {

        Cart cart = new Cart();
        List<CartItem> cartItemList = new ArrayList<>();
        
        IntStream.rangeClosed(1, noOfItemsInCart)
                .forEach((index) -> {
                    String cartItem = "CartItem -".concat(index + "");
                    CartItem cartItem1 = new CartItem(index, cartItem, generateRandomPrice(), index, false);
                    cartItemList.add(cartItem1);
                });
        cart.setCartItemList(cartItemList);
        return cart;
    }
	
	public static double generateRandomPrice() {
        int min = 50;
        int max = 100;
        return Math.random() * (max - min + 1) + min;
    }
}
