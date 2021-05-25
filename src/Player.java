import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Player class that represents the users character
 * @author srikrishna + kinjal(collidable methods)
 * @version 1.0.0
 */
public class Player implements KeyListener, Collidable{
	//Fields

	private int x, y;
	private int width, height;
	private Image image;
	protected boolean isVisible;
	private boolean hit;
	private Image leftImage;
	private Image testImage;
	private int counter;
	private boolean left,right,up,down;
	
	

	private double xSpeed, ySpeed;
	private Image rightImage, leftImages;
	private boolean isLeft;
	
	private double currentSpeedConstant =20;
	
	private Weapon currentWeapon;
	
	private boolean inMotion;
	private HealthBar health;
	private int count = 1;
	
	
	

	/**
	 * Constructor that creates a Player Object
	 * @param x X coordinate of Where the Player object should spawn
	 * @param y Y coordinate of Where the Player object should spawn
	 */
	public Player(int x, int y, int w, int h)
	{
		
		
		
		
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		isVisible = true;
		hit=false;
		counter=0;
		
		xSpeed = 0;
		ySpeed = 0;

		isVisible= true;
		currentWeapon = new Weapon();
		count = 1;
		up = false;
		down = true;
		left = false;
		right = false;
		inMotion = false;
		health = new HealthBar(200,200,Color.GREEN);
	}

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
	public void draw(Graphics g, Image up1, Image up2, Image up3,
			Image down1, Image down2, Image down3,
			Image left1, Image left2, Image left3,
			Image right1, Image right2, Image right3, Image imgWeapon) {

		g.setColor(Color.blue);
		
		if(isLeft) {
		
			g.drawRect(x, y, width, height);
			g.drawString("Player",x+5,y+25);
		}
		else if(isVisible){
			
			g.drawRect(x, y, width, height);
			g.drawString("Player",x+5,y+25);
		}
		
		
		currentWeapon.draw(g, x+50, y-50,up,down,left,right,imgWeapon);
		health.draw(g, x, y-15);
		
		
		if(up) {
			if(count==1) {
				g.drawImage(up1, x-165, y-105, null);
				count++;
			}
			else if(count==2) {
				g.drawImage(up2, x-165, y-105, null);
				count++;
			}
			else if(count==3) {
				g.drawImage(up3, x-165, y-105, null);
				count=1;
			}
		}
		else if(down) {
			if(count==1) {
				g.drawImage(down1, x-165, y-105, null);
				count++;
			}
			else if(count==2) {
				g.drawImage(down2, x-165, y-105, null);
				count++;
			}
			else if(count==3) {
				g.drawImage(down3, x-165, y-105, null);
				count=1;
			}
		}
		else if(left) {
			if(count==1) {
				g.drawImage(left1, x-165, y-105, null);
				count++;
			}
			else if(count==2) {
				g.drawImage(left2, x-165, y-105, null);
				count++;
			}
			else if(count==3) {
				g.drawImage(left3, x-165, y-105, null);
				count=1;
			}
		}
		else if(right) {
			if(count==1) {
				g.drawImage(right1, x-165, y-105, null);
				count++;
			}
			else if(count==2) {
				g.drawImage(right2, x-165, y-105, null);
				count++;
			}
			else if(count==3) {
				g.drawImage(right3, x-165, y-105, null);
				count=1;
			}
		}
		
		
		
		
		
	}



	
	/**
	 * sets left boolean field
	 * @param left boolean which you wish to set left to
	 */
	public void setLeft(boolean left) {
		this.left=left;
	}
	
	/**
	 * sets right boolean field
	 * @param left boolean which you wish to set right to
	 */
	public void setRight(boolean right) {
		this.right=right;
	}
	
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
	


	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println("HI");
		int keycode = e.getKeyCode();
		

		if(keycode==KeyEvent.VK_D) 
		{
		System.out.println("DONE");
			setRight(true);
		
		}
		else if(keycode==KeyEvent.VK_A) 
		{
		
			setLeft(true);
			
		}
		else if(keycode==KeyEvent.VK_S) 
		{
			down = true;
		
		}
		else if(keycode==KeyEvent.VK_W) 
		{
			up = true;
			
		}
		
		

	}
	@Override
	public void keyPressed(KeyEvent e) 
	{
		inMotion=true;

		int keycode = e.getKeyCode();

		if(keycode==KeyEvent.VK_D) 
		{
		
			setRight(true);
			this.setXSpeed(currentSpeedConstant);
		}
		else if(keycode==KeyEvent.VK_A) 
		{
	
			setLeft(true);
			this.setXSpeed(-currentSpeedConstant);
		}
		else if(keycode==KeyEvent.VK_S) 
		{
			down = true;
			this.setYSpeed(currentSpeedConstant);
		}
		else if(keycode==KeyEvent.VK_W) 
		{
			up = true;
			this.setYSpeed(-currentSpeedConstant);
		}
	
	}
	@Override
	public void keyReleased(KeyEvent e) 
	{
		inMotion = false;

		if (e.getKeyCode() == KeyEvent.VK_A) 
		{

			setLeft(false);
			this.setXSpeed(0);
		} 
		else if (e.getKeyCode() == KeyEvent.VK_D) 
		{
	
			setRight(false);
			this.setXSpeed(0);
		} 
		else if (e.getKeyCode() == KeyEvent.VK_W) 
		{
			up = false;
			this.setYSpeed(0);
		} 
		else if (e.getKeyCode() == KeyEvent.VK_S) 
		{
			down = false;
			this.setYSpeed(0);
		}
		else if(e.getKeyCode()==KeyEvent.VK_SPACE) 
		{
			currentWeapon.attack();
		}
		else if(e.getKeyCode()==KeyEvent.VK_SHIFT) {
			System.out.println("HELLO");
			if(currentWeapon.getSpecialMoveName().equals("Heal")) {
				health.increaseHealth(20);
			}
		
		}
		
		
	}
	
	/**
	 * gets X coordinate 
	 * @return gets X coordinate (integer value)
	 */
	public int getX() {
		return x;
	}
	/**
	 * gets Y coordinate 
	 * @return gets Y coordinate (integer value)
	 */
	public int getY() {
		return y;
	}

	@Override
	public void onImpact(Collidable other) {
		if(other.getClass() == Room.class || other.getClass().getSuperclass() == Room.class) {

			xSpeed = 0;
			ySpeed = 0;
		}
		if(other.getClass() == Enemy.class || other.getClass().getSuperclass() == Enemy.class) {

			health.reduceHealth(1);
			}
		
		}

	@Override
	public boolean collisionCheck(Collidable other) {
		if(getHitbox().intersects(other.getHitbox())) {
			onImpact(other);
			other.onImpact(this);
			return true;
		}
		return false;
	}

	@Override
	public Double getHitbox() {
		Rectangle2D.Double hitbox = new Rectangle2D.Double(x+xSpeed,y+ySpeed,width,height+20);//this is a projected hitbox
		return hitbox;
	}
	
	/**
	 * returns boolean expressing whether player is currently attacking or not
	 * @return boolean expressing whether player is currently attacking or not
	 */
	public boolean isAttacking() {
		return currentWeapon.isSwinging();
	}
	
	/**
	 * returns current weapon used by this Player class
	 * @return returns current weapon used by this Player class
	 */
	public Weapon getWeapon() {return currentWeapon;}
	
	/**
	 * returns Player xSpeed
	 * @return returns Player xSpeed
	 */
	public double getXVel() {
		return xSpeed;
	}
	/**
	 * returns Player ySpeed
	 * @return returns Player ySpeed
	 */
	public double getYVel() {
		return ySpeed;
	}
	/**
	 * sets x field of player
	 * @param x integer parameter you wish to set x to
	 */
	public void setX(int x) {
		this.x=x;
	}
	/**
	 * sets y field of player
	 * @param y integer parameter you wish to set y to
	 */
	public void setY(int y) {
		this.y=y;
	}
	
	/**
	 * gets left boolean 
	 * @return left boolean 
	 */
	public boolean getLeft() {
		return left;
	}
	/**
	 * gets right boolean 
	 * @return right boolean 
	 */
	public boolean getRight() {
		return right;
	}
	/**
	 * gets up boolean 
	 * @return up boolean 
	 */
	public boolean getUp() {
		return up;
	}
	/**
	 * gets down boolean 
	 * @return down boolean 
	 */
	public boolean getDown() {
		return down;
	}
	
	/**
	 * sets up boolean to u
	 * @param u boolean you wish to set up to
	 */
	public void setUp(boolean u) {
		up = u;
	}
	/**
	 * sets down boolean to d
	 * @param d boolean you wish to set down to
	 */
	public void setDown(boolean d) {
		down = d;
	}
	/**
	 * sets the health of Player class (using health bar class)
	 * @param h double of new health for player
	 */
	public void setHealth(double h) {
		health.reduceHealth(health.getHealth()-h);
	}
	/**
	 * returns current health for player
	 * @return current health for player
	 */
	public double getHealth() {
		return health.getHealth();
	}
	/**
	 * returns whether player is swinging weapon
	 * @return whether player is swinging weapon
	 */
	public boolean getSwing() {
		return currentWeapon.isSwinging();
	}
	/**
	 * sets currentWeapon to "attack mode" which sets swing to true in currentWeapon
	 */
	public void setSwing() {
		currentWeapon.attack();
	}
	/**
	 * reduces health by amt
	 * @param amt amount by which you want to reduce health
	 */
	public void reduceHealth(double amt) {
		health.reduceHealth(amt);
	}
	
}


