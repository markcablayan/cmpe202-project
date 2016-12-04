import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.*;
import java.util.*;
import org.json.* ;
import org.restlet.ext.json.* ;
import org.restlet.resource.ClientResource;
import org.restlet.representation.Representation ;
import org.restlet.representation.* ;
import org.restlet.ext.json.* ;
import org.restlet.data.* ;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Write a description of class ChatWindow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ChatWindow extends Actor
{
    /**
     * Act - do whatever the ChatWindow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public ChatMessage msg;
    private boolean isDisplayed = false;
    
    public ChatWindow() {
        msg = new ChatMessage();
        msg.setText("");
    }
    
    public void act() 
    {
        // Add your action code here.
        if(Greenfoot.mousePressed(this)) {
            ClientResource helloClientresource = new ClientResource( BackGround.SERVICE_URL + "/gumball/chat" ); 
            JFrame frame = new JFrame("Chat Message");
            String message = JOptionPane.showInputDialog(frame, "Enter your message: ");
            JSONObject object = new JSONObject();
            object.put("message", message);
            Representation result = helloClientresource.put(object, MediaType.APPLICATION_JSON);
        }
    }
    
    public ChatMessage getMessageObject() {
        return this.msg;
    }
    
    public void setIsDisplayed(boolean isDisplayed) {
        this.isDisplayed = isDisplayed;
    }
    
    public boolean isDisplayed() {
        return this.isDisplayed;
    }
    
    public ChatMessage getChatMessage() {
        return this.msg; 
    }
}
