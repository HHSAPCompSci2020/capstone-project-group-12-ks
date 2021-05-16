import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;


/**
 * Room class that represents a single room in the dungeon
 * @author srikrishna
 * @version 1.0.0
 */
public class Room implements Collidable {
	
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
	
	private int width, height;
	/**
	 * Constructor that creates Room Object
	 * @param filePath filePath for subtitles file with text
	 * @param roomName Name of the Room
	 */
	public Room(String filePath, String roomName, int w, int h) {
		//set image
		numEnemiesForRoom=10;
		canMoveToNextRoom = false;
		this.filePath=filePath;
		this.roomName=roomName;
		width = w;
		height = h;
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

	@Override
	public void onImpact(Collidable other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean collisionCheck(Collidable other) {
		if(getHitbox().intersects(other.getHitbox()) && !getHitbox().contains(other.getHitbox())) {
			other.onImpact(this);
			return true;
		}
		return false;
	}

	@Override
	public Double getHitbox() {
		Rectangle2D.Double hitbox = new Rectangle2D.Double(0,0,width,height);
		return hitbox;
	}
	
}
