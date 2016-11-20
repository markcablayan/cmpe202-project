import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import org.json.* ;
import org.restlet.ext.json.* ;
import org.restlet.resource.ClientResource;
import org.restlet.representation.Representation ;

/**
 * Write a description of class OrangeGameWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OrangeGameWorld extends World
{

    /**
     * Constructor for objects of class OrangeGameWorld.
     * 
     */
    private final String service_url = "http://localhost:8080/gumball" ;
    private Orientation orientation;

    public OrangeGameWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
       super(800, 600, 1); 
       orientation = new LineLayout();
       signUp();
       
       addObject(new Timer(60, true),150,100);
        //orientation.drawLayout();
    }
    
    public void signUp()
    {
        
        try {
                ClientResource helloClientresource = new ClientResource( service_url ); 
                Representation result = helloClientresource.get() ; 
                
                JSONObject json = new JSONObject(result.getText());
                redraw(json);
                
                
        } catch ( Exception e ) {
            
        }
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
        removeObjects(getObjects(Actor.class));
        Message msg = new Message();
        msg.setText("You win!!");
        addObject(msg, 100, 200);
    }
}
