import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import org.json.JSONObject ;
import org.restlet.resource.*;
import org.restlet.representation.* ;
import org.restlet.ext.json.* ;
import org.restlet.data.* ;

/**
 * Write a description of class Gumball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gumball extends Actor
{
    private Player owner;
    private final String service_url = "http://localhost:8080/gumball" ;
    
    
    public Gumball()
    {
        GreenfootImage image = getImage() ;
        image.scale( 30, 30 ) ; 
    }
    
    
    public void setOwner(Player owner)
    {
        this.owner = owner;
    }
    
    public void act() 
    {
        int mouseX = 0, mouseY = 0 ;
        MouseInfo mouse = Greenfoot.getMouseInfo();  
        if(Greenfoot.mouseDragged(this)) {          
            mouseX=mouse.getX();  
            mouseY=mouse.getY();  
            setLocation(mouseX, mouseY);  
        } 
        
        if(Greenfoot.mouseDragEnded(this)){
            Player ownerNeighbor = (Player)getOneIntersectingObject(Player.class);
            HandType ownerHand = null;
            ClientResource helloClientresource = new ClientResource( service_url ); 
            JSONObject object = new JSONObject();
            ownerHand = owner.isObjectBelongTo(this);
            object.put("handType", ownerHand.getType());
            object.put("passingPlayer",((Player) owner).myPositionInGameRoom());
            object.put("passedPlayer",ownerNeighbor.myPositionInGameRoom());
            Representation result= helloClientresource.put(object, MediaType.APPLICATION_JSON);
            try{
                JSONObject json = new JSONObject(result.getText());
                //checking the win condition
                String isWin = json.getString("win");
                if(isWin.equals("true")){
                    ((OrangeGameWorld)getWorld()).restartGame();
                }
                else{
                    JSONObject players = new JSONObject(json.getString("players"));
                    ((OrangeGameWorld)getWorld()).redraw(players);
                }
            }catch(Exception ex){}
           
        }
        
    }
    
    public String toString(){
        return "Gumball";
    }
}
