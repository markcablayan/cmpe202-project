import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Gumball extends Actor
{
    private Actor owner;
    
    public Gumball()
    {
        GreenfootImage image = getImage() ;
        image.scale( 30, 30 ) ; 
    }
    
    
    public void setOwner(Actor owner)
    {
        this.owner = owner;
    }
    
    public void act() 
    {
        int mouseX = 0, mouseY = 0 ;
        MouseInfo mouse = Greenfoot.getMouseInfo();  
        if(Greenfoot.mouseDragged(this)) {          
            mouseX=mouse.getX();  
            mouseY=mouse.getY();  
            setLocation(mouseX, mouseY);  
        } 
        
        if(Greenfoot.mouseDragEnded(this)){
            //check to see if Gumball drag ends in its neighbor then we will reset
            //new owner and position for Gumball
            Player ownerNeighbor = (Player)getOneIntersectingObject(Player.class);
            HandType ownerHand = null;
            if(ownerNeighbor != null && ownerNeighbor != (Player)this.owner){
                if(((Player)owner).isMyNeighbor(ownerNeighbor)){
                    HandType neighborHand = ownerNeighbor.whichHandAvailable();
                    if(neighborHand!=null){
                        ownerNeighbor.putObjectIntoHand(this, neighborHand); 
                        ownerHand = ((Player)owner).isObjectBelongTo(this);
                        ((Player)owner).releaseObjFromHand(ownerHand); 
                        this.owner = ownerNeighbor;
                        ((Room) getWorld()).incrementNumOfMoves();
                    }
                }
                
            }
            
            //set position for the object into either owner's left or right hand
            ownerHand = ((Player)owner).isObjectBelongTo(this);
            switch(ownerHand){
                case LEFT:  {mouseX = owner.getX()-50; mouseY = owner.getY()+50; break; }
                case RIGHT: {mouseX = owner.getX()+50; mouseY = owner.getY()+50; break; }
            }
            setLocation(mouseX,mouseY);
        }
        // Add your action code here.
    }
    
    
    
    public String toString(){
        return "Gumball";
    }
}
