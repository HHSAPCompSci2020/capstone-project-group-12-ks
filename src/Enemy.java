import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 * Enemy Class that represents a single enemy unit in the game
 * @author srikrishna joshi
 * @version 1.0.0
 */
public class Enemy implements Collidable{
	
	private double x,y;
	private double xSpeed, ySpeed;
	private boolean isHit;
	private double currentSpeedConstant = 15;
	private Image imgLeft,imgRight;
	private HealthBar health;
	private boolean move;
	
	
	
	/**
	 * Constructor to create a Enemy Object
	 * @param x coordinate spawn point
	 * @param y coordinate spawn point
	 */
	public Enemy(double x, double y, HealthBar health) {
		isHit = false;
		this.x = x;
		this.y = y;
		this.health=health;
		move = true;
		
		
		
	}
	
	
	/**
	 * Draws the a graphical representation of this class using the graphics passed
	 * (the graphics passed should be the pixel grid you intend to draw this object on)
	 * @param g  graphics object passed which is the pixel grid you intend to draw this object on
	 */
	public void draw(Graphics g, Image imgLeft, Image imgRight, double pX, double pY) {
		if(this.x-pX>0) {
			g.drawImage(imgLeft, (int)x - 180, (int)y - 95, null);
		}
		else if(this.x-pX<0) {
			g.drawImage(imgRight, (int)x - 180, (int)y - 95, null);
		}
		g.setColor(Color.red);
		health.draw(g, (int)x, (int)y-15);
	}
	
	/**
	 * Used to make the enemy move, intended to be used continously with other objects
	 */
	public void move(int x, int y) {
		if(!move) {
			move = true;
			return;
		}
		if(this.x-x>0) {
			this.x-=currentSpeedConstant;
		}
		else if(this.x-x<0) {
			this.x+=currentSpeedConstant;
		}
		
		if(this.y-y>0) {
			this.y-=currentSpeedConstant;
		}
		else if(this.y-y<0) {
			this.y+=currentSpeedConstant;
		}
		
		
	}
	
	/**
	 * gets X field from enemy class
	 * @return gets X field from enemy class
	 */
	public double getX() {
		return x;
	}
	/**
	 * gets Y field from enemy class
	 * @return gets Y field from enemy class
	 */
	public double getY() {
		return y;
	}


	@Override
	public void onImpact(Collidable other) {
		if(other.getClass() == Room.class || other.getClass().getSuperclass() == Room.class) {
			move = false;
			
		}
		
		if(other.getClass() == Player.class || other.getClass().getSuperclass() == Player.class) {
			move = false;
			
		}
		if(other.getClass() == Weapon.class || other.getClass().getSuperclass() == Weapon.class) {
			health.reduceHealth(((Weapon) other).getDamage());
		}
		
	}


	@Override
	public boolean collisionCheck(Collidable other) {
		return false;
	}


	@Override
	public Double getHitbox() {
		
		return new Rectangle2D.Double(x,y,45,70);
	}
	
	/**
	 * Reduces health of Enemy by amt (integer)
	 * @param amt amount by which you wish to reduce enemy health
	 */
	public void reduceHealth(double amt) {health.reduceHealth(amt);}
	/**
	 * Increases health of Enemy by amt (integer)
	 * @param amt amount by which you wish to increase enemy health
	 */
	public void increaseHealth(double amt) {health.increaseHealth(amt);}
	/**
	 * returns current health of the enemy
	 * @return returns current health of the enemy
	 */
	public double getHealth() {
		return health.getHealth();
	}

	
}
