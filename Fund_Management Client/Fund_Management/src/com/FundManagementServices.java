package com;

import model.FundManagement;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/FundManagement")

public class FundManagementServices {
	
	FundManagement fundmanagement = new FundManagement();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFundmanagementData() {
		return fundmanagement.readFundmanagementData();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearch(@FormParam("name") String name, @FormParam("email") String email,
			@FormParam("address") String address, @FormParam("fundAmount") String fundAmount,
			@FormParam("interestedCategory") String interestedCategory) {
		String output = fundmanagement.insertfundData(name, email, address, fundAmount, interestedCategory);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateResearch(String fundData) {
		// Convert the input string to a JSON object
		JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject();
		// Read the values from the JSON object

		String fid = fundObject.get("fid").getAsString();
		String name = fundObject.get("name").getAsString();
		String email = fundObject.get("email").getAsString();
		String address = fundObject.get("address").getAsString();
		String fundAmount = fundObject.get("fundAmount").getAsString();
		String interestedCategory = fundObject.get("interestedCategory").getAsString();
		String output = fundmanagement.updatefundData(fid, name, email, address, fundAmount, interestedCategory);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearch(String fundData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(fundData, "", Parser.xmlParser());

		String fid = doc.select("fid").text();
		String output = fundmanagement.deletefundData(fid);
		return output;
	}

}
