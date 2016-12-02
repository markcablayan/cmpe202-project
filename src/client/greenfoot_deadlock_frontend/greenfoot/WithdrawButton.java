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
public class WithdrawButton extends Button
{
    public void action()
    {
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
