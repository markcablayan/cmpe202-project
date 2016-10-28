import greenfoot.*;

public class DeadlockWorld extends World
{
	public DeadlockWorld()
	{
		super(800, 600,1);
		prepare();
	}
	
	private void prepare()
	{
	    Lobby lobby = new Lobby();
	    addObject(lobby, getHeight()/2, getWidth()/2);
	}
}
