import greenfoot.*;
import java.util.*;
import java.awt.Point;

public class Room extends Actor
{
    HashMap<Integer, Player> players;
    int size;
    Orientation orientation;
    int id;
    int playerTurn;
    int numOfMove;
    Button button;

    public Room(int id, int size, Orientation orientation){
        this.id = id;
        this.size = size;
        this.orientation = orientation;
        playerTurn = -1;
        numOfMove = 0;
        players = new HashMap<Integer, Player>();
        
    }

    public void addToWorld(World world){
        button = new Button("Room: " + id + " Size: " + size + " Orientation: ", new Point(200,25));
        world.addObject(button, 100, 12+ 25*id);
    }
    
    public boolean startGame(){
        if(checkNumberOfPlayers() == getSize()){
            assignObjects();
            distributeObjects();
            setPlayerTurn();
            return true;
        }
        return false;
    }

    public void restartGame(){
        playerTurn = -1;
        numOfMove = 0;
    }

    public boolean endGame(){
        //Todo
        return true;
    }
    
    private int checkNumberOfPlayers(){
        return players.size();
    }
    
    private int distributeObjects(){
        //generate random objects to be placed on the players' hands
        Integer[] left_hands = new Integer[size];
        Integer[] right_hands = new Integer[size];
        for(int i = 0; i < size; i++){
            left_hands[i] = i;
            right_hands[i] = i;
        }
        //randomize the arrays
        Collections.shuffle(Arrays.asList(left_hands));
        Collections.shuffle(Arrays.asList(right_hands));
        //iterate through the values in the hashmap
        int i = 0;
        for(Player player : players.values()){
            if(i >= left_hands.length || i >= right_hands.length){
                break;
            }
            player.initPlayerObjects(left_hands[i], right_hands[i]);
            i++;
        }
        
        return i;
    }

    private void assignObjects(){
        Integer[] objects = new Integer[size];
        for(int i = 0; i < size; i++){
            objects[i] = i;
        }
        Collections.shuffle(Arrays.asList(objects));
        {int i = 0;
            for(Player player : players.values()){
                if(i >= objects.length)
                    break;
                player.setAssignedObject(objects[i]);
                i++;
            }
        }
    }

    public int getID(){
        return id;
    }

    public int getSize(){
        return size;
    }

    public Orientation getOrientation(){
        return orientation;
    }

    public HashMap<Integer, Player> getPlayers(){
        return players;
    }

    public boolean addPlayer(Integer id, Player player){
        if(players.size() < size){
            players.put(id, player);
            return true;
        }
        return false;
    }

    public boolean removePlayer(Integer id){
        if(players.containsKey(id)){
            players.remove(id);
            return true;
        }   
        return false;
    }

    public boolean checkWinCondition(){
        for(Player player: players.values()){
            ObjectHold assigned_object = player.getAssignedObject();
            ObjectHold lh_object = player.getObject(0   );
            ObjectHold rh_object = player.getObject(1);
            //if a player does not have the assigned object in their hands, return false
            if(!(lh_object == assigned_object || lh_object == null) && (rh_object == assigned_object || rh_object == null)){
                return false;
            }
        }
        return true;
    }
    
    public int getPlayerTurn(){
        return playerTurn;
    }

    public int setPlayerTurn(){
        for(Player player: players.values()){
            if(player.getObject(0) == null || player.getObject(1) == null){
                return player.getId();
            }
        }
        return -1;
    }
    
    @Override
    public void act()
    {
        /*
       List<Button> button_list = getWorld().getObjects(Button.class);
       for(Button b : button_list){
           if( b == button){
               return;
            }
       }
       */
      if(button.wasClicked()){
          RoomWorld rw = new RoomWorld();
          Greenfoot.setWorld(rw);
      }

    }
}
