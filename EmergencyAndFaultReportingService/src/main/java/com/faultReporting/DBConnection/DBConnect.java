package com.faultReporting.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	public static Connection connect() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/complaints?useTimezone=true&serverTimezone=UTC", "root", "");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		return con;
	}
}