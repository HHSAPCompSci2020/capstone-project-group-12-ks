import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
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
	private boolean up,down,left,right;
	private double x,y;

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
	public void draw(Graphics g,double x, double y, boolean up, boolean down, boolean left, boolean right) {
		this.up=up;
		this.down=down;
		this.left=left;
		this.right=right;
		this.x=x;
		this.y=y;


		if(right) {
			if(swing && swingCount<=3) {
				g.drawRect((int)x, (int)y+90, 90, 10);
				swingCount++;
				g.setColor(Color.PINK);
				g.drawRect((int)x,(int)y,90,100);
			}
			else if(swing && swingCount>3) {
				g.drawRect((int)x, (int)y, 10, 90);
				swing=false;
				g.setColor(Color.PINK);
				g.drawRect((int)x,(int)y,90,100);
			}
			else{
				g.drawRect((int)x, (int)y, 10, 90);
			}
		}
		else if(left) {
			if(swing && swingCount<=3) {
				g.drawRect((int)x-140, (int)y+100, 90, 10);
				swingCount++;
				g.setColor(Color.PINK);
				g.drawRect((int)x-90-50, (int)y, 90, 100);
			}
			else if(swing && swingCount>3) {
				g.drawRect((int)x-140, (int)y+100, 90, 10);
				swing=false;
				g.setColor(Color.PINK);
				g.drawRect((int)x-90-50, (int)y, 90, 100);
			}
			else{
				g.drawRect((int)x-60, (int)y+10, 10, 90);
			}
		}
		else if(down) {
			if(swing && swingCount<=3) {
				g.drawRect((int)x-50, (int)y+100, 10, 90);
				swingCount++;
				g.setColor(Color.PINK);
				g.drawRect((int)x-50,(int)y+100, 90,100);
			}
			else if(swing && swingCount>3) {
				g.drawRect((int)x-50, (int)y+100, 10, 90);
				swing=false;
				g.setColor(Color.PINK);
				g.drawRect((int)x-50,(int)y+100, 90,100);
			}
			else{
				g.drawRect((int)x-50, (int)y+100, 90, 10);
			}
		}
		else if(up) {
			if(swing && swingCount<=3) {
				g.drawRect((int)x-50, (int)y+25-90+15, 10, 90);
				swingCount++;
				g.setColor(Color.PINK);
				g.drawRect((int)x-50,(int)y-50, 90,100);
			}
			else if(swing && swingCount>3) {
				g.drawRect((int)x-50, (int)y+25-90+15, 10, 90);
				swing=false;
				g.setColor(Color.PINK);
				g.drawRect((int)x-50,(int)y-50, 90,100);
			}
			else{
				g.drawRect((int)x-50, (int)y+25+15, 90, 10);
			}
		}
		//	g.drawRect((int)x, (int)y, 90, 90);
		//g.drawRect((int)x, (int)y, 10, 90);
		
		//right
		//g.drawRect((int)x,(int)y,90,100);
		//left
		//g.drawRect((int)x-90-50, (int)y, 90, 100);
		//up
		//g.drawRect((int)x-50,(int)y-50, 90,100);
		//down
		//g.drawRect((int)x-50,(int)y+100, 90,100);
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
		if(swing && getHitbox() != null) {
			if(other.getHitbox().intersects(this.getHitbox())) {
				System.out.println(getHitbox());
				other.onImpact(this);
				return true;
			}
		}
		return false;
	}

	@Override
	public Double getHitbox() {
		Rectangle2D.Double hitbox = null;
		if(!swing) return null;
		else if(right) {
			hitbox = new Rectangle2D.Double(x,y,90,100);
		}
		else if(left){
			hitbox = new Rectangle2D.Double(x-90-50, y, 90, 100);
		}
		else if(up){
			hitbox = new Rectangle2D.Double(x-50,y-50, 90,100);
		}
		else if(up){
			hitbox = new Rectangle2D.Double(x-50,y+100, 90,100);
		}


		return hitbox;
	}

	public boolean isSwinging() {return swing;}
}
