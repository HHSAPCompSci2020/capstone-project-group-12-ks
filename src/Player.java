import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

/**
 * Player class that represents the users character
 * @author srikrishna
 * @version 1.0.0
 */
public class Player implements KeyListener{
	//Fields

	private int x, y;
	private int width, height;
	private Image image;
	protected boolean isVisible;
	private boolean hit;
	private Image leftImage;
	private int counter;
	private boolean left,right,jump,crouch;

	private double xSpeed, ySpeed;
	private Image rightImage, leftImages;
	private boolean isLeft;
	
	private double currentSpeedConstant =20;
	
	private Weapon currentWeapon;

	/**
	 * Constructor that creates a Player Object
	 * @param x X coordinate of Where the Player object should spawn
	 * @param y Y coordinate of Where the Player object should spawn
	 */
	public Player(int x, int y)
	{

		//image = img;
		this.x = x;
		this.y = y;
		//width = w;
		//height = h;
		isVisible = true;
		hit=false;
		counter=0;
		//right=new ImageIcon("png name").getImage();
		//left=new ImageIcon("png name").getImage();
		xSpeed = 0;
		ySpeed = 0;
		isVisible= true;
		currentWeapon = new Weapon();
	}

//	//Methods
//	public void horizontal(int dir) {
//		xSpeed = dir;
//		moveByAmount((int)xSpeed, 0);
//	}
//
//	public void vertical(int dir) {
//		ySpeed = dir;
//		moveByAmount(0,(int)ySpeed);
//	}

	/**
	 * Sets the vertical Speed of the Player class to the parameter passed
	 * @param ySpeed the new vertical (Y) speed
	 */
	public void setYSpeed(double ySpeed) {
		this.ySpeed=ySpeed;
	}
	
	/**
	 * Sets the horizonatl Speed of the Player class to the parameter passed
	 * @param xSpeed the new horizontal (X) speed
	 */
	public void setXSpeed(double xSpeed) {
		this.xSpeed=xSpeed;
	}
	
	/**
	 * Moves the object by altering its x,y position
	 */
	public void move() {
		x+=xSpeed;
		y+=ySpeed;
	}
	
	/**
	 * represents the Player graphically using the Graphics object for the pixel grid it refers to
	 * @param g the graphics object connected to the pixel grid you wish to draw this object on
	 */
	public void draw(Graphics g) {
		g.setColor(Color.black);
		if(isLeft) {
			//g.drawImage(left, x, y,width,height, io);
			g.drawRect(x, y, 50, 50);
			g.drawString("Player",x+5,y+25);
		}
		else if(isVisible){
			//g.drawImage(right, x, y, width, height, io);
			g.drawRect(x, y, 50, 50);
			g.drawString("Player",x+5,y+25);
		}
		
		currentWeapon.draw(g, x+50, y-50);
	}

//	public void setLeft(boolean left) {
//		this.isLeft=left;
//	}
//
//	public void toggleVisibility() {
//		isVisible = true;
//	}
//
	/**
	 * Immediately moves Player to new Location
	 * @param x X coordinate of Where the Player object should move
	 * @param y Y coordinate of Where the Player object should move
	 */
	public void moveToLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Moves the x,y location of the Player by parameters passed
	 * @param x the amount you wish to increment x by 
	 * @param y the amount you wish to increment y by 
	 */
	public void moveByAmount(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
//	public void addKeyListener() {
//		
//	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		int keycode = e.getKeyCode();
		
		

	}
	@Override
	public void keyPressed(KeyEvent e) 
	{
//		System.out.println("FDJ ");
		int keycode = e.getKeyCode();

		if(keycode==KeyEvent.VK_D) 
		{
			//setLeft(false);
			right = true;
			this.setXSpeed(currentSpeedConstant);
		}
		else if(keycode==KeyEvent.VK_A) 
		{
			//setLeft(true);
			left = true;
			this.setXSpeed(-currentSpeedConstant);
		}
		else if(keycode==KeyEvent.VK_S) 
		{
			crouch = true;
			this.setYSpeed(currentSpeedConstant);
		}
		else if(keycode==KeyEvent.VK_W) 
		{
			jump = true;
			this.setYSpeed(-currentSpeedConstant);
		}
	
	}
	@Override
	public void keyReleased(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_A) 
		{
			//setLeft(true);
			left = false;
			this.setXSpeed(0);
		} 
		else if (e.getKeyCode() == KeyEvent.VK_D) 
		{
			//setLeft(false);
			right = false;
			this.setXSpeed(0);
		} 
		else if (e.getKeyCode() == KeyEvent.VK_W) 
		{
			jump = false;
			this.setYSpeed(0);
		} 
		else if (e.getKeyCode() == KeyEvent.VK_S) 
		{
			crouch = false;
			this.setYSpeed(0);
		}
		else if(e.getKeyCode()==KeyEvent.VK_SPACE) 
		{
			currentWeapon.attack();
		}
		
		
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

}


