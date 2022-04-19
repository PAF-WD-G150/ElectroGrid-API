package com.epayment.Ctrl;

import com.epayment.Model.CustomerPayment ;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

@Path("/CustomerPayment")
public class CustomerPaymentService 
{
	CustomerPayment payObj = new CustomerPayment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
		return payObj.readItems();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomerPayment(@FormParam("payCardType") String payCardType,
										 @FormParam("payCardNO") int payCardNO,
										 @FormParam("payExpiryDate") String payExpiryDate,
										 @FormParam("payCVV") int payCVV,
										 @FormParam("payDate") String payDate,
										 @FormParam("payTotalAmount") String payTotalAmount,
										 @FormParam("payAmount") String payAmount)
	{
		String output = payObj.insertCustomerPayment(payCardType,payCardNO,payExpiryDate,payCVV,payDate,payTotalAmount,payAmount);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomerPayment(String customerpaymentData)
	{
		//Convert the input string to a JSON object
		 JsonObject payObject = new JsonParser().parse(customerpaymentData).getAsJsonObject();
		 
		//Read the values from the JSON object
		 String payID 		  = payObject.get("payID").getAsString();
		 String payCardType   = payObject.get("payCardType").getAsString();
		 int payCardNO		  = payObject.get("payCardNO").getAsInt();
		 String payExpiryDate = payObject.get("payExpiryDate").getAsString();
		 int payCVV			  = payObject.get("payCVV").getAsInt();
		 String payDate       = payObject.get("payDate").getAsString();
		 String payTotalAmount     = payObject.get("payTotalAmount").getAsString();
		 String payAmount     = payObject.get("payAmount").getAsString();
		 
		 String output = payObj.updateCustomerPayment(payID, payCardType, payCardNO, payExpiryDate, payCVV,payDate,payTotalAmount,payAmount);
		 
		 return output;
	}
}
