package deadlockmodel;



public class CicularConfig extends OrientationModel {
	
	public boolean checkingNeighborHood(PlayerModel player1, PlayerModel player2) {
		if(player1 == player2 || player1 == null || player2 == null)
			return false;
		DoublyLinkedList.Node head = list.getHead().getNext();
		while(true)
		{
			PlayerModel player = (PlayerModel)head.getItem();
			if(player1 == player){
				//check to see if palyer is obe of my neighbors
				PlayerModel pre =(PlayerModel) head.getPrev().getItem();
				PlayerModel pos =(PlayerModel) head.getNext().getItem();
				if(player2 == pre) return true;
				else if(player2 == pos) return true;
				
			}
			head = head.getNext();
		}
	}

}
