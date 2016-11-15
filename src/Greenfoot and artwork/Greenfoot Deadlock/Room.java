import greenfoot.*;
import java.util.*;
import java.awt.Point;

public class Room extends World
{
    HashMap<Integer, Player> players;
    HashMap<Integer, Message> messages;
    int size;
    Orientation orientation;
    int id;
    int playerTurn;
    int numOfMoves;
    Message numOfMoveMsg;
    Button button;
    Button goBackToLobbyButton;
    Lobby lobby;
    

    public Room(int id, int size, Orientation orientation, Lobby lobby){
        super(800,600,1);
        this.id = id;
        this.size = size;
        this.orientation = orientation;
        this.lobby = lobby;
        playerTurn = -1;
        numOfMoves = 0;
        players = new HashMap<Integer, Player>();
        messages = new HashMap<Integer, Message>();
        prepare();
        
    }
    
    public void prepare(){
        goBackToLobbyButton = new Button("Go Back To Lobby", new Point(200,25)); 
        addObject(goBackToLobbyButton, getWidth()*4/5, getHeight()/4);
        numOfMoveMsg = new Message();
        numOfMoveMsg.setMsg("Number of moves: " + numOfMoves);
        addObject(numOfMoveMsg, getWidth()*1/5, getHeight()/4);
        
        signUp();
        
        for(int i = 0 ; i < players.size() ; i ++){
            addObject(players.get(i), orientation.getPositionXForPlayerAt(i), 
                                                orientation.getPositionYForPlayerAt(i));
            addObject(messages.get(i), orientation.getPositionXForPlayerAt(i), 
                                                orientation.getPositionYForPlayerAt(i) + 75);
        }
    }
    
    public void signUp()
    {
        GumballType[] gumballTypes = {GumballType.RED,GumballType.GREEN,GumballType.BLUE
                                        ,GumballType.PURPLE,GumballType.YELLOW};
        
        int position = 2;
        for(int i = 0 ; i < 5; i++)
        {
            Player player = null;
            int playerID = players.size();
            if(i == position)
                player = new Player(playerID, gumballTypes[i],null);
            else
                player = new Player(playerID, gumballTypes[i],gumballTypes[i]); 
            Message message = new Message();
            message.setMsg(player.toString());
            messages.put(players.size(), message);
            players.put(players.size(), player);
        }
        orientation.addPlayers(players);
    }
    
    public void incrementNumOfMoves(){
        numOfMoves++;
    }

    public boolean checkingNeighborhood(Player player1, Player player2)
    {
        return orientation.checkingNeighborHood(player1, player2);
    }
    
    public void addToWorld(World world){
        button = new Button("Room: " + id + " Size: " + size + " Orientation: " + orientation.toString(), new Point(300,25));
        world.addObject(button, 150, 12+ 25*id);
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
        numOfMoves = 0;
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
            Gumball assigned_object = player.getAssignedObject();
            Gumball lh_object = player.getObject(0   );
            Gumball rh_object = player.getObject(1);
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
        numOfMoveMsg.setMsg("Number of moves: " + numOfMoves);
        if(goBackToLobbyButton.wasClicked()){
            Greenfoot.setWorld(lobby);
        }
    }
}
