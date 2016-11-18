package deadlockmodel;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;


public class GameController {
	private Orientation orientation;
	int numberOfPlayers = 5;
	private ArrayList<PlayerModel> players = new ArrayList<PlayerModel>();
	private boolean gameStart = false;
	private static GameController controller ;

	private GameController(){};

	public static GameController getInstance(){
		if (controller == null) {
			controller = new GameController() ;
			controller.setOrientation(new StraightLineConfig());
			return controller ;
		}
		else {
			return controller ;
		}
	}


	public void setOrientation(Orientation orientation)
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
	private void prepareConfig()
	{
		orientation = new StraightLineConfig();
	}

	public ArrayList<PlayerModel> setUpGame()
	{
		//prepareConfig();
		//we can setup more players. Currently only accept when we have 5 players
		if(!gameStart){
			ObjectHoldType[] objectHoldType = {ObjectHoldType.RED,ObjectHoldType.GREEN,ObjectHoldType.BLUE
					,ObjectHoldType.PURPLE,ObjectHoldType.YELLOW};

			int position = 2;
			for(int i = 0 ; i < numberOfPlayers; i++)
			{
				PlayerModel player = players.get(i);
				Random rand = new Random();

				int  n1 = rand.nextInt(objectHoldType.length);
				int  n2 = rand.nextInt(objectHoldType.length);


				if(i == position){
					player.putObjectIntoHand(ObjectHoldModelFactory.generateGumball(objectHoldType[n1]), HandType.LEFT);
				}
				else{
					player.putObjectIntoHand(ObjectHoldModelFactory.generateGumball(objectHoldType[n1]), HandType.LEFT);
					player.putObjectIntoHand(ObjectHoldModelFactory.generateGumball(objectHoldType[n2]), HandType.RIGHT);
				}
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

	public Orientation getOrientation()
	{
		return orientation;
	}

	public boolean checkWinCondition()
	{
		for(PlayerModel player: players){
			ObjectHoldModel leftHand = player.getGumball(HandType.LEFT);
			ObjectHoldModel rightHand = player.getGumball(HandType.RIGHT);
			if(leftHand==null || rightHand == null)
				continue;
			if(!leftHand.toString().equals(rightHand.toString()))
				return false;
		}
		return true;
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
		json.put("orientation", controller.getOrientation().getClass().getSimpleName());
		return json;
	}

	public boolean isGameStarted()
	{
		return gameStart;
	}
}
