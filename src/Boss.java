import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
/**
 * Represents a boss
 * @author srikrishna
 *
 */
public class Boss extends Enemy {
	

	
	/**
	 * Creates new Boss
	 * @param x boss loc x
	 * @param y boss loc y
	 * @param health boss health
	 */
	public Boss(double x, double y, HealthBar health) {
		super(x, y, new HealthBar(2000,2000,Color.red));
	}
	
	

	@Override
	public Double getHitbox() {
		
		return new Rectangle2D.Double(getX()-65,getY()-35,150,150);
	}

	
}
