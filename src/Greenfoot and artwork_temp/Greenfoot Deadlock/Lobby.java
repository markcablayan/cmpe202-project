import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;
import java.awt.Point;
/**
 * Write a description of class Lobby here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lobby extends World
{
    Button CreateRoomButton;
    HashMap<Integer, Room>  rooms;
    public Lobby(){
        super(800,600,1);
        rooms = new HashMap<Integer, Room>();
        prepare();
    }
    
    public void prepare(){
        CreateRoomButton = new Button("CreateRoom", new Point(100,25)); 
        addObject(CreateRoomButton, getWidth()*4/5, getHeight()/4);
    }
    
    public boolean createRoom(int players, Orientation orientation){
        Room room = new Room(rooms.size(), players, orientation, this);
        rooms.put(rooms.size(), room);
        room.addToWorld(this);
        return true;
    }
    
    public boolean deleteRoom(int id){
        if(rooms.containsKey(id)){
            rooms.remove(id);
            return true;
        }
        return false;
    }
    /**
     * Act - do whatever the Lobby wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(CreateRoomButton.wasClicked()){
            Greenfoot gf = new Greenfoot();
            String num_players = gf.ask("Enter number of players");
            String orientation = gf.ask("Enter orientation");
            switch(orientation){
                case "linear":
                    createRoom(Integer.parseInt(num_players), new Linear());
                    break;
                default:
                    createRoom(Integer.parseInt(num_players), new Linear());
                }
            return;
        }
        
        for(Room room : rooms.values()){
            if(room.button.wasClicked()){
                Greenfoot.setWorld(room);
            }
        }
    }    
}
