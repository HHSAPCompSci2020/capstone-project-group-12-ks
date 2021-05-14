import java.awt.Graphics;
import java.awt.Image;


/**
 * Room class that represents a single room in the dungeon
 * @author srikrishna
 * @version 1.0.0
 */
public class Room {
	
	/**
	 * image object used for background
	 */
	protected Image img;
	/**
	 * filePath for subtitles file with text
	 */
	protected String filePath;
	/**
	 * integer representing initial number of enemies in the Room
	 */
	protected int numEnemiesForRoom;
	/**
	 * Boolean representing whether player can move to the next room
	 */
	protected boolean canMoveToNextRoom;
	/**
	 * Name of the Room
	 */
	protected String roomName;
	
	/**
	 * Constructor that creates Room Object
	 * @param filePath filePath for subtitles file with text
	 * @param roomName Name of the Room
	 */
	public Room(String filePath, String roomName) {
		//set image
		numEnemiesForRoom=10;
		canMoveToNextRoom = false;
		this.filePath=filePath;
		this.roomName=roomName;
	}
	
	/**
	 * Represents the room graphically
	 * @param g the graphics object related to the grid of pixels you wish to draw the object on 
	 */
	public void draw(Graphics g) {
		//draw image here because its the background image for room
		g.drawString(roomName, 230, 10);
	}
	
	/**
	 * Method to check whether the Player can move to the next room
	 * @return boolean representing whether Player can move to next room
	 */
	public boolean checkWhetherCanMoveToNextRoom() {
		return false;
	}
	
}
