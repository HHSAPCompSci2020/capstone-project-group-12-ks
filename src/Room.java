import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


/**
 * Room class that represents a single room in the dungeon
 * @author srikrishna + kinjal(Collision Detection)
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
	 * Field representing the FilePath to access image for background of room
	 */
	protected String imageFilePath;
	/**
	 * Constructor that creates Room Object
	 * @param filePath filePath for subtitles file with text
	 * @param roomName Name of the Room
	 */
	public Room(String filePath, String roomName, String imageFilePath, int w, int h) {
		//set image
		numEnemiesForRoom=10;
		canMoveToNextRoom = false;
		this.filePath=filePath;
		this.roomName=roomName;
		width = w;
		height = h;
		
		img = new ImageIcon(imageFilePath).getImage();
		try {
			img = ImageIO.read(getClass().getClassLoader().getResource(imageFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.imageFilePath=imageFilePath;
	}
	
	/**
	 * Represents the room graphically
	 * @param g the graphics object related to the grid of pixels you wish to draw the object on 
	 */
	public void draw(Graphics g) {
		//draw image here because its the background image for room
		g.drawString(roomName, 230, 10);
		g.drawImage(img, -18, -10, null);
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
		Rectangle2D.Double hitbox;
		if(imageFilePath.indexOf("Option1")!=-1) {
		hitbox = new Rectangle2D.Double(50,50,width-50,height-50);
		}
		else {
		hitbox = new Rectangle2D.Double(0,0,width,height);
		}
		return hitbox;
	}
	
}
