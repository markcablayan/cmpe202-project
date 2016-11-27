package deadlockmodel;

import java.util.*;

public class OrientationModel  
{
	protected DoublyLinkedList<PlayerModel> list;
	private String config = "line";
	public OrientationModel()
	{
		list = new DoublyLinkedList<PlayerModel>();
		
	}
	public void addPlayers(ArrayList<PlayerModel> players){
		for(PlayerModel player : players)
		{
			list.add(player);
		}
	}
    
	public void changeConfig(String conf)
	{
		if(conf.equals("line")){
			list.makeAStraightLine();
			config = conf;
		}
		else if(conf.endsWith("circular")){
			list.makeACicular();
			config =conf;
		}
	}
	
	public String getConfig()
	{
		return config;
	}
	
	public boolean checkingNeighborHood(PlayerModel player1, PlayerModel player2)
	{
		if(player1 == player2 || player1 == null || player2 == null)
			return false;
		DoublyLinkedList.Node head = list.getHead().getNext();
		while(head !=null)
		{
			PlayerModel player = (PlayerModel)head.getItem();
			if(player1 == player){
				//check to see if palyer is obe of my neighbors
				PlayerModel pre=null,pos=null;
				if(head.getPrev()!=null)
					pre =(PlayerModel) head.getPrev().getItem();
				if(head.getNext()!=null)
					pos =(PlayerModel) head.getNext().getItem();
				if(player2 == pre) return true;
				else if(player2 == pos) return true;
				else return false;
			}
			head = head.getNext();
		}
		return false;
	}
}
