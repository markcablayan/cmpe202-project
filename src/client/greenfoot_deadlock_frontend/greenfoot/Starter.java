import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Starter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Starter extends BackGround
{

    /**
     * Constructor for objects of class Starter.
     * 
     */
    private static ChatWindow chatWindow = new ChatWindow();
   
    public Starter()
    {
        prepare();
    }
    
    public void prepare()
    {
        addObject(new PlayerButton(), 554, 537);
        addObject(new Logo(),556,357);
    }
    
    public void act() {
      
    }
}

