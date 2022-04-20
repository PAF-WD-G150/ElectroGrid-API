package com.ebill.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ebill.DBConnection.DBConnect;

public class Unit {

	public String insertUnit(String unit_desc, String unit_charge) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = DBConnect.connect(); 
			
			if (con == null) 
			{
				return "Error while connecting to the database for inserting Tariff."; } 
				
			// create a prepared statement
			String query = " insert into e_units (`unit_id`,`unit_desc`,`unit_charge`)"
					+ " values (?, ?, ?)"; 
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, unit_desc); 
			preparedStmt.setDouble(3, Double.parseDouble(unit_charge)); 
			 
			
			// execute the statement
			preparedStmt.execute(); 
			
			con.close(); 
			output = "Unit Details Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the unit details."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 
	
	
	public String readUnits() 
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
				
			output = "<table border='1'><tr><th>Unit Description</th><th>Unit Charge</th>" +
					"<th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from e_units";
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query); 
			
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String unit_id = Integer.toString(rs.getInt("unit_id")); 
				String unit_desc = rs.getString("unit_desc"); 
				String unit_charge = Double.toString(rs.getDouble("unit_charge")); 
		 
				
				// Add into the HTML table
				output += "<tr><td>" + unit_desc + "</td>"; 
				output += "<td>" + unit_charge + "</td>"; 
				
				 
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='units.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='unit_id' type='hidden' value='" + unit_id 
						+ "'>" + "</form></td></tr>"; 
			} 
				
			con.close(); 
			
			// Complete the HTML table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the unit details."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 }
	
	public String updateUnit(String unit_id, String unit_desc, String unit_charge)
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
			 String query = "UPDATE e_units SET unit_desc=?,unit_charge=? WHERE unit_id=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setString(1, unit_desc); 
			 preparedStmt.setDouble(2, Double.parseDouble(unit_charge)); 
			 preparedStmt.setInt(3, Integer.parseInt(unit_id)); 
		 
			 // execute the statement
			 preparedStmt.execute(); 
		 
			 con.close(); 
			 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while updating the units."; 
			 System.err.println(e.getMessage()); 
		 } 
		 
		 return output; 
	} 
	
	public String deleteUnit(String unit_id) 
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
			String query = "delete from e_units where unit_id=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(unit_id)); 
			
			// execute the statement
			preparedStmt.execute(); 
			
			con.close(); 
			output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while deleting the unit details."; 
		 System.err.println(e.getMessage()); 
	 }
		
		return output; 
	 }
}
