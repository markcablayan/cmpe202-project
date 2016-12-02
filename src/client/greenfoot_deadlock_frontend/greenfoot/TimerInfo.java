import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.Color;
/**
 * Write a description of class TimerInfo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TimerInfo extends SetInfo
{
    /**
     * Act - do whatever the TimerInfo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int count; 
    private Timer timer;
    
    public TimerInfo(int duration, boolean started)
    {
        this.count = duration;
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              count--;
              if(count == 0){
                timer.stop();
                ((BackGround)getWorld()).timer.stop();
                Greenfoot.setWorld(new GameOverWorld());
              }
            }    
      });
      if(started)
        timer.start();
    }
    
    public void act() 
    {
        // Add your action code here.
        updateImage();
    } 
    
    
    public void updateImage()
    {
        int time = count ;
        int hrs = time / 3600;
        int mins = (time % 3600) / 60;
        int secs = time % 3600 % 60 ;
        
        String h = "00"+hrs;
        while (h.length() > 2) h = h.substring(1);
        String m = "00"+mins;
        while (m.length() > 2) m = m.substring(1);
        String s = "00" + secs;
        while (s.length() > 2) s = s.substring(1);
        String text =  h + "h : " + m + "m : " + s + "s";
        GreenfootImage textImage;
        if(mins == 0)
            textImage = new GreenfootImage(text, 20, Color.red, new Color(0, 0, 0, 0));
        else
            textImage = new GreenfootImage(text, 20, Color.white, new Color(0, 0, 0, 0));
        GreenfootImage image = new GreenfootImage(textImage.getWidth()+20, textImage.getHeight()+10);
        image.drawRect(0, 0, image.getWidth()-1, image.getHeight()-1);
        image.drawImage(textImage, (image.getWidth()-textImage.getWidth())/2, (image.getHeight()-textImage.getHeight())/2);
        setImage(image);
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
