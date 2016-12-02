import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    // 0 index will be left, 1 index will be right
    private Gumball leftHandObj;
    private Gumball rightHandObj;
    private int position; //Position of a player in a game room
    
    public Player(GumballType leftHandObjectType, GumballType rightHandObjectType,int pos)
    {
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
        
        position = pos;
    }
   
    public void act() 
    {
        // Add your action code here
        World myWorld = getWorld();
        if(leftHandObj != null){
            myWorld.addObject(leftHandObj, getX()-50, getY()+50);
        }
        if(rightHandObj != null){
            myWorld.addObject(rightHandObj, getX()+50, getY()+50);
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
    
    public void assingGumball(HandType hand, Gumball gumball)
    {
        if(hand == HandType.LEFT)
        {
            leftHandObj = gumball;
        }
        else if(hand == HandType.RIGHT){
            rightHandObj = gumball;
        }
    }
    public int myPositionInGameRoom()
    {
        return position;
    }
}
