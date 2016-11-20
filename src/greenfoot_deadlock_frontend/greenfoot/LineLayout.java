/**
 * Write a description of class LineLayout here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*; 
import java.util.*;

public class LineLayout implements Orientation
{
   public int getPositionXForPlayerAt(int i)
   {
       return 100 + 160 * i;
   }
    
   public int getPositionYForPlayerAt(int i)
   {
       return 300;
   }
    
}
