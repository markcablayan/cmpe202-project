package com.deadlock.server;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import com.deadlock.controller.GameController;

public class RoomResource extends ServerResource {

	   // GumballMachine machine = GumballMachine.getInstance() ;
		GameController controller = GameController.getInstance();
		
		@Get
		public synchronized Representation get(JsonRepresentation jsonReq)
		{
			JSONObject response = new JSONObject();
			response.put("numberPlayers", controller.getRoomCapacity());
			return new JsonRepresentation(response);
		}
		
		@Put
		public synchronized Representation put(JsonRepresentation jsonReq)
		{
			//to withdraw from the game
			JSONObject requestObj = jsonReq.getJsonObject();
			if(requestObj.has("username")){
				String username = requestObj.getString("username");
				boolean isRemoved = controller.getCurrentState().removeFromRoom(username);
				JSONObject respObj = new JSONObject();
				respObj.put("isRemoved", isRemoved);
				return new JsonRepresentation(respObj);
			}	
			return  new StringRepresentation("please provide username");	
		}
	}

