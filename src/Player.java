import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Player {
	//Fields

	protected int x, y;
	protected int width, height;
	private Image image;
	protected boolean isVisible;
	private boolean hit;
	private Image leftImage;
	int counter;

	private double xSpeed, ySpeed;
	private Image right, left;
	private boolean isLeft;

	//Constructor
	public Player(int x, int y)
	{
		
		//image = img;
		this.x = x;
		this.y = y;
		//width = w;
		//height = h;
		isVisible = true;
		hit=false;
		counter=0;
		//right=new ImageIcon("png name").getImage();
		//left=new ImageIcon("png name").getImage();
		xSpeed = 0;
		ySpeed = 0;
		isVisible= true;
	}

	//Methods
	public void horizontal(int dir) {
		xSpeed = dir;
		moveByAmount((int)xSpeed, 0);
	}

	public void vertical(int dir) {
		ySpeed = dir;
		moveByAmount(0,(int)ySpeed);
	}

	public void draw(Graphics g, ImageObserver io) {
		g.setColor(Color.black);
		if(isLeft) {
			//g.drawImage(left, x, y,width,height, io);
			g.drawRect(x, y, 50, 50);
		}
		else if(isVisible){
			//g.drawImage(right, x, y, width, height, io);
			g.drawRect(x, y, 50, 50);
		}
	}

	public void setLeft(boolean left) {
		this.isLeft=left;
	}

	public void toggleVisibility() {
		isVisible = true;
	}


	public void moveToLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void moveByAmount(int x, int y) {
		this.x += x;
		this.y += y;
	}

}
