import java.awt.Color;
import java.awt.Graphics;

/**
 * HealthBar Class represents health of a Enemy/Boss/Player in the Game
 * @author srikrishna
 * 
 */
public class HealthBar {
	private double maxHealth;
	private double currentHealth;
	private int x, y;
	private Color color; 
	
	/**
	 * Constructs HealthBar object
	 * @param maxHealth maxHealth the health bar should have
	 * @param currentHealth current health the health bar should have when drawn initially
	 * @param color color you want health bar to be
	 */
	public HealthBar(double maxHealth, double currentHealth, Color color) {
		this.maxHealth=maxHealth;
		this.currentHealth=currentHealth;
		x=0;
		y=0;
		this.color=color;
		
	}
	/**
	 * This method graphically expresses the health bar object using graphics
	 * @param g Graphics object connected to the grid of pixels you wish to draw on
	 * @param x x location you wish to draw health bar at
	 * @param y y location you wish to draw health bar at
	 */
	public void draw(Graphics g, int x, int y) {
		this.x=x;
		this.y=y;
		g.setColor(Color.black);
		g.drawRect(x, y, (int)(maxHealth/4), 10);
		g.setColor(color);
		g.fillRect(x, y, (int)(currentHealth/4), 10);
	}
	
	/**
	 * reduces current health by amt
	 * @param amt amount by which you want to reduce current health
	 */
	public void reduceHealth(double amt) {
		currentHealth-=amt;
	}
	/**
	 * increases current health by amt
	 * @param amt amount by which you want to increase current health
	 */
	public void increaseHealth(double amt) {
		currentHealth+=amt;
	}
	/**
	 * returns current health
	 * @return returns current health (double)
	 */
	public double getHealth() {
		return currentHealth;
	}
}
