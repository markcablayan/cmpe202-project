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
    private Gumball leftHandObj;
    private Gumball rightHandObj;
    private Gumball[] gumballs = new Gumball[2];
    
    public Player(int id, GumballType leftHandObjectType, GumballType rightHandObjectType)
    {
        this.id = id;
        GreenfootImage image = getImage() ;
        image.scale( 100, 130 ) ;
        
        if(leftHandObjectType != null){
            leftHandObj = GumballFactory.generateGumball(leftHandObjectType);
            leftHandObj.setOwner(this);
        }
        if(rightHandObjectType != null){
             rightHandObj = GumballFactory.generateGumball(rightHandObjectType);
             rightHandObj.setOwner(this);
        }
    }
    
    public void act() 
    {
        World myWorld = getWorld();
        if(leftHandObj != null){
            myWorld.addObject(leftHandObj, getX()-50, getY()+50);
        }
        if(rightHandObj != null){
            myWorld.addObject(rightHandObj, getX()+50, getY()+50);
        }
    }  
    
    public boolean isMyNeighbor(Player player){
        World mW = getWorld();
        Room roomWorld = (Room) mW;
        return roomWorld.checkingNeighborhood(this, player);
    }
    
    public HandType whichHandAvailable()
    {
        if(leftHandObj == null)
            return HandType.LEFT;
        if(rightHandObj == null)
            return HandType.RIGHT;
        return null;
    }
    
    public void releaseObjFromHand(HandType hand)
    {
        switch(hand)
        {
            case LEFT:  leftHandObj = null;break;
            case RIGHT: rightHandObj = null;break;
        }
    }
    
    public void putObjectIntoHand(Gumball object, HandType hand)
    {
        switch(hand)
        {
            case LEFT:  leftHandObj = object;break;
            case RIGHT: rightHandObj = object;break;
        }
    }
    
    public HandType isObjectBelongTo(Gumball obj)
    {
        if(obj == leftHandObj)
            return HandType.LEFT;
        else if(obj == rightHandObj)
            return HandType.RIGHT;
        return null;
    }
    
    public String toString(){
        return "Player " + id;
    }
    
    public void initPlayerObjects(int player_object_l, int player_object_r)
    {
    }
    
    public void passToNeighbor(Player player, Gumball gumball)
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
    
    public Gumball getObject(int hand)
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
    
    public Gumball getAssignedObject()
    {
        return null;
    }
    
    
}
