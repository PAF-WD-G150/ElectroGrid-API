package org.customermanagement.crud.CustomerManagement.resource;

import org.customermanagement.crud.CustomerManagement.model.customermanagementmodel;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("/customermanagement")
public class customermanagementresource {
	
	@Path("/insertion")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public customermanagementmodel addUser(customermanagementmodel user) {
		
	}

}
