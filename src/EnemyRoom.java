
/**
 * This is a subclass of the Room class and represents a room full of enemies
 * @author srikrishna
 * @version 1.0.0
 */
public class EnemyRoom extends Room {
	
	private int enemiesLeft;

	/**
	 * Creates a EnemyRoom Object
	 * @param filePath filePath to the subtitles with the story for this room
	 * @param roomName the name this room will recieve
	 */
	public EnemyRoom(String filePath, String roomName, int w, int h) {
		super(filePath, roomName, w, h);
		enemiesLeft = numEnemiesForRoom;
	}
	
	
	/**
	 * Checks whether the requirements have been fufilled to move to the next room
	 */
	public boolean checkWhetherCanMoveToNextRoom() {
		if(enemiesLeft<=0) {
			canMoveToNextRoom = true;
			return true;
		}
		return false;
	}

	/**
	 * Checks the number of enemies left and status of the EnemyManager class to make any adjustments needed
	 * @param manager EnemyManager class in has the enemies in this room
	 */
	public void reduceEnemiesLeft(EnemyManager manager) {
		//check num enemies left and set variables
	}
	
}
