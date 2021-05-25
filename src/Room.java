import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


/**
 * Room class that represents a single room in the dungeon
 * @author srikrishna + kinjal(Collidable methods)
 * @version 1.0.0
 */
public class Room implements Collidable, MouseListener {
	
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
	 * File with story subtitles for game
	 */
	protected File storyFile;
	
	/**
	 * Scanner to read lines of storyFile
	 */
	protected Scanner storyReader;
	
	/**
	 * Current Line of Story being displayed on screen
	 */
	protected String currentLine;
	
	/**
	 * Boolean determining whether the storymode for the room is finished
	 */
	protected boolean storyActive;
	
	/**
	 * Constructor that creates Room Object
	 * @param filePath filePath for subtitles file with text
	 * @param roomName Name of the Room
	 */
	public Room(String filePath, String roomName, String imageFilePath, int w, int h) {
	
		numEnemiesForRoom=10;
		canMoveToNextRoom = false;
		this.filePath=filePath;
		this.roomName=roomName;
		width = w;
		height = h;
		
		//set image
		img = new ImageIcon(imageFilePath).getImage();
		try {
			img = ImageIO.read(getClass().getClassLoader().getResource(imageFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.imageFilePath=imageFilePath;
		
		//load file
		FileInputStream fis;
		try {
			fis = new FileInputStream(filePath);
			storyReader=new Scanner(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		
		currentLine = storyReader.nextLine();
		if(currentLine.indexOf("---")!=-1) {
			currentLine = storyReader.nextLine();
		}
		storyActive = true;
	}
	
	/**
	 * Represents the room graphically
	 * @param g the graphics object related to the grid of pixels you wish to draw the object on 
	 */
	public void draw(Graphics g) {
		//draw image here because its the background image for room
		g.drawString(roomName, 230, 10);
		g.drawImage(img, -18, -10, null);
		if(storyActive) {
			g.setColor(Color.black);
			g.fillRect(0, 350, 750, 250);
			g.setColor(Color.white);
			g.drawString(currentLine, 50, 350+70);
		}
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
		hitbox = new Rectangle2D.Double(50,50,width-100,height-100);
		}
		else {
		hitbox = new Rectangle2D.Double(0,0,width,height);
		}
		return hitbox;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println("HERE");
		if(!storyReader.hasNext()) {
			storyActive=false;
			return;
		}
		currentLine = storyReader.nextLine();
		if(currentLine.indexOf("---")!=-1) {
			currentLine = storyReader.nextLine();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}
	
	/**
	 * returns whether the storymode of the room is currently active
	 * @return whether the storymode of the room is currently active (true for yes, false for no)
	 */
	public boolean isStoryActive() {
		return storyActive;
	}
	
	/**
	 * returns String description of Object
	 * @return returns String description of Object
	 */
	public String toString() {
		if(filePath.indexOf("Option1")!=-1) {
			return "Option1";
		}
		else {
			return "Option2";
		}
	}
	
}
