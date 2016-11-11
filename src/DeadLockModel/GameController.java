package DeadLockModel;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameController {
	private Orientation orientation;
	int numberOfPlayers = 5;
	private ArrayList<PlayerModel> players = new ArrayList<PlayerModel>();
	
	public void setOrientation(Orientation orientation)
	{
		this.orientation = orientation;
	}
	
	
	public void addPlayer(PlayerModel player)
	{
		players.add(player);
	}
	
	
	private void prepareConfig()
	{
		System.out.println("Please choose the config:");
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		if(s.equals("L"))
			orientation = new StraightLineConfig(); 
	}

	public ArrayList<PlayerModel> setUpGame()
	{
		prepareConfig();
		//we can setup more players. Currently only accept when we have 5 players
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
		return players;
	}

	public Orientation getOrientation()
	{
		return orientation;
	}
	/*
	public void startGame()
	{
		this.setUpGame();
		this.displayRoom();
		int i = 0;
		while(!checkWinCondition()){
			i = i % 5;
			HandType hand = null;
			PlayerModel player = players.get(i);
			System.out.println("Please choose to pass gumball to player at:");
			Scanner scan = new Scanner(System.in);
			int j = scan.nextInt();
			System.out.println("Please choose a object in which hand you want to pass to neighbor");
			String s = scan.next();
			if(s.equals("L"))
				hand = HandType.LEFT;
			else
				hand = HandType.RIGHT;
			PlayerModel neighbor = players.get(j);
			player.passGumballToNeighboor(hand, neighbor);
			this.displayRoom();
			i++;
		}
	}
	*/
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
}
