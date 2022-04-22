package com.ecustomer.Ctrl;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;

import com.ecustomer.Model.Customer;
//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Customer")
public class CustomerService {
	

	Customer customerObj = new Customer();
	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML) 
	public String readCustomer() 
	 { 
	   return customerObj.readCustomer(); 
	 } 
	 
//insert new customer
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertcustomer(
			 
			@FormParam("CustomerName") String CustomerName, 
			@FormParam("NIC") String NIC,
			@FormParam("Address") String  Address,
			@FormParam("PhoneNumber") String  PhoneNumber,
			@FormParam("Email") String  Email,
			@FormParam("Province") String  Province
			
			) 
	{ 
		String output = customerObj.insertCustomer(CustomerName, NIC, Address,PhoneNumber, Email, Province); 
		return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateCustomer(String customerData) 
	{ 
		//Convert the input string to a JSON object 
		JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject();
		
		 
		String ElectricityAcNo = customerObject.get("ElectricityAcNo").getAsString(); 
		String CustomerName =  customerObject.get("CustomerName").getAsString();
		String NIC =  customerObject.get("NIC").getAsString();
		String Address =  customerObject.get("Address").getAsString();
		String PhoneNumber =  customerObject.get("PhoneNumber").getAsString();
		String  Email =  customerObject.get(" Email").getAsString();
		String  Province =  customerObject.get(" Province").getAsString();
		
		
		String output = customerObj.updateCustomer(ElectricityAcNo, CustomerName,NIC, Address, PhoneNumber, Email,Province); 
		return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteCustomer(String customerData) 
	{ 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(customerData, "", Parser.xmlParser()); 
	 
		//Read the value from the element <electricityaccount_id>
		String electricityAcNo = doc.select("electricityAcNo").text(); 
		
		String output = customerObj.deleteCustomer(electricityAcNo); 
		return output; 
	}
	
}
