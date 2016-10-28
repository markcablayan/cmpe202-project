/**
 * Write a description of class ObjectHoldFactory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ObjectHoldFactory  
{
    public static ObjectHold generateObjectHold(ObjectHoldType objectType)
    {
        switch(objectType){
            case RED: return new RedGumball();
            case BLUE: return new BlueGumball();
            case GREEN: return new GreenGumball();
            case PURPLE: return new PurpleGumball();
            case YELLOW: return new YellowGumball();
        }
        return null;
    }
}
