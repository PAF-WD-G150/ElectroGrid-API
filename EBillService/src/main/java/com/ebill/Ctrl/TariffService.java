package com.ebill.Ctrl;

import com.ebill.Model.Tariff;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;


//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Tariffs")
public class TariffService {

	Tariff tariffObj = new Tariff();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML) 
	public String readTariffs() 
	 { 
	   return tariffObj.readTariffs(); 
	 } 
	
	

	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertTariff(@FormParam("tariff_type") String tariff_type, 
			@FormParam("tariff_desc") String tariff_desc, 
			@FormParam("fixed_charge") String fixed_charge) 
	{ 
		String output = tariffObj.insertTariff(tariff_type, tariff_desc, fixed_charge); 
		return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateTariff(String tariffData) 
	{ 
		//Convert the input string to a JSON object 
		JsonObject tariffObject = new JsonParser().parse(tariffData).getAsJsonObject();
		
		//Read the values from the JSON object
		String tariff_id = tariffObject.get("tariff_id").getAsString(); 
		String tariff_type = tariffObject.get("tariff_type").getAsString(); 
		String tariff_desc = tariffObject.get("tariff_desc").getAsString(); 
		String fixed_charge = tariffObject.get("fixed_charge").getAsString(); 
		
		
		String output = tariffObj.updateTariff(tariff_id, tariff_type, tariff_desc, fixed_charge ); 
		return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteTariff(String tariffData) 
	{ 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(tariffData, "", Parser.xmlParser()); 
	 
		//Read the value from the element <tariff_id>
		String tariff_id = doc.select("tariff_id").text(); 
		String output = tariffObj.deleteTariff(tariff_id); 
		return output; 
	}


}
