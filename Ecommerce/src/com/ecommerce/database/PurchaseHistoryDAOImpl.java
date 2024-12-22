package com.ecommerce.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.ecommerce.interfaces.PurchaseHistoryDAOInterface;
public class PurchaseHistoryDAOImpl implements PurchaseHistoryDAOInterface {
    @Override
    public boolean purchaseItems(int userId) {
        try (Connection conn = DataBaseConnection.connect()) {
            // Get items from the cart
            String cartQuery = "SELECT cart_id, prod_id, quantity FROM Cart WHERE user_id = ?";
            PreparedStatement cartStmt = conn.prepareStatement(cartQuery);
            cartStmt.setInt(1, userId);
            ResultSet cartRs = cartStmt.executeQuery();
            boolean purchaseSuccessful = false;
            while (cartRs.next()) {
                int cartId = cartRs.getInt("cart_id");
                int productId = cartRs.getInt("prod_id");
                int quantity = cartRs.getInt("quantity");
                // Verify product exists in the Products table
                String productExistQuery = "SELECT price FROM Products WHERE prod_id = ?";
                PreparedStatement productExistStmt = conn.prepareStatement(productExistQuery);
                productExistStmt.setInt(1, productId);
                ResultSet productRs = productExistStmt.executeQuery();
                if (!productRs.next()) {
                    System.out.println("Product ID " + productId + " does not exist. Skipping item.");
                    continue; // Skip this item
                }
                double price = productRs.getDouble("price");
                double totalPrice = price * quantity;
                // Add entry to PurchaseHistory
                String purchaseQuery = "INSERT INTO PurchaseHistory (user_id, prod_id, quantity, total_price) VALUES (?, ?, ?, ?)";
                PreparedStatement purchaseStmt = conn.prepareStatement(purchaseQuery);
                purchaseStmt.setInt(1, userId);
                purchaseStmt.setInt(2, productId);
                purchaseStmt.setInt(3, quantity);
                purchaseStmt.setDouble(4, totalPrice);
                int rowsInserted = purchaseStmt.executeUpdate();
                if (rowsInserted > 0) {
                    purchaseSuccessful = true;
                    // Remove item from Cart
                    String deleteCartQuery = "DELETE FROM Cart WHERE cart_id = ?";
                    PreparedStatement deleteCartStmt = conn.prepareStatement(deleteCartQuery);
                    deleteCartStmt.setInt(1, cartId);
                    deleteCartStmt.executeUpdate();
                }
            }
            return purchaseSuccessful;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // Method to calculate total bill for a user
    public double calculateTotalBill(int userId) {
        double totalBill = 0.0;
        try (Connection conn = DataBaseConnection.connect()) {
            // Fetch total price from PurchaseHistory for the given userId
            String billQuery = "SELECT SUM(total_price) AS total_bill FROM PurchaseHistory WHERE user_id = ?";
            PreparedStatement billStmt = conn.prepareStatement(billQuery);
            billStmt.setInt(1, userId);
            ResultSet billRs = billStmt.executeQuery();
            if (billRs.next()) {
                totalBill = billRs.getDouble("total_bill");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalBill;
    }
}
