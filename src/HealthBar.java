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
	
	public HealthBar(double maxHealth, double currentHealth, Color color) {
		this.maxHealth=maxHealth;
		this.currentHealth=currentHealth;
		x=0;
		y=0;
		this.color=color;
		
	}
	
	public void draw(Graphics g, int x, int y) {
		this.x=x;
		this.y=y;
		g.setColor(Color.black);
		g.drawRect(x, y, (int)(maxHealth/4), 10);
		g.setColor(color);
		g.fillRect(x, y, (int)(currentHealth/4), 10);
	}
	
	public void reduceHealth(double amt) {
		currentHealth-=amt;
	}
	public void increaseHealth(double amt) {
		currentHealth+=amt;
	}
}
