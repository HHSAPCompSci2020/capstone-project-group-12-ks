
public class EnemyRoom extends Room {
	
	private int enemiesLeft;

	public EnemyRoom(String filePath, String roomName) {
		super(filePath, roomName);
		enemiesLeft = numEnemiesForRoom;
	}
	
	
	public boolean checkWhetherCanMoveToNextRoom() {
		if(enemiesLeft<=0) {
			canMoveToNextRoom = true;
			return true;
		}
		return false;
	}

	public void reduceEnemiesLeft(EnemyManager manager) {
		//check num enemies left and set variables
	}
	
}
