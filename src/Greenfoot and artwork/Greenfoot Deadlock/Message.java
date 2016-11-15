import greenfoot.*;  
import java.awt.Color; 


public class Message extends Actor
{
    private String textDisplay;
    
    public void act()
    {
        if(textDisplay.equals("")){
            getWorld().removeObject(this);
        }
        else{
            setImage(new GreenfootImage(textDisplay, 20 , Color.BLACK, Color.YELLOW));
        }
        
        clickOnMsg();
    }

    
    public void clickOnMsg()
    {
        if(Greenfoot.mouseClicked(this)){
            textDisplay = "";
        }
    }
    
    public void setMsg(String text)
    {
        textDisplay = text;
    }
}