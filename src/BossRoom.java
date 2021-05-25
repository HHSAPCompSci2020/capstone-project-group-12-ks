import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class BossRoom extends EnemyRoom {
	
	private Boss roomBoss;
	//private int alternator;
	
	

	public BossRoom(String filePath, String roomName, String imageFilePath, int w, int h) {
		super(filePath, roomName, imageFilePath, w, h,1);
	//	alternator=0;
		roomBoss= new Boss(300,300,new HealthBar(2000,2000,Color.blue));
	}


	public void draw(Graphics g, Image left, Image right, double pX,double pY) {		
		super.draw(g);
		if(canMoveToNextRoom && alternator<=3) {
			g.setColor(Color.yellow);
			g.fillRect(85, 380, 45, 100);
			System.out.println("done");
		}
		alternator++;
		if(alternator==6) alternator=0;
		
		roomBoss.draw(g, left, right, pX, pY);
	}
	/**
	 * Get boss
	 * @return Boss object
	 */
	public Boss getRoomBoss() {return roomBoss;}
	
	
}
