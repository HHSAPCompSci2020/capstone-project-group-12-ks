import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Player implements KeyListener{
	//Fields

	protected int x, y;
	protected int width, height;
	private Image image;
	protected boolean isVisible;
	private boolean hit;
	private Image leftImage;
	int counter;
	private boolean left,right,jump,crouch;

	private double xSpeed, ySpeed;
	private Image rightImage, leftImages;
	private boolean isLeft;
	
	private double currentSpeedConstant =20;

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

	public void setYSpeed(double ySpeed) {
		this.ySpeed=ySpeed;
	}
	public void setXSpeed(double xSpeed) {
		this.xSpeed=xSpeed;
	}
	
	public void move() {
		x+=xSpeed;
		y+=ySpeed;
	}
	public void draw(Graphics g, ImageObserver io) {
		g.setColor(Color.black);
		if(isLeft) {
			//g.drawImage(left, x, y,width,height, io);
			g.drawRect(x, y, 50, 50);
			g.drawString("Player",x+5,y+25);
		}
		else if(isVisible){
			//g.drawImage(right, x, y, width, height, io);
			g.drawRect(x, y, 50, 50);
			g.drawString("Player",x+5,y+25);
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
	
	public void addKeyListener() {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) 
	{
		System.out.println("FDJ ");
		int keycode = e.getKeyCode();

		if(keycode==KeyEvent.VK_D) 
		{
			setLeft(false);
			right = true;
			this.setXSpeed(currentSpeedConstant);
		}
		else if(keycode==KeyEvent.VK_A) 
		{
			setLeft(true);
			left = true;
			this.setXSpeed(-currentSpeedConstant);
		}
		else if(keycode==KeyEvent.VK_S) 
		{
			crouch = true;
			this.setYSpeed(currentSpeedConstant);
		}
		else if(keycode==KeyEvent.VK_W) 
		{
			jump = true;
			this.setYSpeed(-currentSpeedConstant);
		}
	
	}

	public void keyReleased(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_A) 
		{
			setLeft(true);
			left = false;
			this.setXSpeed(0);
		} 
		else if (e.getKeyCode() == KeyEvent.VK_D) 
		{
			setLeft(false);
			right = false;
			this.setXSpeed(0);
		} 
		else if (e.getKeyCode() == KeyEvent.VK_W) 
		{
			jump = false;
			this.setYSpeed(0);
		} 
		else if (e.getKeyCode() == KeyEvent.VK_S) 
		{
			crouch = false;
			this.setYSpeed(0);
		}
		
	}

}


