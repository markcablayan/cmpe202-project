package deadlockmodel;

import java.util.*;

public interface OrientationModel  
{
    // instance variables - replace the example below with your own
    void addPlayers(ArrayList<PlayerModel> players);
    boolean checkingNeighborHood(PlayerModel player1, PlayerModel player2);
}
