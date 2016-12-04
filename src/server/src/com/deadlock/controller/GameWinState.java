package com.deadlock.controller;

import com.deadlock.playermodel.HandType;

public class GameWinState implements GameStateBase {
	GameController controller;
	
	public GameWinState(GameController controller)
	{
		this.controller = controller;
	}

	@Override
	public boolean changeOrientation(String config) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addPlayer(String username) {
		// TODO Auto-generated method stub
		controller.changeState(controller.getNotStatedState());
		return controller.getCurrentState().addPlayer(username);
	}

	@Override
	public void passGumballToNeighboor(int passingPlayerIndex, int passedPlayerIndex, HandType passingHand) {
		// TODO Auto-generated method stub
		return;
		
	}

	@Override
	public boolean removeFromRoom(String username) {
		// TODO Auto-generated method stub
		return false;
	}
}
