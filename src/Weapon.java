import java.awt.Graphics;
import java.awt.geom.Rectangle2D.Double;

/**
 * Weapon Class that represents a weapon a Player can use (In the Future Enemy Class may use it too)
 * @author srikrishna
 * @version 1.0.0
 */
public class Weapon implements Collidable{
	private String weaponName;
	private int weaponRarity;
	private String weaponType;
	private boolean swing;
	private int swingCount;
	private String specialAbilityName;
	
	/**
	 * Default Constructor that creates Weapon Object
	 */
	public Weapon() {
		weaponName = "Basic Great Sword";
		specialAbilityName = "Dash";
		swing = false;
		swingCount= 0;
		weaponType = "Great Sword";
		weaponRarity = 1;
		
	}
	
	/**
	 * represents the Weapon Object graphically using the Graphics object for the pixel grid it refers to
	 * @param g the graphics object connected to the pixel grid you wish to draw this object on
	 * @param x X coordinate you wish to draw this Weapon Object
	 * @param y Y coordinate you wish to draw this Weapon Object
	 */
	public void draw(Graphics g,double x, double y) {
		if(swing && swingCount<=5) {
			g.drawRect((int)x, (int)y+90, 90, 10);
			swingCount++;
		}
		else if(swing && swingCount>3) {
			g.drawRect((int)x, (int)y, 10, 90);
			swing=false;
		}
		else{
			g.drawRect((int)x, (int)y, 10, 90);
		}
	}
	
	/**
	 * Sets up variables for attack animation and initiates it
	 */
	public void attack() {
		swing=true;
		swingCount = 0;
	}

	@Override
	public void onImpact(Collidable other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean collisionCheck(Collidable other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Double getHitbox() {
		
		return null;
	}

}
