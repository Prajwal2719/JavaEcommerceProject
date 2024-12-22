package com.ecommerce.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.ecommerce.interfaces.ProductDAOInterface;
import com.ecommerce.model.Product;

public class ProductDAOImpl implements ProductDAOInterface {

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM Products ORDER BY name";
        try (Connection conn = DataBaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                productList.add(product);
            }
        } catch (Exception e) {
            System.out.println("Error fetching products.");
            e.printStackTrace();
        }
        return productList;
    }

	@Override
	public boolean addProduct(String name, String description, double price, int quantity) {
	    try (Connection conn = DataBaseConnection.connect()) {
	        String query = "INSERT INTO Products (name, description, price, quantity) VALUES (?, ?, ?, ?)";
	        PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setString(1, name);
	        stmt.setString(2, description);
	        stmt.setDouble(3, price);
	        stmt.setInt(4, quantity);

	        int rowsInserted = stmt.executeUpdate();
	        return rowsInserted > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	public int getProductQuantity(int productId) {
	    try (Connection conn = DataBaseConnection.connect()) {

	        String query = "SELECT quantity FROM Products WHERE prod_id = ?";

	        String query = "SELECT quantity FROM Products WHERE product_id = ?";

	        PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setInt(1, productId);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt("quantity");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return -1; // Indicates product not found
	}
}
