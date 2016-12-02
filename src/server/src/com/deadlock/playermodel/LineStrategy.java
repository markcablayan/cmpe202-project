package com.deadlock.playermodel;


public class LineStrategy implements OrientationStrategy {

	@Override
	public void changeConfig(DoublyLinkedList<PlayerModel> list) {
		// TODO Auto-generated method stub
		list.makeAStraightLine();
	}
	
	
}
