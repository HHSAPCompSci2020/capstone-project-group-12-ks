import java.awt.Graphics;
import java.awt.Image;

public class Room {
	
	protected Image img;
	protected String filePath;
	protected int numEnemiesForRoom;
	protected boolean canMoveToNextRoom;
	protected String roomName;
	
	public Room(String filePath, String roomName) {
		//set image
		numEnemiesForRoom=10;
		canMoveToNextRoom = false;
		this.filePath=filePath;
		this.roomName=roomName;
	}
	
	
	public void draw(Graphics g) {
		//draw image here because its the background image for room
		g.drawString(roomName, 230, 10);
	}
	

	public boolean checkWhetherCanMoveToNextRoom() {
		return false;
	}
	
}
