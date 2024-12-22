package com.ecommerce.interfaces;

import java.util.List;

import com.ecommerce.model.Cart;

public interface CartDAOInterface {
    boolean addToCart(int userId,int productId, int quantity);
    List<Cart> getCartItems(int userId);

}