/**
 * Write a description of class WaitingRoom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import org.json.* ;
import org.restlet.ext.json.* ;
import org.restlet.resource.ClientResource;
import org.restlet.representation.Representation ;
import org.restlet.ext.json.* ;
import org.restlet.data.* ;
import javax.swing.Timer;
import java.awt.event.*;
public class WaitingRoom  extends BackGround
{
   
    String username = "";
    int numberOfPlayers = 0;
    Orientation orientation = new LineLayout();
    public ChatWindow chatWindow = new ChatWindow();
         
   public WaitingRoom(String username)
   {
       //timer.start();
       this.username = username;
     
       addObject(new WithdrawButton(), 495,272);
       timer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               try{
                removeObjects(getObjects(WaitingPerson.class));
                ClientResource helloClientresource = new ClientResource( BackGround.SERVICE_URL+"/room" );
                ClientResource chatRestClient = new ClientResource( BackGround.SERVICE_URL + "/gumball/chat" ); 
                Representation result = helloClientresource.get() ;
                String respString = result.getText();
                if(Utils.isJSONValid(respString)){
                     JSONObject resp = new JSONObject(respString);
                     numberOfPlayers = resp.getInt("numberPlayers");
                    if(numberOfPlayers < 5){
                     for(int i=0;i< numberOfPlayers;i++)
                     {
                         addObject(new WaitingPerson(), orientation.getPositionXForPlayerAt(i), orientation.getPositionYForPlayerAt(i));
                     }
                     }else{
                         timer.stop();
                         Greenfoot.setWorld(new OrangeGameWorld());
                     }
                }
                if(chatWindow != null && chatWindow.isDisplayed()) {
                    Representation chatResult = chatRestClient.get();
                    if(result != null) {
                        JSONObject obj = new JSONObject("{\"message\":" + chatResult.getText() + "}");
                        String r = obj.getString("message");
                        String[] messages = r.split("\n");
                        if (messages.length > 6 ) {
                            r = "";
                            int strIndex = messages.length - 5;
                            for(int i = 0; i < 5; i++) {
                                r += messages[strIndex] + "\n";
                                strIndex++;
                                chatWindow.getMessageObject().setText(r);
                            }
                        }
                        else  {
                                chatWindow.getMessageObject().setText(r);
                            }                
                        }
                }
               }catch(Exception ex){System.out.println(ex);}
            }    
     });
   }
   
   public String getUsername()
   {
       return username;
    }
    
   public void act() {
        if (Greenfoot.isKeyDown("h")) {
            addObject(chatWindow.getMessageObject(), 900, 100);
            addObject(chatWindow,900, 170);
            chatWindow.setIsDisplayed(true);
        }
        if (Greenfoot.isKeyDown("r")) {
            removeObject(chatWindow);
            removeObject(chatWindow.getChatMessage());
            chatWindow.setIsDisplayed(false);
        }
    }
    
    
}
