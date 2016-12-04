import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.Timer;
import java.awt.event.*;
/**
 * Write a description of class setBackGround here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BackGround extends World
{

    /**
     * Constructor for objects of class setBackGround.
     * 
     */
    
    public static final String SERVICE_URL = "http://169.44.10.31:8080";
    private static final int TIME_WAIT = 1000;
    public Timer timer = new Timer(TIME_WAIT, null);
    
    public BackGround()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 700, 1);  
    }
    
    public void startTimer()
    {
        timer.start();
    }
    
    public void stopTimer()
    {
        timer.stop();
    }
}
