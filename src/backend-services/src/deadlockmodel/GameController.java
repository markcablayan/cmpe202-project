package deadlockmodel;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


import org.json.JSONArray;
import org.json.JSONObject;

public class GameController {
	
	private OrientationModel orientation;
	public static final int numberOfPlayers = 5;
	public static final ObjectHoldType[] objectHoldType = {ObjectHoldType.RED,ObjectHoldType.RED,
			ObjectHoldType.GREEN,ObjectHoldType.GREEN,
			ObjectHoldType.BLUE,null,
			ObjectHoldType.PURPLE,ObjectHoldType.PURPLE,
			ObjectHoldType.YELLOW,ObjectHoldType.YELLOW};
	private ArrayList<PlayerModel> players = new ArrayList<PlayerModel>();
	private boolean gameStart = false;
	private static GameController controller ;
	
	private GameController(){};
	
	public void startTimer()
	{
	}
	
	public static GameController getInstance(){
		if (controller == null) {
			controller = new GameController() ;
			controller.setOrientation(new OrientationModel());
			return controller ;
		}
		else {
			return controller ;
		}
	}
	
	
	public void setOrientation(OrientationModel orientation)
	{
		this.orientation = orientation;
	}
	
	
	public void addPlayer(PlayerModel player)
	{
		players.add(player);
	}
	
	public ArrayList<PlayerModel> getList()
	{
		return players;
	}
	

	static void shuffleArray(ObjectHoldType[] ar)
	  {
	    // If running on Java 6 or older, use `new Random()` on RHS here
	    Random rnd = ThreadLocalRandom.current();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      ObjectHoldType a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	  }
	
	public ArrayList<PlayerModel> setUpGame()
	{
		//prepareConfig();
		//we can setup more players. Currently only accept when we have 5 players
		if(!gameStart){
			shuffleArray(objectHoldType);
			for(int i = 0 ; i < numberOfPlayers; i++)
			{
				ObjectHoldType leftOHT = objectHoldType[2*i];
				ObjectHoldType rightOHT = objectHoldType[2*i+1];
				PlayerModel player = players.get(i);
				player.putObjectIntoHand(ObjectHoldModelFactory.generateGumball(leftOHT), HandType.LEFT);	
				player.putObjectIntoHand(ObjectHoldModelFactory.generateGumball(rightOHT), HandType.RIGHT);	
			}
			orientation.addPlayers(players);
			gameStart = true;
			return players;
		}
		else
		{
			return null;
		}
	}

	public OrientationModel getOrientation()
	{
		return orientation;
	}
	
	public boolean removeFromRoom(String username)
	{
		for(PlayerModel player:players)
		{
			if(player.getUsername().equalsIgnoreCase(username))
			{
				players.remove(player);
				return true;
			}
		}
		return false;
	}
	
	public boolean checkUniqueUsername(String username)
	{
		for(PlayerModel player : players)
		{
			if(username.equalsIgnoreCase(player.getUsername()))
				return false;
		}
		return true;
	}
	
	public boolean checkWinCondition()
	{
		if(gameStart){
			for(PlayerModel player: players){
				ObjectHoldModel leftHand = player.getGumball(HandType.LEFT);
				ObjectHoldModel rightHand = player.getGumball(HandType.RIGHT);
				if(leftHand==null || rightHand == null)
					continue;
				if(!leftHand.toString().equals(rightHand.toString()))
					return false;
			}
			return true;
		}else{
			return false;
		}
	}
	
	public void displayRoom()
	{
		System.out.println("\n");
		for(PlayerModel player: players)
		{
			player.display();
		}
		System.out.println("\n");
	}
	
	public JSONObject getJson()
	{
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
        json.put("orientation", controller.getOrientation().getConfig());
        json.put("win", String.valueOf(checkWinCondition()));
        return json;
	}
	
	public boolean isGameStarted()
	{
		return gameStart;
	}
	
	public void restartGame()
	{
		gameStart = false;
		players = new ArrayList<PlayerModel>();
		controller.setOrientation(new OrientationModel());
	}
	
	public void changeOrientation(String config)
	{
		orientation.changeConfig(config);
	}
}
