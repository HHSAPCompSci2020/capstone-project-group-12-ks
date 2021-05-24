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
 * @author srikrishna
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
	private Image up1, up2, up3, down1, down2, down3, left1, left2,left3, right1,right2, right3;
	

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
		System.out.println();
		//image = img;
//		testImage = new ImageIcon("images/Test.png").getImage();
//		testImage = Toolkit.getDefaultToolkit().getImage("images/Test.png");
		up1 = new ImageIcon("Images/position10.png").getImage();
		try {
			up1 = ImageIO.read(getClass().getClassLoader().getResource("Images/position10.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		up2 = new ImageIcon("Images/position11.png").getImage();
		try {
			up2 = ImageIO.read(getClass().getClassLoader().getResource("Images/position11.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		up3 = new ImageIcon("Images/position12.png").getImage();
		try {
			up3 = ImageIO.read(getClass().getClassLoader().getResource("Images/position12.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		left1 = new ImageIcon("Images/position4.png").getImage();
		try {
			left1 = ImageIO.read(getClass().getClassLoader().getResource("Images/position4.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		left2 = new ImageIcon("Images/position5.png").getImage();
		try {
			left2 = ImageIO.read(getClass().getClassLoader().getResource("Images/position5.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		left3 = new ImageIcon("Images/position6.png").getImage();
		try {
			left3 = ImageIO.read(getClass().getClassLoader().getResource("Images/position6.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		right1 = new ImageIcon("Images/position7.png").getImage();
		try {
			right1 = ImageIO.read(getClass().getClassLoader().getResource("Images/position7.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		right2 = new ImageIcon("Images/position8.png").getImage();
		try {
			right2 = ImageIO.read(getClass().getClassLoader().getResource("Images/position8.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		right3 = new ImageIcon("Images/position9.png").getImage();
		try {
			right3 = ImageIO.read(getClass().getClassLoader().getResource("Images/position9.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		down1 = new ImageIcon("Images/position1.png").getImage();
		try {
			down1 = ImageIO.read(getClass().getClassLoader().getResource("Images/position1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		down2 = new ImageIcon("Images/position2.png").getImage();
		try {
			down2 = ImageIO.read(getClass().getClassLoader().getResource("Images/position2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		down3 = new ImageIcon("Images/position3.png").getImage();
		try {
			down3 = ImageIO.read(getClass().getClassLoader().getResource("Images/position3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		isVisible = true;
		hit=false;
		counter=0;
		//right=new ImageIcon("png name").getImage();
		//left=new ImageIcon("png name").getImage();
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
//		System.out.println("IN DRAW --- "+up+" "+down+" "+left+" "+right);
//	    System.out.println(xSpeed+" "+ySpeed+" "+x+" "+y);
		g.setColor(Color.blue);
		//g.drawImage(right1, x-165, y-105, null);
		if(isLeft) {
			//g.drawImage(left, x, y,width,height, io);
			g.drawRect(x, y, width, height);
			g.drawString("Player",x+5,y+25);
		}
		else if(isVisible){
			//g.drawImage(right, x, y, width, height, io);
			g.drawRect(x, y, width, height);
			g.drawString("Player",x+5,y+25);
		}
		
//		if(ySpeed >= 0) {
//			up = true;
//			down = false;
//		} else {
//			up = false;
//			down = true;
//		}
//		
//		if(xSpeed >= 0) {
//			right = true;
//			left = false;
//		} else {
//			right = false;
//			left = true;
//		}
		
		currentWeapon.draw(g, x+50, y-50,up,down,left,right);
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
		
		
		
		//g.drawImage(testImage, 50, 50, null);
		
	}

//	public void setLeft(boolean left) {
//		this.isLeft=left;
//	}
//
//	public void toggleVisibility() {
//		isVisible = true;
//	}
//
	
	public void setLeft(boolean left) {
		this.left=left;
	}
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
	
//	public void addKeyListener() {
//		
//	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		int keycode = e.getKeyCode();
		

		if(keycode==KeyEvent.VK_D) 
		{
			//setLeft(false);
			setRight(true);
		
		}
		else if(keycode==KeyEvent.VK_A) 
		{
			//setLeft(true);
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
		else if(keycode==KeyEvent.VK_R) {
			currentWeapon.attack();
//			System.out.println("DONE");
		}
		

	}
	@Override
	public void keyPressed(KeyEvent e) 
	{
		inMotion=true;
//		System.out.println("IN KEY PRESSED ");
		int keycode = e.getKeyCode();

		if(keycode==KeyEvent.VK_D) 
		{
			//setLeft(false);
			setRight(true);
			this.setXSpeed(currentSpeedConstant);
		}
		else if(keycode==KeyEvent.VK_A) 
		{
			//setLeft(true);
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
//		System.out.println("IN KEY RELEASED");
		if (e.getKeyCode() == KeyEvent.VK_A) 
		{
			//setLeft(true);
			setLeft(false);
			this.setXSpeed(0);
		} 
		else if (e.getKeyCode() == KeyEvent.VK_D) 
		{
			//setLeft(false);
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
		
		
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	@Override
	public void onImpact(Collidable other) {
		if(other.getClass() == Room.class || other.getClass().getSuperclass() == Room.class) {
//			System.out.println("hi");
//			Rectangle2D cross = getHitbox().createIntersection(other.getHitbox());
//			if(cross.getX() < 0) {
//				x+=cross.getWidth();
//			} else {
//				x-=cross.getWidth();
//			}
//			if(cross.getY() < 0) {
//				y+=cross.getHeight();
//			} else {
//				y-=cross.getHeight();
//			}
			xSpeed = 0;
			ySpeed = 0;
		}
		if(other.getClass() == Enemy.class || other.getClass().getSuperclass() == Enemy.class) {
//			System.out.println("hi");
//			Rectangle2D cross = getHitbox().createIntersection(other.getHitbox());
//			if(cross.getX() < 0) {
//				x+=cross.getWidth();
//			} else {
//				x-=cross.getWidth();
//			}
//			if(cross.getY() < 0) {
//				y+=cross.getHeight();
//			} else {
//				y-=cross.getHeight();
//			}
			health.reduceHealth(200);
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
	
	public boolean isAttacking() {
		return currentWeapon.isSwinging();
	}
	
	public Weapon getWeapon() {return currentWeapon;}
	
	public double getXVel() {
		return xSpeed;
	}
	
	public double getYVel() {
		return ySpeed;
	}
	
	public void setX(int x) {
		this.x=x;
	}
	
	public void setY(int y) {
		this.y=y;
	}
	
	public void reduceHealth(double amt) {
		health.reduceHealth(amt);
	}
	
}


