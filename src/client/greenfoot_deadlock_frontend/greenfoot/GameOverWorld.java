import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOverWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverWorld extends BackGround
{

    /**
     * Constructor for objects of class GameOverWorld.
     * 
     */
    public GameOverWorld()
    {
        addObject(new GameOver(), 527, 269);
        addObject(new PlayAgain(), 527, 337);
    }
}
