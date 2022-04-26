package com.ebill.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ebill.DBConnection.DBConnect;

public class Bill {
	
	double bill_amount;
	int consumed_units;
	
	//calculating the bill and inserting
	
	public String calculateBill(String elec_acc_no, String month, int current_meter_reading, int previous_meter_reading) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = DBConnect.connect(); 
			
			if (con == null) 
			{
				return "Error while connecting to the database for inserting Bill Info."; 
			} 
		
			Double bill_amount =0.00;
			
			int consumed_units = current_meter_reading - previous_meter_reading;
			
			if (consumed_units <= 60) {
				String q = " select * from e_units where unit_id = 1";
			  
			      Statement stmt = con.createStatement();
			      ResultSet rs = stmt.executeQuery(q);
			      while (rs.next()) {
			          bill_amount = Double.parseDouble(rs.getString("unit_charge")) * consumed_units  ;
			        
			    }	
						
	        }
	        else if ((consumed_units >= 60) && (consumed_units <= 120)) {
	        	double charge_upto_60 = 0.00;
	        	double charge_60_upto_current = 0.00;
	        	
	        	String q1 = " select * from e_units where unit_id = 1";
			    Statement stmt = con.createStatement();
			    ResultSet rs = stmt.executeQuery(q1);
			    while (rs.next()) {
			        charge_upto_60 = Double.parseDouble(rs.getString("unit_charge")) * 60  ;     
			    }
			    
			    String q2 = " select * from e_units where unit_id = 2";
			    Statement stmt1 = con.createStatement();
			    ResultSet rs2 = stmt1.executeQuery(q2);
			    while (rs2.next()) {
			    	charge_60_upto_current = (consumed_units - 60) * (Double.parseDouble(rs2.getString("unit_charge")))   ;     
			    }
			      
	        	bill_amount = charge_upto_60 + charge_60_upto_current;
	        }
	        else if ((consumed_units >= 120) && (consumed_units <= 180)) {
	        	double charge_upto_60 = 0.00;
	        	double charge_60_to_120 = 0.00;
	        	double charge_120_upto_current = 0.00;
	        	
	        	String q1 = " select * from e_units where unit_id = 1";
			    Statement stmt = con.createStatement();
			    ResultSet rs = stmt.executeQuery(q1);
			    while (rs.next()) {
			        charge_upto_60 = Double.parseDouble(rs.getString("unit_charge")) * 60  ;     
			    }
			    
			    String q2 = " select * from e_units where unit_id = 2";
			    ResultSet rs2 = stmt.executeQuery(q2);
			    while (rs2.next()) {
			    	charge_60_to_120 = Double.parseDouble(rs2.getString("unit_charge"))*60   ;     
			    }
			     
			    String q3 = " select * from e_units where unit_id = 3";
			    ResultSet rs3 = stmt.executeQuery(q3);
			    while (rs3.next()) {
			    	charge_120_upto_current = (consumed_units - 120) * (Double.parseDouble(rs3.getString("unit_charge")))   ;         
			    }
			     
	        	bill_amount = charge_upto_60 +charge_60_to_120+ charge_120_upto_current;
	        }
			
	        else {
	        	double charge_upto_60 = 0.00;
	        	double charge_60_to_120 = 0.00;
	        	double charge_120_to_180 = 0.00;
	        	double charge_180_upto_current = 0.00;
	        	
	        	String q1 = " select * from e_units where unit_id = 1";
			    Statement stmt = con.createStatement();
			    ResultSet rs = stmt.executeQuery(q1);
			    while (rs.next()) {
			        charge_upto_60 = Double.parseDouble(rs.getString("unit_charge")) * 60  ;     
			    }
			    
			    String q2 = " select * from e_units where unit_id = 2";
			    ResultSet rs2 = stmt.executeQuery(q2);
			    while (rs2.next()) {
			    	charge_60_to_120 = Double.parseDouble(rs2.getString("unit_charge"))*60   ;     
			    }
			     
			    String q3 = " select * from e_units where unit_id = 3";
			    ResultSet rs3 = stmt.executeQuery(q3);
			    while (rs3.next()) {
			    	charge_120_to_180 = Double.parseDouble(rs3.getString("unit_charge"))*60   ;         
			    }
			    
			    String q4 = " select * from e_units where unit_id = 4";
			    ResultSet rs4 = stmt.executeQuery(q4);
			    while (rs4.next()) {
			    	charge_180_upto_current = (consumed_units - 180) * (Double.parseDouble(rs4.getString("unit_charge")))   ;         
			    }
			     
	        	bill_amount = charge_upto_60 +charge_60_to_120+ charge_120_to_180 +charge_180_upto_current;
	        	
	        }
		
			// create a prepared statement
			String query = " insert into e_bill (`bill_id`,`elec_acc_no`,`month`,`current_meter_reading`,`previous_meter_reading`,`consumed_units`,`bill_amount`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)"; 
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setInt(2, Integer.parseInt(elec_acc_no));
			preparedStmt.setString(3, month);
			preparedStmt.setInt(4, current_meter_reading);
			preparedStmt.setInt(5, previous_meter_reading);
			preparedStmt.setInt(6, consumed_units);
			preparedStmt.setDouble(7, bill_amount);
			
			 
			
			// execute the statement
			preparedStmt.execute(); 
			
			con.close(); 
			output = "Bill Details Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the bill details."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 }
	
	
	//View bill details by passing the electricity account number
	
	public String GetSBill(String elec_acc_no) {
		String output = "";

		try {
			Connection con = DBConnect.connect();
			
			if (con == null) {
				return "Error while connecting to the database for Get Bill Details.";
			}


			// Prepare the HTML table to be displayed
				
			output = "<table border='1'><tr><th>Electricity Account Number</th><th>Month</th><th>Current Meter Reading</th><th>Previous Meter Reading</th><th>Consumed Units</th><th>Bill Amount</th>"; 
			
			String query = "select * from e_bill where elec_acc_no="+elec_acc_no;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

		

			while (rs.next()) { 
				String elect_acc_no = rs.getString("elec_acc_no");
				String month = rs.getString("month");
				String current_meter_reading = rs.getString("current_meter_reading");
				String previous_meter_reading = rs.getString("previous_meter_reading");
				String consumed_units = rs.getString("consumed_units");
				String bill_amount = Double.toString(rs.getDouble("bill_amount")); 
		 
				
				// Add into the HTML table
				output += "<tr><td>" + elect_acc_no + "</td>"; 
				output += "<td>" + month + "</td>"; 
				output += "<td>" + current_meter_reading + "</td>"; 
				output += "<td>" + previous_meter_reading + "</td>"; 
				output += "<td>" + consumed_units + "</td>"; 
				output += "<td>" + bill_amount + "</td>"; 
				
			
			} 
				
			con.close(); 
			
			// Complete the HTML table
			output += "</table>"; 
				
			return output;
			
		} catch (Exception e) {
			output = "Error while Get Specific Account's Bill Details.";
	
			System.err.println(e.getMessage());
		}
		return output;
	}
	
//View all bill details	

	public String readBills() 
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
				
			output = "<table border='1'><tr><th>Electricity Account Number</th><th>Month</th><th>Current Meter Reading</th><th>Previous Meter Reading</th><th>Consumed Units</th><th>Bill Amount</th>" +
					"<th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from e_bill";
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query); 
			
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String bill_id = Integer.toString(rs.getInt("bill_id")); 
				String elec_acc_no = rs.getString("elec_acc_no");
				String month = rs.getString("month");
				String current_meter_reading = rs.getString("current_meter_reading");
				String previous_meter_reading = rs.getString("previous_meter_reading");
				String consumed_units = rs.getString("consumed_units");
				String bill_amount = Double.toString(rs.getDouble("bill_amount")); 
		 
				
				// Add into the HTML table
				output += "<tr><td>" + elec_acc_no + "</td>"; 
				output += "<td>" + month + "</td>"; 
				output += "<td>" + current_meter_reading + "</td>"; 
				output += "<td>" + previous_meter_reading + "</td>"; 
				output += "<td>" + consumed_units + "</td>"; 
				output += "<td>" + bill_amount + "</td>"; 
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='units.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='bill_id' type='hidden' value='" + bill_id 
						+ "'>" + "</form></td></tr>"; 
			} 
				
			con.close(); 
			
			// Complete the HTML table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the Bill details."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 }
	
	public String updateBill(String bill_id, String elec_acc_no, String month, int current_meter_reading, int previous_meter_reading)
	{ 
		 String output = "";
		 
		 try
		 { 
			 Connection con = DBConnect.connect(); 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for updating."; 
			 }
			 
			 Double bill_amount =0.00;
				
				int consumed_units = current_meter_reading - previous_meter_reading;
				
				if (consumed_units <= 60) {
					String q = " select * from e_units where unit_id = 1";
				  
				      Statement stmt = con.createStatement();
				      ResultSet rs = stmt.executeQuery(q);
				      while (rs.next()) {
				          bill_amount = Double.parseDouble(rs.getString("unit_charge")) * consumed_units  ;
				        
				    }	
							
		        }
		        else if ((consumed_units >= 60) && (consumed_units <= 120)) {
		        	double charge_upto_60 = 0.00;
		        	double charge_60_upto_current = 0.00;
		        	
		        	String q1 = " select * from e_units where unit_id = 1";
				    Statement stmt = con.createStatement();
				    ResultSet rs = stmt.executeQuery(q1);
				    while (rs.next()) {
				        charge_upto_60 = Double.parseDouble(rs.getString("unit_charge")) * 60  ;     
				    }
				    
				    String q2 = " select * from e_units where unit_id = 2";
				    Statement stmt1 = con.createStatement();
				    ResultSet rs2 = stmt1.executeQuery(q2);
				    while (rs2.next()) {
				    	charge_60_upto_current = (consumed_units - 60) * (Double.parseDouble(rs2.getString("unit_charge")))   ;     
				    }
				      
		        	bill_amount = charge_upto_60 + charge_60_upto_current;
		        }
		        else if ((consumed_units >= 120) && (consumed_units <= 180)) {
		        	double charge_upto_60 = 0.00;
		        	double charge_60_to_120 = 0.00;
		        	double charge_120_upto_current = 0.00;
		        	
		        	String q1 = " select * from e_units where unit_id = 1";
				    Statement stmt = con.createStatement();
				    ResultSet rs = stmt.executeQuery(q1);
				    while (rs.next()) {
				        charge_upto_60 = Double.parseDouble(rs.getString("unit_charge")) * 60  ;     
				    }
				    
				    String q2 = " select * from e_units where unit_id = 2";
				    ResultSet rs2 = stmt.executeQuery(q2);
				    while (rs2.next()) {
				    	charge_60_to_120 = Double.parseDouble(rs2.getString("unit_charge"))*60   ;     
				    }
				     
				    String q3 = " select * from e_units where unit_id = 3";
				    ResultSet rs3 = stmt.executeQuery(q3);
				    while (rs3.next()) {
				    	charge_120_upto_current = (consumed_units - 120) * (Double.parseDouble(rs3.getString("unit_charge")))   ;         
				    }
				     
		        	bill_amount = charge_upto_60 +charge_60_to_120+ charge_120_upto_current;
		        }
				
		        else {
		        	double charge_upto_60 = 0.00;
		        	double charge_60_to_120 = 0.00;
		        	double charge_120_to_180 = 0.00;
		        	double charge_180_upto_current = 0.00;
		        	
		        	String q1 = " select * from e_units where unit_id = 1";
				    Statement stmt = con.createStatement();
				    ResultSet rs = stmt.executeQuery(q1);
				    while (rs.next()) {
				        charge_upto_60 = Double.parseDouble(rs.getString("unit_charge")) * 60  ;     
				    }
				    
				    String q2 = " select * from e_units where unit_id = 2";
				    ResultSet rs2 = stmt.executeQuery(q2);
				    while (rs2.next()) {
				    	charge_60_to_120 = Double.parseDouble(rs2.getString("unit_charge"))*60   ;     
				    }
				     
				    String q3 = " select * from e_units where unit_id = 3";
				    ResultSet rs3 = stmt.executeQuery(q3);
				    while (rs3.next()) {
				    	charge_120_to_180 = Double.parseDouble(rs3.getString("unit_charge"))*60   ;         
				    }
				    
				    String q4 = " select * from e_units where unit_id = 4";
				    ResultSet rs4 = stmt.executeQuery(q4);
				    while (rs4.next()) {
				    	charge_180_upto_current = (consumed_units - 180) * (Double.parseDouble(rs4.getString("unit_charge")))   ;         
				    }
				     
		        	bill_amount = charge_upto_60 +charge_60_to_120+ charge_120_to_180 +charge_180_upto_current;
		        	
		        }
			
			 
			 
			 
			 // create a prepared statement
			 String query = "UPDATE e_bill SET elec_acc_no=?,month=?,current_meter_reading=?,previous_meter_reading=?,consumed_units=?,bill_amount=?  WHERE bill_id=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setString(1, elec_acc_no);
			 preparedStmt.setString(2, month);
			 preparedStmt.setInt(3,current_meter_reading );
			 preparedStmt.setInt(4,previous_meter_reading );
			 preparedStmt.setInt(5, consumed_units);
			 preparedStmt.setDouble(6, bill_amount);
			 preparedStmt.setInt(7, Integer.parseInt(bill_id)); 
		 
			 // execute the statement
			 preparedStmt.execute(); 
		 
			 con.close(); 
			 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while updating the bills."; 
			 System.err.println(e.getMessage()); 
		 } 
		 
		 return output; 
	} 
	
	
	public String deleteBill(String bill_id) 
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
			String query = "delete from e_bill where bill_id=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(bill_id)); 
			
			// execute the statement
			preparedStmt.execute(); 
			
			con.close(); 
			output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while deleting the bill details."; 
		 System.err.println(e.getMessage()); 
	 }
		
		return output; 
	 }
}

