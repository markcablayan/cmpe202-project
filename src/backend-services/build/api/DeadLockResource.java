import java.util.ArrayList;

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
	
	@Get
    public Representation get() {
		if(controller.getList().size() == 5){
			controller.setUpGame();
			JSONObject json = new JSONObject() ;
			ArrayList<PlayerModel> list = controller.getList();
	    	JSONArray listOfPlayers = new JSONArray();
	    	for(PlayerModel player:list)
	    	{
	    		String leftHand = (player.getGumball(HandType.LEFT)==null) ? "null" : player.getGumball(HandType.LEFT).getClass().getSimpleName();
	    		String rightHand =(player.getGumball(HandType.RIGHT)==null) ? "null" : player.getGumball(HandType.RIGHT).getClass().getSimpleName();
	    		JSONObject jsonPlayer = new JSONObject();
	    		jsonPlayer.put("lefthand", leftHand);
	    		jsonPlayer.put("righthand", rightHand);
	    		listOfPlayers.put(jsonPlayer);
	    	}
	    	json.put("players",listOfPlayers);
	        json.put("orientation", controller.getOrientation().getClass().getSimpleName());
	        return new JsonRepresentation ( json ) ;
		}else{
			return new StringRepresentation ( "there is not enough user.Please wait" ) ;
		}
    }
}

