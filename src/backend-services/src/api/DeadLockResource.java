import org.json.* ;
import deadlockmodel.*;
import org.restlet.representation.* ;
import org.restlet.ext.json.* ;
import org.restlet.resource.* ;

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
				if(controller.checkUniqueUsername(body.getString("username"))){
					if(controller.getList().size() < 5 ){
						controller.addPlayer(new PlayerModel(controller,body.getString("username")));
						response.put("numberPlayers", controller.getList().size());
						return new JsonRepresentation(response);
				    }
				    else
				    {
				    	return new StringRepresentation ( "cannot add more user" ) ;
				    }
				}
				else{
					return new StringRepresentation ( "somebody has this username in the rooom" ) ;
				}
			}else
			{
				return new StringRepresentation ( "please provide a username" ) ;
			}
		}else{
			return new StringRepresentation ( "please provide a username" ) ;
		}
	}
	
	@Put
	public synchronized Representation put(JsonRepresentation jsonReq)
	{
		if(controller.isGameStarted()){
			JSONObject json = jsonReq.getJsonObject();
			HandType hand = null;
			if( json.getString("handType").equals("left")){
				hand = HandType.LEFT;
			}else{
				hand = HandType.RIGHT;
			}
			int passingPlayer = json.getInt("passingPlayer");
			int passedPlayer = json.getInt("passedPlayer");
			controller.getList().get(passingPlayer).passGumballToNeighboor(hand,controller.getList().get(passedPlayer) );
			if(controller.checkWinCondition()){
				controller.restartGame();
			}
			return new JsonRepresentation (controller.getJson());
			
		}else{
			return new StringRepresentation ( "the game has not started yet" ) ;
		}
	}
	
	@Get
    public synchronized Representation get() {
		
		if(controller.getList().size() == 5){
			controller.setUpGame();
			return new JsonRepresentation ( controller.getJson() ) ;
		}else{
			return new StringRepresentation ( "there is not enough user.Please wait" ) ;
		}
    }
}

