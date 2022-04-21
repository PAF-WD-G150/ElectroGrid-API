package com.ecustomer.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.customer.DBConnection.DBConnect;

public class Customer {
	
	private PreparedStatement preparedStmt;
	private Connection con;

	public String insertCustomer(String ElectricityAcNo, String CustomerName, String NIC, String Address, String PhoneNumber, String Email, String Province)
	{
		String output = "";
		
		try
		{
		Connection con = DBConnect.connect();
		if (con == null)
		{
		return "Error while connecting to the database";
		}
		// create a prepared statement
		String query = " insert into electricityaccount(`electricityaccount_id`,`ElectricityAcNo`,`CustomerName`,`NIC`,`Address`,`PhoneNumber`,`Email`,`Province`)" + " values (?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, ElectricityAcNo);
		preparedStmt.setString(3, CustomerName);
		preparedStmt.setString(4, NIC);
		preparedStmt.setString(5, Address);
		preparedStmt.setString(6, PhoneNumber);
		preparedStmt.setString(7, Email);
		preparedStmt.setString(8, Province);

		
		//execute the statement
		preparedStmt.execute();
		con.close();
		
		output = "Inserted customer successfully";
	}
		
	catch (Exception e)
		
	{
		
		output = "Error while inserting customer";
		
		System.err.println(e.getMessage());
	}
		
	return output;
	
}


        public String readCustomer(){
        	
        String output = "";
        try
        {
           Connection con = DBConnect.connect();
        if (con == null)
        {
           return "Error while connecting to the database for reading.";
        }
        
        // Prepare the html table to be displayed
        output = "<table border='1'><tr><th>Electricity Account No</th>"+"<th>Customer Name</th><th>NIC</th>"+ "<th>Address</th>" + "<th>Phone Number</th>"+ "<th>Email</th>"+ "<th>Province</th>"+ "<th>Update</th><th>Remove</th></tr>";
        
        String query = "select * from electricityaccount";
        
        Statement stmt = con.createStatement();
        
        ResultSet rs = stmt.executeQuery(query);
        
        // iterate through the rows in the result set
        while (rs.next())
        {
          String electricityaccount_id = Integer.toString(rs.getInt("electricityaccount_id")); 
          String electricityAcNo = rs.getString("electricityAcNo");
          String customerName = rs.getString("customerName");
          String nic = rs.getString("nic");
          String address = rs.getString("address");
          String phonenumber = rs.getString("phonenumber");
          String email = rs.getString("email");
          String province = rs.getString("province");
          
          
        // Add a row into the html table
          output += "<tr><td>" + electricityAcNo + "</td>";
          output += "<td>" + customerName + "</td>";
          output += "<td>" + nic + "</td>";
          output += "<td>" + address + "</td>";
          output += "<td>" + phonenumber + "</td>";
          output += "<td>" + email + "</td>";
          output += "<td>" + province + "</td>";
          
          //buttons
          output += "<td><input name='btnUpdate' " + " type='button' value='Update'></td>" + "<td><form method='post' action='customers.jsp'>" + "<input name='btnRemove' " + " type='submit' value='Remove'>" + "<input name='electricityaccount_id' type='hidden' " + " value='" + electricityaccount_id + "'>" + "</form></td></tr>"; } con.close();
          
          //Complete the html table
          output += "</table>";
          }
        
          catch (Exception e)
          {
            output = "Error while reading the customers' details.";
            System.err.println(e.getMessage());
          }
          return output;
          }

public String updateCustomer(String ElectricityAcNo, String CustomerName, String NIC, String Address, String PhoneNumber, String Email, String Province)
     
     { 
	 String output = "";
	 
	 try
	 { 
		 Connection con = DBConnect.connect(); 
		 if (con == null) 
		 {
			 return "Error while connecting to the database for updating."; 
		 }
		 
		 // create a prepared statement
		 String query = "UPDATE electricityaccount SET ElectricityAcNo=?,CustomerName=?,NIC=?,Address=?,PhoneNumber=?,Email=?,Province=?, WHERE electricityaccount_id=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setString(1, ElectricityAcNo);
		 preparedStmt.setString(2, CustomerName);
		 preparedStmt.setString(3, NIC);
		 preparedStmt.setString(4, Address);
		 preparedStmt.setString(5, PhoneNumber);
		 preparedStmt.setString(6, Email);
		 preparedStmt.setString(7, Province);
		 String electricityaccount_id = null;
		preparedStmt.setInt(8, Integer.parseInt(electricityaccount_id)); 
	 
		 // execute the statement
		 preparedStmt.execute(); 
	 
		 con.close(); 
		 output = "Customer Account Details Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while updating the customer account details."; 
		 System.err.println(e.getMessage()); 
	 } 
	 
	 return output; 
} 

public String deleteCustomer(String electricityaccount_id){

	String output = "";

	try

	{

		Connection con = DBConnect.connect();

		if (con == null)

		{

			return "Error while connecting to the database for deleting.";

		}

		// create a prepared statement

		String query = "delete from electricityaccount where electricityaccount_id=?";

		PreparedStatement preparedStmt = con.prepareStatement(query);

		// binding values

		preparedStmt.setInt(1, Integer.parseInt(electricityaccount_id));

		// execute the statement

		preparedStmt.execute();

		con.close();

		output = "Deleted successfully";

	}

	catch (Exception e)

	{

		output = "Error while deleting the customer details.";

		System.err.println(e.getMessage());

	}

	return output;
}
}