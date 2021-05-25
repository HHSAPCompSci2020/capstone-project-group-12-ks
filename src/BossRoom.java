import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
/**
 * A room with the boss
 * @author srikrishna 
 *
 */
public class BossRoom extends EnemyRoom {
	
	private Boss roomBoss;
	
	
	/**
	 * Creates a boss room
	 * @param filePath file for subtitles
	 * @param roomName name to display
	 * @param imageFilePath path for background image
	 * @param w width of room
	 * @param h height of room
	 */
	public BossRoom(String filePath, String roomName, String imageFilePath, int w, int h) {
		super(filePath, roomName, imageFilePath, w, h,1);
		roomBoss= new Boss(300,300,new HealthBar(2000,2000,Color.blue));
	}

	/**
	 * draws the room
	 * @param g Graphics object
	 * @param left 
	 * @param right
	 * @param pX
	 * @param pY
	 */
	public void draw(Graphics g, Image left, Image right, double pX,double pY) {		
		super.draw(g);
		if(canMoveToNextRoom && alternator<=3) {
			g.setColor(Color.yellow);
			g.fillRect(85, 380, 45, 100);
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
