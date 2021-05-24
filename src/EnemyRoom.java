import java.awt.Color;
import java.awt.Graphics;

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
	public EnemyRoom(String filePath, String roomName, String imageFilePath, int w, int h) {
		super(filePath, roomName,imageFilePath, w, h);
		enemiesLeft = numEnemiesForRoom;
	}
	
	
	/**
	 * Checks whether the requirements have been fufilled to move to the next room
	 */
	public boolean checkWhetherCanMoveToNextRoom() {
		if(enemiesLeft<=0) {
			canMoveToNextRoom = true;
			System.out.println("DONE");
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
		//draw image here because its the background image for room
		g.drawString(roomName, 230, 10);
		g.drawImage(img, -18, -10, null);
		if(storyActive) {
			g.setColor(Color.black);
			g.drawRect(0, 300, 750, 200);
			g.setColor(Color.white);
			g.drawString(currentLine, 50, 200+50);
		}
		if(canMoveToNextRoom) {
			g.setColor(Color.yellow);
			g.fillRect(20, 300, 50, 100);
			System.out.println("done");
		}
	}
	
}
