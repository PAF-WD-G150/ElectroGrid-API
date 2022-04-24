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
	@Path("/")
	@Produces(MediaType.TEXT_HTML) 
	public String readUnits() 
	 { 
	   return complaintObj.readComplaints(); 
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
