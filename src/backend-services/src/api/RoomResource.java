import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import deadlockmodel.GameController;

public class RoomResource extends ServerResource {

	   // GumballMachine machine = GumballMachine.getInstance() ;
		GameController controller = GameController.getInstance();
		
		@Get
		public synchronized Representation get(JsonRepresentation jsonReq)
		{
			JSONObject response = new JSONObject();
			response.put("numberPlayers", controller.getList().size());
			return new JsonRepresentation(response);
		}
		
		@Put
		public synchronized Representation put(JsonRepresentation jsonReq)
		{
			//to withdraw from the game
			JSONObject requestObj = jsonReq.getJsonObject();
			if(requestObj.has("username")){
				String username = requestObj.getString("username");
				if(!controller.checkUniqueUsername(username)){
					boolean isRemoved = controller.removeFromRoom(username);
					JSONObject respObj = new JSONObject();
					respObj.put("isRemoved", isRemoved);
					return new JsonRepresentation(respObj);
				}else{
					return new StringRepresentation("there is no " + username + " in the room");
				}
			}	
			return  new StringRepresentation("please provide username");	
		}
	}

