package deadlockmodel;

public enum HandType  
{
    // instance variables - replace the example below with your own
    LEFT("left"),
    RIGHT("right");
    
    private String type;
       HandType(String type) 
       {
          this.type=type;
       }

       public String getType() 
       {
          return type;
       }
}
