import java.awt.Color;

public class Boss extends Enemy {

	public Boss(double x, double y, HealthBar health) {
		super(x, y, new HealthBar(100000,100000,Color.red));
	}

}
