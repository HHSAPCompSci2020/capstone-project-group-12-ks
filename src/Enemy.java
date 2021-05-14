import java.awt.Graphics;
import java.awt.Image;

public class Enemy {
	
	private double x,y;
	private double xSpeed, ySpeed;
	private boolean isHit;
	private double currentSpeedConstant = 15;
	private Image img;
	
	
	
	
	public Enemy(double x, double y) {
		isHit = false;
		this.x = x;
		this.y = y;
		
	}
	
	
	public void draw(Graphics g) {
		g.drawOval((int)x, (int)y, 50, 50);
		g.drawString("Enemy", (int)x+8, (int)y+27);
	}
	
	
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

}
