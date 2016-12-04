package com.deadlock.objectholdmodel;

public class ObjectHoldModelFactory  
{
    public static ObjectHoldModel generateGumball(ObjectHoldType GumballType)
    {
        if(GumballType == null)
        	return null;
    	switch(GumballType){
            case RED: return new RedGumballModel();
            case BLUE: return new BlueGumballModel();
            case GREEN: return new GreenGumballModel();
            case PURPLE: return new PurpleGumballModel();
            case YELLOW: return new YellowGumballModel();
        }
    	return null;
    }
}
