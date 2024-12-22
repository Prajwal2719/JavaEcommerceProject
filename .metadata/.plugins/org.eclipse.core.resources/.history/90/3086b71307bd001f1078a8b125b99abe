package com.ecommerce.console.userinterface;

import java.util.Scanner;


import com.ecommerce.database.UserDAOImpl;

import com.ecommerce.model.User;

public class ECommerceApplication {

	public static void main(String[] args) {
		UserDAOImpl userDAO= new UserDAOImpl();
        System.out.println("Welcome to E-Commerce Application");
        
        System.out.println("1. Register User");
        System.out.println("2. Login");
        
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline
        
        if (choice == 1) {
            User user = new User();
            System.out.println("Enter Username: ");
            user.setUsername(sc.nextLine());
            System.out.println("Enter Password: ");
            user.setPassword(sc.nextLine());
            System.out.println("Enter First Name: ");
            user.setFirstName(sc.nextLine());
            System.out.println("Enter Last Name: ");
            user.setLastName(sc.nextLine());
            System.out.println("Enter Email: ");
            user.setEmail(sc.nextLine());
            System.out.println("Enter Mobile: ");
            user.setMobile(sc.nextLine());
            System.out.println("Enter City: ");
            user.setCity(sc.nextLine());
            user.setRole("user");

            userDAO.registerUser(user);
            System.out.println("Would you like to log in now? (yes/no)");
            String autoLogin = sc.nextLine();
            if ("yes".equalsIgnoreCase(autoLogin)) {
                if (userDAO.loginUser(user.getUsername(), user.getPassword())) {
                    System.out.println("Login successful! Welcome, " + user.getFirstName() + "!");
                    // Navigate to user operations here
                } else {
                    System.out.println("Something went wrong. Please try to login manually.");
                }
            } else {
                System.out.println("You can log in later from the main menu.");
            }
        }else if (choice == 2) {
            System.out.println("Enter Username: ");
            String username = sc.nextLine();
            System.out.println("Enter Password: ");
            String password = sc.nextLine();

            if (userDAO.loginUser(username, password)) {
                System.out.println("Login successful!");
                // Navigate to user operations here
            } else {
                System.out.println("Invalid credentials. Please try again.");
            }
        }
    }
}

