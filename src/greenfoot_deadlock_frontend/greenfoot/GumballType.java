/**
 * Write a description of class GumballType here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum GumballType  
{
    // instance variables - replace the example below with your own
    RED("Red"),
    GREEN("Green"),
    BLUE("Blue"),
    YELLOW("Yellow"),
    PURPLE("Purple");
    
    private String type;
       GumballType(String type) 
       {
          this.type=type;
       }

       public String getType() 
       {
          return type;
       }
}
