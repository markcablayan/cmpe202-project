package com.deadlock.utils;

import com.deadlock.objectholdmodel.ObjectHoldType;

public class Constants {
	public static final int numberOfPlayers = 5;
	public static final int TIMEOUT = 300000;
	public static final ObjectHoldType[] objectHoldType = {ObjectHoldType.RED,ObjectHoldType.RED,
			ObjectHoldType.GREEN,ObjectHoldType.GREEN,
			ObjectHoldType.BLUE,null,
			ObjectHoldType.PURPLE,ObjectHoldType.PURPLE,
			ObjectHoldType.YELLOW,ObjectHoldType.YELLOW};
}
