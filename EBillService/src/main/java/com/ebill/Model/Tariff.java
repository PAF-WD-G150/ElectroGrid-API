package com.ebill.Model;

import com.ebill.DBConnection.*;
import java.sql.*;

public class Tariff {

	
	public String insertTariff(String tariff_type, String tariff_desc, String fixed_charge) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = DBConnect.connect(); 
			
			if (con == null) 
			{
				return "Error while connecting to the database for inserting Tariff."; } 
				
			// create a prepared statement
			String query = " insert into e_tariff (`tariff_id`,`tariff_type`,`tariff_desc`,`fixed_charge`)"
					+ " values (?, ?, ?, ?)"; 
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, tariff_type); 
			preparedStmt.setString(3, tariff_desc); 
			preparedStmt.setDouble(4, Double.parseDouble(fixed_charge)); 
			 
			
			// execute the statement
			preparedStmt.execute(); 
			
			con.close(); 
			output = "Tariff Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the tariff."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 
	
	public String readTariffs() 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = DBConnect.connect(); 
			
			if (con == null) 
			{
				return "Error while connecting to the database for reading.";
			} 
			
			// Prepare the HTML table to be displayed
				
			output = "<table border='1'><tr><th>Tariff Type</th><th>Tariff Description</th>" +
					"<th>Fixed Charge</th>" + 
					"<th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from e_tariff";
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query); 
			
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String tariff_id = Integer.toString(rs.getInt("tariff_id")); 
				String tariff_type = rs.getString("tariff_type"); 
				String tariff_desc = rs.getString("tariff_desc"); 
				String fixed_charge = Double.toString(rs.getDouble("fixed_charge")); 
				
				// Add into the HTML table
				output += "<tr><td>" + tariff_type + "</td>"; 
				output += "<td>" + tariff_desc + "</td>"; 
				output += "<td>" + fixed_charge + "</td>"; 
				 
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='tariffs.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='tariff_id' type='hidden' value='" + tariff_id 
						+ "'>" + "</form></td></tr>"; 
			} 
				
			con.close(); 
			
			// Complete the HTML table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the tariffs."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 
	
	public String updateTariff(String tariff_id, String tariff_type, String tariff_desc, String fixed_charge)
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
			 String query = "UPDATE e_tariff SET tariff_type=?,tariff_desc=?,fixed_charge=? WHERE tariff_id=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setString(1, tariff_type); 
			 preparedStmt.setString(2, tariff_desc); 
			 preparedStmt.setDouble(3, Double.parseDouble(fixed_charge));  
			 preparedStmt.setInt(5, Integer.parseInt(tariff_id)); 
		 
			 // execute the statement
			 preparedStmt.execute(); 
		 
			 con.close(); 
			 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while updating the tariff."; 
			 System.err.println(e.getMessage()); 
		 } 
		 
		 return output; 
	} 
	
	public String deleteTariff(String tariff_id) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = DBConnect.connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for deleting.";
			} 
			
			// create a prepared statement
			String query = "delete from e_tariff where tariff_id=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(tariff_id)); 
			
			// execute the statement
			preparedStmt.execute(); 
			
			con.close(); 
			output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while deleting the tariff."; 
		 System.err.println(e.getMessage()); 
	 }
		
		return output; 
	 } 
	
}
