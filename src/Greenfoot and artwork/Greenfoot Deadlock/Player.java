import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    int id;
    int handExchange;
    ObjectHold assigned_object;
    PlayerObject[] playerObjects;
    
    public void act() 
    {
        // Add your action code here.
    }  
    
    public void initPlayerObjects(int player_object_l, int player_object_r)
    {
    }
    
    public void passToNeighbor(Player player, PlayerObject playerObject)
    {
    }
    
    public int getNumPlayerObjectInHand()
    {
        return 0;
    }
    
    public Player getNeighborWithEmptyHand()
    {
        return null;
    }

    public void sendMessage(String message)
    {
    }
    
    public int getId()
    {
        return id;
    }
    
    public ObjectHold getObject(int hand)
    {
        return null;
    }
    
    public void exchangeObject(int hand, Player player)
    {
    }
    
    public void setHandExchange(int hand)
    {
    }
    
    public void setAssignedObject(int object)
    {
    }
    
    public ObjectHold getAssignedObject()
    {
        return null;
    }
    
    
}
