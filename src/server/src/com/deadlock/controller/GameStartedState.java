package com.deadlock.controller;

import com.deadlock.playermodel.HandType;

public class GameStartedState implements GameStateBase{
	GameController controller;
	
	public GameStartedState(GameController controller)
	{
		this.controller = controller;
	}

	@Override
	public boolean changeOrientation(String config) {
		// TODO Auto-generated method stub
		controller.changeStrategy(config);
		return true;
	}

	@Override
	public boolean addPlayer(String username) {
		// TODO Auto-generated method stub
		System.out.println("Game already starts. Cannot add more users!!!");
		return false;
	}

	@Override
	public void passGumballToNeighboor(int passingPlayerIndex, int passedPlayerIndex, HandType passingHand) {
		// TODO Auto-generated method stub
		controller.getNthPlayer(passingPlayerIndex).passGumballToNeighboor(passingHand,controller.getNthPlayer(passedPlayerIndex) );
		if(controller.checkWinCondition()){
			controller.stopTimeOut();
			controller.changeState(controller.getGameWinState());
		}
	}

	@Override
	public boolean removeFromRoom(String username) {
		// TODO Auto-generated method stub
		return false;
	}
}
