import greenfoot.*;
import java.awt.Point;

public class DeadlockWorld extends World
{
    Button CreateRoomButton;
    Lobby lobby;
    public DeadlockWorld()
    {
        super(800, 600,1);
        prepare();
    }
    
    private void prepare()
    {
        lobby = new Lobby();
        CreateRoomButton = new Button("CreateRoom", new Point(100,25));
        addObject(lobby, getWidth()/2, getHeight()/2);
        addObject(CreateRoomButton, getWidth()*4/5, getHeight()/4);
    }  
    
    @Override
    public void act(){
        if(CreateRoomButton.wasClicked()){
            Greenfoot gf = new Greenfoot();
            String num_players = gf.ask("Enter number of players");
            String orientation = gf.ask("Enter orientation");
            //need to implement Orientation class
            lobby.createRoom(Integer.parseInt(num_players), new Linear());
            return;
        }
    }
}
