import java.awt.Graphics;

public class Weapon {
	private String weaponName;
	private int weaponRarity;
	private String weaponType;
	private boolean swing;
	private int swingCount;
	
	
	public void draw(Graphics g,double x, double y) {
		if(swing && swingCount<=3) {
			g.drawRect((int)x, (int)y+90, 90, 10);
			swingCount++;
		}
		else if(swing && swingCount>3) {
			swing=false;
		}
		else{
			g.drawRect((int)x, (int)y, 10, 90);
		}
	}
	
	public void attack() {
		swing=true;
		swingCount = 0;
	}

}
