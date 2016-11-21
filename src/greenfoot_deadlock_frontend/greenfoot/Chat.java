import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class Message here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Chat extends Actor
{
    public Chat() {
        updateImage("");
    }
    
    
    public Chat(String text) {
        updateImage(text);
    }
    
    public void setText(String text) {
        updateImage(text);
    }
    
    private void updateImage(String text) {
        setImage(new GreenfootImage(text, 12, Color.black, Color.white));
    }
    
    /**
     * Act - do whatever the Message wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
