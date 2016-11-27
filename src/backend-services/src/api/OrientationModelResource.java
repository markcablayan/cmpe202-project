import org.json.* ;
import org.restlet.representation.* ;
import org.restlet.ext.json.* ;
import org.restlet.resource.* ;

import deadlockmodel.GameController;
import deadlockmodel.HandType;
import deadlockmodel.PlayerModel;


public class OrientationModelResource extends ServerResource {
	GameController controller = GameController.getInstance();
	
	@Put
	public synchronized Representation put(JsonRepresentation jsonReq)
	{
		if(controller.isGameStarted()){
			JSONObject respObj = new JSONObject();
			JSONObject json = jsonReq.getJsonObject();
			
			String config = json.getString("config");
			controller.changeOrientation(config);
			if(controller.checkWinCondition()){
				controller.restartGame();
				respObj.put("win", "true");
			}else{
				respObj.put("win", "false");
			}
			respObj.put("players", controller.getJson().toString());
			
			return new JsonRepresentation (respObj);
			
		}else{
			return new StringRepresentation ( "the game has not started yet" ) ;
		}
	}
}

