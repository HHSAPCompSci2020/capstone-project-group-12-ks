import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
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
	private Image img;
	private HealthBar health;
	
	
	
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
	}
	
	
	/**
	 * Draws the a graphical representation of this class using the graphics passed
	 * (the graphics passed should be the pixel grid you intend to draw this object on)
	 * @param g  graphics object passed which is the pixel grid you intend to draw this object on
	 */
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.drawOval((int)x, (int)y, 50, 50);
		g.drawString("Enemy", (int)x+8, (int)y+27);
		health.draw(g, (int)x, (int)y-15);
		g.drawRect((int)x,(int)y,50,50);
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
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}


	@Override
	public void onImpact(Collidable other) {
		if(other.getClass() == Room.class || other.getClass().getSuperclass() == Room.class) {
			x = 100;
			y = 100;
		}
		if(other.getClass() == Weapon.class || other.getClass().getSuperclass() == Weapon.class) {
			health.reduceHealth(((Weapon) other).getDamage());
		}
		
	}


	@Override
	public boolean collisionCheck(Collidable other) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Double getHitbox() {
		
		return new Rectangle2D.Double(x,y,50,50);
	}
	
	public void reduceHealth(double amt) {health.reduceHealth(amt);}
	public void increaseHealth(double amt) {health.increaseHealth(amt);}
	public double getHealth() {
		return health.getHealth();
	}

}
