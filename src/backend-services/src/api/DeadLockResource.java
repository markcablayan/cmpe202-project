package api;

mport java.util.ArrayList;

import org.json.* ;
import org.restlet.representation.* ;
import org.restlet.ext.json.* ;
import org.restlet.resource.* ;

import deadlockmodel.GameController;
import deadlockmodel.HandType;
import deadlockmodel.ObjectHoldModel;
import deadlockmodel.PlayerModel;
import deadlockmodel.StraightLineConfig;

public class DeadLockResource extends ServerResource {

   // GumballMachine machine = GumballMachine.getInstance() ;
	GameController controller = GameController.getInstance();
	
	@Post
	public Representation post(JsonRepresentation jsonRep) {
		
		if(controller.getList().size() < 5 ){
			JSONObject json = new JSONObject() ;
			controller.addPlayer(new PlayerModel(controller));
			json.put("numberPlayers", controller.getList().size());
			return new JsonRepresentation(json);
	    }
	    else
	    {
	    	return new StringRepresentation ( "cannot add more user" ) ;
	    }
		
	}
	
	@Put
	public Representation put(JsonRepresentation jsonReq)
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
			HandType isPassed = controller.getList().get(passingPlayer).passGumballToNeighboor(hand,controller.getList().get(passedPlayer) );
	        if(isPassed!=null)
	        {
	        	return new JsonRepresentation ( new JSONObject().put("isPassed", isPassed.getType())) ;
	        }else{
	        	return new JsonRepresentation ( new JSONObject().put("isPassed", "")) ;
	        }
		}else{
			return new StringRepresentation ( "the game has not started yet" ) ;
		}
	}
	
	@Get
    public Representation get() {
		if(controller.getList().size() == 5){
			controller.setUpGame();
			return new JsonRepresentation ( controller.getJson() ) ;
		}else{
			return new StringRepresentation ( "there is not enough user.Please wait" ) ;
		}
    }
}