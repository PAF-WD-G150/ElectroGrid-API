package com.epayment.Ctrl;

import com.epayment.Model.AdminPayment ;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/AdminPayment")
public class AdminPaymentService 
{
	AdminPayment payObj = new AdminPayment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
		return payObj.readItems();
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAdminPayment(String adminpaymentData)
	{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(adminpaymentData, "", Parser.xmlParser());
	
		//Read the value from the element <itemID>
		 String payID = doc.select("payID").text();
		 
		 String output = payObj.deleteAdminPayment(payID);
		 
		 return output;
	}

}
