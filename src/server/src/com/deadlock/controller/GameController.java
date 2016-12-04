package com.deadlock.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.security.auth.callback.ChoiceCallback;
import javax.swing.Timer;

import org.json.JSONArray;
import org.json.JSONObject;

import com.deadlock.objectholdmodel.ObjectHoldModel;
import com.deadlock.objectholdmodel.ObjectHoldModelFactory;
import com.deadlock.objectholdmodel.ObjectHoldType;
import com.deadlock.playermodel.CircularStrategy;
import com.deadlock.playermodel.HandType;
import com.deadlock.playermodel.LineStrategy;
import com.deadlock.playermodel.OrientationModel;
import com.deadlock.playermodel.PlayerModel;
import com.deadlock.utils.Constants;

public class GameController {

	private OrientationModel orientation;
	
	private ArrayList<PlayerModel> players = new ArrayList<PlayerModel>();
	private static GameController controller ;
    private GameStateBase currentState;
    private GameStartedState gameStartedState;
    private GameWinState gameWinState;
    private NotStartedState notStartedState;
    private Timer timer;
	private GameController(){
		orientation = new OrientationModel();
		notStartedState = new NotStartedState(this);
		gameStartedState  = new GameStartedState(this);
		gameWinState = new GameWinState(this);
		currentState = notStartedState;
		timer = new Timer(Constants.TIMEOUT, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentState = notStartedState;
				timer.stop();
			}
		});
	};

	public GameStateBase getGameStartedState()
	{
		return gameStartedState;
	}
	
	public NotStartedState getNotStatedState()
	{
		return notStartedState;
	}
	
	public GameWinState getGameWinState()
	{
		return gameWinState;
	}
	
	public void changeState(GameStateBase state)
	{
		this.currentState = state;
	}
	
	public static GameController getInstance(){
		if (controller == null) {
			controller = new GameController() ;
			return controller ;
		}
		else {
			return controller ;
		}
	}
	
	public int getRoomCapacity()
	{
		return players.size();
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

	public void stopTimeOut()
	{
		timer.stop();
	}
	public void setUpGame()
	{
		shuffleArray(Constants.objectHoldType);
		for(int i = 0 ; i < Constants.numberOfPlayers; i++)
		{
			ObjectHoldType leftOHT = Constants.objectHoldType[2*i];
			ObjectHoldType rightOHT = Constants.objectHoldType[2*i+1];
			PlayerModel player = players.get(i);
			player.putObjectIntoHand(ObjectHoldModelFactory.generateGumball(leftOHT), HandType.LEFT);	
			player.putObjectIntoHand(ObjectHoldModelFactory.generateGumball(rightOHT), HandType.RIGHT);	
		}
		orientation.addPlayers(players);
		timer.start();
	}
   
	
	public boolean checkWinCondition()
	{
		if(currentState instanceof GameStartedState){
			for(PlayerModel player: players){
				ObjectHoldModel leftHand = player.getGumball(HandType.LEFT);
				ObjectHoldModel rightHand = player.getGumball(HandType.RIGHT);
				if(leftHand==null || rightHand == null)
					continue;
				if(!leftHand.toString().equals(rightHand.toString()))
					return false;
			}
			return true;
		}else if(currentState instanceof GameWinState){
			return true;
		}else{
			return false;
		}
	}

	


	public void restartGame()
	{
		players = new ArrayList<PlayerModel>();
		orientation = new OrientationModel();
		currentState = notStartedState;
	}
	
	public PlayerModel getNthPlayer(int index)
	{
		return players.get(index);
	}
	
	
	void changeStrategy(String config)
	{
		if(config.equals("LineStrategy"))
			orientation.changeStrategy(new LineStrategy());
		else if(config.equals("CircularStrategy"))
			orientation.changeStrategy(new CircularStrategy());
		orientation.changeConfig();
	}
	
	
	
	int checkUniqueUsername(String username)
	{
		int i = -1;
		for(PlayerModel player : players)
		{
			i++;
			if(username.equalsIgnoreCase(player.getUsername()))
				return i;
		}
		return -1;
	}
	
	void addToListPlayerModel(PlayerModel player){
		players.add(player);
	}
	
	
	void removePlayerAt(int index)
	{
		players.remove(index);
	}
	
	public OrientationModel getOrientation()
	{
		return orientation;
	}
	
	
	public GameStateBase getCurrentState()
	{
		return currentState;
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
		//ArrayList<PlayerModel> list = controller.getList();
		JSONArray listOfPlayers = new JSONArray();
		for(PlayerModel player:players)
		{
			String leftHand = (player.getGumball(HandType.LEFT)==null) ? "null" : player.getGumball(HandType.LEFT).getClass().getSimpleName();
			String rightHand =(player.getGumball(HandType.RIGHT)==null) ? "null" : player.getGumball(HandType.RIGHT).getClass().getSimpleName();
			JSONObject jsonPlayer = new JSONObject();
			jsonPlayer.put("lefthand", leftHand);
			jsonPlayer.put("righthand", rightHand);
			listOfPlayers.put(jsonPlayer);
		}
		json.put("players",listOfPlayers);
		json.put("orientation", orientation.getConfig());
		json.put("win", String.valueOf(checkWinCondition()));
		return json;
	}
		

}
