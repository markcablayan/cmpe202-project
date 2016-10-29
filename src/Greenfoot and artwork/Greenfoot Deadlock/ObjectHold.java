import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ObjectHold here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ObjectHold extends Actor
{   
    public ObjectHold()
    {
        GreenfootImage image = getImage() ;
        image.scale( 30, 30 ) ; 
    }
    
    
    
    public String toString(){
        return "Object";
    }
}
