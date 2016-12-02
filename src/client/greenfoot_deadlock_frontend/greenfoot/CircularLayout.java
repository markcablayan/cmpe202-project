/**
 * Write a description of class LineLayout here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*; 
import java.util.*;

public class CircularLayout implements Orientation
{
   private class Point{
       int x;int y;
       public Point(int x,int y)
       {
           this.x = x;
           this.y = y;
       }
       
       public int getX()
       {
           return x;
       }
       
       public int getY()
       {
           return y;
       }
   }
   
   public final Point[] circularPos = {new Point(505,223),new Point(653,343),
                                              new Point(610,507),new Point(396,501),
                                              new Point(347,335)};
   public int getPositionXForPlayerAt(int i)
   {
       return circularPos[i].getX();
   }
    
   public int getPositionYForPlayerAt(int i)
   {
        return circularPos[i].getY();
   }
    
}
