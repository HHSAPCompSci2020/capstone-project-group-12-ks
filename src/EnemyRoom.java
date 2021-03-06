import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

/**
 * This is a subclass of the Room class and represents a room full of enemies
 * @author srikrishna
 * @version 1.0.0
 */
public class EnemyRoom extends Room {

	/**
	 * Num enemies left in room
	 */
	protected int enemiesLeft;
	
	/**
	 * used to draw blinking yellow plate for move to next room availability
	 */
	protected int alternator;
	/**
	 * original number of enemies that were in the room
	 */
	protected int originalNumOfEnemies;
	
	

	/**
	 * Creates a EnemyRoom Object
	 * @param filePath filePath to the subtitles with the story for this room
	 * @param roomName the name this room will recieve
	 */
	public EnemyRoom(String filePath, String roomName, String imageFilePath, int w, int h, int originalNumOfEnemies) {
		super(filePath, roomName,imageFilePath, w, h);
		enemiesLeft = numEnemiesForRoom;
		alternator=0;
		this.originalNumOfEnemies=originalNumOfEnemies;
		
		
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
		int count=0;
		for(int i=0;i<manager.size();i++) {
			if(manager.get(i)!=null) count++;
		}
		enemiesLeft = count;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		if(canMoveToNextRoom && alternator<=3) {
			g.setColor(Color.yellow);
			g.fillRect(85, 380, 45, 100);
		}
		alternator++;
		if(alternator==6) alternator=0;
	}

	/**
	 * Returns Rectangle2D.Double which represents a hitbox for the yellow blinking square allowing
	 * player to move to next room
	 * @return Returns Rectangle2D.Double which represents a hitbox for the yellow blinking square allowing
	 * player to move to next room
	 */
	public Double getHitboxForNextRoomPlate() {
		if(!canMoveToNextRoom) return null;
		Rectangle2D.Double hitbox = null;
		hitbox = new Rectangle2D.Double(85, 380, 45, 100);
		return hitbox;
	}
	
	/**
	 * returns the original number of enemies in room (integer)
	 * @return returns the original number of enemies in room (integer)
	 */
	public int getOriginalNumberOfEnemies() {return originalNumOfEnemies;}

}
