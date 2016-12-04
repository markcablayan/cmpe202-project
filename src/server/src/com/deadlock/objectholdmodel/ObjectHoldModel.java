package com.deadlock.objectholdmodel;

import com.deadlock.playermodel.PlayerModel;

public class ObjectHoldModel {
    private PlayerModel owner;
    
    public void setOwner(PlayerModel owner)
    {
        this.owner = owner;
    }
    
    public String toString(){
        return "Gumball";
    }
    
    public PlayerModel getOwner()
    {
    	return owner;
    }
}
