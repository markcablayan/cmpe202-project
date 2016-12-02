package com.deadlock.server;
import org.json.* ;
import org.restlet.representation.* ;
import org.restlet.ext.json.* ;
import org.restlet.resource.* ;

import com.deadlock.controller.GameController;
import com.deadlock.controller.GameStartedState;
import com.deadlock.playermodel.HandType;
import com.deadlock.playermodel.PlayerModel;


public class OrientationModelResource extends ServerResource {
	GameController controller = GameController.getInstance();
	
	@Put
	public synchronized Representation put(JsonRepresentation jsonReq)
	{
		
		if(controller.getCurrentState() instanceof GameStartedState){
			JSONObject json = jsonReq.getJsonObject();
			String config = json.getString("config");
			controller.getCurrentState().changeOrientation(config);
			return new JsonRepresentation (controller.getJson());
			
		}else{
			return new StringRepresentation ( "the game has not started yet" ) ;
		}
	}
}

