package com.ebill.Ctrl;

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

import com.ebill.Model.Unit;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/Units")
public class UnitService {

Unit unitObj = new Unit();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML) 
	public String readTariffs() 
	 { 
	   return unitObj.readUnits(); 
	 } 
	
	

	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertTariff(@FormParam("unit_desc") String unit_desc, 
			@FormParam("unit_charge") String unit_charge, 
			@FormParam("tariff_id") String tariff_id) 
	{ 
		String output = unitObj.insertUnit(unit_desc, unit_charge, tariff_id); 
		return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateUnit(String unitData) 
	{ 
		//Convert the input string to a JSON object 
		JsonObject unitObject = new JsonParser().parse(unitData).getAsJsonObject();
		
		//Read the values from the JSON object
		String unit_id = unitObject.get("unit_id").getAsString(); 
		String unit_desc = unitObject.get("unit_desc").getAsString(); 
		String unit_charge = unitObject.get("unit_charge").getAsString(); 
		String tariff_id  = unitObject.get("tariff_id").getAsString(); 
		
		
		String output = unitObj.updateUnit(unit_id, unit_desc, unit_charge, tariff_id ); 
		return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteUnit(String unitData) 
	{ 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(unitData, "", Parser.xmlParser()); 
	 
		//Read the value from the element <unit_id>
		String unit_id = doc.select("unit_id").text(); 
		String output = unitObj.deleteUnit(unit_id); 
		return output; 
	}
}
