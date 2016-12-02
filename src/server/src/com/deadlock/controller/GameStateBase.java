package com.deadlock.controller;

import com.deadlock.playermodel.HandType;

public interface GameStateBase {
	boolean changeOrientation(String config);
	boolean addPlayer(String username);
	boolean removeFromRoom(String username);
	public void passGumballToNeighboor(int passingPlayerIndex, int passedPlayerIndex, HandType passingHand);
}
