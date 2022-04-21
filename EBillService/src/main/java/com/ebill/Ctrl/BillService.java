package com.ebill.Ctrl;

import com.ebill.Model.Bill;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;


//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Bills")
public class BillService {

	Bill billObj = new Bill();
	
	//get all bill details
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML) 
	public String readBills() 
	 { 
	   return billObj.readBills(); 
	 }
	
	
	//filter bill details according to the electricity account number
	@GET
	@Path("/single")
	@Produces({ MediaType.TEXT_HTML })
	@Consumes(MediaType.APPLICATION_JSON)
	public String GetSingleBill(String id) {
		JsonObject billObject = new JsonParser().parse(id).getAsJsonObject();
		String elec_acc_no = billObject.get("elec_acc_no").getAsString();
		return billObj.GetSBill(elec_acc_no);
	}
	 

	//insert a bill
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String calculateBill(@FormParam("elec_acc_no") String elec_acc_no, 
			@FormParam("month") String month, 
			@FormParam("current_meter_reading") int current_meter_reading,
			@FormParam("previous_meter_reading") int previous_meter_reading) 
	{ 
		String output = billObj.calculateBill(elec_acc_no, month, current_meter_reading, previous_meter_reading); 
		return output; 
	}
	
	
	//update bill records
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateBill(String billData) 
	{ 
		//Convert the input string to a JSON object 
		JsonObject billObject = new JsonParser().parse(billData).getAsJsonObject();
		
		//Read the values from the JSON object
		String bill_id = billObject.get("bill_id").getAsString(); 
		String elec_acc_no = billObject.get("elec_acc_no").getAsString(); 
		String month = billObject.get("month").getAsString();
		int current_meter_reading = billObject.get("current_meter_reading").getAsInt();
		int previous_meter_reading = billObject.get("previous_meter_reading").getAsInt();
		
		
		String output = billObj.updateBill(bill_id,  elec_acc_no, month,current_meter_reading, previous_meter_reading  ); 
		return output; 
	}
	
	//delete bill
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteBill(String billData) 
	{ 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(billData, "", Parser.xmlParser()); 
	 
		//Read the value from the element <bill_id>
		String bill_id = doc.select("bill_id").text(); 
		
		String output = billObj.deleteBill(bill_id); 
		return output; 
	}
	
}