import java.awt.Color;
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
	
	

	
}
