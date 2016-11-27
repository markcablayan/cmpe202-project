import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import org.json.* ;
import org.restlet.ext.json.* ;
import org.restlet.resource.ClientResource;
import org.restlet.representation.Representation ;
import javax.swing.Timer;
import java.awt.event.*;
import org.restlet.ext.json.* ;
import org.restlet.data.* ;

/**
 * Write a description of class OrangeGameWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OrangeGameWorld extends BackGround
{
    
    private Orientation orientation;
    GreenfootSound backgroundMusic = new GreenfootSound("../artwork/escape.mp3");
     
    public OrangeGameWorld()
    {
      timer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               signUp();
            }    
      });
      timer.start();
      // backgroundMusic.playLoop();
      // addObject(new Timer(60, true),150,100);
    }
    
    
    
    public void signUp()
    {
        try{
                    ClientResource helloClientresource = new ClientResource( BackGround.SERVICE_URL +"/gumball" ); 
                    Representation result = helloClientresource.get() ;
                    String resp = result.getText();
                    if(!Utils.isJSONValid(resp)){
                        restartGame();
                    }else{
                        JSONObject respObj = new JSONObject(resp);
                        String isWin = respObj.getString("win");
                        if(isWin.equals("true")){
                            restartGame();
                        }else{
                            String config = respObj.getString("orientation");
                            changeConfigUI(config);
                        }
                        redraw(respObj);
                    }
               }catch(Exception ex){System.out.println(ex);}
    }
    
    
    public void redraw(JSONObject json)
    {
       removeObjects(getObjects(Player.class));
       removeObjects(getObjects(Gumball.class));
       JSONArray list = json.getJSONArray("players");
        for(int i = 0; i <list.length();i++)
        {
            JSONObject playerJson = list.getJSONObject(i);
            GumballType leftHand = choose(playerJson.getString("lefthand"));
            GumballType rightHand = choose(playerJson.getString("righthand"));
            Player player = new Player(leftHand,rightHand,i); 
            addObject(player, orientation.getPositionXForPlayerAt(i), 
                                        orientation.getPositionYForPlayerAt(i));
        }
       
    }

    private GumballType choose(String type)
    {
        switch(type)
        {
            case "YellowGumballModel": return GumballType.YELLOW;
            case "RedGumballModel": return GumballType.RED;
            case "GreenGumballModel": return GumballType.GREEN;
            case "BlueGumballModel": return GumballType.BLUE;
            case "PurpleGumballModel": return GumballType.PURPLE;
            case "null": return null;
        }
        return null;
    }
    
    public void restartGame()
    {
        timer.stop();
        removeObjects(getObjects(Actor.class));
        Message msg = new Message();
        msg.setText("You win!!");
        addObject(msg, 100, 200);
    }
    
    public void changeConfigUI(String config){
        if(config.equals("circular")){
                orientation = new CircularLayout();
                setBackground(new GreenfootImage("../artwork/darkspace.png"));
        }
        else{
            orientation = new LineLayout();
            setBackground(new GreenfootImage("../artwork/escaperoom.png"));
        }
    }
    

    public void act()
    {
        if (Greenfoot.isKeyDown ("c"))  {
            //send a put request to the server to update config
            ClientResource helloClientresource = new ClientResource( BackGround.SERVICE_URL+ "/config" ); 
            JSONObject object = new JSONObject();
            String config = "";
            if(orientation instanceof LineLayout){
                config = "circular";
            }
            else if(orientation instanceof CircularLayout){
                config = "line";        
            }
            object.put("config", config);
            helloClientresource.put(object, MediaType.APPLICATION_JSON);
            changeConfigUI(config);
            signUp();
            try{
                Thread.sleep(500);
            }catch(Exception ex){}
        }
    }
}
