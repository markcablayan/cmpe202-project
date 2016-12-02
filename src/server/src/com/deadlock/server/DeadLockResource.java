package com.deadlock.server;
import org.json.* ;
import org.restlet.representation.* ;
import org.restlet.ext.json.* ;
import org.restlet.resource.* ;

import com.deadlock.controller.*;
import com.deadlock.playermodel.HandType;

public class DeadLockResource extends ServerResource {

   // GumballMachine machine = GumballMachine.getInstance() ;
	GameController controller = GameController.getInstance();
	
	@Post
	public synchronized Representation post(JsonRepresentation jsonRep) {
		//getting json body
		if(jsonRep != null){
			JSONObject body = jsonRep.getJsonObject();
			JSONObject response = new JSONObject();
			if(body.has("username")){
				//checking uniqueness of username
				if(controller.getCurrentState().addPlayer(body.getString("username"))){
						response.put("numberPlayers", controller.getRoomCapacity());
						return new JsonRepresentation(response);
				}
			    else
			    {
			    	return new StringRepresentation ( "cannot add user" ) ;
			    }
			}
		}
		return null;
	}
	
	@Put
	public synchronized Representation put(JsonRepresentation jsonReq)
	{
		
		if(controller.getCurrentState() instanceof GameStartedState){
			JSONObject json = jsonReq.getJsonObject();
			HandType hand = null;
			if( json.getString("handType").equals("left")){
				hand = HandType.LEFT;
			}else{
				hand = HandType.RIGHT;
			}
			int passingPlayer = json.getInt("passingPlayer");
			int passedPlayer = json.getInt("passedPlayer");
			controller.getCurrentState().passGumballToNeighboor(passingPlayer, passedPlayer, hand);
			return new JsonRepresentation (controller.getJson());
			
		}else{
			return new StringRepresentation ( "the game has not started yet" ) ;
		}
	}
	
	@Get
    public synchronized Representation get() {
		return new JsonRepresentation ( controller.getJson() ) ;
    }
}

