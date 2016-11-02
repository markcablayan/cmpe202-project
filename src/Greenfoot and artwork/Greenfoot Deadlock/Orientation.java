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
    void addPlayersToMatrix(ArrayList<Player> players);
    boolean checkingNeighborhood(Player player1, Player player2);
}
