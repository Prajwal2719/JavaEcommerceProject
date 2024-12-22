package com.ecommerce.interfaces;

import java.util.List;

import com.ecommerce.model.Product;

public interface ProductDAOInterface {

    List<Product> getAllProducts();
     boolean addProduct(String name, String description, double price, int quantity);
}
