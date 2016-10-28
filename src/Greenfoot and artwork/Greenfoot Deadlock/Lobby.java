import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;
/**
 * Write a description of class Lobby here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lobby extends Actor
{
    HashMap<Integer, Room>  rooms;
    public Lobby(){
        rooms = new HashMap<Integer, Room>();
    }
    
    public boolean createRoom(int players, Orientation orientation){
        Room room = new Room(rooms.size(), players, orientation);
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
        // Add your action code here.
    }    
}
