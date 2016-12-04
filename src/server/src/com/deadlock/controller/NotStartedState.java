package com.deadlock.controller;

import com.deadlock.playermodel.HandType;
import com.deadlock.playermodel.PlayerModel;
import com.deadlock.utils.Constants;

public class NotStartedState implements GameStateBase {
	GameController controller;
	
	public NotStartedState(GameController controller)
	{
		this.controller = controller;
	}

	@Override
	public boolean changeOrientation(String config) {
		// TODO Auto-generated method stub
		System.out.println("The game has not started yet");
		return false;
	}

	@Override
	public boolean addPlayer(String username) {
		// TODO Auto-generated method stub
		if(controller.getRoomCapacity() == Constants.numberOfPlayers)
			controller.restartGame();
		int index = controller.checkUniqueUsername(username);
		if(index != -1)
			return false;
		controller.addToListPlayerModel(new PlayerModel(controller, username));
		if(controller.getRoomCapacity() == Constants.numberOfPlayers){
			controller.setUpGame();
			controller.changeState(controller.getGameStartedState());
		}
		return true;
	}

	@Override
	public void passGumballToNeighboor(int passingPlayerIndex, int passedPlayerIndex, HandType passingHand) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public boolean removeFromRoom(String username) {
		// TODO Auto-generated method stub
		int index = controller.checkUniqueUsername(username);
		if(index > -1){
			controller.removePlayerAt(index);
			return true;
		}
		return false;
	}

	

	
}
