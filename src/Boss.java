import java.awt.Color;
import java.awt.Image;

public class Boss extends Enemy {
	
	private Image specialAnimation;

	public Boss(double x, double y, HealthBar health) {
		super(x, y, new HealthBar(100000,100000,Color.red));
		
	}

}
