package com.deadlock.playermodel;



public class CircularStrategy implements OrientationStrategy {
	
	@Override
	public void changeConfig(DoublyLinkedList<PlayerModel> list) {
		// TODO Auto-generated method stub
		list.makeACicular();
	}
}
