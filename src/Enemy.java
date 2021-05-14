import java.awt.Graphics;
import java.awt.Image;
/**
 * Enemy Class that represents a single enemy unit in the game
 * @author srikrishna joshi
 * @version 1.0.0
 */
public class Enemy {
	
	private double x,y;
	private double xSpeed, ySpeed;
	private boolean isHit;
	private double currentSpeedConstant = 15;
	private Image img;
	
	
	
	/**
	 * Constructor to create a Enemy Object
	 * @param x coordinate spawn point
	 * @param y coordinate spawn point
	 */
	public Enemy(double x, double y) {
		isHit = false;
		this.x = x;
		this.y = y;
		
	}
	
	
	/**
	 * Draws the a graphical representation of this class using the graphics passed
	 * (the graphics passed should be the pixel grid you intend to draw this object on)
	 * @param g  graphics object passed which is the pixel grid you intend to draw this object on
	 */
	public void draw(Graphics g) {
		g.drawOval((int)x, (int)y, 50, 50);
		g.drawString("Enemy", (int)x+8, (int)y+27);
	}
	
	/**
	 * Used to make the enemy move, intended to be used continously with other objects
	 */
	public void move() {
		int r1 = (int)(Math.random()*2)+1;
		int r2 = (int)(Math.random()*2)+1; 
		
		if(r1==1) {
			x+=currentSpeedConstant;
		}
		else if(r1==2) {
			x+=-currentSpeedConstant;
		}
		
		if(r2==1) {
			y+=currentSpeedConstant;
		}
		else if(r2==2) {
			y+=-currentSpeedConstant;
		}
	}

}
