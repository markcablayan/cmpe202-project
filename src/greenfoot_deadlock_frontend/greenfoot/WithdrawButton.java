import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import org.json.* ;
import org.restlet.ext.json.* ;
import org.restlet.resource.ClientResource;
import org.restlet.representation.Representation ;
import javax.swing.*;
import org.restlet.ext.json.* ;
import org.restlet.data.* ;

/**
 * Write a description of class WithdrawButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WithdrawButton extends Actor
{
    
    /**
     * Act - do whatever the WithdrawButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mousePressed(this)){
            try{
                ClientResource helloClientresource = new ClientResource( BackGround.SERVICE_URL +"/room" ); 
                JSONObject object = new JSONObject();
                object.put("username", ((WaitingRoom)getWorld()).getUsername());
                Representation result = helloClientresource.put(object, MediaType.APPLICATION_JSON);
                String respObj = result.getText();
                if(Utils.isJSONValid(respObj)){
                    ((WaitingRoom)getWorld()).stopTimer();
                    Greenfoot.setWorld(new Starter());
                }else{
                    JFrame frame = new JFrame("Information");
                    JOptionPane.showMessageDialog(frame,respObj);
                }
            }catch(Exception ex){}
        }
    }    
}
