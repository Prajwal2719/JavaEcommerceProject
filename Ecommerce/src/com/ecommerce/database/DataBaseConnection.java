package com.ecommerce.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {

	private static final String DB_Driver_Name = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "system";
	private static final String PASSWORD = "system";
	
	public static Connection connect() {
		Connection con = null;
		try {
			// load the Driver Class
			Class.forName(DB_Driver_Name);
			// create the connection now
			con = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return con;
	}
}