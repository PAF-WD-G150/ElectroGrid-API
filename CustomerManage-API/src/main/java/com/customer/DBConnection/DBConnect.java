package com.customer.DBConnection;
import java.sql.*;

public class DBConnect {
	
	public static Connection connect() {
		
	Connection con = null;
	
	try
	{
		
	Class.forName("com.mysql.jdbc.Driver");
	con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ecustomerdb",
	"root", "");
	//For testing
	System.out.print("Successfully connected");
	}
	
	catch(Exception e)
	{
	e.printStackTrace();
	}
	
	return con;
}
}

