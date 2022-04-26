package com.faultReporting.Ctrl;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.faultReporting.Model.CustomerFaultReport;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Path("/Complaints")
public class CustomerFaultReportService {
	
CustomerFaultReport complaintObj = new CustomerFaultReport();
	
	@GET
	@Path("/single")
	@Produces({MediaType.TEXT_HTML})
	@Consumes(MediaType.APPLICATION_JSON)
	public String readComplaints(String id) 
	 { 
		JsonObject complaintObject = new JsonParser().parse(id).getAsJsonObject();
		String electricity_acc_no = complaintObject.get("electricity_acc_no").getAsString();
	   return complaintObj.readComplaints(electricity_acc_no); 
	 } 
	

	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertComplaint(@FormParam("electricity_acc_no") String electricity_acc_no, 
			@FormParam("complaint_type") String complaint_type,
			@FormParam("contact_details") String contact_details,
			@FormParam("attachments") String attachments
			) 
	{ 
		String output = complaintObj.insertComplaint(electricity_acc_no, complaint_type,contact_details, attachments); 
		return output; 
	}
	

	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateComplaint(String complaintData) 
	{ 
		//Convert the input string to a JSON object 
		JsonObject complaintObject = new JsonParser().parse(complaintData).getAsJsonObject();
		
		//Read the values from the JSON object
		String complaint_id = complaintObject.get("complaint_id").getAsString();
		String electricity_acc_no = complaintObject.get("electricity_acc_no").getAsString();
		String complaint_type = complaintObject.get("complaint_type").getAsString();
		String contact_details = complaintObject.get("contact_details").getAsString();
		String attachments = complaintObject.get("attachments").getAsString();
		String processing_status = complaintObject.get("processing_status").getAsString(); 
		String reply_message = complaintObject.get("reply_message").getAsString();  
	 
		
		String output = complaintObj.updateComplaint(complaint_id, electricity_acc_no, complaint_type, contact_details, attachments, processing_status, reply_message); 
		return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteComplaint(String complaintData) 
	{ 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(complaintData, "", Parser.xmlParser()); 
	 
		//Read the value from the element <complaint_id>
		String complaint_id = doc.select("complaint_id").text(); 
		String output = complaintObj.deleteComplaint(complaint_id); 
		return output; 
	}
	
}
