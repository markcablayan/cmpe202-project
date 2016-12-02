import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Button extends Actor
{
    
    public abstract void action();
    
    public void act() 
    {
        if(Greenfoot.mousePressed(this)){
            action();
        }
    }    
}
