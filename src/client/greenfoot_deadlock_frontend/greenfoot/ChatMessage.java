import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class ChatMessage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ChatMessage extends Actor
{

    GreenfootImage gi, baseImg, textImg;
    int scroll;
    
    public ChatMessage() {
        gi = new GreenfootImage( 100, 50);
        setImage(gi);      
        gi.scale(200,100);
    }
    
    public void setText( String msg )
    {
        gi.clear();
        gi.setColor( java.awt.Color.WHITE ) ;
        gi.fill() ;
        gi.setColor( java.awt.Color.BLACK ) ;
        gi.drawString( msg, 10, 10 );  
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
