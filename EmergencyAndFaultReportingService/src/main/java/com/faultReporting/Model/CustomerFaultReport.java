package com.faultReporting.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.faultReporting.DBConnection.DBConnect;

public class CustomerFaultReport {
	public String insertComplaint(String electricity_acc_no, String complaint_type, String contact_details, String attachments) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = DBConnect.connect(); 
			
			if (con == null) 
			{
				return "Error while connecting to the database for inserting complaint."; 
			} 
			
			String processing_status = "processing";
			String reply_message = "reply will be updated soon";
				
			// create a prepared statement
			String query = " insert into complaints (`complaint_id`,`electricity_acc_no`,`complaint_type`,`contact_details`,`attachments`,`processing_status`,`reply_message`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)"; 
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, electricity_acc_no); 
			preparedStmt.setString(3, complaint_type);
			preparedStmt.setString(4, contact_details);
			preparedStmt.setString(5, attachments);
			preparedStmt.setString(6, processing_status);
			preparedStmt.setString(7, reply_message);
			
			 
			
			// execute the statement
			preparedStmt.execute(); 
			
			con.close(); 
			output = "Complaint Details Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the complaint details."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 
	
	
	public String readComplaints() 
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
				
			output = "<table border='1'><tr><th>Electricity Account Number</th><th>Complaint Type</th><th>Contact Details</th><th>Attachments</th><th>Processing Status</th><th>Reply Message</th>" +
					"<th>Remove</th></tr>"; 
	 
			String query = "select * from complaints";
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query); 
			
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String complaint_id = Integer.toString(rs.getInt("complaint_id")); 
				String electricity_acc_no = rs.getString("electricity_acc_no"); 
				String complaint_type = rs.getString("complaint_type"); 
				String contact_details = rs.getString("contact_details"); 
				String attachments = rs.getString("attachments");
				String processing_status = rs.getString("processing_status");
				String reply_message = rs.getString("reply_message");
		 
				
				// Add into the HTML table
				output += "<tr><td>" + electricity_acc_no + "</td>"; 
				output += "<td>" + complaint_type + "</td>"; 
				output += "<td>" + contact_details + "</td>"; 
				output += "<td>" + attachments + "</td>"; 
				output += "<td>" + processing_status + "</td>"; 
				output += "<td>" + reply_message + "</td>"; 
				 
				 
				
				// buttons
				output +=  "<td><form method='post' action='complaints.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='unit_id' type='hidden' value='" + complaint_id 
						+ "'>" + "</form></td></tr>"; 
			} 
				
			con.close(); 
			
			// Complete the HTML table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the complaint details."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 }
	
	
	public String deleteComplaint(String complaint_id) 
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
			String query = "delete from complaints where complaint_id=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(complaint_id)); 
			
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
