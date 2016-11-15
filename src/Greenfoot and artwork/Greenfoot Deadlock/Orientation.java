/**
 * Write a description of class Orientation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*;
import java.util.*;
public interface Orientation  
{
    void addPlayers(HashMap<Integer, Player> players);
    boolean checkingNeighborHood(Player player1, Player player2);
    int getPositionXForPlayerAt(int i);
    int getPositionYForPlayerAt(int i);
}
