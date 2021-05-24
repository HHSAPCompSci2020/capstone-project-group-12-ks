import java.awt.Color;
import java.awt.Graphics;

public class BossRoom extends EnemyRoom {
	
	

	public BossRoom(String filePath, String roomName, String imageFilePath, int w, int h) {
		super(filePath, roomName, imageFilePath, w, h,1);
		
	}

	@Override
	public void draw(Graphics g) {
		//draw image here because its the background image for room
		g.drawString(roomName, 230, 10);
		//g.drawImage(img, -18, -10, null);
		g.drawImage(img, 0, 0, 750,550, null);
		if(storyActive) {
			g.setColor(Color.black);
			g.drawRect(0, 300, 750, 200);
			g.setColor(Color.white);
			g.drawString(currentLine, 50, 200+50);
		}
		if(canMoveToNextRoom && alternator<=3) {
			g.setColor(Color.yellow);
			g.fillRect(85, 380, 45, 100);
			System.out.println("done");
		}
		alternator++;
		if(alternator==6) alternator=0;
	}
}
